package fila;

import java.util.Iterator;


public final class FilaEncadeada<T> implements Fila<T>{
    
    private No<T> inicio;
    private No<T> fim;
    private int tamanho;
    
    @Override
    public void enfileirar(T elemento) {
        No<T> novoNo = new No<>(elemento);
        
        if(this.inicio == null){
            this.inicio = this.fim = novoNo;
            this.tamanho++;
        }else{
            this.fim.setProximo(novoNo);
            if(this.inicio == this.fim){
                novoNo.setAnterior(this.inicio);    
            }else{
                novoNo.setAnterior(this.fim);
            }
            this.fim = novoNo;
            this.tamanho++;
        }
    }

    @Override
    public T desenfileirar() {
        T retorno = null;
        
        if(this.inicio == this.fim && this.inicio != null){
            retorno = this.primeiro();
            this.inicio = this.fim = null;
            this.inicio.setProximo(null);
            this.fim.setProximo(null);
            this.tamanho--;
        }else{
            retorno = this.primeiro();
            this.inicio.getProximo().setAnterior(null);
            this.inicio = this.inicio.getProximo();
            this.tamanho--;
        }
        
        return retorno;
    }

    @Override
    public T primeiro() {
        return this.inicio.getElemento();
    }

    @Override
    public int tamanho() {
        return this.tamanho;
    }

    @Override
    public boolean vazia() {
        boolean retorno = true;
        
        if(this.tamanho() > 0){
            retorno = false;
        }
        
        return retorno;
    }

    @Override
    public void limpar() {
        this.inicio = this.fim = null;
        this.inicio.setProximo(null);
        this.fim.setAnterior(null);
        this.tamanho = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

}
