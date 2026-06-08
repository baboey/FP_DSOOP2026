package model;

public class Edge {
    int dst, weight;
    Edge next;
    Edge(int d, int w) {
        this.dst = d;
        this.weight = w;
        next = null;
    }
}


