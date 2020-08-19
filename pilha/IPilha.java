package pilha;

public interface IPilha<T> extends Iterable<T>{
    public void push(T elemento);
    public T pop();
    public int size();
    public boolean isEmpty();
    public T top();
}
