package arvoreavl;


public class No {
    private No noPai;
    private No leftNode;
    private No rightNode;
    private int balanceamento;
    private int value;
    
    public No(int value){
        setEsquerda(setDireita(setPai(null)));
        setBalanceamento(0);
        setValue(value);
    }
    
    public String toString() {
        return Integer.toString(getValue());
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getBalanceamento() {
        return balanceamento;
    }

    public void setBalanceamento(int balanceamento) {
        this.balanceamento = balanceamento;
    }

    public No getPai() {
        return this.noPai;
    }

    public No setPai(No pai) {
        this.noPai = pai;
        return pai;
    }

    public No getDireita() {
        return this.rightNode;
    }

    public No setDireita(No direita) {
        this.rightNode = direita;
        return direita;
    }

    public No getEsquerda() {
        return this.leftNode;
    }

    public void setEsquerda(No esquerda) {
        this.leftNode = esquerda;
    }
}
