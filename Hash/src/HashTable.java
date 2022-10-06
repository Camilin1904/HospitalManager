public interface HashTable <T> {
    public void insert(String key, T value);

    public void addLast(NodeHash<T> input, int i);

    public NodeHash<T> search(String key);

    public void delete(String key);
    
    public void delete(NodeHash<T> current, int goal,NodeHash<T> head);
}
