import java.util.ArrayList;

public class PriorityLine<T> implements Heap<T>{
    private ArrayList<Node<T>> PreorityQ;
    private int n;

    public PriorityLine() {
        this.PreorityQ= new ArrayList<>();
        this.n= PreorityQ.size();
    }

    public void heapSort(){
        buildMaxHeap();
        for(int i=PreorityQ.size()-1; i>=0; i--){
            Node<T> swap= PreorityQ.get(0);
            /*
            arr[0]=arr[i];
            arr[i]=swap;
             */
            PreorityQ.set(0,PreorityQ.get(i));
            PreorityQ.set(i,swap);
            n--;
            heapify(0);
        }
    }

    public void buildMaxHeap(){
        for(int i=((PreorityQ.size()/2)-1); i>=0; i--){
            heapify(i);
        }
    }

    public void heapify(int i)
    {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;


        if (l < n && PreorityQ.get(l).getPreority() > PreorityQ.get(largest).getPreority())
            largest = l;


        if (r < n && PreorityQ.get(r).getPreority() > PreorityQ.get(largest).getPreority())
            largest = r;


        if (largest != i) {
            /*
            int swap = PreorityQ[i];
            PreorityQ[i] = PreorityQ[largest];
            PreorityQ[largest] = swap;
            */
            Node<T> swap= PreorityQ.get(i);
            PreorityQ.set(i,PreorityQ.get(largest));
            PreorityQ.set(largest,swap);
            heapify(largest);
        }
    }
    public T heapMaximum(){
        if(n<1){
            return null;
        }
            return PreorityQ.get(0).getElement();


    }
    public T heapExtracMax(){
        if(n<1){
            System.out.println("Cola vacia");
            return null;
        }
        T max=PreorityQ.get(0).getElement();

        PreorityQ.set(0,PreorityQ.get(n-1));
        if(n==0){

        }else{
            n--;
        }

        heapify(0);
        return max;
    }

    public void increaseKey(int i,int key){
        if(PreorityQ.get(i).getPreority()>key){
            return;
        }

        //PreorityQ[i]=key;
        //PreorityQ.set(i,key);
        PreorityQ.get(i).setPreority(key);


        while(i>0 && PreorityQ.get(i/2).getPreority()<PreorityQ.get(i).getPreority()){
            Node<T> swap=PreorityQ.get(i);
            //PreorityQ[i]=PreorityQ[i/2];
            PreorityQ.set(i,PreorityQ.get(i/2));
            PreorityQ.set(i/2, swap);
            //PreorityQ[i/2]=swap;
            i=i/2;
        }
    }

    public void Insert(T element, int key){
        n++;
        //ProrityQ.add(Integer.MAX_VALUE);
        Node<T> node= new Node(Integer.MIN_VALUE,element);
        PreorityQ.add(node);
        increaseKey(n-1,key);
    }

}
