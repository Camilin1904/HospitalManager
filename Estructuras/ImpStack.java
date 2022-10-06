public class ImpStack<T> implements Stack<T>{
    
    private Node<T> head = null;
    private int n;
    

    public ImpStack(){
        n = 0;
    }

    public void push(T object){
        if (head==null){
            head = new Node<>(object);
        }
        else {
            Node<T> n = new Node<>(object);
            n.setNext(head);
            head.setLast(n);
            head = n;
        }
        n++;
    }   

    public T peek(){
        return head.getValue();
    }

    public T poll() throws NoSuchFieldException{
        if (head==null){
            throw new NoSuchFieldException("Empty Stack");
        }
        else{
            T hold = head.getValue();
            head = head.getNext();
            n--;
            return hold;
        }
    }

    public void clear(){
        head = null;
        n = 0;
    }

    public int size(){
        return n;
    }
}