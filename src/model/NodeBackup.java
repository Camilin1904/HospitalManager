package src.model;

public class NodeBackup implements  Stack<Node<BackUp,String>>{
    
    private Node<BackUp,String> head = null;
    private int n;
    

    public NodeBackup(){
        n = 0;
    }


    @Override
    public void push(Node<BackUp, String> object) {
        if (head==null){
            head = object;
        }
        else {
            Node<BackUp, String> n = object;
            n.setNext(head);
            head.setLast(n);
            head = n;
        }
        n++;
        
    }


    @Override
    public Node<BackUp, String> peek() {
        return head;
    }


    @Override
    public Node<BackUp, String> poll() throws NoSuchFieldException {
        if (head==null){
            throw new NoSuchFieldException("Empty Stack");
        }
        else{
            Node<BackUp,String> hold = head;
            head = head.getNext();
            n--;
            return hold;
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
