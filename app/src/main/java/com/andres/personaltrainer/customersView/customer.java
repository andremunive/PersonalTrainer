package com.andres.personaltrainer.customersView;

public class customer {

    private String name, user, age;

    public customer(String name, String user, String age) {
        this.name = name;
        this.user = user;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "customer{" +
                "name='" + name + '\'' +
                ", user='" + user + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
