package arvore;

public class Arvore {
    public static void main(String[] args) {
        //Árvore Binária de Busca
        No no = new No(50);
        no.inserir(40);
        no.inserir(45);
        no.inserir(30);
        no.inserir(35);
        no.inserir(60);
        no.inserir(55);
        no.inserir(70);
        no.inserir(53);
        
        /*
        no._NLR();
        System.out.println("");
        no._LRN();
        System.out.println("");
        no._LNR();
        */
        int []vetor1 = new int[]{50, 40, 30, 35, 45, 60, 55, 53, 70}; //NLR
        int []vetor2 = new int[]{35, 30, 45, 40, 53, 55, 70, 60, 50}; //LRN
        int []vetor3 = new int[]{30, 35, 40, 45, 50, 53, 55, 60, 70}; //LNR
        
        //Métodos que retornam árvores passando como parâmetro
        //o pos-order, pre-order e in-order.
        //no.arvoreNLR(vetor1);  
        //no.arvoreLRN(vetor2);
        //no.arvoreLNR(vetor3);
        
        //Método que retorna os menores valores.
        //no.menoresValores();
        
        //Método para verificar se é estritamente binário.
        //System.out.println(no.ehEstritamenteBin());
        
        //Método que imprime os nós pais e o maior filho de cada.
        //no.paiMaiorFilho();
        
    }

}
