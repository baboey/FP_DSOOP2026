package tree;

import model.HeapNode;
import java.util.ArrayList;

public class MinHeap {
    // Array dinamis untuk menyimpan node-node di dalam Heap
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
        // 1. Masukkan node baru ke posisi paling akhir (paling bawah di Tree)
        heap.add(node);
        
        // 2. Lakukan Heapify Up untuk menaikkan node ke posisi yang benar
        heapifyUp(heap.size() - 1);
    }

    // ==========================================
    // OPERASI UTAMA 2: EXTRACT MIN (AMBIL DATA TERKECIL)
    // ==========================================
    public HeapNode extractMin() {
        if (heap.isEmpty()) return null;
        
        // 1. Ambil node paling atas (Root) yang merupakan nilai terkecil
        HeapNode minNode = heap.get(0);
        
        // 2. Ambil node paling akhir untuk sementara ditaruh di Root
        HeapNode lastNode = heap.remove(heap.size() - 1);
        
        if (!heap.isEmpty()) {
            heap.set(0, lastNode);
            // 3. Turunkan node tersebut ke posisi yang benar agar aturan Min-Heap terjaga
            heapifyDown(0);
        }
        return minNode;
    }

    // ==========================================
    // LOGIKA PROSES HEAPIFY (PENATAAN ULANG)
    // ==========================================
    
    // Menaikkan node jika total risikonya lebih kecil dari Parent-nya
    private void heapifyUp(int index) {
        int parentIndex = (index - 1) / 2;
        
        // Jika belum sampai Root DAN risiko node saat ini < risiko Parent-nya
        if (index > 0 && heap.get(index).totalRisk < heap.get(parentIndex).totalRisk) {
            // Tukar posisi dengan Parent
            swap(index, parentIndex);
            // Rekursif: cek lagi ke atas
            heapifyUp(parentIndex);
        }
    }

    // Menurunkan node jika total risikonya lebih besar dari anak-anaknya
    private void heapifyDown(int index) {
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;
        int smallest = index;

        // Cek apakah anak kiri lebih kecil risikonya
        if (leftChild < heap.size() && heap.get(leftChild).totalRisk < heap.get(smallest).totalRisk) {
            smallest = leftChild;
        }

        // Cek apakah anak kanan lebih kecil risikonya
        if (rightChild < heap.size() && heap.get(rightChild).totalRisk < heap.get(smallest).totalRisk) {
            smallest = rightChild;
        }

        // Jika salah satu anaknya ternyata lebih kecil, lakukan penukaran
        if (smallest != index) {
            swap(index, smallest);
            // Rekursif: cek lagi ke bawah
            heapifyDown(smallest);
        }
    }

    // Fungsi pembantu untuk menukar posisi dua elemen di dalam ArrayList
    private void swap(int i, int j) {
        HeapNode temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}