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

    public void printVertices() {
        for (Vertex i = ls; i != null; i = i.next) {
            System.out.println(i.key);
        }
    }

    public Vertex findVertex(int key) {
        for (Vertex i = ls; i != null; i = i.next) {
            if (i.key == key)
                return i;
        }
        return null;
    }

    public void addEdge(int src, int dst, int weight) {
        Vertex match = findVertex(src);
        if (match != null)
            match.addEdge(dst, weight);
    }
}
