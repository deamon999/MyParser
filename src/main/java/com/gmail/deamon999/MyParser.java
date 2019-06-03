package com.gmail.deamon999;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MyParser {

    public static List<Car> readData(String filePath) {
        List<Car> carList = null;
        if (filePath != null) {
            String extension;
            if (filePath.contains(".")) {
                int position = filePath.lastIndexOf(".");
                extension = filePath.substring(position + 1);
                System.out.println(extension);
                File file = new File(filePath);
                if (file.exists() && file.isFile()) {
                    switch (extension) {
                        case "xml":
                            XmlWorker xmlWorker = new XmlWorker();
                            carList = xmlWorker.readXml(file);
                            break;
                        case "json":
                            JsonWorker jsonWorker = new JsonWorker();
                            carList = jsonWorker.readJson(file);
                            break;
                        default:
                            System.out.println("Bad request!!!");
                    }
                } else {
                    System.out.println("Bad request!!!");
                }
            } else {
                System.out.println("Bad request!!!");
            }
        } else {
            System.out.println("Bad request!!!");
        }
        return carList;
    }

    public static void saveData(String filePath, List<Car> carList) {
        if (filePath != null) {
            String extension;
            if (filePath.contains(".")) {
                int position = filePath.lastIndexOf(".");
                extension = filePath.substring(position + 1);
                System.out.println(extension);
                File file = new File(filePath);
                if(!file.exists()){
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (file.exists() && file.isFile()) {
                    switch (extension) {
                        case "xml":
                            XmlWorker xmlWorker = new XmlWorker();
                            xmlWorker.saveXml(file, carList);
                            break;
                        case "json":
                            JsonWorker jsonWorker = new JsonWorker();
                            jsonWorker.saveJson(file, carList);
                            break;
                        default:
                            System.out.println("Bad request!!!");
                    }
                } else {
                    System.out.println("Bad request!!!");
                }
            } else {
                System.out.println("Bad request!!!");
            }
        } else {
            System.out.println("Bad request!!!");
        }
    }
}
