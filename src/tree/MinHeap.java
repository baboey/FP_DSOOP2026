package tree;

import model.HeapNode;
import java.util.ArrayList;

public class MinHeap {
    // Array dinamis untuk menyimpan objek HeapNode (isinya vertex int dan totalRisk int)
    private ArrayList<HeapNode> heap;

    // Constructor untuk menginisialisasi Heap kosong
    public MinHeap() {
        this.heap = new ArrayList<>();
    }

    // Fungsi pembantu untuk mengecek apakah heap kosong
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    // ==========================================
    // OPERASI UTAMA 1: INSERT (MASUKKAN DATA)
    // ==========================================
    public void insert(HeapNode node) {
        // 1. Masukkan node baru ke posisi paling akhir array
        heap.add(node);
        
        // 2. Jalankan logika manual menaikkan node jika risikonya lebih kecil dari Parent
        heapifyUp(heap.size() - 1);
    }

    // ==========================================
    // OPERASI UTAMA 2: EXTRACT MIN (AMBIL DATA TERKECIL)
    // ==========================================
    public HeapNode extractMin() {
        if (heap.isEmpty()) return null;
        
        // 1. Ambil Root (indeks 0) yang risikonya paling minimal untuk Dijkstra
        HeapNode minNode = heap.get(0);
        
        // 2. Ambil elemen terakhir untuk menggantikan posisi Root sementara
        HeapNode lastNode = heap.remove(heap.size() - 1);
        
        if (!heap.isEmpty()) {
            heap.set(0, lastNode);
            // 3. Turunkan node tersebut ke bawah sampai posisinya stabil kembali
            heapifyDown(0);
        }
        return minNode;
    }

    // ==========================================
    // LOGIKA PROSES HEAPIFY (PENATAAN ULANG)
    // ==========================================
    
    // Proses manual mengecek ke atas (Parent)
    private void heapifyUp(int index) {
        int parentIndex = (index - 1) / 2;
        
        // Membandingkan totalRisk (int) milik node saat ini dengan Parent-nya
        if (index > 0 && heap.get(index).totalRisk < heap.get(parentIndex).totalRisk) {
            // Tukar posisi jika melanggar aturan Min-Heap
            swap(index, parentIndex);
            // Cek terus ke atas secara rekursif
            heapifyUp(parentIndex);
        }
    }

    // Proses manual mengecek ke bawah (Children)
    private void heapifyDown(int index) {
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;
        int smallest = index;

        // Validasi apakah anak kiri ada dan memiliki totalRisk lebih kecil
        if (leftChild < heap.size() && heap.get(leftChild).totalRisk < heap.get(smallest).totalRisk) {
            smallest = leftChild;
        }

        // Validasi apakah anak kanan ada dan memiliki totalRisk lebih kecil
        if (rightChild < heap.size() && heap.get(rightChild).totalRisk < heap.get(smallest).totalRisk) {
            smallest = rightChild;
        }

        // Jika ditemukan anak yang totalRisk-nya lebih kecil dari parent saat ini
        if (smallest != index) {
            swap(index, smallest);
            // Turunkan terus ke bawah secara rekursif
            heapifyDown(smallest);
        }
    }

    // Fungsi pembantu untuk menukar elemen di dalam ArrayList
    private void swap(int i, int j) {
        HeapNode temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}
