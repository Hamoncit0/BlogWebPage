
package DAO;

import entidades.Usuario;
import modelos.Database;
import java.sql.*; //huele peligro :skull:

public class DAOUsuario {
    Database db = new Database();
    Usuario usu = new Usuario();
    
    
    public Object login(Object obj){
        //obj es algun objeto que recibira el metodo y ya nada mas lo pasamos a Usuario
        usu = (Usuario) obj;
        
        //servira para guardar el usuario loggeado y sera el objeto que regresaremos
        Usuario log = new Usuario(); 
        
        //para conectarnos a la base de datos
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String query = "SELECT * FROM tb_usuario WHERE Usuario = ? AND Pass = ?;";
        
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
            pst.setString(1, usu.getUsuario());
            pst.setString(2, usu.getPass());
            //ejecutar el query
            rs = pst.executeQuery();
            //el result set guarda el asunto
            while(rs.next()){
                log.setIdUsuario(rs.getInt("Idusuario"));
                log.setUsuario(rs.getString("Usuario"));
                log.setPass(rs.getString("Contraseña"));
                
            }
            con.close();
            
        }catch(SQLException | ClassNotFoundException e){
        System.out.println("ERROR EN LOGIN: " + e.getMessage());
        
        }finally{
            return log;
        }
        
        
    }
    
    
    public boolean signUp(Object obj){
         usu = (Usuario) obj;
        Usuario log = new Usuario();
        //para conectarnos a la base de datos
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String query = "INSERT INTO tb_usuario(Correo, Usuario, Pass, FechaNac, Edad, Nombre, SNombre, ApPaterno, ApMaterno, FechaAlta) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            pst.setString(1, usu.getCorreo());
            pst.setString(2, usu.getUsuario());
            pst.setString(3, usu.getPass());
            pst.setDate(4, Date.valueOf(usu.getFechaNac()));
            pst.setInt(5, usu.getEdad());
            pst.setString(6, usu.getNombre());
            pst.setString(7, usu.getSNombre());
            pst.setString(8, usu.getApPaterno());
            pst.setString(9, usu.getApMaterno());
            pst.setTimestamp(10, Timestamp.valueOf(usu.getFechaAlta()));
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
    
}
