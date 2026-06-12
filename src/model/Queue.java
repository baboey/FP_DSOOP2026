package model;

public class Queue {
    public Vertex vertex;
    public Queue next;

    public Queue(Vertex v) {
        this.vertex = v;
        this.next = null;
    }
}
