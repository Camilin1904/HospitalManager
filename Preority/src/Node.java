public class Node<T>{
    private int Preority;
    private T element;

    public Node(int preority, T element) {
        Preority = preority;
        this.element = element;
    }

    public int getPreority() {
        return Preority;
    }

    public void setPreority(int preority) {
        Preority = preority;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }
}