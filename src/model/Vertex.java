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
}
