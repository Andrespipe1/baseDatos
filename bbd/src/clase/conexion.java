package clase;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class conexion extends JFrame {
    private JButton button1;
    private JPanel panel1;
    private JLabel verdatos;

    public conexion() {
        super("Ventana de login");
        setSize(400,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/esfotventa";
                String user = "root";
                String password = "";
                try (Connection connection = DriverManager.getConnection(url, user, password)) {
                    System.out.println("Conectado a la base de datos");
                    String query = "SELECT * FROM cliente";
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);
                    StringBuilder datos = new StringBuilder("<html>");

                    while (resultSet.next()) {
                        datos.append("<b>Nombre:</b> ").append(resultSet.getString("nombre")).append("<br>");
                        datos.append("<b>Correo:</b> ").append(resultSet.getString("correo")).append("<br>");
                        datos.append("<b>Password:</b> ").append(resultSet.getString("password")).append("<br><br>");
                    }
                    datos.append("</html>");
                    verdatos.setText(datos.toString());
                } catch (SQLException ex) {
                    verdatos.setText("Error: " + ex.getMessage());
                }
            }
        });
    }
//    public void iniciar(){
//        setVisible(true);
//        setSize(600,500);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }
}
