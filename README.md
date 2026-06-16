# Final Project Struktur Data (A)
### Kelompok 06
| Nama                          | NRP         |
| --:                           | :--         |
| Sean Arthur Tamajaya          | 5027251050  |
| Bima Aria Perthama            | 5027241060  |
| Afriezal Suryapraba Laiasach  | 5027251096  |
| Dian Hanna Simanjuntak        | 5027251116  |
| Thio Billy Amansyah           | 5027231007  |

### Opsi 2: Emergency Evacuation System  
Emergency Evacuation System merupakan program simulasi pencarian jalur evakuasi darurat pada gedung menggunakan konsep Graph dan Tree. Sistem merepresentasikan lokasi gedung sebagai vertex dan jalur sebagai edge, kemudian menentukan jalur evakuasi terbaik berdasarkan kondisi keamanan jalur.  

### Features  
- Menampilkan struktur peta gedung menggunakan Graph (Adjacency List)
- Mencari jalur evakuasi teraman menggunakan algoritma Dijkstra
- Melakukan traversal area menggunakan BFS
- Simulasi jalur tertutup/runtuh dengan penghapusan edge
- Menghapus lokasi yang tidak dapat digunakan
- Mengubah bobot jalur berdasarkan kondisi terbaru
- Mencari lokasi berdasarkan prefix
  
### Data Structure & Algorithm  
Data Structure:  
- Graph (Adjacency List berbasis Linked List)
- MinHeap
- Vertex dan Edge
  
Algorithm:  
- Breadth First Search (BFS)
- Dijkstra Algorithm
  
### Dataset  
Dataset disimpan dalam format CSV yang berisi informasi jalur antar lokasi, meliputi:  
- Time
- Risk
- Distance
- Congestion
- Smoke Level

Bobot jalur pada algoritma Dijkstra menggunakan kombinasi faktor keamanan:
`
weight = risk + congestion + smoke_level
`  
  
### How to Run  
1. Clone repository
2. Pastikan Java sudah terinstall
3. Jalankan program melalui file: `Main.java`
4. Pilih menu sesuai kebutuhan.

### Compile Manual 

```bash

E:\FP Strukdat\FP_DSOOP2026> javac src/model/*.java src/graph/*.java src/tree/*.java src/Main.java && java -cp src Main

```
