public interface HashTable <T, K> {
    public void insert(K key, T value);

    public void addLast(NodeHash<T,K> input, int i);

    public T search(K key);

    public void delete(K key);
}
