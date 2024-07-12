import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class login extends JFrame{
    private JPanel panel_acceso;
    private JButton accederButton;
    private JPasswordField password;
    private JTextField usuario;

    public login(){
        super("Ventana de login");
        setContentPane(panel_acceso);

        accederButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                String user ="";
//                String contra= "";
//                String userIngresado = usuario.getText();
//                char[] contraIngresadaChars = password.getPassword();
//                String contraIngresada = new String(contraIngresadaChars);
//                if (contra.equals(contraIngresada)&& user.equals(userIngresado))
//                {
//                    vPrincipal vmenu = new vPrincipal();
//                    vmenu.iniciar();
//                    dispose();
//                }
//                else
//                {
//                    JOptionPane.showMessageDialog(null,"El usuario o  contraseña es incorrecta");
//                    usuario.setText("");
//                    password.setText("");
//                }
                try {
                    verificarDatos();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public void verificarDatos() throws SQLException {

        String usering = usuario.getText();
        String pass = new String(password.getPassword());
        String url = "jdbc:mysql://localhost:3306/curso";
        String user = "root";
        String password = "";
        Connection connection = DriverManager.getConnection(url, user, password);
        String sql = "SELECT * FROM usuarioadministrador WHERE usuario = ? AND contrasena = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, usering);
        pstmt.setString(2, pass);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            JOptionPane.showMessageDialog(null,"La información ingresada es correcta.");
            vPrincipal vmenu = new vPrincipal();
            vmenu.iniciar();
            dispose();
        } else {
            JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrectos.");
        }
        rs.close();
        pstmt.close();
        connection.close();
    }
    public void iniciar(){
        setVisible(true);
        setSize(550,550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
