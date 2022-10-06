public interface Heap<T> {
    public void heapify (int i);

    public void buildMaxHeap();

    public void heapSort();
    
    public T heapMaximum();
    
    public T heapExtracMax();

}
