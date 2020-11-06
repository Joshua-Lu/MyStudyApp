package com.joshua.myapplication.database;

import java.util.ArrayList;

/**
 * Created by Joshua on 2020/11/6 0:47
 */
interface IPersonDao {
    long add(Person person);

    int deleteById(int id);

    int update(Person person);

    Person queryByID(int id);

    ArrayList<Person> listAll();
}
