import java.security.Key;

@SuppressWarnings("unchecked")
public class Tabla<V,K> implements HashTable<V,K>{
    private NodeHash<V,K>[] arr;

    public Tabla() {
        arr= new NodeHash[10];
    }

    public void insert(K key, V value){

        int keyy=key.hashCode();
        keyy%=10;
        NodeHash<V,K> add= new NodeHash<>(key,value);
        addLast(add,keyy);
    }

    public void addLast(NodeHash<V,K> input, int i){
        if(arr[i] == null){
            arr[i] = input;
            arr[i].setNext(input);
            arr[i].setPrevious(input);
        }else{
            NodeHash<V,K> tail = arr[i].getPrevious();
            //Los enlaces next
            tail.setNext(input);
            input.setNext(arr[i]);

            //Los enlaces previous
            arr[i].setPrevious(input);
            input.setPrevious(tail);
        }
    }

    public V search(K key){
        int keyy=key.hashCode();
        keyy%=10;
        NodeHash<V,K> u = search(key,arr[keyy],keyy);
        if (u!=null)return (u.getValue());
        else return null;
    }

    private NodeHash<V,K> search(K key,NodeHash<V,K> a,int first){


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

    public void delete(K key) {
        int keyy=hashCode();
        keyy%=10;
        delete(arr[keyy], key,arr[keyy]);
    }

    //Eliminar un nodo por su ID
    public void delete(NodeHash<V,K> current, K goal,NodeHash<V,K> head) {
        if (goal == head.getKey()) {
            NodeHash<V,K> prev = current.getPrevious();
            NodeHash<V,K> next = current.getNext();
            prev.setNext(next);
            next.setPrevious(prev);
            head = next;
        }
        if(current.getKey() == goal){
            NodeHash<V,K> prev = current.getPrevious();
            NodeHash<V,K> next = current.getNext();
            prev.setNext(next);
            next.setPrevious(prev);
        }
        if(current.getNext() == head){
            return;
        }

        delete(current.getNext(), goal,head);


    }
}
