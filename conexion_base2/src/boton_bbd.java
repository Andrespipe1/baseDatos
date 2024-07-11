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

    public boton_bbd() {
        super("Ventana de BBD");
        setContentPane(panel1);
        bbd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    conexion();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    insertar();
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

    public void iniciar(){
        setVisible(true);
        setSize(450,350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
