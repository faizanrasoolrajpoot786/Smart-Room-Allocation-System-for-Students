// ============================================================
//  CompatibilityGraph.java  –  DAY 2 : Graph + BFS + Matching
//  Represents students as nodes, compatibility as edges.
//  Uses Graph Matching + BFS Traversal + Sorting.
// ============================================================

import java.util.*;

public class CompatibilityGraph {

    // ── Inner class: Edge between two students ───────────────
    static class Edge {
        String studentA;
        String studentB;
        int    score;       // compatibility score 0-100

        Edge(String a, String b, int score) {
            this.studentA = a;
            this.studentB = b;
            this.score    = score;
        }
    }

    // GRAPH represented as adjacency list (HashMap of ArrayLists)
    // Key = studentID, Value = list of (neighborID, score) pairs
    HashMap<String, ArrayList<int[]>> adjList;
    // int[] = {neighborIndex, score}  – we use index into studentIDs array

    ArrayList<String>  studentIDs;   // node list (like a vertex array)
    ArrayList<Edge>    allEdges;     // all edges sorted by score

    // ── Constructor ──────────────────────────────────────────
    public CompatibilityGraph() {
        adjList    = new HashMap<>();
        studentIDs = new ArrayList<>();
        allEdges   = new ArrayList<>();
    }

    // ── Add a student node ───────────────────────────────────
    public void addNode(String studentID) {
        if (!adjList.containsKey(studentID)) {
            adjList.put(studentID, new ArrayList<>());
            studentIDs.add(studentID);
        }
    }

    // ── Add compatibility edge between two students ──────────
    public void addEdge(String a, String b, int score) {
        allEdges.add(new Edge(a, b, score));
        // Add to adjacency list (undirected graph)
        adjList.get(a).add(new int[]{studentIDs.indexOf(b), score});
        adjList.get(b).add(new int[]{studentIDs.indexOf(a), score});
    }

    // ── Calculate compatibility score between two students ───
    // SCORING LOGIC (each matching preference = +20 points)
    public int calculateScore(Student s1, Student s2) {
        int score = 0;

        // Same study schedule → +20
        if (s1.studyTime.equals(s2.studyTime))       score += 20;

        // Same cleanliness preference → +20
        if (s1.cleanliness.equals(s2.cleanliness))   score += 20;

        // Same smoking preference → +20
        if (s1.smoker == s2.smoker)                  score += 20;

        // Same lifestyle → +20
        if (s1.lifestyle.equals(s2.lifestyle))       score += 20;

        // Same department → +20
        if (s1.department.equals(s2.department))     score += 20;

        return score;  // max 100
    }

    // ── Build full graph from student registry ───────────────
    public void buildGraph(StudentRegistry registry) {
        ArrayList<Student> students = registry.getAllStudents();

        // Add all students as nodes
        for (Student s : students) {
            addNode(s.id);
        }

        // Add edges between every pair
        for (int i = 0; i < students.size(); i++) {
            for (int j = i + 1; j < students.size(); j++) {
                Student s1    = students.get(i);
                Student s2    = students.get(j);
                int     score = calculateScore(s1, s2);
                addEdge(s1.id, s2.id, score);
            }
        }

        // SORTING – sort edges by score descending (best pairs first)
        allEdges.sort((e1, e2) -> e2.score - e1.score);
    }

    // ── GRAPH MATCHING – find best roommate pairs ────────────
    // Greedy matching: pick highest-score edge, mark both matched
    public ArrayList<String[]> findBestPairs(StudentRegistry registry) {
        ArrayList<String[]> pairs   = new ArrayList<>();
        HashSet<String>     matched = new HashSet<>();

        for (Edge e : allEdges) {
            // Both students must be unmatched
            if (!matched.contains(e.studentA) && !matched.contains(e.studentB)) {
                Student s1 = registry.getStudent(e.studentA);
                Student s2 = registry.getStudent(e.studentB);
                if (s1 != null && s2 != null) {
                    pairs.add(new String[]{e.studentA, e.studentB,
                            String.valueOf(e.score)});
                    matched.add(e.studentA);
                    matched.add(e.studentB);
                }
            }
        }

        // Any unmatched students go solo
        for (String id : studentIDs) {
            if (!matched.contains(id)) {
                pairs.add(new String[]{id, null, "0"});
            }
        }

        return pairs;
    }

    // ── BFS TRAVERSAL from a starting student ───────────────
    // Returns students in BFS order (used for graph exploration)
    public ArrayList<String> bfsTraversal(String startID) {
        ArrayList<String> visited = new ArrayList<>();
        Queue<String>     queue   = new LinkedList<>();  // QUEUE – DSA requirement
        HashSet<String>   seen    = new HashSet<>();

        queue.add(startID);
        seen.add(startID);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            visited.add(current);
            int currentIndex = studentIDs.indexOf(current);

            // Visit all neighbors
            for (int[] neighbor : adjList.get(current)) {
                String neighborID = studentIDs.get(neighbor[0]);
                if (!seen.contains(neighborID)) {
                    seen.add(neighborID);
                    queue.add(neighborID);
                }
            }
        }
        return visited;
    }

    // ── Get score between two specific students ──────────────
    public int getScore(String idA, String idB) {
        for (Edge e : allEdges) {
            if ((e.studentA.equals(idA) && e.studentB.equals(idB)) ||
                    (e.studentA.equals(idB) && e.studentB.equals(idA))) {
                return e.score;
            }
        }
        return 0;
    }
}