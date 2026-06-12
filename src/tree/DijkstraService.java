package tree;

import model.HeapNode;
import model.Vertex;
import model.Edge;
import graph.DS; 
import java.util.*;

public class DijkstraService {

    public static void cariJalurTeraman(DS graph, int asal, int tujuan) {
        HashMap<Integer, Integer> totalRisiko = new HashMap<>();
        HashMap<Integer, Integer> parentNode = new HashMap<>();
        
        for (Vertex i = graph.getLs(); i != null; i = i.next) {
            totalRisiko.put(i.key, Integer.MAX_VALUE);
        }
        
        if (!totalRisiko.containsKey(asal) || !totalRisiko.containsKey(tujuan)) {
            System.out.println("Lokasi asal atau tujuan tidak valid di dalam sistem!");
            return;
        }
        
        MinHeap minHeap = new MinHeap();
        totalRisiko.put(asal, 0);
        minHeap.insert(new HeapNode(asal, 0));
        
        while (!minHeap.isEmpty()) {
            HeapNode current = minHeap.extractMin();
            int u = current.vertexName;
            
            if (u == tujuan) break;
            
            Vertex currentVertex = graph.findVertex(u);
            if (currentVertex == null) continue;
            
            for (Edge edge = currentVertex.ls; edge != null; edge = edge.next) {
                int v = edge.dst; 
                int bobotRisikoJalur = edge.weight; 
                
                int risikoBaru = totalRisiko.get(u) + bobotRisikoJalur;
                
                if (risikoBaru < totalRisiko.get(v)) {
                    totalRisiko.put(v, risikoBaru);
                    parentNode.put(v, u);
                    minHeap.insert(new HeapNode(v, risikoBaru));
                }
            }
        }
        
        cetakRute(parentNode, totalRisiko, asal, tujuan);
    }

    private static void cetakRute(HashMap<Integer, Integer> parentNode, HashMap<Integer, Integer> totalRisiko, int asal, int tujuan) {
        if (totalRisiko.get(tujuan) == Integer.MAX_VALUE) {
            System.out.println("Tidak ada jalur aman yang tersedia menuju titik " + tujuan);
            return;
        }
        
        // Mengurutkan rute dari belakang menggunakan Stack
        Stack<Integer> path = new Stack<>();
        Integer curr = tujuan;
        while (curr != null) {
            path.push(curr);
            curr = parentNode.get(curr);
        }
        
        System.out.print("Rute Evakuasi Teraman (Dijkstra): ");
        while (!path.isEmpty()) {
            System.out.print(path.pop() + (path.size() > 0 ? " -> " : ""));
        }
        System.out.println("\nTotal Bobot Risiko Keamanan: " + totalRisiko.get(tujuan));
    }
}
