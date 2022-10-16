package model;

@SuppressWarnings("unchecked")
public class IdTable implements HashTable<Node<Patient, String>, Patient, String>{

    private Node<Patient,String>[] table;
    private int arraySize;
    private int actualSize;
    public IdTable(int arraySize) {
        this.arraySize = arraySize;
        table = (Node<Patient,String>[]) new Object[arraySize];
    }

    private int generateHashCode(String key){
        return Math.abs(key.hashCode())%arraySize;
    }

    @Override
    public void insert(String key, Patient value) {
        int keyy=generateHashCode(key);
        Node<Patient,String> add= new Node<Patient,String>(value, key);
        if (search(key)==null){
            addLast(add,keyy);
        }
        else{
            search(key,table[keyy],keyy).setValue(value);
        }
        
    }

    @Override
    public void addLast(Node<Patient, String> input, int i) {
        if(table[i] == null){
            table[i] = input;
            table[i].setNext(input);
            table[i].setLast(input);
        }else{
            Node<Patient,String> tail = table[i].getLast();
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
    public Patient search(String key) {
        int keyy=generateHashCode(key);
        Node<Patient,String> u = search(key,table[keyy],keyy);
        if (u!=null)return (u.getValue());
        else return null;
    }
    public Node<Patient,String> search2(String key){
        int keyy=generateHashCode(key);
        Node<Patient,String> u = search(key,table[keyy],keyy);
        return (u);
    }

    private Node<Patient,String> search(String key,Node<Patient,String> actual,int first){


        if(actual==null){
            return null;
        }

        if(actual.getNext()==table[first] && actual.getKey()!=key){
            return null;
        }

        if(actual.getKey()==key){
            return actual;
        }

        if(actual.getKey()!=key){
            return search(key,actual.getNext(),first);
        }

        return null;
    }
    @Override
    public boolean delete(String key) {
        int keyy=generateHashCode(key);
        System.out.println(keyy);
        Node<Patient,String> toDelete = search2(key);
        if (toDelete!=null){
            Node<Patient,String> toReplace = toDelete.getLast();
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
    
}
