package pilha;

import java.util.Iterator;

public class PilhaEncadeada<T> implements IPilha<T> {
    
    private No<Object> inicio;
    private No<Object> fim;
    private int tamanho;
    
    @Override
    public void push(T elemento) {
        No<Object> novoNo = new No<>(elemento);
        
        if(this.inicio == null){
            this.inicio = this.fim = novoNo;
            this.tamanho++;
        }else{
            this.fim.setProximo(novoNo);
            novoNo.setAnterior(this.fim);
            this.fim = novoNo;
            this.tamanho++;
        }
    }
    
    @Override
    public T pop() {
        T retorno = null;
        
        if(this.inicio == this.fim){
            retorno = (T) this.fim;
            this.inicio = this.fim = null;
            this.tamanho--;
        }else{
            retorno = (T) this.fim;
            this.fim.getAnterior().setProximo(null);
            this.fim = this.fim.getAnterior();
            this.tamanho--;
        }
        
        return retorno;
    }

    @Override
    public T top() {
        T retorno = null;
        
        if(!isEmpty()){
            retorno = (T)this.fim;
        }
        
        return retorno;
    }

    @Override
    public int size() {
        return tamanho;
    }

    @Override
    public boolean isEmpty() {
        boolean retorno = false;
        
        if(this.inicio == null){
            retorno = true;
        }
        
        return retorno;
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
