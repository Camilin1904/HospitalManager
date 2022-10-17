package model;

public class NodeBackup<T,K> implements  Stack<T>{
    
    private Node<T,K> head = null;
    private int n;
    

    public NodeBackup(){
        n = 0;
    }


    @Override
    public void push(T object) {
        if (head==null){
            head = new Node<>(object, null);
        }
        else {
            Node<T, K> n = new Node<>(object, null);
            n.setNext(head);
            head.setLast(n);
            head = n;
        }
        n++;
        
    }


    @Override
    public T peek() {
        return head.getValue();
    }


    @Override
    public T poll() throws NoSuchFieldException {
        if (head==null){
            throw new NoSuchFieldException("Empty Stack");
        }
        else{
            Node<T,K> hold = head;
            head = head.getNext();
            n--;
            return hold.getValue();
        }
    }


    @Override
    public void clear() {
        head = null;
        n = 0;
    }


    @Override
    public int size() {
        return n;
    }

    
}
