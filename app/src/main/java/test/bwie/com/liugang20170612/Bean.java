package test.bwie.com.liugang20170612;

import java.util.Comparator;

/**
 * @ Description:
 * @ Date:2017/6/12
 * @ Author:刘刚
 */

public class Bean implements Comparator<Bean>{
    private float price;
    private String date;

    @Override
    public String toString() {
        return "Bean{" +
                "date='" + date + '\'' +
                ", price=" + price +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Bean() {
    }

    @Override
    public int compare(Bean o1, Bean o2) {

        return 0;
    }
}
