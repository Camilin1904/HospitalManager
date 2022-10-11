package src.model;
import java.util.ArrayList;

public class PriorityLine<K,V> implements PriorityQueue<K>, Heap<K> {
    private ArrayList<Node<K,V>> priorityQ;
    private int n;

    public PriorityLine() {
        this.priorityQ= new ArrayList<>();
        this.n= priorityQ.size();
    }

    public void heapSort(){
        buildMaxHeap();
        for(int i=priorityQ.size()-1; i>=0; i--){
            Node<K,V> swap= priorityQ.get(0);
            /*
            arr[0]=arr[i];
            arr[i]=swap;
             */
            priorityQ.set(0,priorityQ.get(i));
            priorityQ.set(i,swap);
            n--;
            heapify(0);
        }
    }

    public void buildMaxHeap(){
        for(int i=((priorityQ.size()/2)-1); i>=0; i--){
            heapify(i);
        }
    }

    public void heapify(int i)
    {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;


        if (l < n && priorityQ.get(l).getPriority() > priorityQ.get(largest).getPriority())
            largest = l;


        if (r < n && priorityQ.get(r).getPriority() > priorityQ.get(largest).getPriority())
            largest = r;


        if (largest != i) {
            /*
            int swap = priorityQ[i];
            priorityQ[i] = priorityQ[largest];
            priorityQ[largest] = swap;
            */
            Node<K,V> swap= priorityQ.get(i);
            priorityQ.set(i,priorityQ.get(largest));
            priorityQ.set(largest,swap);
            heapify(largest);
        }
    }
    public K heapMaximum(){
        if(n<1){
            return null;
        }
            return priorityQ.get(0).getValue();


    }
    public K heapExtractMax(){
        if(n<1){
            System.out.println("Cola vacia");
            return null;
        }
        K max=priorityQ.get(0).getValue();

        priorityQ.set(0,priorityQ.get(n-1));
        if(n==0){

        }else{
            n--;
        }

        heapify(0);
        return max;
    }

    public void increaseKey(int i,int key){
        if(priorityQ.get(i).getPriority()>key){
            return;
        }

        //priorityQ[i]=key;
        //priorityQ.set(i,key);
        priorityQ.get(i).setPriority(key);


        while(i>0 && priorityQ.get(i/2).getPriority()<priorityQ.get(i).getPriority()){
            Node<K,V> swap=priorityQ.get(i);
            //priorityQ[i]=priorityQ[i/2];
            priorityQ.set(i,priorityQ.get(i/2));
            priorityQ.set(i/2, swap);
            //priorityQ[i/2]=swap;
            i=i/2;
        }
    }

    public void insert(K element, int key){
        n++;
        V nose=null;
        //ProrityQ.add(Integer.MAX_VALUE);
        Node<K,V> node= new Node<>(element,nose,Integer.MIN_VALUE);
        priorityQ.add(node);
        increaseKey(n-1,key);
    }
    
}
