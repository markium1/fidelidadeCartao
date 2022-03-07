import Database.FuncionarioDAO;
import domain.Cliente;
import domain.Funcionario;
import domain.Premios;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<Cliente> clientes = atualizar();
        List<Premios> premios = FuncionarioDAO.listaPremios();
        Scanner s = new Scanner(System.in);
        int op;
        do {
            menu();
            clientes = atualizar();
            premios = FuncionarioDAO.listaPremios();
            try{
                op = Integer.parseInt(s.nextLine());

                switch (op){
                    case 1:
                        cadastrarCliente(clientes.size());
                        break;
                    case 2:
                        pontos();
                        break;
                    case 3:
                        retirarPremios();
                        break;
                    case 4:
                        cadastrarPremio(premios.size());
                        break;
                    case 5:
                        procurarCliente();
                        break;
                    default:
                        System.out.println("opcao invalida");
                }

            }catch (Exception e){
                System.out.println("Digite uma opçao valida");
                continue;
            }

        }while (true);


        //cadastrarCliente(clientes.size());
        //FuncionarioDAO.addPontos();
        //retirarPremios();
        //FuncionarioDAO.cadastrarPremio("boneca",15,3);
        //FuncionarioDAO.buscarCliente();
        //Cliente c = FuncionarioDAO.buscarCliente(0);



    }


    public static void menu(){
        System.out.println("*******MENU*******");
        System.out.println("[1] CADASTRAR CLIENTE");
        System.out.println("[2] INSERIR PONTOS");
        System.out.println("[3] RETIRAR PREMIOS");
        System.out.println("[4] CADASTRAR PREMIOS");
        System.out.println("[5] PROCURAR CLIENTE");
    }

    public static Cliente cadastrarCliente(int t){

        Scanner s = new Scanner(System.in);
        String nome,cpf;

        System.out.println("Insira o nome do Cliente:");
        nome = s.nextLine();
        System.out.println("Insira o CPF:");
        cpf = s.nextLine();
        Cliente c = new Cliente(nome,cpf,t+1,t+1);
        FuncionarioDAO.salvarCliente(c);
        return c;
    }

    public static void procurarCliente(){
        int codCli;
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Insira o Codigo do cliente:");
            codCli = Integer.parseInt(scanner.nextLine());
            Cliente c = FuncionarioDAO.buscarCliente(codCli);

            if(c != null){
                System.out.println(c);
            }else{
                System.out.println("Cliente não encontrado");
            }

        }catch (Exception e){
            System.out.println("Codigo invalido");
            return;

        }


    }

    public static void cadastrarPremio(int t){

        Scanner scan = new Scanner(System.in);
        String nome;
        int qt;
        float valor;
        System.out.print("Insira o nome do Cliente:");
        nome = scan.nextLine();
        System.out.print("Insira o CPF:");
        valor = Float.parseFloat(scan.nextLine());
        System.out.print("Insira a quantidade:");
        qt = Integer.parseInt(scan.nextLine());
        FuncionarioDAO.cadastrarPremio(nome,valor,qt,t+1);

    }

    public static void pontos(){
        Scanner scan = new Scanner(System.in);
        int cod;
        float pont;

        System.out.println("Insira o codigo do cliente:");
        try {
            cod = Integer.parseInt(scan.nextLine());
        }catch (Exception e){
            System.out.println("Codigo invalido!");
            return;
        }
        Cliente c = FuncionarioDAO.buscarCliente(cod);

        if(c != null) {
            try {
                System.out.println("Insira o Quantidade de Pontos:");
                pont = Float.parseFloat(scan.nextLine());
            } catch (Exception e) {
                System.out.println("Quantidade inserida invalida!");
                return;
            }
        }
        else{
            System.out.println("Cliente não encontrado!");
            return;
        }
        FuncionarioDAO.addPontos(c,pont);

    }

    public static void retirarPremios(){
        Scanner s = new Scanner(System.in);
        int codCli = 0;
        System.out.println("insira o codigo do cliente:");

        try{
            codCli  = Integer.parseInt(s.nextLine());
        }catch (Exception e){
            System.out.println("Codigo invalido");
            return;
        }

        Cliente c = FuncionarioDAO.buscarCliente(codCli);
        if(c != null){

            int codPre;
            try{
                System.out.println("Insira o codigo do premio:");
                codPre = Integer.parseInt(s.nextLine());
            }catch (Exception e){
                System.out.println("Codigo invalido");
                return;
            }

            Premios prem = FuncionarioDAO.retornaPremios(codPre);
            if(prem != null){
                if(c.getQtPontos() > prem.getQtPontosRetira()){
                    System.out.println("Premio retirado com sucesso");
                    c.setQtPontos(c.getQtPontos()-prem.getQtPontosRetira());
                    prem.setQuantidade(prem.getQuantidade()-1);
                    FuncionarioDAO.atualizarCliente(c);
                    //atualizar no banco (fazer função pra atualizar apenas 1 cliente e o premio)
                }else{
                    System.out.println("Quantidade de pontos inferior!");
                    return;
                }
            }
            return;
        }else{
            System.out.println("Cliente não encontrado");
        }
        return;

    }

    public static List<Cliente> atualizar(){
        List<Cliente> c = FuncionarioDAO.listaClientes();
        return c;
    }

}
