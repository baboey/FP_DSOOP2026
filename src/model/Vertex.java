package model;
import model.Edge;

public class Vertex {
    public int key;
    public Vertex next;
    public Edge ls;

    public Vertex(int k) {
        this.key = k;
        next = null;
        ls = null;
    }

    public void addEdge(int dst, int weight) {
        Edge match = findEdge(dst);
        // Update when there is a matching edge with diff weight val
        if (match != null && match.weight != weight) {
            match.weight = weight;
        // Insert when there arent any matching edges
        } else if (match == null) {
            Edge newe = new Edge(dst, weight);
            if (ls == null)
                ls = newe;
            else {
                newe.next = ls;
                ls = newe;
            }
        }
        return;
    }

    public void printEdge() {
        for (Edge i = ls; i != null; i = i.next) {
            System.out.println(key + " - " + i.dst + " (" + i.weight + ")");
        }
        return;
    }

    public Edge findEdge(int dst) {
        for (Edge i = ls; i != null; i = i.next) {
            if (i.dst == dst)
                return i;
        }
        return null;
    }

    public void deleteEdge(int dst) {
        Edge i, j;
        i = ls;
        j = null;
        while (i != null) {
            if (i.dst == dst) {
                if (j != null)
                    j.next = i.next;
                else
                    ls = i.next;
                return;
            }
            j = i;
            i = i.next;
        }
        return;
    }
}
