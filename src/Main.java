import graph.DS;
import tree.DijkstraService; 
import java.io.*;
import java.util.Scanner;

public class Main {
    // Ubah jadi static agar bisa diakses langsung di method main static
    static DS graph = new DS(); 

    public static void main(String[] args) {
        Main app = new Main();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Menggenerate dataset otomatis...");
        app.generateDataset(25, 45, 10); 
        
        // DIUBAH: Menggunakan Mode 6 (Gabungan Risiko + Kepadatan + Asap)
        app.readDataset(6); 
        System.out.println("Graph berhasil dibangun menggunakan Integrasi Mode Keselamatan Cerdas!");

        while (true) {
            System.out.println("=================================");
            System.out.println("   SISTEM EVAKUASI DARURAT (POS 2) ");
            System.out.println("=================================");
            System.out.println("1. Tampilkan Struktur Peta Gedung (Graph)");
            System.out.println("2. Cari Rute Evakuasi Teraman (Dijkstra - Mode Cerdas)");
            System.out.println("3. Simulasi Jalur Blokir / Runtuh (Hapus Edge)");
            System.out.println("4. Telusuri Seluruh Area (BFS Traversal)"); 
            System.out.println("5. Hapus Ruangan (Delete Vertex)"); 
            System.out.println("6. Ubah Bobot Koridor (Update Edge)"); 
            System.out.println("7. Cari Ruangan dengan Prefix"); 
            System.out.println("8. Keluar");
            System.out.print("Pilih menu (1-8): ");

            int pilihan = scanner.nextInt();

            if (pilihan == 1) {
                System.out.println("\n--- PETA STRUKTUR GEDUNG (ADJACENCY LIST) ---");
                graph.printGraph();
            } 
            else if (pilihan == 2) {
                System.out.print("\nMasukkan lokasi posisi Anda saat ini (Angka): ");
                int posisiKini = scanner.nextInt();
                System.out.print("Masukkan nomor titik aman / Safe Zone (Angka): ");
                int titikAman = scanner.nextInt();

                System.out.println("\nMenghitung rute evakuasi memproses Min-Heap...");
                DijkstraService.cariJalurTeraman(graph, posisiKini, titikAman);
            } 
            else if (pilihan == 3) {
                System.out.println("\n--- SIMULASI KEADAAN DARURAT (JALUR TERTUTUP) ---");
                System.out.print("Masukkan titik asal jalan yang runtuh: ");
                int src = scanner.nextInt();
                System.out.print("Masukkan titik tujuan jalan yang runtuh: ");
                int dst = scanner.nextInt();

                graph.deleteEdge(src, dst);
                System.out.println("JALUR ANTARA " + src + " DAN " + dst + " TELAH DITUTUP!");
            } 
            else if (pilihan == 4) { 
                System.out.print("\nMasukkan titik awal penelusuran BFS (Angka): ");
                int startNode = scanner.nextInt();
                System.out.println("\nHasil Penelusuran BFS:");
                graph.bfsTraversal(startNode);
            }
            else if (pilihan == 5) { //DELETE VERTEX
                System.out.println("\n--- HAPUS RUANGAN TOTAL ---");
                System.out.print("Masukkan nomor ruangan yang hancur/runtuh total: ");
                int targetVertex = scanner.nextInt();
                
                graph.deleteVertex(targetVertex);
                System.out.println("Ruangan " + targetVertex + " dan seluruh akses jalurnya telah dihapus dari peta!");
            }
            else if (pilihan == 6) { //UPDATE EDGE
                System.out.println("\n--- UBAH BOBOT KORIDOR ---");
                System.out.print("Masukkan nomor ruangan asal: ");
                int src = scanner.nextInt();
                System.out.print("Masukkan nomor ruangan tujuan: ");
                int dst = scanner.nextInt();
                System.out.print("Masukkan nilai bobot risiko baru (Angka): ");
                int newWeight = scanner.nextInt();

                graph.updateEdgeWeight(src, dst, newWeight);
            }
            else if (pilihan == 7) { //PREFIX
                System.out.println("\n--- CARI RUANGAN BERDASARKAN PREFIX ---");
                System.out.print("Masukkan angka prefix/awalan lantai yang dicari: ");
                scanner.nextLine(); // Bersihkan buffer scanner
                String prefix = scanner.nextLine();

                graph.searchVertexWithPrefix(prefix);
            }
            else if (pilihan == 8) {
                System.out.println("Program selesai. Stay safe dan semoga nilai FP kita A!");
                break;
            } 
            else {
                System.out.println("Pilihan tidak valid!");
            }
        }
        scanner.close();
    }

    void readDataset(int mode) {
        FileReader      fr;
        BufferedReader  br;
        String path     = "data/dataset.csv",
               ln       = null,
               delimiter = ",";
        int src, dst, weight = 0;

        try {
            fr = new FileReader(path);
            br = new BufferedReader(fr);

            br.readLine(); // skips first line
            while ((ln = br.readLine()) != null) {
                String[] values = ln.split(delimiter);
                src     = Integer.parseInt(values[0]);
                dst     = Integer.parseInt(values[1]);
                graph.addVertex(src);
                graph.addVertex(dst);

                int risk = Integer.parseInt(values[3]);
                int congestion = Integer.parseInt(values[5]);
                int smoke = Integer.parseInt(values[6]);

                if (mode == 1)      // Waktu
                    weight = Integer.parseInt(values[2]);
                else if (mode == 2) // Risiko Fisik Saja
                    weight = risk;
                else if (mode == 3) // Jarak
                    weight = Integer.parseInt(values[4]);
                else if (mode == 4) // Kepadatan Saja
                    weight = congestion;
                else if (mode == 5) // Asap Saja
                    weight = smoke;
                else if (mode == 6) //Mode Cerdas: Gabungan Risiko + Macet + Asap
                    weight = risk + congestion + smoke;

                graph.addEdge(src, dst, weight);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Gagal membaca dataset di path: " + path);
            e.printStackTrace();
        }
    }

    void generateDataset(int nvertex, int nedge, int rweight) {
        FileWriter      fw;
        BufferedWriter  bw;
        String path     = "data/dataset.csv";
        String src, dst, pdst, weight;
        int rand = 0;
        if (nedge >= nvertex)
            nedge = (int) (nedge/nvertex);
        else
            return;

        try {
            fw = new FileWriter(path);
            bw = new BufferedWriter(fw);

            // Menuliskan 5 Atribut Tambahan ke CSV
            bw.write("src,dst,time,risk,distance,congestion,smoke_level\n");
            for (int i = 0; i < nvertex; i ++) {
                src = Integer.toString(i);

                pdst = null;
                for (int k = 0; k < nedge; ++k) {
                    rand = (int)(Math.random() * nvertex);
                    dst = Integer.toString(rand);

                    if (src.equals(dst) || dst.equals(pdst)) { 
                        --k;
                        continue;
                    }
                    pdst = dst;

                    bw.append(src);
                    bw.append("," + dst);
                    
                    // Loop 5 kali untuk mengisi nilai time, risk, distance, congestion, smoke_level
                    for (int j = 0; j < 5; j++) {
                        rand = (int)(Math.random() * rweight) + 1; // +1 supaya tidak ada bobot 0
                        weight = Integer.toString(rand);
                        bw.append("," + weight);
                    }
                    bw.append("\n");
                }
            } 
            bw.close();
        } catch (IOException e) {
            System.out.println("Gagal membuat dataset di path: " + path);
            e.printStackTrace();
        }        
    }

}
