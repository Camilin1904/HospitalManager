package model;
    
@SuppressWarnings("unchecked")
public class IdTable<T,K> implements HashTable<Node<T, K>, T, K>{
    private Node<T,K>[] table;
    private int arraySize;
    private int actualSize;
    public IdTable(int arraySize) {
        this.arraySize = arraySize;
        table = new Node[arraySize];
    }

    private int generateHashCode(K key){
        return Math.abs(key.hashCode())%arraySize;
    }

    
    @Override
    public void insert(Node<T, K> node) {
        int keyy=generateHashCode(node.getKey());
        if (search(node.getKey())==null){
            addLast(node,keyy);
        }
    }

    @Override
    public void insert(K key, T value){
        int keyy=generateHashCode(key);
        Node<T,K> add= new Node<T,K>(value, key);
        if (search(key)==null){
            addLast(add,keyy);
        }
        else{
            search(key,table[keyy],keyy).setValue(value);
        }
        
    }

    @Override
    public void addLast(Node<T, K> input, int i) {
        if(table[i] == null){
            table[i] = input;
            table[i].setNext(input);
            table[i].setLast(input);
        }else{
            Node<T,K> tail = table[i].getLast();
            //Los enlaces next
            tail.setNext(input);
            input.setNext(table[i]);

            //Los enlaces previous
            table[i].setLast(input);
            input.setLast(tail);
        }
        actualSize++;
        
    }

    @Override
    public T search(K key) {
        int keyy=generateHashCode(key);
        Node<T,K> u = search(key,table[keyy],keyy);
        if (u!=null)return (u.getValue());
        else return null;
    }
    public Node<T,K> search2(K key){
        int keyy=generateHashCode(key);
        Node<T,K> u = search(key,table[keyy],keyy);
        return (u);
    }

    private Node<T,K> search(K key,Node<T,K> actual,int first){


        if(actual==null){
            return null;
        }

        if(actual.getNext()==table[first] && !actual.getKey().equals(key)){
            return null;
        }

        if(actual.getKey().equals(key)){
            return actual;
        }

        if(!actual.getKey().equals(key)){
            return search(key,actual.getNext(),first);
        }

        return null;
    }
    @Override
    public boolean delete(K key) {
        int keyy=generateHashCode(key);
        System.out.println(keyy);
        Node<T,K> toDelete = search2(key);
        if (toDelete!=null){
            Node<T,K> toReplace = toDelete.getLast();
            if(toReplace.equals(toDelete)){
                table[keyy] = null;
            }
            toDelete.getNext().setLast(toReplace);
            toReplace.setNext(toDelete.getNext());
            if ( table[keyy]==toDelete){
                table[keyy] = toReplace; 
            }
            toDelete = null;
            actualSize--; 
            return true;
        }
        else return false;
    }

    @Override
    public int size() {
        return actualSize;
    }

    protected Node<T,K>[] internalArray(){
        return table;
    }
}

