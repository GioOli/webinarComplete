package com.example.kotlintutorial;

public class Userjava {

    private String name = "";
    private int age = -1;
    private String name_age = "";

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

    public String getName_age() {
        return name_age;
    }

    public void setName_age(String name_age) {
        this.name_age = name_age;
    }

    Userjava(){}

    Userjava(String name, int age, String name_age){
        this.name = name;
        this.age = age;
        this.name_age = name_age;
    }
}
