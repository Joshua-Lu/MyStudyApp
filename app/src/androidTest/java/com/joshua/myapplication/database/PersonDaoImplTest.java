package com.joshua.myapplication.database;

import android.content.Context;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

/**
 * Created by Joshua on 2020/11/6 1:04
 */
@RunWith(AndroidJUnit4.class)
public class PersonDaoImplTest {

    private static final String TAG = "PersonDaoImplTest";
    public PersonDaoImpl personDao;

    @Before
    public void setUp() throws Exception {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        personDao = new PersonDaoImpl(appContext);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void add() {
        long addResult = personDao.add(new Person(1, "路飞", 18));
        Log.d(TAG, "addResult: addResult = [" + addResult + "]");
    }

    @Test
    public void deleteById() {
        int deleteResult = personDao.deleteById(1);
        Log.d(TAG, "deleteById: deleteResult = [" + deleteResult + "]");
    }

    @Test
    public void update() {
        int updateResult = personDao.update(new Person(1, "路飞", 20));
        Log.d(TAG, "update: updateResult = [" + updateResult + "]");
    }

    @Test
    public void queryByID() {
        Person person = personDao.queryByID(1);
        Log.d(TAG, "queryByID: person = [" + person + "]");
    }

    @Test
    public void listAll() {
        ArrayList<Person> people = personDao.listAll();
        for (Person person : people) {
            System.out.println("PersonDaoImplTest.listAll: person = [" + person + "]");
        }
    }
}