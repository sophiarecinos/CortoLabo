/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FiltroDao;

import Metodos.Metodos;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Filtro;

/**
 *
 * @author LN710Q
 */
public class FiltroDao implements Metodos<Filtro>{
    private static final String SQL_INSERT="INSERT INTO filtros_aceite(codFiltro,marca,stock,existencia) VALUES(?,?,?,?)";
    private static final String SQL_UPDATE="UPDATE filtros_aceite SET marca = ?, stock=?, existencia=?, WHERE codFiltro=?";
    private static final String SQL_DELETE="DELETE FROM filtros_aceite WHERE codFiltro=?";
    private static final String SQL_READ="SELECT * FROM filtros_aceite WHERE codFiltro=?";
    private static final String SQL_READALL="SELECT*FROM filtros_aceite";
    private static final Conexion con = Conexion.conectar();
    @Override
    public boolean create(Filtro g) {
        //Nos servira para preparar la consulta de INSERT 
        PreparedStatement ps;
        try{
            ps=con.getCnx().prepareStatement(SQL_INSERT);
            ps.setString(1, g.getAFP());
            ps.setString(2,g.getNombre());
            ps.setString(3,g.getApellido());
            ps.setString(4,g.getProfesion());
            ps.setInt(5, g.getEdad());
            ps.setBoolean(6,true);
            if(ps.executeUpdate()>0){
                return true;
            }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE,null,ex);
        }
        finally{
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean delete(Object key) {
        PreparedStatement ps;
        try{
             ps=con.getCnx().prepareStatement(SQL_DELETE);
             ps.setString(1, key.toString());
             if(ps.executeUpdate()>0){
                 return true;
             }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE,null,ex);
        }
        finally{
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean update(Filtro c) {
        PreparedStatement ps;
        try{
            System.out.println(c.getAFP());
            ps=con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApellido());
            ps.setString(3, c.getProfesion());
            ps.setInt(4, c.getEdad());
            ps.setBoolean(5, c.getEstado());
            ps.setString(6,c.getAFP());
            if(ps.executeUpdate()>0){
                return true;
            }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE,null,ex);
        }
        return false;
    }

    @Override
    public Filtro read(Object key) {
        PreparedStatement ps;
        Filtro f = null;
        ResultSet rs;
         try{
            ps=con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            
            rs=ps.executeQuery();
            while(rs.next()){
                f=new Filtro(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getBoolean(5));
            }
            rs.close();
         }catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE,null,ex);
        }
        return f;
    }

    @Override
    public ArrayList<Filtro> readAll() {
          ArrayList<Filtro> all = new ArrayList();
          Statement s;
          ResultSet rs;
          try{
              s=con.getCnx().prepareStatement(SQL_READALL);
              rs=s.executeQuery(SQL_READALL);
              while(rs.next()){
                  all.add(new Filtro(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getBoolean(5)));
              }
              rs.close();
          }catch(SQLException ex){
            System.out.println(ex.getMessage());
            Logger.getLogger(FiltroDao.class.getName()).log(Level.SEVERE,null,ex);        
    }
          return all;
}
    
}
