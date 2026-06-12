package model;

public class HeapNode {
    public int vertexName; // Menyimpan angka ruangan (key dari Vertex temanmu)
    public int totalRisk;  // Menyimpan akumulasi risiko dari lokasi awal

    // Constructor untuk membuat objek data baru
    public HeapNode(int vertexName, int totalRisk) {
        this.vertexName = vertexName;
        this.totalRisk = totalRisk;
    }
}
