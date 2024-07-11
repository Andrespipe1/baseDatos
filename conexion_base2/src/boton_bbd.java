import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class boton_bbd extends JFrame{
    private JButton bbd;
    private JPanel panel1;
    private JTextField nom;
    private JTextField eda;
    private JTextField nota1;
    private JTextField nota2;
    private JButton ver;
    private JLabel datos;

    public boton_bbd() {
        super("Ventana de BBD");
        setContentPane(panel1);
        bbd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    insertar();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        ver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mostrarDatos();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public Connection conexion() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/esfotventa";
        String user = "root";
        String password = "";
        return DriverManager.getConnection(url, user, password);
    }
    public void insertar() throws SQLException{

            String nombre = nom.getText();
                String edad = eda.getText();
                double not1 = Double.parseDouble(nota1.getText());
                double not2 = Double.parseDouble(nota2.getText());
                String sql = "INSERT INTO estudiantes (nombre,edad,nota1,nota2) VALUES (?,?,?,?)";
                Connection connection = conexion();
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1,nombre);
                pstmt.setInt(2,Integer.parseInt(edad));
                pstmt.setDouble(3,not1);
                pstmt.setDouble(4,not2);

                int rowAffect = pstmt.executeUpdate();
                if(rowAffect > 0){
                    JOptionPane.showMessageDialog(null,"Registro insertado correctamente");
                }
                pstmt.close();
                connection.close();
    }
    public void mostrarDatos() throws SQLException{
        Connection connection = conexion();
        String nombre = nom.getText();
        String sql = "select * from estudiantes where nombre=?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,nombre);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()){
            String edad = rs.getString("edad");
            JOptionPane.showMessageDialog(null,"Nombre: "+nombre+"Edad: "+edad);
        }
        rs.close();
        pstmt.close();

//        String query = "SELECT * FROM estudiantes;";
//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery(query);
//        StringBuilder datoss = new StringBuilder("<html>");
//
//        while (resultSet.next()) {
//            datoss.append("<b>Nombre:</b> ").append(resultSet.getString("nombre")).append("<br>");
//            datoss.append("<b>Edad:</b> ").append(resultSet.getString("edad")).append("<br>");
//            datoss.append("<b>Nota 1:</b> ").append(resultSet.getString("nota1")).append("<br>");
//            datoss.append("<b>Nota 2:</b> ").append(resultSet.getString("nota2")).append("<br><br><br>");
//
//        }
//        datoss.append("</html>");
//        datos.setText(datoss.toString());

    }

    public void iniciar(){
        setVisible(true);
        setSize(450,350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
