/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.resource;

import com.smartcampus.model.Room;
import com.smartcampus.repository.RoomRepository;
import com.smartcampus.repository.SensorRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomResource {
    
    private final RoomRepository roomRepo = RoomRepository.getInstance();
    private final SensorRepository sensorRepo = SensorRepository.getInstance();
    
    @GET
    public Response getAllRooms() {
        return Response.ok(roomRepo.getAllRooms()).build();
    }
    
    @GET
    @Path("/{roomId}")
    public Response getRoomById(@PathParam("roomId") int roomId) {
        Room room = roomRepo.getRoomById(roomId);
        if (room == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"Room not found\"}")
                    .build();
        }
        return Response.ok(room).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createRoom(Room room) {
        System.out.println("Received room: " + room.getName());
        
        if (room.getName() == null || room.getName().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\":\"Room name is required\"}")
                    .build();
        }
        
        Room created = roomRepo.addRoom(room);
        URI location = URI.create("/api/v1/rooms/" + created.getId());
        return Response.created(location).entity(created).build();
    }
    
    @DELETE
    @Path("/{roomId}")
    public Response deleteRoom(@PathParam("roomId") int roomId) {
        if (sensorRepo.hasSensorsInRoom(roomId)) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("{\"error\":\"Cannot delete room with active sensors\"}")
                    .build();
        }
        boolean deleted = roomRepo.deleteRoom(roomId);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"Room not found\"}")
                    .build();
        }
        return Response.noContent().build();
    }
}