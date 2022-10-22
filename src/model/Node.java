package model;

public class Node <T,K> {
    private Node<T,K> next;
    private Node<T, K> last;
    private K key;
    private T value;
    private int priority;
    private boolean del = false;
    private String procedence = "";

    public Node(T value, K key){
        this.value = value;
        this.key = key;
    }
    public Node(T value, K key, int priority){
        this.value = value;
        this.key = key;
        this.priority = priority;
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
    public int getPriority() {
        return priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }
    public void setDel(boolean del) {
        this.del = del;
    }
    public boolean getDel(){
        return del;
    }
    public String getProcedence() {
        return procedence;
    }
    public void setProcedence(String procedence) {
        this.procedence = procedence;
    }

}
