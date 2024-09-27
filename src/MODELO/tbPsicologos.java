/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author Estudiante
 */
import VISTA.jfrmCRUD;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
public class tbPsicologos {
    
     private int id;
    private String nombre;
    private int edad;
    private double peso;
    private String correo;
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public static void Insertar(String nombre, int Edad, double Peso, String Correo){
        Connection con = ClaseConexion.getConexion();
        try{
            PreparedStatement query = con.prepareStatement("insert into tbPsicologos values(default,?,?,?,?)");
            query.setString(1, nombre);
            query.setInt(2, Edad);
            query.setDouble(3, Peso);
            query.setString(4, Correo);
            query.executeUpdate();
            
            PreparedStatement commit = con.prepareStatement("commit");
            commit.execute();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void Eliminar(JTable Tabla){
        Connection con = ClaseConexion.getConexion();
        int filaSeleccionada = Tabla.getSelectedRow();
        String id = Tabla.getValueAt(filaSeleccionada, 0).toString();
        try{
            String sql = "delete from tbPsicologos where id_psic = ?";
            PreparedStatement query = con.prepareStatement(sql);
            query.setString(1, id);
            query.executeUpdate();   
        } catch (Exception e2){
            e2.printStackTrace();
        }
    }
    
    public void Actualizar(JTable Tabla){
        Connection con = ClaseConexion.getConexion();
        int filaSeleccionada = Tabla.getSelectedRow();
        if(filaSeleccionada != -1){
            int id = Integer.parseInt(Tabla.getValueAt(filaSeleccionada, 0).toString());
            try {
                String sql = "update tbPsicologos set nombre_psic = ?, edad_psicologo = ?, peso_psicologo = ?, correo_psicologo = ? where id_psic = ?";
                PreparedStatement query = con.prepareStatement(sql);
                query.setString(1,getNombre());
                query.setInt(2, getEdad());
                query.setDouble(3, getPeso());
                query.setString(4, getCorreo());
                query.setInt(5, id);
                query.executeUpdate();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public void MostrarDatos(JTable Tabla){
        Connection con = ClaseConexion.getConexion();
        DefaultTableModel modelito = new DefaultTableModel();
        modelito.setColumnIdentifiers( new Object[] {"id_psic", "Nombre", "Edad", "Peso", "Contacto"});
        try{
            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery("Select * from tbPsicologos");
            while(rs.next()){
                modelito.addRow(new Object[] {rs.getInt("id_psic"),
                rs.getString("nombre_psic"),
                rs.getInt("edad_psicologo"),
                rs.getDouble("peso_psicologo"),
                rs.getString("correo_psicologo") 
                });
            }
            Tabla.setModel(modelito);
            Tabla.getColumnModel().getColumn(0).setMinWidth(0);
            Tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            Tabla.getColumnModel().getColumn(0).setWidth(0);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void llenarTexts(jfrmCRUD vista){
        int filaSeleccionada = vista.tablaMostrar.getSelectedRow();
        if(filaSeleccionada != -1){
            int id = Integer.parseInt(vista.tablaMostrar.getValueAt(filaSeleccionada, 0).toString());
            String nombre = vista.tablaMostrar.getValueAt(filaSeleccionada, 1).toString();
            String edad =vista.tablaMostrar.getValueAt(filaSeleccionada, 2).toString();
            String peso = vista.tablaMostrar.getValueAt(filaSeleccionada, 3).toString();
            String correo = vista.tablaMostrar.getValueAt(filaSeleccionada, 4).toString();
            
            vista.txtNombre.setText(nombre);
            vista.txtEdad.setText(edad);
            vista.txtPeso.setText(peso);
            vista.txtCorreo.setText(correo);
        }
    }
    
}
