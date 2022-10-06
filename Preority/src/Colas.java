import java.util.ArrayList;
import java.util.PriorityQueue;

public class Colas<T> {
    private PriorityQueue<T> queue;

    public Colas(ArrayList<T> a) {
        queue= new PriorityQueue<>();
        queue.addAll(a);
    }



}
