/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import DAO.DAOPublicacion;
import entidades.Publicacion;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;

/**
 *
 * @author yazmi
 */
@WebServlet(name = "ChangePostServlet", urlPatterns = {"/ChangePostServlet"})
@MultipartConfig
public class ChangePostServlet extends HttpServlet {

    DAOPublicacion daoPub = new DAOPublicacion();

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("LLEGO AL CHANGE POST SERVLET METHOD POST");
        
        String Titulo = request.getParameter("ModalTitulo");
        String Contenido = request.getParameter("ModalContenido");
        String Categoria = request.getParameter("ModalCategoria");
        String IDPub = request.getParameter("ModalID");
        int id = Integer.parseInt(IDPub);
        //CHECAR SI HAY UNA IMAGEN
        //filename
        String fileName = null;
        Part part = request.getPart("Modalfile-upload");
        fileName = getFileName(part);
        System.out.println("despues del fileName" + fileName);
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
        Publicacion pub = new Publicacion();
        pub.setTitulo(Titulo);
        pub.setContenido(Contenido);
        pub.setImagen(fileName);
        pub.setCategoria(Categoria);
        pub.setIdPublicacion(id);
        Boolean SeUpdateo = daoPub.ModificarPublicacion(pub);
        if(SeUpdateo){
             response.sendRedirect(request.getContextPath() + "/GetPostsServlet");
        }else{
         //Si hubo un error se viene para aca
            request.setAttribute("error", 1);
            RequestDispatcher rs = request.getRequestDispatcher("error.jsp");
            rs.forward(request, response);
            
        
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

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
