public class Tabla<V> {
    private NodeHash<V>[] arr;

    public Tabla() {
        arr= new NodeHash[10];
    }

    public void insert(String key, V value){

        int keyy=0;
        for(int i=0; i<key.length(); i++){
            keyy+=((int)key.charAt(i))*(i+1);
        }
        int j=keyy;
        keyy%=10;
        NodeHash<V> add= new NodeHash<>(j,value);
        addLast(add,keyy);
    }

    public void addLast(NodeHash<V> input, int i){
        if(arr[i] == null){
            arr[i] = input;
            arr[i].setNext(input);
            arr[i].setPrevious(input);
        }else{
            NodeHash<V> tail = arr[i].getPrevious();
            //Los enlaces next
            tail.setNext(input);
            input.setNext(arr[i]);

            //Los enlaces previous
            arr[i].setPrevious(input);
            input.setPrevious(tail);
        }
    }

    public NodeHash<V> search(String key){
        int keyy=0;
        for(int i=0; i<key.length(); i++){
            keyy+=((int)key.charAt(i))*(i+1);
        }
        int j=keyy;
        keyy%=10;

        return search(j,arr[keyy],keyy);
    }

    private NodeHash<V> search(int key,NodeHash<V> a,int first){


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
    public void delete(NodeHash<V> current, int goal,NodeHash<V> head) {
        if (goal == head.getKey()) {
            NodeHash<V> prev = current.getPrevious();
            NodeHash<V> next = current.getNext();
            prev.setNext(next);
            next.setPrevious(prev);
            head = next;
        }
        if(current.getKey() == goal){
            NodeHash<V> prev = current.getPrevious();
            NodeHash<V> next = current.getNext();
            prev.setNext(next);
            next.setPrevious(prev);
        }
        if(current.getNext() == head){
            return;
        }

        delete(current.getNext(), goal,head);


    }
}
