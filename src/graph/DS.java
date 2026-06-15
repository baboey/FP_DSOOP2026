package graph;
import model.Vertex;
import model.Edge;
import java.util.*;

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

    public void bfsTraversal(int startKey) {
        Vertex startVertex = findVertex(startKey);
        if (startVertex == null) {
            System.out.println("Vertex " + startKey + " tidak ditemukan dalam graph!");
            return;
        }

        Set<Integer> visited = new HashSet<>();
        Queue<Vertex> queue = new LinkedList<>();

        visited.add(startVertex.key);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            System.out.print(current.key + " ");

            Edge e = current.ls; 
            while (e != null) {
                if (!visited.contains(e.dst)) {
                    Vertex neighbor = findVertex(e.dst);
                    if (neighbor != null) {
                        visited.add(e.dst);
                        queue.add(neighbor);
                    }
                }
                e = e.next; 
            }
        }
        System.out.println();
    }

    // 1. Fungsi untuk Mengubah Bobot Koridor 
    public void updateEdgeWeight(int src, int dst, int newWeight) {
        Vertex matchSrc = findVertex(src);
        Vertex matchDst = findVertex(dst);

        if (matchSrc != null && matchDst != null) {
            // Ubah bobot di sisi Source menuju Destination
            for (model.Edge e = matchSrc.ls; e != null; e = e.next) {
                if (e.dst == dst) {
                    e.weight = newWeight;
                    break;
                }
            }
            // Ubah bobot di sisi Destination menuju Source (Bidirectional)
            for (model.Edge e = matchDst.ls; e != null; e = e.next) {
                if (e.dst == src) {
                    e.weight = newWeight;
                    break;
                }
            }
            System.out.println("Bobot jalur antara " + src + " dan " + dst + " berhasil diubah menjadi: " + newWeight);
        } else {
            System.out.println("Gagal mengubah bobot. Ruangan tidak ditemukan!");
        }
    }

    // 2. Fungsi untuk Mencari Ruangan dengan Prefix
    public void searchVertexWithPrefix(String prefix) {
        System.out.println("Hasil pencarian ruangan dengan awalan '" + prefix + "':");
        boolean ditemukan = false;
        for (Vertex i = ls; i != null; i = i.next) {
            String currentKeyStr = Integer.toString(i.key);
            if (currentKeyStr.startsWith(prefix)) {
                System.out.println("- Ruangan [ID: " + i.key + "]");
                ditemukan = true;
            }
        }
        if (!ditemukan) {
            System.out.println("Tidak ada ruangan dengan awalan tersebut.");
        }
    }
}
