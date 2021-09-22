package arvoreavl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class ArvoreAVL{
    public No raiz;
    
    public void inserir(int value){
        No node = new No(value);
        inserirAVL(this.raiz, node);
    }
    
    private void inserirAVL(No aComparar, No aInserir){
        if(aComparar == null){
            this.raiz = aInserir;
        }else{
                if(aInserir.getValue() < aComparar.getValue()) {
                    if(aComparar.getEsquerda() == null){
                        aComparar.setEsquerda(aInserir);
                        aInserir.setPai(aComparar);
                        verificarBalanceamento(aComparar);
                    }else{
                        inserirAVL(aComparar.getEsquerda(), aInserir);
                    }

                }else if(aInserir.getValue() > aComparar.getValue()){
                    if (aComparar.getDireita() == null) {
                        aComparar.setDireita(aInserir);
                        aInserir.setPai(aComparar);
                        verificarBalanceamento(aComparar);
                    }else{
                        inserirAVL(aComparar.getDireita(), aInserir);
                    }

                }else{
                        // O nó já existe
                }
        }
    }

    private void verificarBalanceamento(No atual) {
        setBalanceamento(atual);
        int balanceamento = atual.getBalanceamento();

        if (balanceamento == -2){
            System.out.println("Não está balanceada.");
            if (altura(atual.getEsquerda().getEsquerda()) >= altura(atual.getEsquerda().getDireita())) {
                System.out.println("Rotação simples à direita.");
                Scanner scan = new Scanner(System.in);
                scan.nextLine();
                atual = rotacaoDireita(atual);
            } else {
                System.out.println("Rotação dupla à direita.");
                Scanner scan = new Scanner(System.in);
                scan.nextLine();
                atual = duplaRotacaoEsquerdaDireita(atual);
            }

        }else if(balanceamento == 2){
            System.out.println("Não está balanceada.");
            if(altura(atual.getDireita().getDireita()) >= altura(atual.getDireita().getEsquerda())){
                System.out.println("Rotação simples à esquerda.");
                Scanner scan = new Scanner(System.in);
                scan.nextLine();
                atual = rotacaoEsquerda(atual);
            }else{
                System.out.println("Rotação dupla à esquerda.");
                Scanner scan = new Scanner(System.in);
                scan.nextLine();
                atual = duplaRotacaoDireitaEsquerda(atual);
            }
        }

        if(atual.getPai() != null){
            verificarBalanceamento(atual.getPai());
        }else{
            this.raiz = atual;
        }
    }

    public void remover(int value) {
        removerAVL(this.raiz, value);
    }

    private void removerAVL(No atual, int value) {
        if(atual == null){
        }else{
            if(atual.getValue() > value){
                removerAVL(atual.getEsquerda(), value);
            }else if(atual.getValue() < value){
                removerAVL(atual.getDireita(), value);
            }else if(atual.getValue() == value){
                removerNoEncontrado(atual);
            }
        }
    }

    private void removerNoEncontrado(No aRemover) {
        No raizAux;

        if(aRemover.getEsquerda() == null || aRemover.getDireita() == null){
            if(aRemover.getPai() == null){
                this.raiz = null;
                aRemover = null;
                return;
            }
            raizAux = aRemover;

        }else{
            raizAux = sucessor(aRemover);
            aRemover.setValue(raizAux.getValue());
        }

        No p;
        if(raizAux.getEsquerda() != null){
            p = raizAux.getEsquerda();
        }else{
            p = raizAux.getDireita();
        }

        if(p != null){
            p.setPai(raizAux.getPai());
        }

        if(raizAux.getPai() == null){
            this.raiz = p;
        }else{
            if(raizAux == raizAux.getPai().getEsquerda()){
                raizAux.getPai().setEsquerda(p);
            }else{
                raizAux.getPai().setDireita(p);
            }
            verificarBalanceamento(raizAux.getPai());
        }
        raizAux = null;
    }

    private No rotacaoEsquerda(No inicial){
        No direita = inicial.getDireita();
        direita.setPai(inicial.getPai());
        inicial.setDireita(direita.getEsquerda());

        if(inicial.getDireita() != null){
            inicial.getDireita().setPai(inicial);
        }

        direita.setEsquerda(inicial);
        inicial.setPai(direita);

        if(direita.getPai() != null){
            if(direita.getPai().getDireita() == inicial){
                direita.getPai().setDireita(direita);
            }else if (direita.getPai().getEsquerda() == inicial){
                direita.getPai().setEsquerda(direita);
            }
        }

        setBalanceamento(inicial);
        setBalanceamento(direita);

        return direita;
    }

    private No rotacaoDireita(No inicial){
        No esquerda = inicial.getEsquerda();
        esquerda.setPai(inicial.getPai());
        inicial.setEsquerda(esquerda.getDireita());

        if(inicial.getEsquerda() != null){
            inicial.getEsquerda().setPai(inicial);
        }

        esquerda.setDireita(inicial);
        inicial.setPai(esquerda);

        if(esquerda.getPai() != null){
            if(esquerda.getPai().getDireita() == inicial){
                esquerda.getPai().setDireita(esquerda);
            }else if (esquerda.getPai().getEsquerda() == inicial){
                esquerda.getPai().setEsquerda(esquerda);
            }
        }

        setBalanceamento(inicial);
        setBalanceamento(esquerda);

        return esquerda;
    }

    private No duplaRotacaoEsquerdaDireita(No inicial){
        inicial.setEsquerda(rotacaoEsquerda(inicial.getEsquerda()));
        return rotacaoDireita(inicial);
    }

    private No duplaRotacaoDireitaEsquerda(No inicial){
        inicial.setDireita(rotacaoDireita(inicial.getDireita()));
        return rotacaoEsquerda(inicial);
    }

    public No sucessor(No node){
        if(node.getDireita() != null){
            No rightAux = node.getDireita();
            while (rightAux.getEsquerda() != null) {
                rightAux = rightAux.getEsquerda();
            }
            return rightAux;
        }else{
            No paiAux = node.getPai();
            while(paiAux != null && node == paiAux.getDireita()){
                node = paiAux;
                paiAux = node.getPai();
            }
            return paiAux;
        }
    }

    public int altura(No atual){
            if(atual == null){
                return -1;
            }
            
            if(atual.getEsquerda() == null && atual.getDireita() == null){
                return 0;
            }else if (atual.getEsquerda() == null){
                return 1 + altura(atual.getDireita());
            }else if (atual.getDireita() == null){
                return 1 + altura(atual.getEsquerda());
            }else{
                return 1 + Math.max(altura(atual.getEsquerda()), altura(atual.getDireita()));
            }
    }

    private void setBalanceamento(No no){
        no.setBalanceamento(altura(no.getDireita()) - altura(no.getEsquerda()));
    }

    final protected ArrayList<No> inorder(){
        ArrayList<No> ret = new ArrayList<No>();
        inorder(raiz, ret);
        return ret;
    }

    final protected void inorder(No no, ArrayList<No> lista){
        if (no == null) {
            return;
        }
        inorder(no.getEsquerda(), lista);
        lista.add(no);
        inorder(no.getDireita(), lista);
    }
    
    public boolean consulta(int value){
        boolean existe = false;
        ArrayList<No> listaAux = inorder();
        
        for(int i = 0; i < listaAux.size(); i++){
            if(listaAux.get(i).getValue() == value){
                existe = true;
                break;
            }
        }
        
        if(existe){
            if(this.raiz.getValue() == value){
                return true;
            }else{
                if(this.raiz.getValue() > value && this.raiz.getEsquerda() != null){
                    return consulta(this.raiz.getEsquerda(), value);
                }else if(this.raiz.getValue() < value && this.raiz.getDireita() != null){
                    return consulta(this.raiz.getDireita(), value);
                }else{
                    return false;
                }
            }
        }else{
            return false;
        }
    }
    
    private boolean consulta(No node, int value){
        if(node.getValue() == value){
            return true;
        }else{
            if(node.getValue() > value && node.getEsquerda() != null){
                return consulta(node.getEsquerda(), value);
            }else if(node.getValue() < value && node.getDireita() != null){
                return consulta(node.getDireita(), value);
            }else{
                return false;
            }
        }
    }
    
    void percorrer() {
        LinkedList fila = new LinkedList();
        fila.add(this.raiz);
        while(fila.size() != 0) {
        No r = (No) fila.remove();
        System.out.println(r.getValue() + " ");
            if(r.getEsquerda() != null){
                fila.add(r.getEsquerda());
            }

            if(r.getDireita() != null){
                fila.add(r.getDireita());
            }
        }
    }
    
    public void imprimirArvore(){
        print(this.raiz);
    }
    
    public static void print(No root)
    {
        List<List<String>> lines = new ArrayList<List<String>>();

        List<No> level = new ArrayList<No>();
        List<No> next = new ArrayList<No>();

        level.add(root);
        int nn = 1;

        int widest = 0;

        while (nn != 0) {
            List<String> line = new ArrayList<String>();

            nn = 0;

            for (No n : level) {
                if (n == null) {
                    line.add(null);

                    next.add(null);
                    next.add(null);
                } else {
                    String aa = Integer.toString(n.getValue());
                    line.add(aa);
                    if (aa.length() > widest) widest = aa.length();

                    next.add(n.getEsquerda());
                    next.add(n.getDireita());

                    if (n.getEsquerda()!= null) nn++;
                    if (n.getDireita()!= null) nn++;
                }
            }

            if (widest % 2 == 1) widest++;

            lines.add(line);

            List<No> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {

                    // split node
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? ' ' : ' ';
                        } else {
                            if (j < line.size() && line.get(j) != null) c = ' ';
                        }
                    }
                    System.out.print(c);

                    // lines and spaces
                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {

                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : " ");
                        }
                        System.out.print(j % 2 == 0 ? " " : " ");
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : " ");
                        }
                    }
                }
                System.out.println();
            }

            // print line of numbers
            for (int j = 0; j < line.size(); j++) {

                String f = line.get(j);
                if (f == null) f = "";
                int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

                // a number
                for (int k = 0; k < gap1; k++) {
                    System.out.print(" ");
                }
                System.out.print(f);
                for (int k = 0; k < gap2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();

            perpiece /= 2;
        }
    }
}
