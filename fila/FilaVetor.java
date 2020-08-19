package fila;

import java.util.Iterator;


public final class FilaVetor<T> implements Fila<T>{
    private T[] vetor;
    private int tamanho, numeroMax;
    private int inicio, fim;
    
    public FilaVetor(){
        this.numeroMax = 10;
        this.vetor = (T[]) new Object [this.numeroMax];
        this.tamanho = 0;
        this.inicio = this.fim = -1;
    }
    
    public FilaVetor(int quantidadeInicial){
        this.numeroMax = quantidadeInicial;
        this.vetor = (T[]) new Object [this.numeroMax]; 
        this.tamanho = 0;
        this.inicio = this.fim = -1;
    }
        
    @Override
    public void enfileirar(T elemento) {
        if(this.inicio == -1){
            this.inicio++;
            this.fim++;
            this.tamanho++;
            vetor[this.inicio] = elemento;
        }else{
            if(this.tamanho() < this.numeroMax && this.fim == this.numeroMax - 1){
                this.fim = 0;
                this.tamanho++;
                vetor[this.fim] = elemento;
            }else if(this.tamanho() < this.numeroMax){
                this.fim++;
                this.tamanho++;
                vetor[this.fim] = elemento;
            }
        }
    }

    @Override
    public T desenfileirar() {
        T retorno = null;
        
        if(this.inicio == this.fim){
            retorno = vetor[this.inicio];
            vetor[this.inicio] = null;
            this.inicio = this.fim = -1;
            this.tamanho--;
        }else if(this.inicio == this.numeroMax - 1){
            retorno = vetor[this.inicio];
            vetor[this.inicio] = null;
            this.inicio = 0;
            this.tamanho--;
        }else{
            retorno = vetor[this.inicio];
            vetor[this.inicio] = null;
            this.inicio++;
            this.tamanho--;
        }
        
        return retorno;
    }

    @Override
    public T primeiro() {
        return vetor[this.inicio];
    }

    @Override
    public int tamanho() {
        return tamanho;
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
        for(int i = 0; i < this.numeroMax; i++){
            vetor[i] = null;
        }
        this.tamanho = 0;
    }

    @Override
    public Iterator iterator() {
        return null;
    }
    
}
