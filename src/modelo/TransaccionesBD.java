package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class TransaccionesBD extends Conexion {

    PreparedStatement ps;
    ResultSet rs;
    int codigo = -1;
    int[] datos = null;
    int i = 0, n = 0;
    int nroCodigo = 0;

    public boolean buscar(Estudiante estudiantes) throws SQLException {
        try {
            Connection conexion = getConnection();
            ps = conexion.prepareStatement("select * from estudiantes where codigoE=?");
            ps.setInt(1, estudiantes.getCodigo());
            rs = ps.executeQuery();
            if (rs.next()) {
                estudiantes.setNombre(rs.getString("nombreE"));
                estudiantes.setGenero(rs.getString("sexo"));
                estudiantes.setMateria(rs.getString("materia"));
                estudiantes.setNota(rs.getDouble("nota"));
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.err.println("Error revisar " + ex);
            return false;
        }
    }

    public boolean insertar(Estudiante estudiantes) {
        try {
            Connection conexion = getConnection();
            ps = conexion.prepareStatement("insert into estudiantes (codigoE,nombreE,nota,sexo,materia) values(?,?,?,?,?)");
            ps.setInt(1, estudiantes.getCodigo());
            ps.setString(2, estudiantes.getNombre());
            ps.setDouble(3, estudiantes.getNota());
            ps.setString(4, estudiantes.getGenero());
            ps.setString(5, estudiantes.getMateria());
            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.err.println("Error revisar " + ex);
            return false;
        }

    }

    public boolean modificar(Estudiante estudiantes) throws SQLException {
        try {
            Connection conexion = getConnection();
            ps = conexion.prepareStatement("update estudiantes set nombreE=?,nota=?,sexo=?,materia=? where codigoE=?");
            ps.setString(1, estudiantes.getNombre());
            ps.setDouble(2, estudiantes.getNota());
            ps.setString(3, estudiantes.getGenero());
            ps.setString(4, estudiantes.getMateria());
            ps.setInt(5, estudiantes.getCodigo());
            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                return true;

            } else {
                return false;
            }
        } catch (Exception ex) {
            System.err.println("Error revisar " + ex);
            return false;
        }

    }

    public boolean eliminar(Estudiante estudiantes) throws SQLException {
        try {
            Connection conexion = getConnection();
            ps = conexion.prepareStatement("delete from estudiantes where codigoE=?");
            ps.setInt(1, estudiantes.getCodigo());
            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.err.println("Error revisar " + ex);
            return false;
        }
    }

    public boolean siguiente(Estudiante estudiantes) {
        try {
            int i = 0, n = 0, aux;
            Connection conexion = getConnection();
            ps = conexion.prepareStatement("select * from estudiantes ");
            rs = ps.executeQuery();
            while (rs.next()) {
                n++;
            }
            datos = new int[n];
            ps = conexion.prepareStatement("select * from estudiantes ");
            rs = ps.executeQuery();
            while (rs.next()) {
                datos[i] = rs.getInt("codigoE");
                i++;
            }
            aux = n - 1;

            if (codigo >= aux) {
                codigo = aux - 1;
            }
            codigo++;
            ps = conexion.prepareStatement("select * from estudiantes where codigoE=?");
            ps.setInt(1, Integer.parseInt(String.valueOf(datos[codigo])));
            rs = ps.executeQuery();
            if (rs.next()) {
                estudiantes.setNombre(rs.getString("nombreE"));
                estudiantes.setGenero(rs.getString("sexo"));
                estudiantes.setMateria(rs.getString("materia"));
                estudiantes.setNota(rs.getDouble("nota"));
                estudiantes.setCodigo(datos[codigo]);
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.err.println("Error revisar " + ex);
            return false;
        }

    }

    public boolean anterior(Estudiante estudiantes) {
        try {
            int i = 0, n = 0;
            Connection conexion = getConnection();
            ps = conexion.prepareStatement("select * from estudiantes ");
            rs = ps.executeQuery();
            while (rs.next()) {
                n++;
            }
            datos = new int[n];
            ps = conexion.prepareStatement("select * from estudiantes ");
            rs = ps.executeQuery();
            while (rs.next()) {
                datos[i] = rs.getInt("codigoE");
                i++;
            }
            if (codigo <= 0) {
                codigo = 1;
            }
            codigo--;

            ps = conexion.prepareStatement("select * from estudiantes where codigoE=?");
            ps.setInt(1, Integer.parseInt(String.valueOf(datos[codigo])));
            rs = ps.executeQuery();
            if (rs.next()) {
                estudiantes.setNombre(rs.getString("nombreE"));
                estudiantes.setGenero(rs.getString("sexo"));
                estudiantes.setMateria(rs.getString("materia"));
                estudiantes.setNota(rs.getDouble("nota"));
                estudiantes.setCodigo(datos[codigo]);
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.err.println("Error revisar " + ex);
            return false;
        }
    }
}
