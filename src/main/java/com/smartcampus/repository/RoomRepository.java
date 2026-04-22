/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.repository;

import com.smartcampus.model.Room;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class RoomRepository {
    private static RoomRepository instance;
    private final ConcurrentHashMap<Integer, Room> rooms = new ConcurrentHashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);
    
    private RoomRepository() {
        rooms.put(1, new Room(1, "Lab 101", "Engineering", 1));
        rooms.put(2, new Room(2, "Lecture Hall A", "Main", 0));
        idGenerator.set(3);
    }
    
    public static RoomRepository getInstance() {
        if (instance == null) {
            instance = new RoomRepository();
        }
        return instance;
    }
    
    public List<Room> getAllRooms() {
        return new ArrayList<>(rooms.values());
    }
    
    public Room getRoomById(int id) {
        return rooms.get(id);
    }
    
    public Room addRoom(Room room) {
        int newId = idGenerator.getAndIncrement();
        room.setId(newId);
        rooms.put(newId, room);
        System.out.println("Room added - ID: " + newId + ", Name: " + room.getName());
        return room;
    }
    
    public boolean deleteRoom(int id) {
        if (rooms.containsKey(id)) {
            rooms.remove(id);
            return true;
        }
        return false;
    }
    
    public boolean roomExists(int id) {
        return rooms.containsKey(id);
    }
}