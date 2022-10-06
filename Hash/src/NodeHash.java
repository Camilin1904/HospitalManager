public class NodeHash<V,K> {
    private K key;
    private V value;
    private NodeHash<V,K> next;
    private NodeHash<V,K> previous;

    public NodeHash(K key, V phoneNumber){
        this.key=key;

        this.value=phoneNumber;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setPhoneNumber(V phoneNumber) {
        this.value = phoneNumber;
    }

    public NodeHash<V,K> getNext() {
        return next;
    }

    public void setNext(NodeHash<V,K> next) {
        this.next = next;
    }

    public NodeHash<V,K> getPrevious() {
        return previous;
    }

    public void setPrevious(NodeHash<V,K> previous) {
        this.previous = previous;
    }
}
