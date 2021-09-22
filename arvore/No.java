package arvore;

import java.util.List;

public class No {

    No noPai;
    int value;
    No leftNode;
    No rightNode;
    
    public No(int value, No pai) {
        this.value = value;
        this.noPai = pai;
    }
    
    public No(int value) {
        this.value = value;
    }
    
    public No(){
    }
    
    public boolean temFilhoEsq(){
        if(this.leftNode == null){
            return false;
        }else{
            return true;
        }
    }
    
    public boolean temFilhoDir(){
        if(this.rightNode == null){
            return false;
        }else{
            return true;
        }
    }
    
    public void inserir(int value){
        if(this.value == 0){
            this.value = value;
        }else{
            if(value < this.value && !temFilhoEsq()){
                this.leftNode = new No(value, this);
            }else if(value < this.value && temFilhoEsq()){
                inserirNo(this.leftNode, value);
            }else if(value >= this.value && !temFilhoDir()){
                this.rightNode = new No(value, this);
            }else if(value >= this.value && temFilhoDir()){
                inserirNo(this.rightNode, value);
            }else{
                System.out.println("Não foi inserido.");
            }
        }
    }
    
    private void inserirNo(No node, int value){
        if(value < node.value && !node.temFilhoEsq()){
            node.leftNode = new No(value, node);
        }else if(value < node.value && node.temFilhoEsq()){
            inserirNo(node.leftNode, value);
        }else if(value >= node.value && !node.temFilhoDir()){
            node.rightNode = new No(value, node);
        }else if(value >= node.value && node.temFilhoDir()){
            inserirNo(node.rightNode, value);
        }else{
            System.out.println("Não foi inserido.");
        }
    }
    
    public boolean procurar(int value){
        if(this.value == value){
            return true;
        }else{
            if(value < this.value && this.leftNode != null){
                return procurar(value, this.leftNode);
            }else if(value >= this.value && temFilhoDir()){
                return procurar(value, this.rightNode);
            }else{
                return false;
            }
        }
        
    }
    
    private boolean procurar(int value, No node){
        if(node.value == value){
            return true;
        }else{
            if(value < node.value && node.temFilhoEsq()){
                return procurar(value, node.leftNode);
            }else if(value >= node.value && node.temFilhoDir()){
                return procurar(value, node.rightNode);
            }else{
                return false;
            }
        }
    }
    
    public int grau(int value){
        if(this.value == value){
            if(temFilhoEsq() && temFilhoDir()){
                return 2;
            }else if(temFilhoDir() || temFilhoEsq()){
                return 1;
            }else{
                return 0;
            }
        }else{
            if(value < this.value && this.leftNode != null){
                return grau(value, this.leftNode);
            }else if(value >= this.value && temFilhoDir()){
                return grau(value, this.rightNode);
            }
            return -1;
        }
    }
    
    private int grau(int value, No node){
        if(node.value == value){
            if(node.temFilhoEsq() && node.temFilhoDir()){
                return 2;
            }else if(node.temFilhoDir() || node.temFilhoEsq()){
                return 1;
            }else{
                return 0;
            }
        }else{
            if(value < node.value && node.leftNode != null){
                return grau(value, node.leftNode);
            }else if(value >= node.value && temFilhoDir()){
                return grau(value, node.rightNode);
            }
            return -1;
        }
    }
    
    public int nivel(int value){
        if(this.value == value){
            return 0;
        }else{
            if(value < this.value && this.leftNode != null){
                return nivel(value, this.leftNode) + 1;
            }else if(value >= this.value && temFilhoDir()){
                return nivel(value, this.rightNode) + 1;
            }
        }
        return -1;
    }
    
    private int nivel(int value, No node){
        if(node.value == value){
            return 0;
        }else{
            if(value < node.value && node.leftNode != null){
                return nivel(value, node.leftNode) + 1;
            }else if(value >= node.value && temFilhoDir()){
                return nivel(value, node.rightNode) + 1;
            }
        }
        return -1;
    }
    
    public int profundidade(int value){
        return nivel(value);
    }
    
    public int numeroNos(){
        if(!temFilhoEsq() && !temFilhoDir()){
            return 1;
        }else if(temFilhoEsq() && temFilhoDir()){
            return ladoEsquerdo(this.leftNode) + ladoDireito(this.rightNode) + 1;
        }else if(temFilhoEsq() && !temFilhoDir()){
            return ladoEsquerdo(this.leftNode) + 1;
        }else if(temFilhoDir() && !temFilhoEsq()){
            return ladoDireito(this.rightNode) + 1;
        }else{
            return 0;
        }
    }
    
    private int ladoEsquerdo(No node){
        if(!node.temFilhoEsq() && !node.temFilhoDir()){
            return 1;
        }else if(node.temFilhoEsq() && node.temFilhoDir()){
            return ladoEsquerdo(node.leftNode) + ladoDireito(node.rightNode) + 1;
        }else if(node.temFilhoEsq() && !node.temFilhoDir()){
            return ladoEsquerdo(node.leftNode) + 1;
        }else if(node.temFilhoDir() && !node.temFilhoEsq()){
            return ladoDireito(node.rightNode) + 1;
        }else{
            return 0;
        }
    }
    
    private int ladoDireito(No node){
        if(!node.temFilhoEsq() && !node.temFilhoDir()){
            return 1;
        }else if(node.temFilhoEsq() && node.temFilhoDir()){
            return ladoEsquerdo(node.leftNode) + ladoDireito(node.rightNode) + 1;
        }else if(node.temFilhoEsq() && !node.temFilhoDir()){
            return ladoEsquerdo(node.leftNode) + 1;
        }else if(node.temFilhoDir() && !node.temFilhoEsq()){
            return ladoDireito(node.rightNode) + 1;
        }else{
            return 0;
        }
    }
    
    public No _LNR(){
        No[] lista = new No[numeroNos()];
        if(temFilhoEsq()){
            return _LNR(this.leftNode, lista);
        }else{
            if(temFilhoDir()){
                System.out.println(this.value);
                return _LNR(this.rightNode, inserirLista(this, lista));
            }else{
                System.out.println(this.value);
                return null;
            }
        }
    }
    
    private No _LNR(No node, No[] lista){
        if(contains(node, lista)){
            if(node.noPai == null){
                return null;
            }else{
                return _LNR(node.noPai, lista);
            }
        }else if(node.temFilhoEsq() && !contains(node.leftNode, lista)){
            return _LNR(node.leftNode, lista);
        }else{
            if(node.temFilhoDir()){
                System.out.println(node.value);
                return _LNR(node.rightNode, inserirLista(node, lista));
            }else{
                System.out.println(node.value);
                return _LNR(node.noPai, inserirLista(node, lista));
            }
        }
    }
    
    public No _NLR(){
        No[] lista = new No[numeroNos()];
        if(temFilhoEsq()){
            System.out.println(this.value);
            return _NLR(this.leftNode, inserirLista(this, lista));
        }else{
            if(temFilhoDir()){
                System.out.println(this.value);
                return _NLR(this.rightNode, inserirLista(this, lista));
            }else{
                System.out.println(this.value);
                return null;
            }
        }
    }
    
    private No _NLR(No node, No[] lista){
        if(contains(node, lista)){
            if(node.temFilhoDir() && !contains(node.rightNode, lista)){
                return _NLR(node.rightNode, lista);
            }else{
                if(node.noPai == null){
                    return null;
                }else{
                    return _NLR(node.noPai, lista);
                }
            }
        }else if(node.temFilhoEsq() && !contains(node, lista)){
            System.out.println(node.value);
            return _NLR(node.leftNode, inserirLista(node, lista));
        }else{
            if(node.temFilhoDir()){
                System.out.println(node.value);
                return _NLR(node.rightNode, inserirLista(node, lista));
            }else{
                System.out.println(node.value);
                return _NLR(node.noPai, inserirLista(node, lista));
            }
        }
    }
    
    public No _LRN(){
        No[] lista = new No[numeroNos()];
        if(temFilhoEsq()){
            return _LRN(this.leftNode, lista);
        }else{
            if(temFilhoDir()){
                return _LRN(this.rightNode, lista);
            }else{
                System.out.println(this.value);
                return null;
            }
        }
    }
    
    private No _LRN(No node, No[] lista){
        if(node.temFilhoEsq() && !contains(node.leftNode, lista)){
            return _LRN(node.leftNode, lista);
        }else{
            if(node.temFilhoDir() && !contains(node.rightNode, lista)){
                return _LRN(node.rightNode, lista);
            }else{
                if(node.noPai == null){
                    System.out.println(node.value);
                    return null;
                }else{
                    System.out.println(node.value);
                    return _LRN(node.noPai, inserirLista(node, lista));
                }
            }
        }
    }
    
    private boolean contains(No node, No[] lista){
        boolean aux = false;
        for(int i = 0; i < lista.length; i++){
            if(lista[i] == node){
                aux = true;
                break;
            }
        }
        return aux;
    }
    
    private No[] inserirLista(No node, No[] lista){
        for(int i = 0; i < lista.length; i++){
            if(lista[i] == null){
                lista[i] = node;
                break;
            }
        }
        return lista;
    }
    
    public int altura(int value){
        if(this.value == value){
            if(temFilhoEsq() && temFilhoDir()){
                int aux1 = profLadoEsq(this.leftNode) + 1;
                int aux2 = profLadoDir(this.rightNode) + 1;

                if(aux1 >= aux2){
                    return aux1;
                }else{
                    return aux2;
                }
            }else if(temFilhoEsq() && !temFilhoDir()){
                return profLadoEsq(this.leftNode) + 1;
            }else if(!temFilhoEsq() && temFilhoDir()){
                return profLadoDir(this.rightNode) + 1;
            }else{
                return 0;
            }
        }else{
            if(value < this.value){
                return altura(value, this.leftNode);
            }else if(value >= this.value){
                return altura(value, this.rightNode);
            }else{
                return 0;
            }
        }
    }
    
    private int altura(int value, No node){
        if(node.value == value){
            if(node.temFilhoEsq() && node.temFilhoDir()){
                int aux1 = profLadoEsq(node.leftNode);
                int aux2 = profLadoDir(node.rightNode);

                if(aux1 >= aux2){
                    return aux1;
                }else{
                    return aux2;
                }
            }else if(node.temFilhoEsq() && !node.temFilhoDir()){
                return profLadoEsq(node.leftNode);
            }else if(!node.temFilhoEsq() && node.temFilhoDir()){
                return profLadoDir(node.rightNode);
            }else{
                return 0;
            }
        }else{
            if(value < node.value){
                return altura(value, node.leftNode);
            }else if(value >= node.value){
                return altura(value, node.rightNode);
            }else{
                return 0;
            }
        }
    }
    
    private int profLadoEsq(No node){
        if(!node.temFilhoEsq() && !node.temFilhoDir()){
            return 1;
        }else if(node.temFilhoEsq() && node.temFilhoDir()){
            int aux1 = profLadoEsq(node.leftNode);
            int aux2 = profLadoDir(node.rightNode);
            
            if(aux1 >= aux2){
                return aux1;
            }else{
                return aux2;
            }
        }else if(node.temFilhoDir() && !node.temFilhoEsq()){
            return profLadoDir(node.rightNode) + 1;
        }else if(node.temFilhoEsq() && !node.temFilhoDir()){
            return profLadoEsq(node.leftNode) + 1;
        }else{
            return -1;
        }
    }
    
    private int profLadoDir(No node){
        if(!node.temFilhoEsq() && !node.temFilhoDir()){
            return 1;
        }else if(node.temFilhoEsq() && node.temFilhoDir()){
            int aux1 = profLadoEsq(node.leftNode);
            int aux2 = profLadoDir(node.rightNode);
            
            if(aux1 >= aux2){
                return aux1;
            }else{
                return aux2;
            }
        }else if(node.temFilhoDir() && !node.temFilhoEsq()){
            return profLadoDir(node.rightNode) + 1;
        }else if(node.temFilhoEsq() && !node.temFilhoDir()){
            return profLadoEsq(node.leftNode) + 1;
        }else{
            return -1;
        }
    }
    
    public void removerNo(int value){
        if(this.value == value){
            
        }else{
            if(value < this.value){
                removerNo(value, this.leftNode);
            }else{
                removerNo(value, this.rightNode);
            }
        }
    }
    
    private void removerNo(int value, No node){
        if(node.value == value){
            if(!node.temFilhoEsq() && !node.temFilhoDir()){
                if(node.value < node.noPai.value){
                    node.noPai.leftNode = null;
                }else{
                    node.noPai.rightNode = null;
                }
            }else if(node.temFilhoDir() && !node.temFilhoEsq()){
                if(node.value < node.noPai.value){
                    node.noPai.leftNode = node.rightNode;
                }else{
                    node.noPai.rightNode = node.rightNode;
                }
            }else if(!node.temFilhoDir() && node.temFilhoEsq()){
                if(node.value < node.noPai.value){
                    node.noPai.leftNode = node.leftNode;
                }else{
                    node.noPai.rightNode = node.leftNode;
                }
            }else if(node.temFilhoEsq() && node.temFilhoDir()){
                No nodeAux = maiorNo(node.leftNode);
                node.value = nodeAux.value;
            }
        }else{
            if(value < node.value){
                removerNo(value, node.leftNode);
            }else{
                removerNo(value, node.rightNode);
            }
        }
    }
    
    public void inverterSubArvores(){
        No aux;
        System.out.println("Árvore original:");
        imprimirArvore();
        
        aux = this.leftNode;
        this.leftNode = this.rightNode;
        this.rightNode = aux;
        
        System.out.println("\nNova árvore:");
        imprimirArvore();
    }
    
    private void imprimirArvore(){
        System.out.println("Raiz: " + this.value);
        if(temFilhoEsq() && temFilhoDir()){
            imprimirArvore(this.leftNode);
            imprimirArvore(this.rightNode);
        }else if(temFilhoEsq() && !temFilhoDir()){
            imprimirArvore(this.leftNode);
        }else if(!temFilhoEsq() && temFilhoDir()){
            imprimirArvore(this.rightNode);
        }
        
    }
    
    private void imprimirArvore(No node){
        System.out.println("Nó: " + node.value);
        if(node.temFilhoEsq() && node.temFilhoDir()){
            imprimirArvore(node.leftNode);
            imprimirArvore(node.rightNode);
        }else if(node.temFilhoEsq() && !node.temFilhoDir()){
            imprimirArvore(node.leftNode);
        }else if(!node.temFilhoEsq() && node.temFilhoDir()){
            imprimirArvore(node.rightNode);
        }
    }
    
    private No maiorNo(No node){
        if(node.temFilhoDir()){
            return maiorNo(node.rightNode);
        }else if(node.temFilhoEsq()){
            node.noPai.rightNode = node.leftNode;
            node.leftNode.noPai = node.noPai;
            return node;
        }else{
            return node;
        }
    }
    
    public void menoresValores(){
        imprimirArvore(this.leftNode);
    }
    
    public boolean ehEstritamenteBin(){
        if(this.temFilhoEsq() && this.temFilhoDir()){
            return (ehEstritamenteBin(this.leftNode) && ehEstritamenteBin(this.rightNode));
        }else if(!this.temFilhoEsq() && !this.temFilhoDir()){
            return true;
        }else{
            return false;
        }
    }
    
    private boolean ehEstritamenteBin(No node){
        if(node.temFilhoEsq() && node.temFilhoDir()){
            return (ehEstritamenteBin(node.leftNode) && ehEstritamenteBin(node.rightNode));
        }else if(!node.temFilhoEsq() && !node.temFilhoDir()){
            return true;
        }else{
            return false;
        }
    }
    
    public void paiMaiorFilho(){
        if(this.temFilhoEsq() && this.temFilhoDir()){
            System.out.println("Nó pai: " + this.value);
            System.out.println("Maior filho: " + this.rightNode.value);
            paiMaiorFilho(this.leftNode);
            paiMaiorFilho(this.rightNode);
        }else if(this.temFilhoEsq() && !this.temFilhoDir()){
            System.out.println("Nó pai: " + this.value);
            System.out.println("Maior filho: " + this.leftNode.value);
            paiMaiorFilho(this.leftNode);
        }else if(this.temFilhoDir() && !this.temFilhoEsq()){
            System.out.println("Nó pai: " + this.value);
            System.out.println("Maior filho: " + this.rightNode.value);
            paiMaiorFilho(this.rightNode);
        }else{
            System.out.println("O nó " + this.value + " é um nó folha.");
        }
    }
    
    private void paiMaiorFilho(No node){
        if(node.temFilhoEsq() && node.temFilhoDir()){
            System.out.println("\nNó pai: " + node.value);
            System.out.println("Maior filho: " + node.rightNode.value);
            paiMaiorFilho(node.leftNode);
            paiMaiorFilho(node.rightNode);
        }else if(node.temFilhoEsq() && !node.temFilhoDir()){
            System.out.println("\nNó pai: " + node.value);
            System.out.println("Maior filho: " + node.leftNode.value);
            paiMaiorFilho(node.leftNode);
        }else if(node.temFilhoDir() && !node.temFilhoEsq()){
            System.out.println("\nNó pai: " + node.value);
            System.out.println("Maior filho: " + node.rightNode.value);
            paiMaiorFilho(node.rightNode);
        }else{
            System.out.println("\nO nó " + node.value + " é um nó folha.");
        }
    }
    
    public void arvoreNLR(int[] vetor){
        No novoNo = new No(vetor[0]);
        for(int i = 1; i < vetor.length; i++){
            novoNo.inserir(vetor[i]);
        }
        novoNo.imprimirArvore();
    }
    
    public void arvoreLRN(int[] vetor){
        No novoNo = new No(vetor[vetor.length - 1]);
        for(int i = vetor.length-2; i >= 0; i--){
            novoNo.inserir(vetor[i]);
        }
        novoNo.imprimirArvore();
    }
    
    public void arvoreLNR(int[] vetor){
        No novoNo = new No(vetor[vetor.length - 1]);
        for(int i = vetor.length-2; i >= 0; i--){
            novoNo.inserir(vetor[i]);
        }
        novoNo.imprimirArvore();
    }
    
}
