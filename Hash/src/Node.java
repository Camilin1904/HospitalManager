public class Node<V> {
    private int key;
    private V value;
    private Node next;
    private Node previous;

    public Node(int key, V phoneNumber){
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

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }
}
