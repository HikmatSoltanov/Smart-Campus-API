/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.resource;

import com.smartcampus.model.Reading;
import com.smartcampus.model.Sensor;
import com.smartcampus.repository.SensorRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorReadingResource {
    
    private final SensorRepository sensorRepo = SensorRepository.getInstance();
    
    @GET
    public Response getReadings(@PathParam("sensorId") int sensorId) {
        Sensor sensor = sensorRepo.getSensorById(sensorId);
        if (sensor == null) {
            return Response.status(404)
                    .entity("{\"error\":\"Sensor not found\"}")
                    .build();
        }
        List<Reading> readings = sensorRepo.getReadings(sensorId);
        return Response.ok(readings).build();
    }
    
    @POST
    public Response addReading(@PathParam("sensorId") int sensorId, ReadingRequest request) {
        Sensor sensor = sensorRepo.getSensorById(sensorId);
        if (sensor == null) {
            return Response.status(404)
                    .entity("{\"error\":\"Sensor not found\"}")
                    .build();
        }
        
        if ("MAINTENANCE".equals(sensor.getStatus())) {
            return Response.status(403)
                    .entity("{\"error\":\"Sensor is in MAINTENANCE mode, cannot accept readings\"}")
                    .build();
        }
        
        Reading reading = sensorRepo.addReading(sensorId, request.getValue());
        if (reading == null) {
            return Response.status(500)
                    .entity("{\"error\":\"Failed to add reading\"}")
                    .build();
        }
        
        return Response.ok(reading).build();
    }
    
    public static class ReadingRequest {
        private double value;
        
        public double getValue() { return value; }
        public void setValue(double value) { this.value = value; }
    }
}