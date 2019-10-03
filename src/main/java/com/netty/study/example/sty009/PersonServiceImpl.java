package com.netty.study.example.sty009;

public class PersonServiceImpl implements  PersonService.Iface {

    @Override
    public Person getPersonByUserName(String username) throws DataExeption, org.apache.thrift.TException {

        System.out.println("get client param:" + username);

        Person person = new Person();
        person.setUsername(username);
        person.setAge(20);
        person.setMarried(false);
        return person;


    }

    @Override
    public void savePerson(Person person) throws DataExeption, org.apache.thrift.TException {

        System.out.println("get client param:" + person);
        System.out.println(person.getAge());
        System.out.println(person.getUsername());
        System.out.println(person.isMarried());

    }
}
