/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.model;

import java.time.LocalDateTime;

public class Reading {
    private int id;
    private int sensorId;
    private LocalDateTime timestamp;
    private double value;
    
    public Reading() {}
    
    public Reading(int id, int sensorId, LocalDateTime timestamp, double value) {
        this.id = id;
        this.sensorId = sensorId;
        this.timestamp = timestamp;
        this.value = value;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public int getSensorId() { return sensorId; }
    public void setSensorId(int sensorId) { this.sensorId = sensorId; }
    
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    
    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }
}

