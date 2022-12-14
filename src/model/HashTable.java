package model;
public interface HashTable <N, T, K> {
    public void insert(K key, T value);
    public void insert(N node);

    public void addLast(N input, int i);

    public T search(K key);

    public boolean delete(K key);

    public int size();
}
