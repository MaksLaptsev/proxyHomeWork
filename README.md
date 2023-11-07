## CustomProxyClass
Для выполнения задания использовалась библиотека org.Reflections(для сканирования пакета)
## Задание
- Необходимо сделать прокси класс, который добавляет какую то логику перед выполнением метода у оригинала.
- Создать аннотации, исходя из которых понимать какой класс проксировать/какой метод дополнять
- Использовать какие либо готовые решения( java.lang.reflect.Proxy.newProxyInstance(), CGlib и т.д.) нельзя.
- Сделать фабрику, которая будет выдавать необходимый прокси класс

Пример создания прокси объекта:
```java
        ProxyFactory proxyFactory = new ProxyFactory(App.class.getPackage());
        Service<Person> personService =  proxyFactory.getProxy(PersonServiceImpl.class);
        Service<Hospital> hospitalService = proxyFactory.getProxy(HospitalServiceImpl.class);
   ```
