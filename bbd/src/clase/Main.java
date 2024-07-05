package clase;
import java.sql.*;
public class Main {
    public static void main(String[] args) throws SQLException{
        String url = "jdbc:mysql://localhost:3306/esfotventa";//nombre de la base de datos
        String user = "root";
        String password = "";

//        Connection connection = DriverManager.getConnection(url,user,password);
//        System.out.println("Conectando a la base de datos");
//        String query = "select * from cliente limit 3";
//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery(query);
//
//        while (resultSet.next()){
//            System.out.println(resultSet.getString("nombre"));
//        }
//        connection.close();
//    }
//      conexion ini = new conexion();
//      ini.iniciar();
        conexion conec = new conexion();
        conec.setVisible(true);
    }
}
