package graph;
import model.Vertex;

public class DS {
    private Vertex ls;

    public DS() {
        ls = null;
    }

    public void addVertex(int key) {
        if (findVertex(key) != null)
            return;
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

    public Vertex findVertex(int key) {
        for (Vertex i = ls; i != null; i = i.next) {
            if (i.key == key)
                return i;
        }
        return null;
    }

    public void addEdge(int src, int dst, int weight) {
        Vertex matchSrc = findVertex(src);
        Vertex matchDst = findVertex(dst);
        if (matchSrc != null && matchDst != null)
            matchSrc.addEdge(dst, weight);
    }

    public void deleteVertex(int key) {
        Vertex i, j;
        i = ls;
        j = null;
        while (i != null) {
            if (i.key == key) {
                i.deleteAllEdge();
                if (j != null)
                    j.next = i.next;
                else
                    ls = i.next;
                return;
            }
            j = i;
            i = i.next;
        }
    }

    public void deleteEdge(int src, int dst) {
        Vertex match = findVertex(src);
        if (match != null)
            match.deleteEdge(dst);
    }
}
