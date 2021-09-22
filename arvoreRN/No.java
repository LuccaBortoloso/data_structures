package arvoreRN;


public class No {
    private No noPai;
    private No leftNode;
    private No rightNode;
    private int color;
    private int value;
    
    public No(int value){
        setEsquerda(setDireita(setPai(null)));
        setColor(1);
        setValue(value);
    }
    
    public String toString() {
        if(getColor() == 0){
            return Integer.toString(getValue()) + "P";
        }else{
            return Integer.toString(getValue()) + "V";
        }
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
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
