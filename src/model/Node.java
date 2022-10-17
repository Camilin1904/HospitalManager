package model;

public class Node <T,K> {
    private Node<T,K> next;
    private Node<T, K> last;
    private K key;
    private T value;
    public Node(T value, K key){
        this.value = value;
        this.key = key;
    }
    public T getValue() {
        return value;
    }
    public Node<T, K> getLast() {
        return last;
    }
    public Node<T, K> getNext() {
        return next;
    }
    public void setLast(Node<T, K> last) {
        this.last = last;
    }
    public void setNext(Node<T, K> next) {
        this.next = next;
    }
    public K getKey(){
        return key;
    }
    public void setValue(T value) {
        this.value = value;
    }
    public void setKey(K key) {
        this.key = key;
    }
}
