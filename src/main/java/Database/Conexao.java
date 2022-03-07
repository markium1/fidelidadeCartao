package Database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static Connection getConection() throws SQLException, ClassNotFoundException {

        final String driver = "com.mysql.cj.jdbc.Driver";
        final String url = "jdbc:mysql://localhost:3306/projectfidelidade";
        final String user = "root";
        final String pass = "mm050@@sql";

        Class.forName(driver);
        return DriverManager.getConnection(url,user,pass);


    }
}
