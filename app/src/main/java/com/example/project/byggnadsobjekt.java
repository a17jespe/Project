package com.example.project;

public class byggnadsobjekt {

    private String name;
    private int size;
    private String location;
    private String company;
    private Integer cost;
    private String ID;
    private String Login;

    public byggnadsobjekt(String name, Integer size, String location, String company, Integer cost, String ID, String Login) {
        name = name;
        size = size;
        location = location;
        company = company;
        cost = cost;
        String login = Login;
        String id = ID;
    }


    @Override
    public String toString(){
        return name;
    }

    public String getCompany() {return company;}
    public String getName() {return name;}
    public String getSize() {return String.valueOf(size);}
    public String getLocation() {return location;}
    public String getCost() {return String.valueOf(cost);}
    public String getID() {return ID;}
    public String getLogin() {return Login;}


}
