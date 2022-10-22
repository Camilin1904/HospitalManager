package model;
import java.util.ArrayList;

public class PriorityLine<T,K> implements PriorityQueue<Node<T,K>>, Heap<T> {
    private ArrayList<Node<T,K>> PriorityQ;
    private int n;
    private int unit;

    public PriorityLine(int unit) {
        this.PriorityQ= new ArrayList<>();
        this.n= PriorityQ.size();
        this.unit = unit;
    }

    public void heapSort(){
        buildMaxHeap();
        for(int i=PriorityQ.size()-1; i>=0; i--){
            Node<T,K> swap= PriorityQ.get(0);
            /*
            arr[0]=arr[i];
            arr[i]=swap;
             */
            PriorityQ.set(0,PriorityQ.get(i));
            PriorityQ.set(i,swap);
            n--;
            heapify(0);
        }
    }

    public void buildMaxHeap(){
        for(int i=((PriorityQ.size()/2)-1); i>=0; i--){
            heapify(i);
        }
    }

    public void heapify(int i)
    {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;


        if (l < n && PriorityQ.get(l).getPriority() > PriorityQ.get(largest).getPriority())
            largest = l;


        if (r < n && PriorityQ.get(r).getPriority() > PriorityQ.get(largest).getPriority())
            largest = r;


        if (largest != i) {
            /*
            int swap = priorityQ[i];
            priorityQ[i] = priorityQ[largest];
            priorityQ[largest] = swap;
            */
            Node<T,K> swap= PriorityQ.get(i);
            PriorityQ.set(i,PriorityQ.get(largest));
            PriorityQ.set(largest,swap);
            heapify(largest);
        }
    }
    public Node<T,K> heapMaximum(){
        if(n<1){
            return null;
        }
            return PriorityQ.get(0);


    }
    public Node<T,K> heapExtractMax(){
        if(n<1){
            return null;
        }
        Node<T,K> max=PriorityQ.get(0);

        PriorityQ.remove(0);
        if(n!=0){
            n--;
        }

        heapify(0);
        return max;
    }

    public void increaseKey(int i,int key){
        if(PriorityQ.get(i).getPriority()>key){
            return;
        }

        //PreorityQ[i]=key;
        //PreorityQ.set(i,key);
        PriorityQ.get(i).setPriority(key);


        while(i>0 && PriorityQ.get(i/2).getPriority()<PriorityQ.get(i).getPriority()){
            Node<T,K> swap=PriorityQ.get(i);
            //PreorityQ[i]=PreorityQ[i/2];
            PriorityQ.set(i,PriorityQ.get(i/2));
            PriorityQ.set(i/2, swap);
            //PreorityQ[i/2]=swap;
            i=i/2;
        }
    }

    public void insert(Node<T,K> element, int priority){
        //V nose=null;
        //ProrityQ.add(Integer.MAX_VALUE);
        //Node<K,V> node= new Node<>(element,nose,Integer.MIN_VALUE);
        element.setPriority(priority);
        PriorityQ.add(element);
        n = PriorityQ.size();
        increaseKey(n-1,element.getPriority());
    }

    public int getUnit() {
        return unit;
    }

    public boolean takeOut(T value){
        Node<T,K> toRemove = null;
        for (Node<T,K> n : PriorityQ){
            if(n.getValue().equals(value)) toRemove = n;
        }
        return PriorityQ.remove(toRemove);
    }

    public int getHeapSize(){
        return PriorityQ.size();
    }

    public void increaseAllKeys(int increment){
        for (Node<T,K> n : PriorityQ){
            n.setPriority(n.getPriority()+increment);
        }
    }

    public ArrayList<Node<T,K>> getInternalArrayList(){
        return PriorityQ;
    }
}
