package com.gmail.deamon999;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

class JsonWorker {

    private final Type REVIEW_TYPE = new TypeToken<List<Car>>() {
    }.getType();
    private Gson gson = new GsonBuilder().setDateFormat("dd.MM.yyyy").create();

    void saveJson(File file, List<Car> carList) {
        if (file != null && carList != null) {
            try (FileWriter writer = new FileWriter(file, false)) {
                String data = gson.toJson(carList);
                writer.write(data);
            } catch (IOException e) {
                System.out.println("File can not be created!");
            }
        } else {
            System.out.println("Bad request!!!");
        }
    }

    List<Car> readJson(File file) {
        List<Car> carList = new ArrayList<>();
        if (file != null) {
            try (JsonReader reader = new JsonReader(new FileReader(file))){
                List<Car> data = gson.fromJson(reader, REVIEW_TYPE);
                carList.addAll(data);
            } catch (FileNotFoundException e) {
                System.out.println("File not found!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Bad request!!!");
        }
        return carList;
    }
}
