package com.gmail.deamon999;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class AppTest {
    private Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();

    @Before
    public void createFile() {
        try (FileWriter writer = new FileWriter("test.json", false)) {
            String data = "[{\"branName\":\"bmw\",\"price\":56000,\"date\":\"03.06.2019\"}]";
            writer.write(data);
        } catch (IOException e) {
            System.out.println("File can not be created!");
        }
    }

    @Test
    public void shouldAnswerWithTrue() {
        Type REVIEW_TYPE = new TypeToken<List<Car>>() {
        }.getType();
        try (JsonReader reader = new JsonReader(new FileReader("test.json"))) {
            List<Car> carList = gson.fromJson(reader, REVIEW_TYPE);
            Assert.assertEquals(1, carList.size());
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void deleteFile() {
        File file = new File("test.json");
        if (file.exists()) {
            file.delete();
        }
    }
}
