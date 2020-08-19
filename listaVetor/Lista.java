package listaVetor;

public interface Lista<T> extends Iterable<T>{
    public void adicionar(T elemento);
    public void adicionar(T elemento, int posicao);
    public void adicionarNoInicio(T elemento);
    
    public T substituir(T elemento, int posicao);
    public int substituir(T elementoNovo, T elementoAntigo);
    
    public T remover(int posicao);
    public int remover(T elemento);
    
    public T buscar(int posicao);
    public int buscar(T elemento);
    
    public int tamanho();
    public void limpar();
    
    public void listar();
}
