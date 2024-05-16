/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import DAO.DAOUsuario;
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
import java.time.LocalDate;


/**
 *
 * @author yazmi
 */
@WebServlet(name = "ChangeUserDataServlet", urlPatterns = {"/ChangeUserDataServlet"})
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024, // 1 MB
    maxFileSize = 1024 * 1024 * 5,   // 5 MB
    maxRequestSize = 1024 * 1024 * 10 // 10 MB
)
public class ChangeUserDataServlet extends HttpServlet {
    DAOUsuario daoUsu = new DAOUsuario();

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("NO SE QUE HACES EN EL GET WTF");
        RequestDispatcher rs = request.getRequestDispatcher("profile.jsp");
        rs.forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Llego al change user data servlet");
       //hacer datos para mandar al constructor de Usuario
            String firstName = request.getParameter("firstNameCU");
            String secondName = request.getParameter("secondNameCU");
            String lastName = request.getParameter("lastNameCU");
            String lastName2 = request.getParameter("lastName2CU");
            String username = request.getParameter("usernameCU");
            LocalDate birthday = LocalDate.parse(request.getParameter("birthdayCU"));
            String email = request.getParameter("emailCU");
            //String password = request.getParameter("password");
            String foto = request.getParameter("file-uploadCU");
             
            //CHECAR SI HAY UNA IMAGEN
         //filename
            String fileName = null;
            Part part = request.getPart("file-uploadCU");
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

            Usuario uwu = (Usuario) request.getSession().getAttribute("UserObj");
        System.out.println("Llegodespues del fileName");
            Usuario usu = new Usuario();
            usu.setNombre(firstName);
            usu.setSNombre(secondName); 
            usu.setApPaterno(lastName);
            usu.setApMaterno(lastName2);
            usu.setUsuario(username);
            usu.setFechaNac(birthday);
            usu.setCorreo(email);
            if(fileName!=""){
             usu.setFoto(fileName);
            }else{
                usu.setFoto(uwu.getFoto());
                
            }
            
             
            
            
            usu.setIdUsuario(uwu.getIdUsuario());
            usu.setPass(uwu.getPass());
            Usuario form = (Usuario) daoUsu.updateUser(usu);
            
        System.out.println("Sobrevivio el update");
            if(form.getIdUsuario()!=0){
             //si es true si se pudo hacer este pedo :)
             HttpSession MiSesion = request.getSession();
             MiSesion.setAttribute("UserObj", form);
             request.setAttribute("Usuario", form);
              response.sendRedirect(request.getContextPath() + "/GetProfilePostsServlet");
              
            }
            else{
            //Si hubo un error se viene para aca
            request.setAttribute("error", 1);
            RequestDispatcher rs = request.getRequestDispatcher("error.jsp");
            rs.forward(request, response);
            
            }
         }
    

   
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
