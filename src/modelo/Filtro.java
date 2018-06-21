/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author LN710Q
 */
public class Filtro {
    private int edad;
    private String afp, nombre, apellido, profesion;
    private boolean estado;

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getAFP() {
        return afp;
    }

    public void setAFP(String afp) {
        this.afp = afp;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Filtro(){
        
    }
    // afp, nombre, apellido, edad,  profesion, estado
    public Filtro(String afp, String nombre, String apellido, int edad, String profesion, boolean estado){
        this.afp = afp; 
        this.nombre = nombre;
        this.apellido = apellido;
        this. profesion = profesion;
        this.edad = edad; 
        this.estado = estado;
    }
    //afp, nombre, apellido, profesion, estado 
    public Filtro(String afp, String nombre, String apellido, String profesion, boolean estado){
        this.afp = afp; 
        this.nombre = nombre;
        this.apellido = apellido;
        this. profesion = profesion;
        
        this.estado = estado;
    }
 
    
}
