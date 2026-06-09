// ============================================================
//  Student.java  –  DAY 1 : Core Data Model
//  Stores all student information and preferences
// ============================================================

public class Student {

    // ── Fields ───────────────────────────────────────────────
    String id;
    String name;
    String department;
    String studyTime;    // "Morning" or "Night"
    String cleanliness;  // "Neat" or "Messy"
    boolean smoker;
    String lifestyle;    // "Introvert" or "Extrovert"

    // ── Constructor ──────────────────────────────────────────
    public Student(String id, String name, String department,
                   String studyTime, String cleanliness,
                   boolean smoker, String lifestyle) {
        this.id          = id;
        this.name        = name;
        this.department  = department;
        this.studyTime   = studyTime;
        this.cleanliness = cleanliness;
        this.smoker      = smoker;
        this.lifestyle   = lifestyle;
    }

    // ── Display in terminal ──────────────────────────────────
    public void display() {
        System.out.println("ID: " + id + " | Name: " + name
                + " | Dept: "      + department
                + " | Study: "     + studyTime
                + " | Clean: "     + cleanliness
                + " | Smoker: "    + smoker
                + " | Lifestyle: " + lifestyle);
    }

    // toString used in table/list displays
    @Override
    public String toString() {
        return name + " (" + id + ")";
    }
}