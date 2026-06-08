package graph;

public class Graph {
    class Vertex {
        int key;
        Vertex next;
        Edge ls;

        Vertex(int k) {
            this.key = k;
            next = null;
            ls = null;
        }

        class Edge {
            int weight;
            Edge next;
            Edge(int w) {
                this.weight = w;
                next = null;
            }
        }


    }

    Vertex ls;

    public Graph() {
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

    // addvertex(k)
    // addedge(src, dst)
}

