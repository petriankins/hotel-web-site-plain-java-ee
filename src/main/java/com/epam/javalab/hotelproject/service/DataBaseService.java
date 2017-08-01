package com.epam.javalab.hotelproject.service;

import java.sql.*;
import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DataBaseService {
    private BlockingQueue<PooledConnection> connectionQueue;
    private BlockingQueue<PooledConnection> givenAwayConnection;

    private String driverName = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11188080";
    private String user = "sql11188080";
    private String password = "WFJLwRnBBE";
    private int poolSize = 5;
    private static final DataBaseService instance = new DataBaseService();

    private DataBaseService() {
        Locale.setDefault(Locale.ENGLISH);
        try {
            Class.forName(driverName);
            connectionQueue = new ArrayBlockingQueue<PooledConnection>(poolSize);
            givenAwayConnection = new ArrayBlockingQueue<PooledConnection>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                PooledConnection pooledConnection = new PooledConnection(DriverManager.getConnection(url, user, password));
                connectionQueue.add(pooledConnection);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e1) {
            System.out.println(e1.getMessage());
        }
    }

    public static DataBaseService getInstance() {
        return instance;
    }

    private void closeConnectionsQueue(BlockingQueue<PooledConnection> queue) throws SQLException {
        PooledConnection pooledConnection;
        while ((pooledConnection = queue.poll()) != null) {
            if (!pooledConnection.getAutoCommit()) {
                pooledConnection.commit();
            }
            pooledConnection.reallyClose();
        }
    }

    private void clearConnectionQueue() {
        try {
            closeConnectionsQueue(connectionQueue);
            closeConnectionsQueue(givenAwayConnection);
        } catch (SQLException e) {
            //TODO
        }
    }

    public void dispose() {
        clearConnectionQueue();
    }

    public PooledConnection takeConnection() {
        PooledConnection pooledConnection = null;
        try {
            pooledConnection = connectionQueue.take();
            givenAwayConnection.add(pooledConnection);
        } catch (InterruptedException e) {
            //TODO
        }
        return pooledConnection;

    }

    public void closeConnection(PooledConnection con, Statement st, ResultSet rs) {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(PooledConnection con, Statement st) {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    protected class PooledConnection {
        private Connection connection;

        public PooledConnection(Connection connection) throws SQLException {
            this.connection = connection;
            this.connection.setAutoCommit(true);
        }

        public void reallyClose() throws SQLException {
            connection.close();
        }

        public boolean getAutoCommit() throws SQLException {
            return connection.getAutoCommit();
        }

        public void commit() throws SQLException {
            connection.commit();
        }

        public void close() throws SQLException {
            if (connection.isClosed()) {
                throw new SQLException();
            }
            if (connection.isReadOnly()) {
                connection.setReadOnly(false);
            }
            if (!givenAwayConnection.remove(this)) {
                throw new SQLException();
            }
            if (!connectionQueue.offer(this)) {
                throw new SQLException();
            }
        }

        public Statement createStatement() throws SQLException {
            return connection.createStatement();
        }
    }

}
