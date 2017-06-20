package test.bwie.com.liugang20170612;

/**
 * @ Description:
 * @ Date:2017/6/12
 * @ Author:刘刚
 */

public class Person {
    /**等级*/
    private int level;
    /**姓名*/
    private String name;
    /**年龄*/
    private int age;

    public Person(int type,String name,int age) {
        this.name = name;
        this.level = type;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
