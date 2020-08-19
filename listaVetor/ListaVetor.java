package listaVetor;

import java.util.Iterator;

public class ListaVetor<T> implements Lista<T>{
    private T[] vetor;
    private int tamanho;

    public ListaVetor() {
        this(10);
    }
    
    public ListaVetor(int capacidadeInicial) {
        this.vetor = (T[]) new Object[capacidadeInicial];
    }

    @Override
    public void adicionar(T elemento) {
        for(int i = 0; i < this.vetor.length; i++){
            if(this.vetor[i] == null){
                this.vetor[i] = elemento;
                break;
            }
        }
    }

    @Override
    public void adicionar(T elemento, int posicao) {
        if(this.vetor[posicao] == null){
            this.vetor[posicao] = elemento;
        }else{
            substituir(elemento, posicao);
        }
        
    }

    @Override
    public void adicionarNoInicio(T elemento) {
        if(this.vetor[0] == null){
            this.vetor[0] = elemento;
        }else{
            substituir(elemento, 0);
        }
        
    }

    @Override
    public T substituir(T elemento, int posicao) {
        for(int i = posicao; i < this.vetor.length; i++){
            if(this.vetor[i+1] == null){
                this.vetor[i+1] = this.vetor[i];
                break;
            }else{
                this.vetor[i+1] = this.vetor[i];
            }
        }
        this.vetor[posicao] = elemento;
        return elemento;
    }

    @Override
    public int substituir(T elementoNovo, T elementoAntigo) {
        int position = -1;
        for(int i = 0; i < this.vetor.length; i++){
            if(this.vetor[i].equals(elementoAntigo)){
                position = i;
                this.vetor[i] = elementoNovo;
                break;
            }
        }
        return position;
    }

    @Override
    public T remover(int posicao) {
        T retorno = this.vetor[posicao];
        this.vetor[posicao] = null;
        return retorno;
    }

    @Override
    public int remover(T elemento) {
        int posicao = 0;
        for(int i = 0; i < this.vetor.length; i++){
            if(this.vetor[i] == elemento){
                posicao = i;
                this.vetor[i] = null;
            }
        }
        return posicao;
    }

    @Override
    public T buscar(int posicao) {
        return this.vetor[posicao];
    }

    @Override
    public int buscar(T elemento) {
        int position = -1;
        for(int i =0; i < vetor.length; i++){
            if(vetor[i].equals(elemento)){
                position = i;
                break;
            }
        }
        return position;
    }

    @Override
    public int tamanho() {
        return this.vetor.length;
    }

    @Override
    public void limpar() {
        for(int i = 0; i < this.vetor.length; i++){
            this.vetor[i] = null;
        }
    }

    @Override
    public void listar() {
        for(int i = 0; i < this.vetor.length; i++){
            System.out.println(vetor[i]);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterador();
    }
    
    class Iterador implements Iterator<T> {
        int posicao;

        public Iterador() {
            this.posicao = 0;
        }
        
        @Override
        public boolean hasNext() {
            //verificar a posição e o tamanho do vetor
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public T next() {
            //Pegar elememento da posição e incrementar posicao
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
}
