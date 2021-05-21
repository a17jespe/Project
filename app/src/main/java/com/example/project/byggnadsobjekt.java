package com.example.project;

public class byggnadsobjekt {

    private String name;
    private int size;
    private String location;
    private String company;
    private Integer cost;
    private String id;
    private String login;

    public byggnadsobjekt(String Name, Integer Size, String Location, String Company, Integer Cost, String ID, String Login) {
        name = Name;
        size = Size;
        location = Location;
        company = Company;
        cost = Cost;
        login = Login;
        id = ID;
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
    public String getID() {return id;}
    public String getLogin() {return login;}


}
