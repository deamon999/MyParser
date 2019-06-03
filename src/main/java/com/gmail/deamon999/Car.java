package com.gmail.deamon999;

import java.util.Date;

public class Car {
    private String branName;
    private int price;
    private Date date;

    public Car(String branName, int price, Date date) {
        this.branName = branName;
        this.price = price;
        this.date = date;
    }

    public Car() {
    }

    public String getBranName() {
        return branName;
    }

    public void setBranName(String branName) {
        this.branName = branName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Car{" +
                "branName='" + branName + '\'' +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}
