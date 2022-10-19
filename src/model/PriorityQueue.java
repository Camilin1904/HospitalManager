package model;

public interface PriorityQueue<T>{

    public T heapMaximum();

    public T heapExtractMax();

    public void increaseKey(int i, int key);

    public void insert(T element, int priority);

    
} 
