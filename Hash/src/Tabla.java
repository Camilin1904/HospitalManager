@SuppressWarnings("unchecked")
public class Tabla<V,K> implements HashTable<V,K>{
    private NodeHash<V,K>[] arr;
    private int n = 0;
    private int s = 0;
    public Tabla(int n) {
        this.n = n;
        arr= new NodeHash[n];
    }

    private int generateHashCode(K key){
        return Math.abs(key.hashCode())%n;
    }

    public void insert(K key, V value){

        int keyy=Math.abs(key.hashCode());
        keyy%=n;
        if(key.equals("gomez")) System.out.println(keyy);
        NodeHash<V,K> add= new NodeHash<>(key,value);
        if (search(key)==null){
            addLast(add,keyy);
        }
        else{
            search(key,arr[keyy],keyy).setValue(value);
        }
        
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
        s++;
    }

    public V search(K key){
        int keyy=generateHashCode(key);
        NodeHash<V,K> u = search(key,arr[keyy],keyy);
        if (u!=null)return (u.getValue());
        else return null;
    }
    private NodeHash<V,K> search2(K key){
        int keyy=generateHashCode(key);
        NodeHash<V,K> u = search(key,arr[keyy],keyy);
        return (u);
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

    public boolean delete(K key) {
        int keyy=generateHashCode(key);
        System.out.println(keyy);
        //return delete(arr[keyy], key,arr[keyy]);
        NodeHash<V,K> u = search2(key);
        if (u!=null){
            NodeHash<V,K> a = u.getPrevious();
            if(a.equals(u)){
                arr[keyy] = null;
            }
            u.getNext().setPrevious(a);
            a.setNext(u.getNext());
            if ( arr[keyy]==u){
                arr[keyy] = a; 
            }
            u = null;
            s--; 
            return true;
        }
        else return false;
    }

    //Eliminar un nodo por su ID
    public boolean delete(NodeHash<V,K> current, K goal,NodeHash<V,K> head) {

        if(head==null){
            return false;
        }

        if(head.getNext()==head){
            head=null;
            return true;
        }
        
        if(current.getNext() == head){
            return false;
        }

        if (goal == head.getKey()) {
            NodeHash<V,K> prev = current.getPrevious();
            NodeHash<V,K> next = current.getNext();
            prev.setNext(next);
            next.setPrevious(prev);
            head = next;
            return true;
        }

        if(current.getKey() == goal){
            NodeHash<V,K> prev = current.getPrevious();
            NodeHash<V,K> next = current.getNext();
            prev.setNext(next);
            next.setPrevious(prev);
            return true;
        }
        

         return delete(current.getNext(), goal,head);


    }
    public int size(){
        return s;
    }
    
}
