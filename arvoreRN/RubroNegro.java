package arvoreRN;

import java.util.ArrayList;
import java.util.List;


public class RubroNegro {
    public No raiz;
    
    public void inserir(int value){
        No node = new No(value);
        inserirRN(this.raiz, node);
    }
    
    private void inserirRN(No aComparar, No aInserir){
        if(aComparar == null){
            this.raiz = aInserir;
            this.raiz.setColor(0);
        }else{
                if(aInserir.getValue() < aComparar.getValue()) {
                    if(aComparar.getEsquerda() == null){
                        aComparar.setEsquerda(aInserir);
                        aInserir.setPai(aComparar);
                        verificarBalanceamento(aInserir);
                    }else{
                        inserirRN(aComparar.getEsquerda(), aInserir);
                    }

                }else if(aInserir.getValue() > aComparar.getValue()){
                    if (aComparar.getDireita() == null) {
                        aComparar.setDireita(aInserir);
                        aInserir.setPai(aComparar);
                        verificarBalanceamento(aInserir);
                    }else{
                        inserirRN(aComparar.getDireita(), aInserir);
                    }

                }else{
                        // O nó já existe
                }
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
    
    public void imprimirArvore(){
        percorrer();
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
                    String aa = n.toString();
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
    
    
    private void verificarBalanceamento(No atual) {
        int color = atual.getColor();
        
        if(this.raiz.getColor() == 1){
            this.raiz.setColor(0);
        }
        
        if(atual.getPai().getColor() == color && atual.getPai() != this.raiz){
            if(atual.getPai().getValue() < atual.getPai().getPai().getValue()){ //Pai ta na esq do avo?
                if(atual.getPai().getPai().getDireita() != null && atual.getPai().getPai().getDireita().getColor() == 1){ //Pega o tio
                    recolorirPaiAvoTio(atual);
                }else{
                    if(atual.getValue() < atual.getPai().getValue()){ //O atual ta na esq do pai?
                        No pai = atual.getPai();
                        No avo = atual.getPai().getPai();
                        
                        if(avo.getColor() == 0){
                            avo.setColor(1);
                        }else{
                            avo.setColor(0);
                        }
                        
                        if(pai.getColor() == 0){
                            pai.setColor(1);
                        }else{
                            pai.setColor(0);
                        }
                        
                        System.out.println("Rotação simples à direita");
                        atual = rotacaoDireita(atual.getPai());
                    }else{
                        if(atual.getColor() == 0){
                            atual.setColor(1);
                        }else{
                            atual.setColor(0);
                        }
                        
                        No avo = atual.getPai().getPai();
                        if(avo.getColor() == 0){
                            avo.setColor(1);
                        }else{
                            avo.setColor(0);
                        }
                        
                        System.out.println("Rotação dupla à direita");
                        atual = duplaRotacaoEsquerdaDireita(atual);
                    }
                }
            }else{ //pai ta na direita
                if(atual.getPai().getPai().getEsquerda() != null && atual.getPai().getPai().getEsquerda().getColor() == 1){
                    recolorirPaiAvoTio(atual);
                }else{
                    if(atual.getValue() > atual.getPai().getValue()){ //O atual ta na dir do pai?
                        No pai = atual.getPai();
                        No avo = atual.getPai().getPai();
                        
                        if(avo.getColor() == 0){
                            avo.setColor(1);
                        }else{
                            avo.setColor(0);
                        }
                        
                        if(pai.getColor() == 0){
                            pai.setColor(1);
                        }else{
                            pai.setColor(0);
                        }
                        
                        System.out.println("Rotação simples à esquerda");
                        atual = rotacaoEsquerda(atual.getPai());
                    }else{
                        if(atual.getColor() == 0){
                            atual.setColor(1);
                        }else{
                            atual.setColor(0);
                        }
                        
                        No avo = atual.getPai().getPai();
                        if(avo.getColor() == 0){
                            avo.setColor(1);
                        }else{
                            avo.setColor(0);
                        }
                        
                        System.out.println("Rotação dupla à esquerda");
                        atual = duplaRotacaoDireitaEsquerda(atual);
                    }
                }
            }
        }
    }
    
    private void percorrer(){
        if(this.raiz.getEsquerda() != null){
            percorrer(this.raiz.getEsquerda());
        }else if(this.raiz.getDireita() != null){
            
        }else{
            //nada
        }
    }
    
    private void percorrer(No atual){
        if(atual.getEsquerda() != null){
            if(atual.getEsquerda().getColor() == 1 && atual.getColor() == 1){
                verificarBalanceamento(atual.getEsquerda());
                percorrer(atual.getEsquerda());
            }else{
                percorrer(atual.getEsquerda());
            }
        }else if(atual.getDireita() != null){
            if(atual.getDireita().getColor() == 1 && atual.getColor() == 1){
                verificarBalanceamento(atual.getDireita());
                percorrer(atual.getDireita());
            }else{
                percorrer(atual.getDireita());
            }
        }
    }
    
    private void recolorirPaiAvoTio(No atual){
        if(atual.getPai().getColor() == 0){
            atual.getPai().setColor(1);
        }else{
            atual.getPai().setColor(0);
        }
        
        if(atual.getPai().getPai().getColor() == 0){
            atual.getPai().getPai().setColor(1);
        }else{
            atual.getPai().getPai().setColor(0);
        }
        
        
        if(atual.getPai().getValue() < atual.getPai().getPai().getValue()){
            if(atual.getPai().getPai().getDireita().getColor() == 0){
                atual.getPai().getPai().getDireita().setColor(1);
            }else{
                atual.getPai().getPai().getDireita().setColor(0);
            }
        }else{
            if(atual.getPai().getPai().getEsquerda().getColor() == 0){
                atual.getPai().getPai().getEsquerda().setColor(1);
            }else{
                atual.getPai().getPai().getEsquerda().setColor(0);
            }
        }
        
    }
    private No rotacaoEsquerda(No atual){
        No aux = atual.getPai();
        atual.setPai(aux.getPai());
        aux.setPai(atual);
        
        if(atual.getEsquerda() != null){
            aux.setDireita(atual.getEsquerda());
            atual.setEsquerda(aux);
        }else{
            
                aux.setDireita(null);
                atual.setEsquerda(aux);
            
        }
        
        if(atual.getPai() == null){
            this.raiz = atual;
        }
        
        return atual;
    }
    
    private No rotacaoDireita(No atual){
        No aux = atual.getPai();
        atual.setPai(aux.getPai());
        aux.setPai(atual);
        
        if(atual.getDireita() != null){
            aux.setEsquerda(atual.getDireita());
            atual.setDireita(aux);
        }else{
            aux.setEsquerda(null);
            atual.setDireita(aux);
        }
        
        if(atual.getPai() == null){
            this.raiz = atual;
        }
        
        return atual;
    }

    private No duplaRotacaoEsquerdaDireita(No inicial){
        rotacaoEsquerda(inicial);
        return rotacaoDireita(inicial.getPai());
    }

    private No duplaRotacaoDireitaEsquerda(No inicial){
        rotacaoDireita(inicial);
        return rotacaoEsquerda(inicial.getPai());
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
    
}
