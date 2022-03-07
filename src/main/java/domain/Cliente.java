package domain;

import java.util.Date;

public class Cliente extends Pessoa{

    private int codCli;
    private int codFidelidade;
    private float qtPontos;

    public Cliente(String nome, String cpf, int codCli, int codFidelidade) {
        super(nome, cpf);
        this.setCodCli(codCli);
        this.setCodFidelidade(codFidelidade);
        this.setQtPontos(0);
    }

    @Override
    public String toString() {
        return "{nome=" + this.getNome() +
                ", cpf=" + this.getCpf() +
                ", codCli=" + codCli +
                ", codFidelidade=" + codFidelidade +
                ", qtPontos=" + qtPontos +
                '}';
    }

    public int getCodCli() {
        return codCli;
    }

    public void setCodCli(int codCli) {
        this.codCli = codCli;
    }

    public int getCodFidelidade() {
        return codFidelidade;
    }

    public void setCodFidelidade(int codFidelidade) {
        this.codFidelidade = codFidelidade;
    }

    public float getQtPontos() {
        return qtPontos;
    }

    public void setQtPontos(float qtPontos) {
        this.qtPontos = qtPontos;
    }
}
