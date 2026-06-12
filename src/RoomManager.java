// ============================================================
//  RoomManager.java  –  DAY 1 : Array + HashMap Storage
//  Manages rooms using Array and HashMap together
// ============================================================

import java.util.HashMap;

public class RoomManager {

    // ARRAY – DSA requirement
    Room[]   rooms;
    int      roomCount;

    // HASHMAP for fast room lookup by room number
    HashMap<String, Room> roomMap = new HashMap<>();

    // ── Constructor ──────────────────────────────────────────
    public RoomManager(int maxRooms) {
        rooms     = new Room[maxRooms];
        roomCount = 0;
    }

    // ── Add a room ───────────────────────────────────────────
    public void addRoom(Room r) {
        if (roomCount < rooms.length) {
            rooms[roomCount] = r;
            roomCount++;
            roomMap.put(r.roomNumber, r);
        }
    }

    // ── Get room by number (O(1) HashMap lookup) ─────────────
    public Room getRoom(String roomNumber) {
        return roomMap.get(roomNumber);
    }

    // ── Remove room ──────────────────────────────────────────
    public void removeRoom(String roomNumber) {
        Room r = roomMap.get(roomNumber);
        if (r == null) return;
        roomMap.remove(roomNumber);
        // Rebuild array without removed room
        Room[] newRooms = new Room[rooms.length];
        int newCount = 0;
        for (int i = 0; i < roomCount; i++) {
            if (!rooms[i].roomNumber.equals(roomNumber)) {
                newRooms[newCount++] = rooms[i];
            }
        }
        rooms     = newRooms;
        roomCount = newCount;
    }

    // ── Assign a student to a specific room ──────────────────
    public boolean assignStudent(String roomNumber, Student s) {
        Room r = roomMap.get(roomNumber);
        if (r == null)    return false;
        if (r.isFull())   return false;
        return r.addStudent(s);
    }

    // ── Find first available room ────────────────────────────
    public Room findAvailableRoom() {
        for (int i = 0; i < roomCount; i++) {
            if (!rooms[i].isFull()) return rooms[i];
        }
        return null;
    }

    // ── Print all rooms to terminal ──────────────────────────
    public void showAllRooms() {
        System.out.println("\n=== Room Allocation ===");
        for (int i = 0; i < roomCount; i++) {
            rooms[i].display();
        }
    }
}