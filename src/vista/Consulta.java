/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import FiltroDao.FiltroDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Filtro;
// afp, nombre, apellido, edad, profesion combobox, estado*
/**
 *
 * @author LN710Q
 */
public class Consulta extends JFrame{
    public JLabel lblAFP, lblNombre, lblApellido, lblEdad, lblProfesion, lblEstado;
    
    public JTextField afp, nombre, apellido, edad;
    public JComboBox profesion;
    
    ButtonGroup estado = new ButtonGroup();
    public JRadioButton no;
    public JRadioButton si;
    public JTable resultados;
    
    public JPanel table;
    
    public JButton buscar, insertar, actualizar, eliminar, limpiar;
    
    private static final int ANCHOC = 130, ALTOC = 30;
    
    DefaultTableModel tm; 
    
    public Consulta(){
        super("Inscripciones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
        container.add(lblAFP);
        container.add(lblNombre);
        container.add(lblApellido);
        container.add(lblEdad);
        container.add(lblProfesion);
        container.add(lblEstado);
        container.add(afp);
        container.add(nombre);
        container.add(apellido);
        container.add(edad);
        container.add(profesion);
        container.add(si);
        container.add(no);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(limpiar);
        container.add(table);
        setSize(600, 600);
        eventos();
    }
    
    public final void agregarLabels(){
        lblAFP = new JLabel("N° AFP");
        lblNombre = new JLabel("Nombre: ");
        lblApellido = new JLabel("Apellido: ");
        lblEdad = new JLabel("Edad: ");
        lblProfesion = new JLabel("Profesion: ");
        lblEstado = new JLabel("Estado: ");
        lblAFP.setBounds(10, 10, ANCHOC, ALTOC);
        lblNombre.setBounds(10, 60, ANCHOC, ALTOC);
        lblApellido.setBounds(60, 60, ANCHOC, ALTOC);
        lblEdad.setBounds(10, 100, ANCHOC, ALTOC);
        lblProfesion.setBounds(10, 140, ANCHOC, ALTOC);
        lblEstado.setBounds(10, 180, ANCHOC, ALTOC);
    }
    
    public final void formulario(){
        afp = new JTextField();
        profesion = new JComboBox();
        nombre = new JTextField();
        apellido = new JTextField();
        edad = new JTextField();
        si = new JRadioButton("si", true);
        no= new JRadioButton("no");
        resultados = new JTable();
        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");
        limpiar = new JButton("Limpiar");
        
        table = new JPanel();
        profesion.addItem("Ingeniero");
        profesion.addItem("Mecanico");
        profesion.addItem("Profesor");
        profesion.addItem("Arquitecto");

        estado = new ButtonGroup();
        estado.add(si);
        estado.add(no);

        afp.setBounds(140, 10, ANCHOC, ALTOC);
        nombre.setBounds(30, 10, ANCHOC, ALTOC);
        apellido.setBounds(90, 10, ANCHOC, ALTOC);
        edad.setBounds(30, 100, ANCHOC, ALTOC);
        profesion.setBounds(60, 140, ANCHOC, ALTOC);
        si.setBounds(140, 140, 50, ALTOC);
        no.setBounds(210, 140, 50, ALTOC);

        buscar.setBounds(300, 10, ANCHOC, ALTOC);
        insertar.setBounds(10, 210, ANCHOC, ALTOC);
        actualizar.setBounds(150, 210, ANCHOC, ALTOC);
        eliminar.setBounds(300, 210, ANCHOC, ALTOC);
        limpiar.setBounds(450, 210, ANCHOC, ALTOC);
        resultados = new JTable();
        
        resultados = new JTable() {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; 
            }
        };
        table.setBounds(10, 250, 500, 200);
        table.add(new JScrollPane(resultados));
    }
    
    public void llenarTabla() {
        tm = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };

        tm.addColumn("N° AFP");
        tm.addColumn("Nombre");
        tm.addColumn("Apellidos");
        tm.addColumn("Profesion");
        tm.addColumn("Estado");

        FiltroDao fd = new FiltroDao();
        ArrayList<Filtro> filtros = fd.readAll();

        for (Filtro fi : filtros) {
            tm.addRow(new Object[]{fi.getAFP(), fi.getNombre(), fi.getApellido(), fi.getProfesion(), fi.getEstado()});
        }

        resultados.setModel(tm);
    }
    public void eventos() {
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = new Filtro(afp.getText(), nombre.getText(), apellido.getText(), Integer.parseInt(edad.getText()), profesion.getSelectedItem().toString(), true);

                if (no.isSelected()) {
                    f.setEstado(false);
                }

                if (fd.create(f)) {
                    JOptionPane.showMessageDialog(null, "Filtro registrado");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Problema al querer crear el filtro");
                }
            }
             // ********************************
        });

        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = new Filtro(afp.getText(), nombre.getText(), apellido.getText(), Integer.parseInt(edad.getText()), profesion.getSelectedItem().toString(), true);

                if (no.isSelected()) {
                    f.setEstado(false);
                }

                if (fd.update(f)) {
                    JOptionPane.showMessageDialog(null, "Filtro Modificado");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Problemas al querer modificar el filtro");

                }
            }
        });

        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                if (fd.delete(afp.getText())) {
                    JOptionPane.showMessageDialog(null, "Filtro Eliminado");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Problema al querer eliminar el filtro");
                }
            }
        });

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = fd.read(afp.getText());
                if (f == null) {
                    JOptionPane.showMessageDialog(null, "Filtro no encontrado");
                } else {
                    afp.setText(f.getAFP());
                    nombre.setText(f.getNombre());
                    apellido.setText(f.getApellido());
                    profesion.setSelectedItem(f.getProfesion());
                    edad.setText(Integer.toString(f.getEdad()));

                    if (f.getEstado()) {
                        si.setSelected(true);
                    } else {
                        no.setSelected(true);
                    }
                }
            }
        });

        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        resultados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evnt) {
                if (evnt.getClickCount() == 1) {
                    afp.setText(resultados.getValueAt(resultados.getSelectedRow(), 0).toString());
                    nombre.setText(resultados.getValueAt(resultados.getSelectedRow(), 1).toString());
                    apellido.setText(resultados.getValueAt(resultados.getSelectedRow(), 2).toString());
                    profesion.setSelectedItem(resultados.getValueAt(resultados.getSelectedRow(), 3).toString());
                    //estado.setSelected(resultados.getValueAt(resultados.getSelectedRow(), 4).toString(), true);
                    if ("false".equals(resultados.getValueAt(resultados.getSelectedRow(), 4).toString())) {
                        no.setSelected(true);
                    } else {
                        si.setSelected(true);
                    }
                }
            }
        });
}
     public void limpiarCampos() {
        afp.setText("");
        nombre.setText("");
        apellido.setText("");
        edad.setText("");
        profesion.setSelectedItem("Ingeniero");
    }


    
    
    
    
    
    
}
