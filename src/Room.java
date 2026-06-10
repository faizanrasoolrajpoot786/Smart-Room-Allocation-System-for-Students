// ============================================================
//  Room.java  –  DAY 1 : Core Data Model
//  Stores room info using Array for occupants
// ============================================================

public class Room {

    // ── Fields ───────────────────────────────────────────────
    String    roomNumber;
    int       capacity;
    Student[] occupants;   // ARRAY – DSA requirement
    int       count;       // current number of occupants

    // ── Constructor ──────────────────────────────────────────
    public Room(String roomNumber, int capacity) {
        this.roomNumber = roomNumber;
        this.capacity   = capacity;
        this.occupants  = new Student[capacity];
        this.count      = 0;
    }

    // ── Helper methods ───────────────────────────────────────
    public boolean isFull() {
        return count >= capacity;
    }

    public boolean addStudent(Student s) {
        if (isFull()) return false;
        occupants[count] = s;
        count++;
        return true;
    }

    public int getAvailableSlots() {
        return capacity - count;
    }

    // Print room details to terminal
    public void display() {
        System.out.println("Room " + roomNumber
                + " (" + count + "/" + capacity + "):");
        for (int i = 0; i < count; i++) {
            System.out.println("   -> " + occupants[i].name
                    + " (" + occupants[i].id + ")");
        }
        if (count == 0) System.out.println("   (empty)");
    }
}