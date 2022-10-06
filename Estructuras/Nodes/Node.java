package Nodes;

public class Node <T> {
    Node<T> next;
    Node<T> last;
    T value;
    public Node(T value){
        this.value = value;
    }
    public T getValue() {
        return value;
    }
    public Node<T> getLast() {
        return last;
    }
    public Node<T> getNext() {
        return next;
    }
    public void setLast(Node<T> last) {
        this.last = last;
    }
    public void setNext(Node<T> next) {
        this.next = next;
    }
}
