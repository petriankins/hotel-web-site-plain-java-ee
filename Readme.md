### Model
|Name| Description |
|--------------| --- |
|	User		|		- Пользователя системы  | 
|	Room		|		- Номер отеля |
|	Request		|		- Заказ на номер от пользователя |
|	Bill		|		- Счет на оплату |

### View
| Name | Description |
| -----------| -------------|
|	Homepage		|	- Домашняя страница
|	Login			|	- Форма авторизации
|	Registration	|		- Форма регистрации
|	Request form		|	- Форма размещения заказа)
|	Request view/edit/delete form |	- Форма просмотра заказа)
|	Admin request view form  |	- Форма просмотра размещенных заказов для выбора подходящей комнаты
|	Bill view		|	- Форма печати(оплаты?) счета
|	Rooom info view(?)	|	- Форма информации о номерах

###	Service
| Name | Description |
| --- | --- |
|		DatabaseService	|	- Сервис для работы с БД, Connection Pool 
|		UserService		| - Сервис для регистрации/авторизации пользователей системы
|		SecurityService	|	- Внутреннй сервис для авторизации пользователей
|		RequestService	|	- Сервис для манипуляций с заказами пользователями и админом
|		RoomService	|	- Сервис для отображения наиболее ревелантных комнат под конкретный заказ. Предоставление описания комнат для страницы комнант????
|		BillService	|	- Сервис для выставления и контроля счетов

### Controller
| Name | Description |
|---|---|
|	MainController	|		- Главный контроллер. Точка входа на сайт
|	RegistrationController	|	- Контроллер для регистрации в системе
|	LoginController(registration?) |	- Контроллер для аутентификации в системе
|	RequestController	|	- Контроллер для работы с заказами
|	AdminController		|	- Контроллер для админской страницы
|	BillController		|	- Контроллер для счетов