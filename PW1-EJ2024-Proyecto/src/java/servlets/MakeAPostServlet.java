/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import DAO.DAOPublicacion;
import entidades.Publicacion;
import entidades.Usuario;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.time.*;
import java.util.List;
/**
 *
 * @author yazmi
 */
@WebServlet(name = "MakeAPostServlet", urlPatterns = {"/MakeAPostServlet"})
@MultipartConfig
public class MakeAPostServlet extends HttpServlet {

    DAOPublicacion daoPub = new DAOPublicacion();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          //Una vez actualizada la pagina hace una consulta otra vez
        List<Publicacion> publicaciones = daoPub.consultarRecientes();
        request.setAttribute("Recientes", publicaciones);
        
        // Y ya lo muestra
        RequestDispatcher rs = request.getRequestDispatcher("home.jsp");
        rs.forward(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("Make A Post SERVLET POST");
        //hacer datos para mandar al constructor de Usuario
        String Titulo = request.getParameter("Titulo");
        String Contenido = request.getParameter("Contenido");
        String Imagen = request.getParameter("file-upload");
        String CategoriaSTR = request.getParameter("Categoria");
        int Categoria = Integer.parseInt(CategoriaSTR);
        
        //CHECAR SI HAY UNA IMAGEN
         //filename
            String fileName = null;
            Part part = request.getPart("file-upload");
            
            fileName = getFileName(part);
         //CHECAR SI HAY ALGO EN FILENAME
         if(!fileName.isEmpty()){
             //guardar imagen
            String uploadPath= "C:\\Users\\yazmi\\OneDrive\\Documents\\Facu\\6to semestre\\Programacion web Gp. 51\\PW1-EJ2024-Proyecto\\PW1-EJ2024-Proyecto\\PW1-EJ2024-Proyecto\\web\\IMGSPFP";
            File fileSaveDir = new File (uploadPath);
            if(!fileSaveDir.exists()){
                fileSaveDir.mkdirs();
            
            }
           
            
            if(fileName != ""){
                part.write(uploadPath + File.separator + fileName);
            }
         }
            
        
        Publicacion publicacion = new Publicacion();
        publicacion.setIDCategoria(Categoria);
        publicacion.setTitulo(Titulo);
        publicacion.setContenido(Contenido);
        publicacion.setImagen(fileName);
            Usuario usuario = new Usuario(); 
            usuario = (Usuario) request.getAttribute("Usuario");
            Usuario uwu = (Usuario) request.getSession().getAttribute("UserObj");
        publicacion.setIDUsuario(uwu.getIdUsuario());
        Boolean SePublico = daoPub.Publicar(publicacion);
        String pantalla = "";
            
        if(SePublico){
            //si es true si se pudo hacer este pedo :)
              response.sendRedirect(request.getContextPath() + "/MakeAPostServlet");
            }
            else{
            //Si hubo un error se viene para aca
            request.setAttribute("error", 1);
            RequestDispatcher rs = request.getRequestDispatcher("error.jsp");
            rs.forward(request, response);
            
            }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private String getFileName(Part part){ 
    String contentDisp = part.getHeader("content-disposition");   
    String[] tokens = contentDisp.split(";");
           
    for(String token : tokens){
           
        if(token.trim().startsWith("filename")){
            return token.substring(token.indexOf("=") +2, token.length()-1);
        
        }
    }
    
    return "";
    }
}
