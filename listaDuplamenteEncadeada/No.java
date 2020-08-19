package listaDuplamenteEncadeada;

import listaDuplamenteEncadeada.*;

public class No<T> {
    private T elemento;
    private No<T> proximo, anterior;

    public No() {
    }
    
    public No(T elemento) {
        this.elemento = elemento;
    }
    public No(T elemento, No<T> proximo, No<T> anterior) {
        this.elemento = elemento;
        this.proximo = proximo;
        this.anterior = anterior;
    }

    public No<T> getAnterior() {
        return anterior;
    }

    public void setAnterior(No<T> anterior) {
        this.anterior = anterior;
    }

    public T getElemento() {
        return elemento;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public No<T> getProximo() {
        return proximo;
    }

    public void setProximo(No<T> proximo) {
        this.proximo = proximo;
    }
    
}
