package graph;
import model.Vertex;
import model.Edge;

public class DS {
    private Vertex ls;

    public DS() {
        ls = null;
    }

    //fungsi getter
    public model.Vertex getLs() {
    return this.ls;
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
        return;
    }

    public void printVertex() {
        for (Vertex i = ls; i != null; i = i.next) {
            System.out.println(i.key);
        }
        return;
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

        // Bidirectional path/edge
        if (matchSrc != null && matchDst != null) {
            matchSrc.addEdge(dst, weight);
            matchDst.addEdge(src, weight);
        }
        return;
    }

    public void deleteVertex(int key) {
        Vertex i, j;
        i = ls;
        j = null;
        while (i != null) {
            if (i.key == key) {
                deleteOtherEdge(i);
                if (j != null)
                    j.next = i.next;
                else
                    ls = i.next;
                return;
            }
            j = i;
            i = i.next;
        }
        return;
    }

    public void deleteEdge(int src, int dst) {
        Vertex matchSrc = findVertex(src);
        Vertex matchDst = findVertex(dst);

        if (matchSrc != null && matchDst != null) {
            matchSrc.deleteEdge(dst);
            matchDst.deleteEdge(src);
        }
        return;
    }

    // Delete edge on other vertex to target vertex
    public void deleteOtherEdge(Vertex target) {
        Edge i = target.ls;
        Vertex otherVertex = null;
        while (i != null) {
            deleteEdge(target.key, i.dst);
            otherVertex = findVertex(i.dst);
            if (otherVertex.ls == null)
                deleteVertex(otherVertex.key);
            i = target.ls;
        }
        return;
    }

    public void printGraph() {
        for (Vertex i = ls; i != null; i = i.next) {
            System.out.println("[" + i.key + "]");
            i.printEdge();
            System.out.println("---------");
        }
        return;
    }

    // TODO: BFS traversal
}
