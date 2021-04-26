package ConexionBase;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {

    private final String nombreBaseDatos = "basegestorcontraseñas";
    private final String usuarioBaseDatos = "Jonathan";
    private final String contraseñaBaseDatos = "Jonathan1234";
    private final String lineaBaseConexion = "jdbc:mysql://localhost:3306/"
            + nombreBaseDatos
            + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetmeCode=false&serverTimezone=UTC";

    public Connection conexionBase() {
        Connection conectar = null;
        try {
            conectar = (Connection) DriverManager.getConnection(lineaBaseConexion, usuarioBaseDatos, contraseñaBaseDatos);
            if (conectar != null) {
                System.out.println("Éxito al conectar con la base de datos. " + "Felicidades!");
            }
        } catch (SQLException error) {
            System.out.println("Error al conectar con la base de datos: " + error.getMessage());
        }
        return conectar;
    }
    public static void main(String[] args) {
        ConexionBaseDatos con = new ConexionBaseDatos();
        con.conexionBase();
    }
}
