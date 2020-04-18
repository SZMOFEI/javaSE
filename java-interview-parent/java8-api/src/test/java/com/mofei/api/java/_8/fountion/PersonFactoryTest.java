package com.mofei.api.java._8.fountion;

import com.mofei.api.Person;

public class PersonFactoryTest {
    public static void main(String[] args) {
         PersonFactory<Person> p = Person::new;
         p.create2("abc",23);
    }
}
