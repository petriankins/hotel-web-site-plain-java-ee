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
|	Homepage		|	- Домашняя страница | ? |
|	Login			|	- Форма авторизации | ? |
|	Registration	|		- Форма регистрации | ? |
|	Request form		|	- Форма размещения заказа) | ? |
|	Request view/edit/delete form |	- Форма просмотра заказа) | ? |
|	Admin request view form  |	- Форма просмотра размещенных заказов для выбора подходящей комнаты | ? |
|	Bill view		|	- Форма печати(оплаты?) счета | ? |
|	Room info view(?)	|	- Форма информации о номерах | ? | 

###	Service
| Name | Description | Status |
| --- | --- | --- | 
|		DatabaseService	|	- Сервис для работы с БД, Connection Pool | Ready| 
|		UserService		| - Сервис для регистрации/авторизации пользователей системы | Ready |
|		SecurityService	|	- Внутреннй сервис для авторизации пользователей | Ready(?) |
|		RequestService	|	- Сервис для манипуляций с заказами пользователями и админом | Not ready |
|		RoomService	|	- Сервис для отображения наиболее ревелантных комнат под конкретный заказ. Предоставление описания комнат для страницы комнант???? | Not Ready |
|		BillService	|	- Сервис для выставления и контроля счетов | Not ready |

### Controller
| Name | Description | Status | 
|---|---| --- |
|	MainController	|		- Главный контроллер. Точка входа на сайт | ? |
|	RegistrationController	|	- Контроллер для регистрации в системе | ? |
|	LoginController(registration?) |	- Контроллер для аутентификации в системе | ? |
|	RequestController	|	- Контроллер для работы с заказами | ? |
|	AdminController		|	- Контроллер для админской страницы | ? |
|	BillController		|	- Контроллер для счетов | ? |