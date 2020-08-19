package pilha;

import java.util.Iterator;


public class PilhaVetor<T> implements IPilha<T>{
    private T[] vetor;
    private int tamanho, topo;
    
    public PilhaVetor(){
        this(10);
        this.tamanho = 0;
        this.topo = -1;
    }
    
    public PilhaVetor(int capacidadeInicial){
        this.vetor = (T[]) new Object[capacidadeInicial];
        this.tamanho = 0;
        this.topo = -1;
    }
    
    @Override
    public void push(T elemento) {
        this.topo++;
        this.vetor[topo] = elemento;
        this.tamanho++;
    }

    @Override
    public T pop() {
        T retorno = null;
        
        if(!isEmpty()){
            retorno = (T) this.vetor[topo];
            this.topo--;
            this.tamanho--;
        }
        
        return retorno;
    }

    @Override
    public int size() {
        return this.tamanho;
    }

    @Override
    public boolean isEmpty() {
        boolean retorno = false;
        
        if(this.topo == -1){
            retorno = true;
        }
        
        return retorno;
    }

    @Override
    public T top() {
        T retorno = null;
        
        if(!isEmpty()){
            retorno = (T)this.vetor[topo];
        }
        
        return retorno;
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
