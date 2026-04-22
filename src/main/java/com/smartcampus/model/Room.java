/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.model;

public class Room {
    private int id;
    private String name;
    private String building;
    private int floor;
    
    public Room() {}
    
    public Room(int id, String name, String building, int floor) {
        this.id = id;
        this.name = name;
        this.building = building;
        this.floor = floor;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getBuilding() { return building; }
    public void setBuilding(String building) { this.building = building; }
    
    public int getFloor() { return floor; }
    public void setFloor(int floor) { this.floor = floor; }
}
