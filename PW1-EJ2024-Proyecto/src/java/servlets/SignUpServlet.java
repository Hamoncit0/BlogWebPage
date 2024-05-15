
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
import jakarta.servlet.http.Part;
import java.io.File;
import java.time.*;

@WebServlet(name = "SignUpServlet", urlPatterns = {"/SignUpServlet"})
@MultipartConfig
public class SignUpServlet extends HttpServlet {

    
    
    DAOUsuario daoUsu = new DAOUsuario();

   //HTTP METHODS OF SIGNUPSERVLET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("LOGIN SERVLET POST");
            //hacer datos para mandar al constructor de Usuario
            String firstName = request.getParameter("firstName");
            String secondName = request.getParameter("secondName");
            String lastName = request.getParameter("lastName");
            String lastName2 = request.getParameter("lastName2");
            String username = request.getParameter("usernameSignUp");
            LocalDate birthday = LocalDate.parse(request.getParameter("birthday"));
            String email = request.getParameter("email");
            String password = request.getParameter("passwordSignUp");
            String foto = request.getParameter("file-upload");
            
            //guardar imagen
            String uploadPath= "C:\\Users\\yazmi\\OneDrive\\Documents\\Facu\\6to semestre\\Programacion web Gp. 51\\PW1-EJ2024-Proyecto\\PW1-EJ2024-Proyecto\\PW1-EJ2024-Proyecto\\web\\IMGSPFP";
            File fileSaveDir = new File (uploadPath);
            if(!fileSaveDir.exists()){
                fileSaveDir.mkdirs();
            
            }
            //filename
            String fileName = null;
            Part part = request.getPart("file-upload");
            
            fileName = getFileName(part);
            
            if(fileName != ""){
                part.write(uploadPath + File.separator + fileName);
            }
            Usuario form = new Usuario(email, username, birthday, firstName, secondName, lastName, lastName2, password, fileName);
            Usuario usu= new Usuario(); //este vato guarda
            
            Boolean signUp = daoUsu.signUp(form); //este vato se la rifa y consigue la info
            String pantalla = "";
            
            if(signUp){
            //si es true si se pudo hacer este pedo :)
                pantalla = "logIn.jsp";
                request.setAttribute("Usuario", usu);
                request.setAttribute("error", 0);
            }
            else{
            //si entra aca es que valio :(
                pantalla="logIn.jsp";
                request.setAttribute("error", 1);
            
            }
            RequestDispatcher rs =request.getRequestDispatcher(pantalla);
            rs.forward(request, response);
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
