# ISchedule design patterns
При реализации данного проекта были использованы паттерны проектирования. Некоторые из них используются применяемыми фреймворками, некоторые реализованы собственноручно. Ниже приводится объяснение некоторых из них:
### Singletone
Данный паттерн очень популярен при разработке ПО, особенно в фреймворках, использующих Inversion of Control. Механизм Dependency Injection (для данного проекта это механизм в Spring Framework и Angular Framework) манипулирует бинами и выполняет их внедрение при обращении из различных классов. В данном проекте, используя scope бинов, реализован данный паттерн (все бины являются синглтонами). При объявлении бина с помощью аннотации, если явно не указать scope бина, то он по умолчанию устанавливается как singleton. Это означает, что контейнер Spring IoC создаёт только один экземпляр объекта определённого в бине. Этот экземпляр помещается в кэш таких же бинов (синглетонов) и все последующие вызовы бина с таким именем будут возвращать объект из кэша..

![Singleton](https://github.com/IlyaMarkevichV/ISchedule/blob/master/DesignPatterns/Screens/singleton.jpg)
### Factory
Паттерн Factory применяется при инициализации бинов приложения. Для данного проекта при инициализации бинов используется класс BeanFactory. При инициализации SpringContext вызывается метод getBean, который создает бин в соответствии с xml конфигурацией (для данного проекта вместо xml используются java аннотации).

![BeanFactory](https://github.com/IlyaMarkevichV/ISchedule/blob/master/DesignPatterns/Screens/springFactory.png)

![Bean](https://github.com/IlyaMarkevichV/ISchedule/blob/master/DesignPatterns/Screens/factory.png)
### MVC
Паттерн Model View Controller является основопологающим при разработке многих проектов. Идея состоит в разделении данных, UI и управляющей логики. Для данного проекта используются аннотации @RestController, @Entity, @Service для указания к какому блоку относится данный бин.

![MVC](https://github.com/IlyaMarkevichV/ISchedule/blob/master/DesignPatterns/Screens/mvc.png)

![Model](https://github.com/IlyaMarkevichV/ISchedule/blob/master/DesignPatterns/Screens/model.png)

![Controller](https://github.com/IlyaMarkevichV/ISchedule/blob/master/DesignPatterns/Screens/controller.png)

![ViewHtml](https://github.com/IlyaMarkevichV/ISchedule/blob/master/DesignPatterns/Screens/group-tab-html.png)
![ViewTs](https://github.com/IlyaMarkevichV/ISchedule/blob/master/DesignPatterns/Screens/group-tab-ts.png)
### Observer
Данный паттерн был реализован при создании frontendа. Идея заключается в создании класса наблюдателя за другим классом. Наблюдатель подписывается на изменение состояния наблюдаемого объекта и реакции на него. Для примера можно взять любой компонент frontend.Для примера рассмотрим table-model.service-ts. Метод getFaculties() выполняет запрос, данный метод возвращает Observable<Faculty[]>, на который подписывается метод getFaculties(). После изменения состояния страница обновляет содержимое в зависимости от результата метода получения факультетов.
