package model;
public interface Stack <T>{
    public void push(T object);
    public T peek();
    public T poll() throws NoSuchFieldException;
    public void clear();
    public int size();
}
