### Model
|Name| Description | Status |
|--------------| --- | --- |
|	User		|		- Пользователя системы  | Ready  
|	Room		|		- Номер отеля | Not ready
|	Request		|		- Заказ на номер от пользователя | Not ready
|	Bill		|		- Счет на оплату | Not ready

### View
| Name | Description | Status |
| -----------| -------------| --- |
|	Homepage		|	- Домашняя страница | In progress  |
|	Login			|	- Форма авторизации | In progress |
|	Registration	|		- Форма регистрации | In progress  |
|	Request form		|	- Форма размещения заказа) | In progress |
|	Request view/edit/delete form |	- Форма просмотра заказа) | In progress |
|	Admin request view form  |	- Форма просмотра размещенных заказов для выбора подходящей комнаты | In progress |
|	Bill view		|	- Форма печати(оплаты?) счета | Not ready |
|	Room info view(?)	|	- Форма информации о номерах | Not ready(???) | 

###	Service
| Name | Description | Status |
| --- | --- | --- | 
|		DatabaseService	|	- Сервис для работы с БД, Connection Pool | Ready | 
|		UserService		| - Сервис для регистрации/авторизации пользователей системы | Ready(?) |
|		SecurityService	|	- Внутреннй сервис для авторизации пользователей | Ready(?) |
|		RequestService	|	- Сервис для манипуляций с заказами пользователями и админом | Not ready |
|		RoomService	|	- Сервис для отображения наиболее ревелантных комнат под конкретный заказ. Предоставление описания комнат для страницы комнант???? | Not Ready |
|		BillService	|	- Сервис для выставления и контроля счетов | Not ready |

### Controller
| Name | Description | Status | 
|---|---| --- |
|	MainController	|		- Главный контроллер. Точка входа на сайт | In progress |
|	RegistrationController	|	- Контроллер для регистрации в системе | In progress |
|	LoginController(registration?) |	- Контроллер для аутентификации в системе | In progress |
|	RequestController	|	- Контроллер для работы с заказами | In progress(reservation?) |
|	AdminController		|	- Контроллер для админской страницы | In progress(administrator?) |
|	BillController		|	- Контроллер для счетов | Not ready  |