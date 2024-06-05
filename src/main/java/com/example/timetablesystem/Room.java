package com.example.timetablesystem;
import java.util.Collection;
import java.util.List;

public class Room {
        String RoomId;
        int Capacity;
        List<String> AvailableTimes;

        Room(String RoomId, int Capacity, List<String> AvailableTimes) {
            this.RoomId = RoomId;
            this.Capacity = Capacity;
            this.AvailableTimes = AvailableTimes;
        }
}
