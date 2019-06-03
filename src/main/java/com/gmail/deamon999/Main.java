//package com.gmail.deamon999;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class Main {
//    public static void main(String[] args) {
//
//        List<Car> carList = new ArrayList<>();
//        carList.add(new Car("bmw", 56000, new Date()));
//        MyParser.saveData("D:\\test.json", carList);
//
//        carList = MyParser.readData("D:\\test.json");
//        for (Car car : carList) {
//            System.out.println(car.toString());
//        }
//        carList.add(new Car("bmw", 56000, new Date()));
//        MyParser.saveData("D:\\test.json", carList);
//        carList = MyParser.readData("D:\\test.json");
//        for (Car car : carList) {
//            System.out.println(car.toString());
//        }
//
//
//        carList = MyParser.readData("D:\\test2.xml");
//        for (Car car : carList) {
//            System.out.println(car.toString());
//        }
//        carList.add(new Car("bmw", 56000, new Date()));
//        MyParser.saveData("D:\\test2.xml", carList);
//        carList = MyParser.readData("D:\\test2.xml");
//        for (Car car : carList) {
//            System.out.println(car.toString());
//        }
//    }
//}
