package src.model;

public class NodeBackup implements  Stack<Node<Patient,String>>{
    
    private Node<Patient,String> head = null;
    private int n;
    

    public NodeBackup(){
        n = 0;
    }


    @Override
    public void push(Node<Patient, String> object) {
        if (head==null){
            head = object;
        }
        else {
            Node<Patient, String> n = object;
            n.setNext(head);
            head.setLast(n);
            head = n;
        }
        n++;
        
    }


    @Override
    public Node<Patient, String> peek() {
        return head;
    }


    @Override
    public Node<Patient, String> poll() throws NoSuchFieldException {
        if (head==null){
            throw new NoSuchFieldException("Empty Stack");
        }
        else{
            Node<Patient,String> hold = head;
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
