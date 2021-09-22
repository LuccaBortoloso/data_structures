package arvoreavl;
public class Avl {
    public static void main(String[] args) {
        ArvoreAVL arvore = new ArvoreAVL();
        arvore.inserir(20);
        arvore.inserir(15);
        arvore.inserir(25);
        arvore.inserir(12);
        arvore.inserir(17);
        arvore.inserir(24);
        arvore.inserir(30);
        arvore.inserir(10);
        arvore.inserir(14);
        arvore.inserir(13);
        
        //20, 15, 25, 12, 17, 24, 30, 10, 14, 13
        //arvore.imprimirArvore();
        //arvore.inserir(43);
        //arvore.inserir(53);
        arvore.imprimirArvore();
    }

}
