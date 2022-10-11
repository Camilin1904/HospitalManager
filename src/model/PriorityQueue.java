package src.model;

public interface PriorityQueue<T, V>{

    public V heapMaximum();

    public V heapExtractMax();

    public void increaseKey(int i, int key);

    public void insert(T element, int key);

    
} 
