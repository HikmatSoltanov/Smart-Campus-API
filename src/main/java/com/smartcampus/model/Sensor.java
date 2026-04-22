/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.model;

public class Sensor {
    private int id;
    private String type;
    private int roomId;
    private double currentValue;
    private String status;
    
    public Sensor() {}
    
    public Sensor(int id, String type, int roomId, double currentValue, String status) {
        this.id = id;
        this.type = type;
        this.roomId = roomId;
        this.currentValue = currentValue;
        this.status = status;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public int getRoomId() { return roomId; }
    public void setRoomId(int roomId) { this.roomId = roomId; }
    
    public double getCurrentValue() { return currentValue; }
    public void setCurrentValue(double currentValue) { this.currentValue = currentValue; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
