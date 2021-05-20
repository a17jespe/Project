package com.example.project;

public class byggnadsobjekt {

    private String name;
    private int size;
    private String location;
    private String company;
    private Integer cost;

    public byggnadsobjekt(String name, int size, String location, String company, Integer cost) {
        this.name = name;
        this.size = size;
        this.location = location;
        this.company = company;
        this.cost = cost;
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

}
