public class NodeHash<V> {
    private int key;
    private V value;
    private NodeHash<V> next;
    private NodeHash<V> previous;

    public NodeHash(int key, V phoneNumber){
        this.key=key;

        this.value=phoneNumber;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setPhoneNumber(V phoneNumber) {
        this.value = phoneNumber;
    }

    public NodeHash<V> getNext() {
        return next;
    }

    public void setNext(NodeHash<V> next) {
        this.next = next;
    }

    public NodeHash<V> getPrevious() {
        return previous;
    }

    public void setPrevious(NodeHash<V> previous) {
        this.previous = previous;
    }
}
