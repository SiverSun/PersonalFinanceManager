package org.example;

public class Product {
    protected String title;
    protected String date;
    protected double sum;

    public Product() {

    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public double getSum() {
        return sum;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", sum=" + sum +
                '}';
    }
}
