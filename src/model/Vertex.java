package model;
import model.Edge;

public class Vertex {
    int key;
    Vertex next;
    Edge ls;

    Vertex(int k) {
        this.key = k;
        next = null;
        ls = null;
    }
}
