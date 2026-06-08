import graph.DS;

public class Main {
    public void main() {
        DS graph = new DS();
        graph.addVertex(2);
        graph.addVertex(4);
        graph.addVertex(3);
        graph.addVertex(1);
        graph.printVertices();

        graph.addEdge(2,3,100);
        graph.addEdge(2,1,100);
        graph.addEdge(2,4,100);
        graph.findVertex(2).printEdges();
        graph.findVertex(1).printEdges();
    }
}
