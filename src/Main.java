import graph.DS;
//import tree.DijkstraService; // Memanggil logika Dijkstra kustom kamu
import java.io.*;
import java.util.Scanner;

public class Main {
    // Ubah jadi static agar bisa diakses langsung di method main static
    static DS graph = new DS(); 

    public static void main(String[] args) {
        Main app = new Main();
        Scanner scanner = new Scanner(System.in);

        // 1. GENERATE & BACA DATASET (Gunakan parameter minimal dosen: 25 node, 45 edge, max bobot 10)
        System.out.println("⏳ Menggenerate dataset otomatis...");
        app.generateDataset(25, 45, 10); 
        
        // Mode 2 dipilih karena tugas kita mencari jalur dengan RISIKO MINIMUM
        app.readDataset(2); 
        System.out.println("✅ Graph berhasil dibangun dari data keselamatan (Risk Mode)!");

        // 2. MENU UTAMA INTERAKTIF
        while (true) {
            System.out.println("\n=================================");
            System.out.println("   SISTEM EVAKUASI DARURAT (POS 2) ");
            System.out.println("=================================");
            System.out.println("1. Tampilkan Struktur Peta Gedung (Graph)");
            System.out.println("2. Cari Rute Evakuasi Teraman (Dijkstra)");
            System.out.println("3. Simulasi Jalur Blokir / Runtuh (HOTS)");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu (1-4): ");
            
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
                
                System.out.println("\n🔄 Menghitung rute evakuasi memproses Min-Heap...");
                // Memanggil fungsi Dijkstra buatanmu
                DijkstraService.cariJalurTeraman(graph, posisiKini, titikAman);
            } 
            else if (pilihan == 3) {
                System.out.println("\n--- SIMULASI KEADAAN DARURAT (JALUR TERTUTUP) ---");
                System.out.print("Masukkan titik asal jalan yang runtuh/terbakar: ");
                int src = scanner.nextInt();
                System.out.print("Masukkan titik tujuan jalan yang runtuh/terbakar: ");
                int dst = scanner.nextInt();

                // Menggunakan fungsi deleteEdge buatan POS 1 secara dinamis
                graph.deleteEdge(src, dst);
                System.out.println("⚠️ JALUR ANTARA " + src + " DAN " + dst + " TELAH DITUTUP!");
                System.out.println("Silakan pilih Menu 2 kembali untuk mencari rute alternatif.");
            } 
            else if (pilihan == 4) {
                System.out.println("Program selesai. Stay safe dan semoga nilai FP kita A!");
                break;
            } 
            else {
                System.out.println("❌ Pilihan tidak valid!");
            }
        }
        scanner.close();
    }

    // Fungsi readDataset bawaan temanmu (tidak diubah, hanya dirapikan jalurnya jika dibutuhkan)
    void readDataset(int mode) {
        FileReader      fr;
        BufferedReader  br;
        // Menggunakan "data/dataset.csv" agar jalurnya pas dengan struktur root folder project
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

                if      (mode == 1) // weight as time
                    weight = Integer.parseInt(values[2]);
                else if (mode == 2) // ~ risk
                    weight = Integer.parseInt(values[3]);
                else if (mode == 3) // ~ distance
                    weight = Integer.parseInt(values[4]);
                graph.addEdge(src, dst, weight);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Fungsi generateDataset bawaan temanmu (tidak diubah)
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

            bw.write("src,dst,time,risk,distance\n");
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
                    for (int j = 0; j < 3; j++) {
                        rand = (int)(Math.random() * rweight);
                        weight = Integer.toString(rand);
                        bw.append("," + weight);
                    }
                    bw.append("\n");
                }
            } 
            bw.close(); // never forget ts
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }
}