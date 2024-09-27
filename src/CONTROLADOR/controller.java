/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.tbPsicologos;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import VISTA.jfrmCRUD;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
/**
 *
 * @author Estudiante
 */
public class controller implements MouseListener{
    private jfrmCRUD vista;
    private tbPsicologos tabla;
    private int id_psic;
    public controller(jfrmCRUD vista, tbPsicologos tabla){
        this.vista = vista;
        this.tabla = tabla;
        vista.btnAgregar.addMouseListener(this);
        vista.btnDelete.addMouseListener(this);
        vista.btnUpdate.addMouseListener(this);
        tabla.MostrarDatos(vista.tablaMostrar);
        vista.tablaMostrar.addMouseListener(this);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == vista.btnAgregar){
            try{
                if(vista.txtEdad.getText().length() > 2){
                    JOptionPane.showMessageDialog(vista, "Ingrese una edad válida", "Inserción fallida", 2);
                    vista.txtEdad.setText("");
                    vista.txtEdad.requestFocus();
                } else if (vista.txtPeso.getText().length() > 5){
                    JOptionPane.showMessageDialog(vista, "Ingrese un peso real (en kg)", "Inserción fallida", 2);
                    vista.txtPeso.setText("");
                    vista.txtPeso.requestFocus();
                } else if(!vista.txtCorreo.getText().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")){
                     JOptionPane.showMessageDialog(vista, "Ingrese un correo valido", "Inserción fallida", 2);
                    vista.txtCorreo.setText("");
                    vista.txtCorreo.requestFocus();
                } else {
                    String nombre = vista.txtNombre.getText();
                    int edad = Integer.parseInt(vista.txtEdad.getText());
                double peso = Double.parseDouble(vista.txtPeso.getText());
                String correo = vista.txtCorreo.getText();
                
                tbPsicologos.Insertar(nombre, edad, peso, correo);
                tabla.MostrarDatos(vista.tablaMostrar);
                
                vista.txtNombre.setText("");
                vista.txtEdad.setText("");
                vista.txtPeso.setText("");
                vista.txtCorreo.setText("");
                vista.txtNombre.requestFocus();
                }
            }
            catch (NumberFormatException e1){
                JOptionPane.showMessageDialog(vista, "Por favor ingrese datos validos", "Inserción fallida", 2);
                vista.txtNombre.setText("");
                vista.txtEdad.setText("");
                vista.txtPeso.setText("");
                vista.txtCorreo.setText("");
                vista.txtNombre.requestFocus();
                e1.printStackTrace();
            }
        }
        if(e.getSource() == vista.tablaMostrar){
            tbPsicologos.llenarTexts(vista);
        }
        if(e.getSource() == vista.btnDelete){
            if(vista.txtNombre.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || vista.txtPeso.getText().isEmpty() || vista.txtCorreo.getText().isEmpty()){
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para eliminar", "Eliminación fallida", 2);
            } else {
                tbPsicologos.Eliminar(vista.tablaMostrar);
                tabla.MostrarDatos(vista.tablaMostrar);
                vista.txtNombre.setText("");
                vista.txtEdad.setText("");
                vista.txtPeso.setText("");
                vista.txtCorreo.setText("");
                vista.txtNombre.requestFocus();
            }
        }
        if(e.getSource() == vista.btnUpdate){
            if(vista.txtNombre.getText().isEmpty() || vista.txtEdad.getText().isEmpty() || vista.txtPeso.getText().isEmpty() || vista.txtCorreo.getText().isEmpty()){
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para actualizar", "Actualización fallida", 2);
            } else {
                try{
                    tabla.setNombre(vista.txtNombre.getText());
                    tabla.setEdad(Integer.parseInt(vista.txtEdad.getText().toString()));
                    tabla.setPeso(Double.parseDouble(vista.txtPeso.getText().toString()));
                    tabla.setCorreo(vista.txtCorreo.getText());
                    
                    tabla.Actualizar(vista.tablaMostrar);
                    tabla.MostrarDatos(vista.tablaMostrar);
                    vista.txtNombre.setText("");
                    vista.txtEdad.setText("");
                    vista.txtPeso.setText("");
                    vista.txtCorreo.setText("");
                    vista.txtNombre.requestFocus();
                } catch(Exception e3){
                    e3.printStackTrace();
                    JOptionPane.showMessageDialog(vista, "Inserte datos válidos", "Actualización fallida", 2);
                }
               
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
