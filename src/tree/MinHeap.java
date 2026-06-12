package tree;

import model.HeapNode;
import java.util.ArrayList;

public class MinHeap {
    private ArrayList<HeapNode> heap;

    public MinHeap() {
        this.heap = new ArrayList<>();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public void insert(HeapNode node) {
        heap.add(node);
        heapifyUp(heap.size() - 1);
    }

    public HeapNode extractMin() {
        if (heap.isEmpty()) return null;
        
        HeapNode minNode = heap.get(0);
        HeapNode lastNode = heap.remove(heap.size() - 1);
        
        if (!heap.isEmpty()) {
            heap.set(0, lastNode);
            heapifyDown(0);
        }
        return minNode;
    }

    private void heapifyUp(int index) {
        int parentIndex = (index - 1) / 2;
        
        if (index > 0 && heap.get(index).totalRisk < heap.get(parentIndex).totalRisk) {
            swap(index, parentIndex);
            heapifyUp(parentIndex);
        }
    }

    private void heapifyDown(int index) {
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;
        int smallest = index;

        if (leftChild < heap.size() && heap.get(leftChild).totalRisk < heap.get(smallest).totalRisk) {
            smallest = leftChild;
        }

        if (rightChild < heap.size() && heap.get(rightChild).totalRisk < heap.get(smallest).totalRisk) {
            smallest = rightChild;
        }

        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }

    private void swap(int i, int j) {
        HeapNode temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}
