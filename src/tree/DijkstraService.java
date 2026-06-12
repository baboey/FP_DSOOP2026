package tree;

import model.HeapNode;
import model.Vertex;
import model.Edge;
import graph.DS; // Mengimpor class Graph milik temanmu yang bernama DS
import java.util.*;

public class DijkstraService {

    public static void cariJalurTeraman(DS graph, int asal, int tujuan) {
        // Map untuk menyimpan total risiko terendah ke setiap node (key menggunakan Integer)
        HashMap<Integer, Integer> totalRisiko = new HashMap<>();
        // Map untuk mencatat jejak rute (node sebelumnya) agar bisa dicetak di akhir
        HashMap<Integer, Integer> parentNode = new HashMap<>();
        
        // 1. Inisialisasi semua node dengan risiko tak terhingga (Infinity)
        // Kita melacak daftar vertex menggunakan pointer Linked List (i = graph.getLs() ke i.next)
        // Catatan: Pastikan di class DS milik temanmu variabel 'ls' bisa diakses (public atau ada getter)
        for (Vertex i = graph.getLs(); i != null; i = i.next) {
            totalRisiko.put(i.key, Integer.MAX_VALUE);
        }
        
        // Jika lokasi asal atau tujuan tidak terdaftar di Graph, langsung hentikan
        if (!totalRisiko.containsKey(asal) || !totalRisiko.containsKey(tujuan)) {
            System.out.println("Lokasi asal atau tujuan tidak valid di dalam sistem!");
            return;
        }
        
        // 2. Siapkan MinHeap kustommu dan masukkan node asal (risiko = 0)
        MinHeap minHeap = new MinHeap();
        totalRisiko.put(asal, 0);
        minHeap.insert(new HeapNode(asal, 0));
        
        while (!minHeap.isEmpty()) {
            // Ambil node dengan risiko terkecil dari heap
            HeapNode current = minHeap.extractMin();
            int u = current.vertexName;
            
            // Jika sudah sampai di titik aman tujuan, stop pencarian
            if (u == tujuan) break;
            
            // Cari objek Vertex asal untuk menelusuri tetangganya
            Vertex currentVertex = graph.findVertex(u);
            if (currentVertex == null) continue;
            
            // 3. Cek semua tetangga dari node saat ini lewat Linked List Edge
            for (Edge edge = currentVertex.ls; edge != null; edge = edge.next) {
                int v = edge.dst; // Berdasarkan variabel 'dst' di objek Edge temanmu
                int bobotRisikoJalur = edge.weight; // Berdasarkan variabel 'weight' di objek Edge temanmu
                
                // Hitung alternatif risiko baru
                int risikoBaru = totalRisiko.get(u) + bobotRisikoJalur;
                
                // Jika jalur baru ini lebih aman (risikonya lebih kecil)
                if (risikoBaru < totalRisiko.get(v)) {
                    totalRisiko.put(v, risikoBaru);
                    parentNode.put(v, u); // Catat jalurnya
                    minHeap.insert(new HeapNode(v, risikoBaru));
                }
            }
        }
        
        // 4. Cetak Hasil Rute dan Total Risikonya
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
