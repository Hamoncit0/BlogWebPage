/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;



import java.util.ArrayList;
import java.util.List;
import entidades.Publicacion;
import modelos.Database;
import java.sql.*; //huele peligro :skull:
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * @author yazmi
 */
public class DAOPublicacion {
    
    Database db = new Database();
    Publicacion pub = new Publicacion();
    //Database db = new Database;
    
    public List<Publicacion> consultarRecientes(){
        List<Publicacion> lista = new ArrayList();
        
       //para conectarnos a la base de datos
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String query = "SELECT pub.*, cat.Categoria, usu.Usuario, usu.Foto FROM tb_publicacion pub JOIN tb_categoria cat ON pub.IDCategoria = cat.IDCategoria JOIN tb_usuario usu ON pub.IDUsuario = usu.IDUsuario WHERE pub.Estatus = 1 ORDER BY FechaAlta DESC LIMIT 10;";
        
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
                Publicacion pub = new Publicacion();
                pub.setIdPublicacion(rs.getInt("IDPublicacion"));
                pub.setTitulo(rs.getString("Titulo"));
                pub.setContenido(rs.getString("Contenido"));
                pub.setImagen(rs.getString("Imagen"));
                pub.setCategoria(rs.getString("Categoria"));
                pub.setIDUsuario(rs.getInt("IDUsuario"));
                pub.setIDCategoria(rs.getInt("IDCategoria"));
                pub.setFotoUsu(rs.getString("Foto"));
                //lo recibe en timestamp
                Timestamp timestamp = rs.getTimestamp("FechaAlta");
                //lo pasa a DateTime
                LocalDateTime dateTime = timestamp.toLocalDateTime();
                //le da el formato
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy h:mma", new Locale("es", "ES"));
                //lo guarda en un string
                String formattedDateTime = dateTime.format(formatter);
                pub.setFechaAlta(formattedDateTime);
                
                pub.setUsuario(rs.getString("Usuario"));
                lista.add(pub);
            }
        }
        catch(SQLException | ClassNotFoundException e){
        System.out.println("ERROR EN LOGIN: " + e.getMessage());
        
        }
        finally{
        return lista;
        }
        
        
        
        
        
    }
    
    public Boolean Publicar(Object obj){
        //obj es algun objeto que recibira el metodo y ya nada mas lo pasamos a Usuario
        pub = (Publicacion) obj;
        
        //servira para guardar el usuario loggeado y sera el objeto que regresaremos
        Publicacion log = new Publicacion(); 
        
        //para conectarnos a la base de datos
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String query = "INSERT INTO tb_publicacion(IDUsuario, IDCategoria, Titulo, Contenido, Imagen, Estatus) VALUES(?, ?, ?, ?, ?, 1);";
        
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
            pst.setInt(1, pub.getIDUsuario());
            pst.setInt(2, pub.getIDCategoria());
            pst.setString(3, pub.getTitulo());
            pst.setString(4, pub.getContenido());
            pst.setString(5, pub.getImagen());
            
            //ejecutar el query
            int rows = pst.executeUpdate();
            if(rows>0){
                
             System.out.println("Registro insertado correctamente");
            }else{
                
             System.out.println("No se pudo registrar correctamente");
            }
            //el result set guarda el asunto
            
            con.close();
            return true;
        }catch(SQLException | ClassNotFoundException e){
        System.out.println("ERROR EN LOGIN: " + e.getMessage());
        return false;
        }finally{
             
        }
        
    
    }

    public List<Publicacion> postsUsuario(int IDUsuario){
        List<Publicacion> lista = new ArrayList();
        
       //para conectarnos a la base de datos
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String query = "SELECT pub.*, cat.Categoria, usu.Usuario FROM tb_publicacion pub JOIN tb_categoria cat ON pub.IDCategoria = cat.IDCategoria JOIN tb_usuario usu ON pub.IDUsuario = usu.IDUsuario WHERE pub.Estatus = 1 AND pub.IDUsuario = ? ORDER BY FechaAlta DESC LIMIT 15;";
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
            pst.setInt(1, IDUsuario);
            //ejecutar el query
            rs = pst.executeQuery();
            while(rs.next()){
                Publicacion pub = new Publicacion();
                pub.setIdPublicacion(rs.getInt("IDPublicacion"));
                pub.setTitulo(rs.getString("Titulo"));
                pub.setContenido(rs.getString("Contenido"));
                pub.setImagen(rs.getString("Imagen"));
                pub.setCategoria(rs.getString("Categoria"));
                pub.setIDUsuario(rs.getInt("IDUsuario"));
                //lo recibe en timestamp
                Timestamp timestamp = rs.getTimestamp("FechaAlta");
                //lo pasa a DateTime
                LocalDateTime dateTime = timestamp.toLocalDateTime();
                //le da el formato
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy h:mma", new Locale("es", "ES"));
                //lo guarda en un string
                String formattedDateTime = dateTime.format(formatter);
                pub.setFechaAlta(formattedDateTime);
                
                pub.setUsuario(rs.getString("Usuario"));
                lista.add(pub);
            }
        }
        catch(SQLException | ClassNotFoundException e){
        System.out.println("ERROR EN LOGIN: " + e.getMessage());
        
        }
        finally{
        return lista;
        }
}
    
    public List<Publicacion> consultarPorIndex(String index, int cantidad){
        List<Publicacion> lista = new ArrayList();
        
       //para conectarnos a la base de datos
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String query = "SELECT pub.*, cat.Categoria, usu.Usuario, usu.Foto FROM tb_publicacion pub JOIN tb_categoria cat ON pub.IDCategoria = cat.IDCategoria JOIN tb_usuario usu ON pub.IDUsuario = usu.IDUsuario WHERE pub.Estatus = 1 ORDER BY FechaAlta DESC LIMIT "+index+", "+cantidad+";";
        
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
                Publicacion pub = new Publicacion();
                pub.setIdPublicacion(rs.getInt("IDPublicacion"));
                pub.setTitulo(rs.getString("Titulo"));
                pub.setContenido(rs.getString("Contenido"));
                pub.setImagen(rs.getString("Imagen"));
                pub.setCategoria(rs.getString("Categoria"));
                pub.setIDUsuario(rs.getInt("IDUsuario"));
                pub.setFotoUsu(rs.getString("Foto"));
                //lo recibe en timestamp
                Timestamp timestamp = rs.getTimestamp("FechaAlta");
                //lo pasa a DateTime
                LocalDateTime dateTime = timestamp.toLocalDateTime();
                //le da el formato
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy h:mma", new Locale("es", "ES"));
                //lo guarda en un string
                String formattedDateTime = dateTime.format(formatter);
                pub.setFechaAlta(formattedDateTime);
                
                pub.setUsuario(rs.getString("Usuario"));
                lista.add(pub);
            }
        }
        catch(SQLException | ClassNotFoundException e){
        System.out.println("ERROR EN LOGIN: " + e.getMessage());
        
        }
        finally{
        return lista;
        }
        
        
    }
    
    public int getTotalPublicaciones(){
        int total = 0;
    //para conectarnos a la base de datos
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String query = "SELECT COUNT(*) as Total FROM tb_publicacion  WHERE Estatus = 1 AND IDUsuario is not null AND IDCategoria is not null;";
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
                total = rs.getInt("Total");
            }
        }
        catch(SQLException | ClassNotFoundException e){
        System.out.println("ERROR EN totalPublicaciones: " + e.getMessage());
        
        }
        finally{
        return total;
        }
        
        
    
    }
    
    public Boolean ModificarPublicacion(Object obj){
        //obj es algun objeto que recibira el metodo y ya nada mas lo pasamos a Usuario
        pub = (Publicacion) obj;
        
        //servira para guardar el usuario loggeado y sera el objeto que regresaremos
        Publicacion log = new Publicacion(); 
        
        //para conectarnos a la base de datos
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String query = "UPDATE tb_publicacion SET Titulo=?, Contenido=?, Imagen=?, IDCategoria=? WHERE IDPublicacion = ?;";
        
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
            pst.setString(1, pub.getTitulo());
            pst.setString(2, pub.getContenido());
            pst.setString(3, pub.getImagen());
            pst.setString(4, pub.getCategoria());
            pst.setInt(5, pub.getIdPublicacion());
            
            //ejecutar el query
            int rows = pst.executeUpdate();
            if(rows>0){
                
             System.out.println("Registro updateo correctamente");
            }else{
                
             System.out.println("No se pudo updatear correctamente");
            }
            //el result set guarda el asunto
            
            con.close();
            return true;
        }catch(SQLException | ClassNotFoundException e){
        System.out.println("ERROR EN LOGIN: " + e.getMessage());
        return false;
        }finally{
             
        }
        
    
    }
    
    public Boolean BorrarPublicacion(Object obj){
        //obj es algun objeto que recibira el metodo y ya nada mas lo pasamos a Usuario
        pub = (Publicacion) obj;
        
       
        //para conectarnos a la base de datos
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String query = "UPDATE tb_publicacion SET Estatus=? WHERE IDPublicacion = ?;";
        
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
            pst.setInt(1, 0);
            pst.setInt(2, pub.getIdPublicacion());
            
            //ejecutar el query
            int rows = pst.executeUpdate();
            if(rows>0){
                
             System.out.println("Registro 'borrado' correctamente");
            }else{
                
             System.out.println("No se pudo 'borrar' correctamente");
            }
            //el result set guarda el asunto
            
            con.close();
            return true;
        }catch(SQLException | ClassNotFoundException e){
        System.out.println("ERROR EN LOGIN: " + e.getMessage());
        return false;
        }finally{
             
        }
        
    
    }
    
    public List<Publicacion> BusquedaAvanzada(String palabra, int categoria, String fechaInicio, String fechaFin){
    
         List<Publicacion> lista = new ArrayList();
        
       //para conectarnos a la base de datos
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String query = "SELECT pub.*, cat.Categoria, usu.Usuario, usu.Foto\n" +
"FROM tb_publicacion pub\n" +
"JOIN tb_categoria cat\n" +
"ON pub.IDCategoria = cat.IDCategoria\n" +
"JOIN tb_usuario usu\n" +
"ON usu.IDUsuario = pub.IDUsuario\n" +
"WHERE (pub.Titulo LIKE IFNULL(?,pub.Titulo) OR pub.Contenido LIKE IFNULL(?,pub.Contenido)) \n" +
"AND pub.IDCategoria LIKE IFNULL(?,pub.IDCategoria)\n" +
"AND pub.FechaAlta >= IFNULL(?,pub.FechaAlta)\n" +
"AND pub.FechaAlta <= IFNULL(?,pub.FechaAlta)\n" +
"AND ESTATUS = 1\n" +
"ORDER by pub.FechaAlta DESC LIMIT 10;";
        
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
            if("".equals(palabra))
             {
                 pst.setString(1, null);
                 pst.setString(2, null);
             }
             else
             {
             pst.setString(1,"%"+ palabra + "%");
             pst.setString(2,"%"+ palabra + "%");
             }
             if(categoria == 0)
             {
             pst.setString(3, null);
             }
             else
             {
             pst.setInt(3, categoria);
             }
             pst.setString(4, fechaInicio);
             pst.setString(5, fechaFin);
            //ejecutar el query
            rs = pst.executeQuery();
            while(rs.next()){
                Publicacion pub = new Publicacion();
                pub.setIdPublicacion(rs.getInt("IDPublicacion"));
                pub.setTitulo(rs.getString("Titulo"));
                pub.setContenido(rs.getString("Contenido"));
                pub.setImagen(rs.getString("Imagen"));
                pub.setCategoria(rs.getString("Categoria"));
                pub.setIDUsuario(rs.getInt("IDUsuario"));
                pub.setIDCategoria(rs.getInt("IDCategoria"));
                pub.setFotoUsu(rs.getString("Foto"));
                //lo recibe en timestamp
                Timestamp timestamp = rs.getTimestamp("FechaAlta");
                //lo pasa a DateTime
                LocalDateTime dateTime = timestamp.toLocalDateTime();
                //le da el formato
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy h:mma", new Locale("es", "ES"));
                //lo guarda en un string
                String formattedDateTime = dateTime.format(formatter);
                pub.setFechaAlta(formattedDateTime);
                System.out.println("TITUlo:"+pub.getTitulo());
                pub.setUsuario(rs.getString("Usuario"));
                lista.add(pub);
            }
        }
        catch(SQLException | ClassNotFoundException e){
        System.out.println("ERROR EN LOGIN: " + e.getMessage());
        
        }
        finally{
        return lista;
        }
        
    
    }
    
    public List<Publicacion> Busqueda(String palabra){
        
         List<Publicacion> lista = new ArrayList();
        
       //para conectarnos a la base de datos
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String query = "SELECT pub.*, cat.Categoria, usu.Usuario, usu.Foto FROM tb_publicacion pub JOIN tb_categoria cat ON pub.IDCategoria = cat.IDCategoria JOIN tb_usuario usu ON usu.IDUsuario = pub.IDUsuario WHERE (pub.Titulo LIKE IFNULL(?,pub.Titulo) OR pub.Contenido LIKE IFNULL(?,pub.Contenido))  AND ESTATUS = 1 ORDER by pub.FechaAlta DESC LIMIT 10;";
        
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
            if("".equals(palabra))
             {
                 pst.setString(1, null);
                 pst.setString(2, null);
             }
             else
             {
             pst.setString(1,"%"+ palabra + "%");
             pst.setString(2,"%"+ palabra + "%");
             }
             
            //ejecutar el query
            rs = pst.executeQuery();
            while(rs.next()){
                Publicacion pub = new Publicacion();
                pub.setIdPublicacion(rs.getInt("IDPublicacion"));
                pub.setTitulo(rs.getString("Titulo"));
                pub.setContenido(rs.getString("Contenido"));
                pub.setImagen(rs.getString("Imagen"));
                pub.setCategoria(rs.getString("Categoria"));
                pub.setIDUsuario(rs.getInt("IDUsuario"));
                pub.setIDCategoria(rs.getInt("IDCategoria"));
                pub.setFotoUsu(rs.getString("Foto"));
                //lo recibe en timestamp
                Timestamp timestamp = rs.getTimestamp("FechaAlta");
                //lo pasa a DateTime
                LocalDateTime dateTime = timestamp.toLocalDateTime();
                //le da el formato
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy h:mma", new Locale("es", "ES"));
                //lo guarda en un string
                String formattedDateTime = dateTime.format(formatter);
                pub.setFechaAlta(formattedDateTime);
                System.out.println("TITUlo:"+pub.getTitulo());
                pub.setUsuario(rs.getString("Usuario"));
                lista.add(pub);
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
