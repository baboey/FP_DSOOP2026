public class App {
	public void main() {

	}
}

class Graph {
	class Vertex {
		int key;
		Vertex next;
		Edge[] ls;

		Vertex(int k) {
			this.key = k;
			next = null;
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

	Vertex[] ls;

	Graph() {}

	// addvertex(k)
	// addedge(src, dst)
}
