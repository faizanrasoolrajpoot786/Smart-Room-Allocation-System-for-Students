// ============================================================
//  StudentRegistry.java  –  DAY 1 : HashMap Storage
//  Fast O(1) lookup of students using HashMap
// ============================================================

import java.util.HashMap;
import java.util.ArrayList;

public class StudentRegistry {

    // HASHMAP – DSA requirement (fast access by ID)
    HashMap<String, Student> map = new HashMap<>();

    // ── Add a student ────────────────────────────────────────
    public void addStudent(Student s) {
        map.put(s.id, s);
    }

    // ── Get student by ID (O(1) HashMap lookup) ──────────────
    public Student getStudent(String id) {
        return map.get(id);
    }

    // ── Remove student by ID ─────────────────────────────────
    public void removeStudent(String id) {
        map.remove(id);
    }

    // ── Check if student exists ──────────────────────────────
    public boolean exists(String id) {
        return map.containsKey(id);
    }

    // ── Get all students as ArrayList ────────────────────────
    public ArrayList<Student> getAllStudents() {
        return new ArrayList<>(map.values());
    }

    // ── Total count ──────────────────────────────────────────
    public int getCount() {
        return map.size();
    }

    // ── Print all to terminal ────────────────────────────────
    public void showAll() {
        System.out.println("\n=== All Students ===");
        for (Student s : map.values()) {
            s.display();
        }
        System.out.println("Total: " + map.size());
    }
}