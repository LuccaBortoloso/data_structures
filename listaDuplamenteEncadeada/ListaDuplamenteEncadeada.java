package listaDuplamenteEncadeada;

import listaDuplamenteEncadeada.*;
import listaVetor.*;
import java.util.Iterator;

public class ListaDuplamenteEncadeada<T> implements Lista<T>{
    private No<T> inicio, fim;
    private int tamanho;

    public ListaDuplamenteEncadeada() {
        this.inicio = this.fim = null;
        this.tamanho = 0;
    }
    
    @Override
    public void adicionar(T elemento) {
        No<T> NovoNo = new No<>(elemento);
        
        if (this.inicio == null) {
            this.inicio = this.fim = NovoNo;
            this.tamanho++;
        } else {
            this.fim.setProximo(NovoNo);
            NovoNo.setAnterior(this.fim);
            this.fim = NovoNo;
            this.tamanho++;
        }
    }

    @Override
    public void adicionar(T elemento, int posicao) {
        No<T> NovoNo = new No<>(elemento);
        No<T> no = this.inicio;
        
        if (posicao == 0) {
            this.adicionarNoInicio(elemento);
        } else if (posicao == tamanho()-1) {
            this.adicionarNoFim(elemento);
        } else {
            for (int i = 0; i < posicao - 1; i++) {
                no = no.getProximo();
            }
            NovoNo.setProximo(no.getProximo());
            NovoNo.setAnterior(no);
            no.getProximo().setAnterior(NovoNo);
            no.setProximo(NovoNo);
            this.tamanho++;
        }
    }

    @Override
    public void adicionarNoInicio(T elemento) {
        No<T> NovoNo = new No<>(elemento);
        
        if (this.inicio == fim) {
            this.inicio = fim = NovoNo;
            this.tamanho++;
        } else {
            NovoNo.setProximo(inicio);
            this.inicio.setAnterior(NovoNo);
            this.inicio = NovoNo;
            this.tamanho++;
        }
    }
    
    public void adicionarNoFim(T elemento){
        No<T> NovoNo = new No<>(elemento);
        
        if (this.inicio == null) {
            this.inicio = this.fim = NovoNo;
            this.tamanho++;
        } else {
            this.fim.setProximo(NovoNo);
            NovoNo.setAnterior(this.fim);
            this.fim = NovoNo;
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
            if(no.getElemento() == elementoAntigo){
                no.setElemento(elementoNovo);
                retorno = i;
                break;
            }else{
                no = no.getProximo();
            }
        }
        return retorno;
    }

    @Override
    public T remover(int posicao) {
        No<T> no = this.inicio;
        No<T> no2 = this.fim;
        T retorno = null;
        
        if (posicao == 0) {
            removerNoInicio();
        } else if (posicao == tamanho() - 1) {
            removerNoFim();
        } else if (posicao > (tamanho() / 2)) {
            for (int i = tamanho() - 1; i > posicao + 1; i--) {
                no2 = no2.getAnterior();
            }
            retorno = (T) no.getAnterior();
            no2.setAnterior(no2.getAnterior().getAnterior());
            no2.getAnterior().getAnterior().setProximo(no2);
            this.tamanho--;
        } else {
            for (int i = 0; i < posicao - 1; i++) {
                no = no.getProximo();
            }
            no.setProximo(no.getProximo().getProximo());
            no.getProximo().setAnterior(no);
            retorno = (T) no.getProximo().getProximo();
            this.tamanho--;
        }
        return retorno;
    }
    
    public T removerNoInicio() {
        T retorno = null;
        
        if (this.inicio == this.fim) {
            this.inicio = this.fim = null;
            this.tamanho--;
        } else {
            retorno = (T)this.inicio;
            this.inicio = this.inicio.getProximo();
            this.inicio.setAnterior(null);
            this.tamanho--;
        }
        return retorno;
    }

    public T removerNoFim() {
        T retorno = null;
        
        if (this.inicio == this.fim) {
            this.inicio = this.fim = null;
            this.tamanho--;
        }else{
            retorno = (T)this.fim;
            this.fim = this.fim.getAnterior();
            this.fim.setProximo(null);
            this.tamanho--;
        }

        return retorno;
    }
    
    @Override
    public int remover(T elemento) {
        No<T> no = this.inicio;
        No<T> noAux;
        int retorno = -1;
        
        for(int i = 0; i < tamanho(); i++){
            if(no.getElemento() == elemento){
                noAux = no.getAnterior();
                no = no.getProximo();
                no.setAnterior(noAux);
                this.tamanho--;
                retorno = i;
            }else{
                no = no.getProximo();
            }
        }
        return retorno;
    }

    @Override
    public T buscar(int posicao) {
        No<T> no = this.inicio;
        No<T> no2 = this.fim;
        T retorno = null;
        
        if (posicao == 0) {
            retorno = no.getElemento();
        } else if (posicao == tamanho() - 1) {
            retorno = this.fim.getElemento();
        } else if (posicao > (tamanho() / 2)) {
            for (int i = tamanho() - 1; i > posicao + 1; i--) {
                no2 = no2.getAnterior();
            }
            retorno = no2.getAnterior().getElemento();

        } else {
            for (int i = 0; i < posicao - 1; i++) {
                no = no.getProximo();
            }
            retorno = no.getProximo().getElemento();
        }
        return retorno;
    }

    @Override
    public int buscar(T elemento) {
        No<T> no = inicio;
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
