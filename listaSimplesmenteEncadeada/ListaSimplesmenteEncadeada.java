package listaSimplesmenteEncadeada;

import listaVetor.*;
import java.util.Iterator;

public class ListaSimplesmenteEncadeada<T> implements Lista<T>{
    private No<T> inicio, fim;
    private int tamanho;

    public ListaSimplesmenteEncadeada() {
        this.inicio = this.fim = null;
        this.tamanho = 0;
    }
    
    @Override
    public void adicionar(T elemento) {
        No<T> novoNo = new No<>(elemento);
        
        if(this.inicio == null){
            this.inicio = this.fim = novoNo;
            this.tamanho++;
        }else{
            this.fim.setProximo(novoNo);
            this.fim = novoNo;
            this.tamanho++;
        }
    }

    @Override
    public void adicionar(T elemento, int posicao) {
        No<T> novoNo = new No<>(elemento);
        No<T> no = inicio;
        if ((posicao - 1) == 0) {
            this.adicionarNoInicio(elemento);
        } else if (posicao == tamanho()) {
            this.adicionarNoFim(elemento);
        } else {
            for (int i = 0; i < posicao - 2; i++) {
                no = no.getProximo();
            }
            novoNo.setProximo(no.getProximo());
            no.setProximo(novoNo);
            this.tamanho++;
        }
    }

    @Override
    public void adicionarNoInicio(T elemento) {
        No<T> novoNo = new No<>(elemento);
        if (this.inicio == this.fim) {
            this.inicio = this.fim = novoNo;
            this.tamanho++;
        } else {
            novoNo.setProximo(this.inicio);
            this.inicio = novoNo;
            this.tamanho++;
        }
    }
    
    public void adicionarNoFim(T elemento){
        No<T> novoNo = new No<>(elemento);
        if (this.inicio == null) {
            this.inicio = this.fim = novoNo;
            this.tamanho++;
        } else {
            this.fim.setProximo(novoNo);
            this.fim = novoNo;
            this.tamanho++;
        }
    }
    
    @Override
    public T substituir(T elemento, int posicao) {
        No<T> no = this.inicio;
        T retorno = null;
        for(int i = 0; i < tamanho(); i++){
            if(i == posicao){
                retorno = (T)no;
                no.setElemento(elemento);
                break;
            }else{
                no = no.getProximo();
            }
        }
        return retorno;
    }

    @Override
    public int substituir(T elementoNovo, T elementoAntigo) {
        No<T> no = this.inicio;
        int retorno = -1;
        for(int i = 0; i < tamanho(); i++){
            if((T)no == elementoAntigo){
                retorno = i;
                no.setElemento(elementoNovo);
                break;
            }else{
                no = no.getProximo();
            }
        }
        return retorno;
    }

    @Override
    public T remover(int posicao) {
        No<T> no = inicio;
        T retorno = null;
        
        if (posicao == 0) {
            this.removerNoInicio();
        } else if (posicao == tamanho-1) {
            this.removerNoFim();
        } else {
            for (int i = 0; i < posicao - 1; i++) {
                no = no.getProximo();
            }
            no.setProximo(no.getProximo().getProximo());
            retorno = (T) no.getProximo().getProximo();
            this.tamanho--;
        }
        return retorno;
    }
    
    public T removerNoInicio() {
        No<T> no = this.inicio;
        
        if (this.inicio == this.fim) {
            this.inicio = this.fim = null;
            this.tamanho--;
        } else {
            this.inicio = this.inicio.getProximo();
            this.tamanho--;
        }
        return (T)no;
    }

    public T removerNoFim() {
        No<T> no = this.inicio;
        T retorno = null;
        
        if (this.inicio == this.fim) {
            this.inicio = this.fim = null;
            this.tamanho--;
        }else{
            for (int i = 0; i <= tamanho() - 1; i++) {
                if (no.getProximo() == this.fim) {
                    no.setProximo(null);
                    this.fim = no;
                    this.tamanho--;
                    retorno = (T) no.getProximo();
                    break;
                }
                no = no.getProximo();
            }
        }

        return retorno;
    }
    
    @Override
    public int remover(T elemento) {
        No<T> no = this.inicio;
        int retorno = -1;
        
        if(this.inicio.getElemento() == elemento){
            this.inicio = this.inicio.getProximo();
            this.tamanho--;
        }else{
            for(int i = 0; i < tamanho(); i++){
                if(no.getProximo().getElemento() == elemento){
                    no.setProximo(no.getProximo().getProximo());
                    this.tamanho--;
                    retorno = i;
                }else{
                    no = no.getProximo();
                }
            }
        }
        return retorno;
    }

    @Override
    public T buscar(int posicao) {
        No<T> no = inicio;
        T retorno = null;
        
        if (posicao - 1 == 0) {
            retorno = (T) no.getElemento();
        } else if (posicao == tamanho() - 1) {
            retorno = (T) this.fim.getElemento();
        } else {
            for (int i = 0; i < posicao - 2; i++) {
                no = no.getProximo();
            }

            retorno = (T) no.getProximo().getElemento();
        }
        return retorno;
    }

    @Override
    public int buscar(T elemento) {
        No<T> no = this.inicio;
        int retorno = -1;
        
        for (int i = 0; i <= tamanho(); i++) {
            if (no.getElemento()== elemento) {
                retorno = i;
                break;
            }
            no = no.getProximo();
        }
        return retorno;
    }

    @Override
    public int tamanho() {
        return this.tamanho;
    }

    @Override
    public void limpar() {
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }

    @Override
    public void listar() {
        No<T> no = this.inicio;
        
        for(int i = 0; i < tamanho(); i++){
            System.out.println(no.getElemento().toString());
            no = no.getProximo();
        }
    }

    @Override
    public Iterator<T> iterator() {
        Iterador<T> iterador = new Iterador<>(this.inicio);
        
        return iterador;
    }
    
}
