package domain;

public class Premios {

    private String descricao;
    private int quantidade;
    private float qtPontosRetira;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getQtPontosRetira() {
        return qtPontosRetira;
    }

    public void setQtPontosRetira(float qtPontosRetira) {
        this.qtPontosRetira = qtPontosRetira;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Premios{" +
                "descricao='" + descricao + '\'' +
                ", quantidade=" + quantidade +
                ", qtPontosRetira=" + qtPontosRetira +
                '}';
    }
}
