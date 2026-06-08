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
        if (hasEdge(dst) == true)
            return;
        Edge newe = new Edge(dst, weight);
        if (ls == null)
            ls = newe;
        else {
            newe.next = ls;
            ls = newe;
        }
    }

    public void printEdge() {
        for (Edge i = ls; i != null; i = i.next) {
            System.out.println(i.dst + " - " + i.weight);
        }
    }

    public boolean hasEdge(int dst) {
        for (Edge i = ls; i != null; i = i.next) {
            if (i.dst == dst)
                return true;
        }
        return false;
    }
}
