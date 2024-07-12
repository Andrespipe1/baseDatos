import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class vPrincipal extends JFrame{
    private JButton ingresar;
    private JPanel panel1;
    private JTextField nom;
    private JTextField dire;
    private JTextField eda;
    private JTextField cod;
    private JButton ver;
    private JButton buscar;
    private JLabel registros;
    private JTextField corre;
    private JTextField nota1;
    private JTextField nota2;

    public vPrincipal(){
        super("MENU");
        setContentPane(panel1);
        ingresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    insertarDatos();
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
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buscarPorCodigo();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public void iniciar(){
        setVisible(true);
        setSize(700,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public Connection conexion() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/curso";
        String user = "root";
        String password = "";
        return  DriverManager.getConnection(url,user,password);
    }
    public void insertarDatos() throws SQLException {
        String nombre = nom.getText();
        String direccion = dire.getText();
        int edad = Integer.parseInt(eda.getText());
        String correo = corre.getText();
        double notauno = Double.parseDouble(nota1.getText());
        double notados = Double.parseDouble(nota2.getText());
        Connection connection = conexion();

        String sql = "INSERT INTO estudiantes (nombre_apellido,direccion,edad,correo,nota1,nota2) VALUES (?,?,?,?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,nombre);
        pstmt.setString(2,direccion);
        pstmt.setInt(3,edad);
        pstmt.setString(4,correo);
        pstmt.setDouble(5,notauno);
        pstmt.setDouble(6,notados);
        int rowAffect = pstmt.executeUpdate();
        if(rowAffect > 0){
            JOptionPane.showMessageDialog(null,"Registro insertado correctamente");
        }
        pstmt.close();
        connection.close();
    }

    //yo hize este el otro chat gpt lo intente :v
//    public void mostrarDatos() throws SQLException {
//        Connection connection = conexion();
//        String query = "SELECT * FROM estudiantes;";
//        Statement statement = connection.createStatement();
//        ResultSet rs = statement.executeQuery(query);
//        while (rs.next()) {
//            String nombre = rs.getString("nombre_apellido");
//            String direccion = rs.getString("direccion");
//            String edad = rs.getString("edad");
//            String correo = rs.getString("correo");
//            String nota1 = rs.getString("nota1");
//            String nota2 = rs.getString("nota2");
//            registros.setText("Nombre: "+nombre+" -Direccion: "+direccion+" -Edad: "+edad+" -Correo: "+correo+" -Nota1: "+nota1+" -Nota 2: "+nota2+"\n");
//        }
//    }
    public void mostrarDatos() throws SQLException {
        Connection connection = conexion();
        String query = "SELECT * FROM estudiantes;";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);

        StringBuilder registrosBuilder = new StringBuilder();
        while (rs.next()) {
            String nombre = rs.getString("nombre_apellido");
            String direccion = rs.getString("direccion");
            String edad = rs.getString("edad");
            String correo = rs.getString("correo");
            String nota1 = rs.getString("nota1");
            String nota2 = rs.getString("nota2");
            registrosBuilder.append("Nombre: ").append(nombre)
                    .append(" - Dirección: ").append(direccion)
                    .append(" - Edad: ").append(edad)
                    .append(" - Correo: ").append(correo)
                    .append(" - Nota1: ").append(nota1)
                    .append(" - Nota2: ").append(nota2)
                    .append("\n");
        }

        registros.setText("<html>" + registrosBuilder.toString().replace("\n", "<br>") + "</html>");
        rs.close();
        statement.close();
        connection.close();
    }
    public void buscarPorCodigo() throws SQLException {
        String codigo = cod.getText();
        Connection connection = conexion();
        String query = "SELECT * FROM estudiantes WHERE codigo_matricula = ?;";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, codigo);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            String nombre = rs.getString("nombre_apellido");
            String direccion = rs.getString("direccion");
            String edad = rs.getString("edad");
            String correo = rs.getString("correo");
            String notauno = rs.getString("nota1");
            String notados = rs.getString("nota2");

            nom.setText(nombre);
            dire.setText(direccion);
            eda.setText(edad);
            corre.setText(correo);
            nota1.setText(notauno);
            nota2.setText(notados);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró un registro con ese código");
        }

        rs.close();
        pstmt.close();
        connection.close();
    }

}
