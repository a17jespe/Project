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
        this.name = name;
        this.size = size;
        this.location = location;
        this.company = company;
        this.cost = cost;
        this.Login = ID;
        this.ID = Login;
    }


    @Override
    public String toString(){
        return name;
    }

    public String getCompany() {return this.company;}
    public String getName() {return this.name;}
    public int getSize() {return this.size;}
    public String getLocation() {return this.location;}
    public Integer getCost() {return this.cost;}
    public String getID() {return this.ID;}
    public String getLogin() {return this.Login;}


}
