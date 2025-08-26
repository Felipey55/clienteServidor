package clienteservidor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase para manejar las operaciones de base de datos relacionadas con Person
 * @author carpi
 */
public class DatabaseManager {
    
    /**
     * Busca una persona por código
     * @param codigo El código de la persona a buscar
     * @return Person objeto con los datos encontrados, null si no se encuentra
     */
    public static Person buscarPorCodigo(int codigo) {
        String sql = "SELECT codigo, nombre, edad FROM personas WHERE codigo = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, codigo);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Person(
                    rs.getInt("codigo"),
                    rs.getString("nombre"),
                    rs.getInt("edad")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar por código: " + e.getMessage());
        }
        
        return null;
    }
    
    /**
     * Busca una persona por nombre
     * @param nombre El nombre de la persona a buscar
     * @return Person objeto con los datos encontrados, null si no se encuentra
     */
    public static Person buscarPorNombre(String nombre) {
        String sql = "SELECT codigo, nombre, edad FROM personas WHERE nombre LIKE ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, "%" + nombre + "%");
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Person(
                    rs.getInt("codigo"),
                    rs.getString("nombre"),
                    rs.getInt("edad")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar por nombre: " + e.getMessage());
        }
        
        return null;
    }
    
    /**
     * Busca una persona por código o nombre automáticamente
     * @param criterio El criterio de búsqueda (código numérico o nombre)
     * @return Person objeto con los datos encontrados, null si no se encuentra
     */
    public static Person buscarPersona(String criterio) {
        if (criterio == null || criterio.trim().isEmpty()) {
            return null;
        }
        
        // Intentar convertir a número para buscar por código
        try {
            int codigo = Integer.parseInt(criterio.trim());
            return buscarPorCodigo(codigo);
        } catch (NumberFormatException e) {
            // Si no es un número, buscar por nombre
            return buscarPorNombre(criterio.trim());
        }
    }
}