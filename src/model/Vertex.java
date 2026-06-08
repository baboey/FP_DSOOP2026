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
        if (findEdge(dst) != null)
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
                i = null;
                return;
            }
            j = i;
            i = i.next;
        }
    }
}
