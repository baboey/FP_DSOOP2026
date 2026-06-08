package graph;
import model.Vertex;

public class DS {
    private Vertex ls;

    public DS() {
        ls = null;
    }

    public void addVertex(int key) {
        Vertex newv = new Vertex(key);

        if (ls == null) 
            ls = newv;
        else {
            newv.next = ls;
            ls = newv;
        }
    }

    public void printVertex() {
        for (Vertex i = ls; i != null; i = i.next) {
            System.out.println(i.key);
        }
    }
}

