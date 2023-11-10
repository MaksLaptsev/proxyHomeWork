## CustomProxy and psevdoSpring
Для выполнения задания использовалась библиотека org.Reflections(для сканирования пакета)
## Задание
- Написать аннотацию @Log, которою можно указывать на методами класса.
- Если над методом стоит аннотация, то при его вызове в консоль должен выводиться лог о его начале и завершении (с указанием названия класса и метода).
- Для реализации аннотации необходимо использовать паттерн "Прокси", поэтому для логирования класс должен иметь интерфейс.
- Использовать какие либо готовые решения( java.lang.reflect.Proxy.newProxyInstance(), CGlib и т.д.) нельзя.
## Что было сделано
-  [MyProxy](https://github.com/MaksLaptsev/proxyHomeWork/blob/8fbb1484d68f15201cc1ff6434ab87fa94152c38/app/src/main/java/proxyhomework/proxy/proxyEntity/MyProxy.java#L20) класс, выступающий в роли прокси, реализует тот же интерфейс, что и оригинальный(проксируемый) класс, так же имеет в себе ссылку на оригинальный обьект. В случае, если метод у проксируемого класса имеет аннотацию @Log - выводит в лог информацию об начале и завершении метода + параметры (с указанием названия класса и метода).
-  [AppRun](https://github.com/MaksLaptsev/proxyHomeWork/blob/b12ea59c0a640a1fc90377f1ab604ba568c38544/app/src/main/java/proxyhomework/psevdoSpring/AppRun.java#L8) данный класс запускает инициализацию и настройку JavaConfig, AppContext и ObjectFactory
-  [JavaConfig](https://github.com/MaksLaptsev/proxyHomeWork/blob/b12ea59c0a640a1fc90377f1ab604ba568c38544/app/src/main/java/proxyhomework/psevdoSpring/JavaConfig.java#L14) сканирует пакет(обычно корневой приложения) с помощью org.Reflections, по мере необходимости отдает класс, который реализует переданный интерфейс(если передать класс, то вернет его же),реализацию которого нужно отдать.
-  [ObjectFactory](https://github.com/MaksLaptsev/proxyHomeWork/blob/b12ea59c0a640a1fc90377f1ab604ba568c38544/app/src/main/java/proxyhomework/psevdoSpring/ObjectFactory.java#L12) - фабрика объектов. Занимается созданием и конфигурацией запрашиваемых объектов. Создание объекта происходит посредством вызовама метода `createObject`
 ```java
    public <T> T createObject(Class<T> clazz){
        T t = clazz.getDeclaredConstructor().newInstance();

        for (ObjectConfig config : configs) {
            t = (T) config.config(t,context);
        }

        return t;
    }
   ```
    где clazz - класс, объект которого необходимо создать.
    если класс имеет аннтоацию @MakeProxy, то объект будет обернут в MyProxy.class
    
  - [AppContext](https://github.com/MaksLaptsev/proxyHomeWork/blob/b12ea59c0a640a1fc90377f1ab604ba568c38544/app/src/main/java/proxyhomework/psevdoSpring/AppContext.java#L12) - используется для получения готовых объектов, и их кеширования, от ObjectFactory. Получение объекта через вызов метода `getObject(Class<T> tClass)`, если `tClass` является интерфейсом, то запрашивает у `JavaConfig` класс, который реализует этот интерфейс, далее отдает класс фабрике, получает готовый объект, после его кеширует.
     ```java
    public <T> T getObject(Class<T> tClass){
        if (cacheMap.containsKey(tClass)){
            return (T) cacheMap.get(tClass);
        }

        Class<? extends T> implClass = tClass;
        if(tClass.isInterface()){
            implClass = config.getImplClass(tClass);
        }

        T t = objectFactory.createObject(implClass);

        cacheMap.put(tClass,t);

        return t;
    }
     ```
- [Конфигураторы.](https://github.com/MaksLaptsev/proxyHomeWork/tree/feature/psevdoSpring/app/src/main/java/proxyhomework/psevdoSpring/configurator/impl) Для каждой аннотации создается свой конфигуратор, реализующий Config. С помощью конфигураторов происходит настройка объекта,после его создания.
- Аннотации, для маркировки классов(`MakeProxy,ProxyClass ....`), полей (`Autowired, Inject, InjectMap ...`), которые используются для правильной инициализации и конфигурации объектов.
## Пример использования
```java
  public class HospitalServiceImpl implements HospitalService<Hospital> {
      @Autowired
      private HospitalDao<Hospital> hospitalDao;
```
```java
  public class HospitalDaoImpl implements HospitalDao<Hospital> {
      @InjectMap
      private Map<Integer,Hospital> hospitalMap;
```

```java
  public class PersonServiceImpl implements PersonService<Person> {
      @Autowired
      private PersonDao<Person> personDao;
```
```java
  public class PersonDaoImpl implements PersonDao<Person> {
      @InjectMap
      private Map<Integer,Person> personMap;
```

```java
  public class HospitalVisit {
      @Autowired
      private PersonService<Person> personService;
      @Autowired
      private HospitalService<Hospital> hospitalService;
      "
      "
      "
      "
  }
 ```
 ```java
        AppContext context = AppRun.run(App.class.getPackageName());
        HospitalVisit hospitalVisit = context.getObject(HospitalVisit.class);
 ```