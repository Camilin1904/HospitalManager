package src.model;

@SuppressWarnings("unchecked")
public class IdTable implements HashTable<Node<Patient,String>,Patient, String>{

    private  Node<Patient,String>[] table;
    private int arraySize;
    private int actualSize;

    public IdTable(int arraySize){
        this.arraySize = arraySize;
        actualSize = 0;
        table = (Node<Patient,String>[]) new Object[arraySize];
    }

    private int generateHashCode(String key){
        return Math.abs(key.hashCode())%arraySize;
    }

    @Override
    public void insert(String key, Patient value) {
        int keyy=Math.abs(key.hashCode());
        keyy%=arraySize;
        if(key.equals("gomez")) System.out.println(keyy);
        Node<Patient,String> add= new Node<>(value, key);
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
    private Node<Patient,String> search2(String key){
        int keyy=generateHashCode(key);
        Node<Patient,String> u = search(key,table[keyy],keyy);
        return (u);
    }
    private Node<Patient,String> search(String key,Node<Patient,String> a,int first){


        if(a==null){
            return null;
        }

        if(a.getNext()==table[first] && a.getKey()!=key){
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

    @Override
    public boolean delete(String key) {
        int keyy=generateHashCode(key);
        Node<Patient,String> deletable = search2(key);
        if (deletable!=null){
            Node<Patient,String> replace = deletable.getLast();
            if(replace.equals(deletable)){
                table[keyy] = null;
            }
            deletable.getNext().setLast(replace);
            replace.setNext(deletable.getNext());
            if (table[keyy]==deletable){
                table[keyy] = replace; 
            }
            deletable = null;
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
