/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entidades.Categoria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelos.Database;

/**
 *
 * @author yazmi
 */
public class DAOCategoria {
    Database db = new Database();
    //Categoria cat = new Categoria();
    
    
    public List<Categoria> ObtenerCategorias(){
    
        List<Categoria> lista = new ArrayList();
        //para conectarnos a la base de datos
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String query = "SELECT * FROM tb_categoria";
        try{
          //se conecta
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(
            db.getUrl() + db.getDatabase(),
            db.getUser(),
            db.getPass()
            );
             //Preparar el query
            pst = con.prepareStatement(query);
            //ejecutar el query
            rs = pst.executeQuery();
            while(rs.next()){
                Categoria cat = new Categoria();
                cat.setIDCategoria(rs.getInt("IDCategoria"));
                cat.setCategoria(rs.getString("Categoria"));
                
                lista.add(cat);
            }
        }
        catch(SQLException | ClassNotFoundException e){
        System.out.println("ERROR EN LOGIN: " + e.getMessage());
        
        }
        finally{
            return lista;
        }
        
    
    }
    public List<Categoria> ObtenerCategoriasMasPublicadas(){
    
        List<Categoria> lista = new ArrayList();
        //para conectarnos a la base de datos
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String query = "SELECT ROW_NUMBER() OVER (ORDER BY COUNT(pub.IDPublicacion) DESC) AS Lugar, cat.Categoria, COUNT(pub.IDPublicacion) AS Cantidad FROM tb_categoria cat INNER JOIN tb_publicacion pub ON cat.IDCategoria = pub.IDCategoria GROUP BY cat.categoria ORDER BY Cantidad DESC LIMIT 5;";
        try{
          //se conecta
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(
            db.getUrl() + db.getDatabase(),
            db.getUser(),
            db.getPass()
            );
             //Preparar el query
            pst = con.prepareStatement(query);
            //ejecutar el query
            rs = pst.executeQuery();
            while(rs.next()){
                Categoria cat = new Categoria();
                cat.setIDCategoria(rs.getInt("Lugar"));
                cat.setCategoria(rs.getString("Categoria"));
                cat.setCantidad(rs.getInt("Cantidad"));
                System.out.println(cat.getCategoria());
                lista.add(cat);
            }
        }
        catch(SQLException | ClassNotFoundException e){
        System.out.println("ERROR EN LOGIN: " + e.getMessage());
        
        }
        finally{
            return lista;
        }
        
    
    
    
    }
}
