package Database;

import domain.Cliente;
import domain.Premios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class FuncionarioDAO {

    public static Cliente buscarCliente(int id){

        Cliente cliente = null;
        String sql = "SELECT * FROM CLIENTES";

        try{

            Connection conn = Conexao.getConection();
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while(rs.next()){

                if(rs.getInt("codcli") == id){
                    cliente = new Cliente(rs.getString("nome"),rs.getString("cpf"),
                                            rs.getInt("codcli"),rs.getInt("codfidelidade"));

                    cliente.setQtPontos(rs.getFloat("qtpontos"));
                    break;
                }
            }

            pst.close();
            conn.close();


        }catch (Exception e){
            e.printStackTrace();
        }

        return cliente;
    }

    public static Cliente buscarCliente(String cpf){

        Cliente cliente = null;
        String sql = "SELECT * FROM CLIENTES";

        try{

            Connection conn = Conexao.getConection();
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while(rs.next()){

                if(rs.getString("cpf").equals(cpf)){
                    cliente = new Cliente(rs.getString("nome"),rs.getString("cpf"),
                            rs.getInt("codcli"),rs.getInt("codfidelidade"));

                    cliente.setQtPontos(rs.getFloat("qtpontos"));
                    break;
                }
            }

            pst.close();
            conn.close();


        }catch (Exception e){
            e.printStackTrace();
        }

        return cliente;
    }

    public static void atualizarCliente(Cliente c){

        String sql2 = "UPDATE CLIENTES SET qtpontos = ? WHERE CODCLI = ?";

        try{

            Connection conn = Conexao.getConection();
            PreparedStatement pst2 = conn.prepareStatement(sql2);

            pst2.setFloat(1,c.getQtPontos());
            pst2.setInt(2,c.getCodCli());

            pst2.executeUpdate();
            pst2.close();
            conn.close();


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static List<Cliente> listaClientes(){

        List<Cliente> clienteList = new ArrayList<>();
        String sql = "SELECT * FROM CLIENTES";
        try{

            Connection conn = Conexao.getConection();
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while(rs.next()){

                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                int codCli = rs.getInt("codcli");
                int codFidelidade = rs.getInt("codfidelidade");
                float qtPontos = rs.getFloat("qtpontos");

                Cliente c = new Cliente(nome,cpf,codCli,codFidelidade);
                c.setQtPontos(qtPontos);

                clienteList.add(c);
            }

            pst.close();
            conn.close();


        }catch (Exception e){
            e.printStackTrace();
        }

        return clienteList;
    }

    public static void salvarCliente(Cliente c){

        String sql = "INSERT INTO CLIENTES VALUES(?,?,?,?,?)";

        try{

            Connection conn = Conexao.getConection();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1,c.getCodCli());
            pst.setString(2,c.getNome());
            pst.setString(3,c.getCpf());
            pst.setInt(4,c.getCodFidelidade());
            pst.setFloat(5,c.getQtPontos());


            pst.execute();


            pst.close();
            conn.close();


        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void addPontos(Cliente c, float qtPontos){
        String sql = "UPDATE clientes SET qtPontos = ? WHERE codCli = ?";

        try{

            Connection conn = Conexao.getConection();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setFloat(1,c.getQtPontos() + qtPontos);
            pst.setInt(2,c.getCodCli());

            pst.execute();

            System.out.println("pontos adicionados :)");
            pst.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void cadastrarPremio(String n, float v,int qt,int tam){

        String sql = "INSERT INTO premio VALUES(?,?,?,?)";
        try{
            Connection conn = Conexao.getConection();
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1,tam);
            pst.setString(2,n);
            pst.setFloat(3,v);
            pst.setInt(4,qt);
            pst.execute();
            System.out.println("Premio Cadastrado com Sucesso");

        }catch (Exception e){

        }
    }

    public static Premios retornaPremios(int idPremio){

        Premios p = new Premios();
        String sql = "SELECT * FROM PREMIO";

        try{

            Connection conn = Conexao.getConection();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                if(rs.getInt("id") == idPremio){

                    p.setQtPontosRetira(rs.getFloat("valor"));
                    p.setDescricao(rs.getString("descricao"));
                    p.setQuantidade(rs.getInt("quantidade"));
                }

            }

            pst.close();
            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return p;
    }

    public static List<Premios> listaPremios(){

        List<Premios> premiosList = new ArrayList<>();
        String sql = "SELECT * FROM PREMIO";
        try{

            Connection conn = Conexao.getConection();
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            while(rs.next()){

                String nome = rs.getString("descricao");
                float valor = rs.getFloat("valor");
                int qt = rs.getInt("quantidade");

                Premios p = new Premios();
                p.setDescricao(nome);
                p.setQuantidade(qt);
                p.setQtPontosRetira(valor);

                premiosList.add(p);
            }

            pst.close();
            conn.close();


        }catch (Exception e){
            e.printStackTrace();
        }

        return premiosList;
    }

}
