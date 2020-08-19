/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listaDuplamenteEncadeada;

import ListaDuplamenteEncadeada.*;
import java.util.Iterator;

/**
 *
 * @author 11645
 */
public class Iterador<T> implements Iterator<T>{
    private No<T> atual;

    public Iterador(No<T> inicio) {
        this.atual = inicio;
    }

    @Override
    public boolean hasNext() {
        No<T> no = this.atual;
        boolean retorno = false;
        if(no.getProximo() != null){
            retorno = true;
        }
        return retorno;
    }

    @Override
    public T next() {
        No<T> no = this.atual;
        T retorno = null;
        if(hasNext()){
            retorno = (T) no.getProximo();
        }
        return retorno;
    }
}
