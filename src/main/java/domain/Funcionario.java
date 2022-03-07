package domain;

import Database.FuncionarioDAO;

public class Funcionario extends Pessoa{

    private int codFunc;

    public Funcionario(String nome, String cpf, int codFunc) {
        super(nome, cpf);
        this.setCodFunc(codFunc);
    }

    /*getter e setter*/
    public int getCodFunc() {
        return codFunc;
    }

    public void setCodFunc(int codFunc) {
        this.codFunc = codFunc;
    }
}
