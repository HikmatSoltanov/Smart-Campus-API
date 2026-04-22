/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.resource;

import com.smartcampus.model.Sensor;
import com.smartcampus.repository.SensorRepository;
import com.smartcampus.repository.RoomRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/sensors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorResource {
    
    private final SensorRepository sensorRepo = SensorRepository.getInstance();
    private final RoomRepository roomRepo = RoomRepository.getInstance();
    
    @GET
    public Response getSensors(@QueryParam("type") String type) {
        if (type != null && !type.isEmpty()) {
            return Response.ok(sensorRepo.getSensorsByType(type)).build();
        }
        return Response.ok(sensorRepo.getAllSensors()).build();
    }
    
    @GET
    @Path("/{sensorId}")
    public Response getSensorById(@PathParam("sensorId") int sensorId) {
        Sensor sensor = sensorRepo.getSensorById(sensorId);
        if (sensor == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"Sensor not found\"}")
                    .build();
        }
        return Response.ok(sensor).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createSensor(Sensor sensor) {
        System.out.println("Received sensor - Type: " + sensor.getType() + ", RoomId: " + sensor.getRoomId());
        
        if (!roomRepo.roomExists(sensor.getRoomId())) {
            return Response.status(422)
                    .entity("{\"error\":\"Room with ID " + sensor.getRoomId() + " does not exist\"}")
                    .build();
        }
        
        Sensor created = sensorRepo.addSensor(sensor);
        URI location = URI.create("/api/v1/sensors/" + created.getId());
        return Response.created(location).entity(created).build();
    }
    
    @Path("/{sensorId}/readings")
    public SensorReadingResource getSensorReadingResource() {
        return new SensorReadingResource();
    }
}