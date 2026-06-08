package model;

public class Edge {
    public int dst, weight;
    public Edge next;
    public Edge(int d, int w) {
        this.dst = d;
        this.weight = w;
        next = null;
    }
}


