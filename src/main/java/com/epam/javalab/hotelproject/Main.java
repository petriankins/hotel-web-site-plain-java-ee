package com.epam.javalab.hotelproject;

import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.epam.javalab.hotelproject.service.DatabaseServiceImpl;
import org.apache.catalina.startup.Tomcat;
import org.apache.log4j.Logger;

public class Main {
    private final static Logger LOGGER = Logger.getLogger(Main.class);

    public static final Optional<String> PORT     = Optional.ofNullable(System.getenv("PORT"));
    public static final Optional<String> HOSTNAME = Optional.ofNullable(System.getenv("HOSTNAME"));

    public static void main(String[] args) throws Exception {
        String contextPath = "/";
        String appBase = ".";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(Integer.valueOf(PORT.orElse("8080")));
        tomcat.setHostname(HOSTNAME.orElse("localhost"));
        tomcat.getHost().setAppBase(appBase);
        tomcat.addWebapp(contextPath, appBase);
        tomcat.start();
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(() -> tomcat.getServer().await());
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.nextLine().equals("stop")) {
                LOGGER.info("Received STOP command.");
                DatabaseServiceImpl.getInstance().dispose();
                LOGGER.info("DatabaseService disposed");
                break;
            }
        }
        LOGGER.info("Stopping Tomcat");
        tomcat.stop();
        LOGGER.info("Goodbye!");
        System.exit(0);
    }
}