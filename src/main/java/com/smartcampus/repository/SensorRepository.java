/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.repository;

import com.smartcampus.model.Sensor;
import com.smartcampus.model.Reading;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class SensorRepository {
    private static SensorRepository instance;
    private final ConcurrentHashMap<Integer, Sensor> sensors = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Integer, List<Reading>> readings = new ConcurrentHashMap<>();
    private final AtomicInteger sensorIdGenerator = new AtomicInteger(1);
    private final AtomicInteger readingIdGenerator = new AtomicInteger(1);
    
    private SensorRepository() {
        sensors.put(1, new Sensor(1, "CO2", 1, 420.5, "ACTIVE"));
        sensors.put(2, new Sensor(2, "OCCUPANCY", 1, 3.0, "ACTIVE"));
        sensors.put(3, new Sensor(3, "CO2", 2, 380.0, "ACTIVE"));
        sensorIdGenerator.set(4);
        
        readings.put(1, new ArrayList<>());
        readings.put(2, new ArrayList<>());
        readings.put(3, new ArrayList<>());
    }
    
    public static SensorRepository getInstance() {
        if (instance == null) {
            instance = new SensorRepository();
        }
        return instance;
    }
    
    public List<Sensor> getAllSensors() {
        return new ArrayList<>(sensors.values());
    }
    
    public List<Sensor> getSensorsByType(String type) {
        return sensors.values().stream()
                .filter(s -> s.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }
    
    public Sensor getSensorById(int id) {
        return sensors.get(id);
    }
    
    public Sensor addSensor(Sensor sensor) {
        int newId = sensorIdGenerator.getAndIncrement();
        sensor.setId(newId);
        sensors.put(newId, sensor);
        readings.put(newId, new ArrayList<>());
        System.out.println("Sensor added - ID: " + newId + ", Type: " + sensor.getType());
        return sensor;
    }
    
    public boolean hasSensorsInRoom(int roomId) {
        return sensors.values().stream().anyMatch(s -> s.getRoomId() == roomId);
    }
    
    public List<Reading> getReadings(int sensorId) {
        return readings.getOrDefault(sensorId, new ArrayList<>());
    }
    
    public Reading addReading(int sensorId, double value) {
        Sensor sensor = sensors.get(sensorId);
        if (sensor == null) return null;
        
        if ("MAINTENANCE".equals(sensor.getStatus())) return null;
        
        int newId = readingIdGenerator.getAndIncrement();
        Reading reading = new Reading(newId, sensorId, LocalDateTime.now(), value);
        
        readings.computeIfAbsent(sensorId, k -> new ArrayList<>()).add(reading);
        sensor.setCurrentValue(value);
        
        System.out.println("Reading added - Sensor ID: " + sensorId + ", Value: " + value);
        return reading;
    }
    
    public boolean updateSensorStatus(int sensorId, String status) {
        Sensor sensor = sensors.get(sensorId);
        if (sensor == null) return false;
        sensor.setStatus(status);
        return true;
    }
}