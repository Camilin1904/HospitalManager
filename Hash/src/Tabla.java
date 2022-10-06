public class Tabla<V> {
    private Node[] arr;

    public Tabla() {
        arr= new Node[10];
    }

    public void insert(String key, V value){

        int keyy=0;
        for(int i=0; i<key.length(); i++){
            keyy+=((int)key.charAt(i))*(i+1);
        }
        int j=keyy;
        keyy%=10;
        Node add= new Node(j,value);
        addLast(add,keyy);
    }

    public void addLast(Node input, int i){
        if(arr[i] == null){
            arr[i] = input;
            arr[i].setNext(input);
            arr[i].setPrevious(input);
        }else{
            Node tail = arr[i].getPrevious();
            //Los enlaces next
            tail.setNext(input);
            input.setNext(arr[i]);

            //Los enlaces previous
            arr[i].setPrevious(input);
            input.setPrevious(tail);
        }
    }

    public Node search(String key){
        int keyy=0;
        for(int i=0; i<key.length(); i++){
            keyy+=((int)key.charAt(i))*(i+1);
        }
        int j=keyy;
        keyy%=10;

        return search(j,arr[keyy],keyy);
    }

    private Node search(int key,Node a,int first){


        if(a==null){
            return null;
        }

        if(a.getNext()==arr[first] && a.getKey()!=key){
            return null;
        }

        if(a.getKey()==key){
            return a;
        }

        if(a.getKey()!=key){
            return search(key,a.getNext(),first);
        }

        return null;
    }

    public void delete(String key) {
        int keyy=0;
        for(int i=0; i<key.length(); i++){
            keyy+=((int)key.charAt(i))*(i+1);
        }
        int j=keyy;
        keyy%=10;
        delete(arr[keyy], j,arr[keyy]);
    }

    //Eliminar un nodo por su ID
    public void delete(Node current, int goal,Node head) {
        if (goal == head.getKey()) {
            Node prev = current.getPrevious();
            Node next = current.getNext();
            prev.setNext(next);
            next.setPrevious(prev);
            head = next;
        }
        if(current.getKey() == goal){
            Node prev = current.getPrevious();
            Node next = current.getNext();
            prev.setNext(next);
            next.setPrevious(prev);
        }
        if(current.getNext() == head){
            return;
        }

        delete(current.getNext(), goal,head);


    }
}
