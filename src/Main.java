import graph.DS;

public class Main {
    public void main() {
        DS graph = new DS();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
//        graph.printVertex();

        graph.addEdge(2,1,100);
        graph.addEdge(2,2,100);
        graph.addEdge(2,3,100);
        graph.addEdge(2,4,100);
//        graph.findVertex(2).printEdge();
//        graph.findVertex(1).printEdge();

        graph.deleteVertex(4);
        graph.deleteVertex(1);
        graph.printVertex();

        graph.deleteEdge(2,4);
        graph.deleteEdge(2,1);
        graph.findVertex(2).printEdge();
    }
}
