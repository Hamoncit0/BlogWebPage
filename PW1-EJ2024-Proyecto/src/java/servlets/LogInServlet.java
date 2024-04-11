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
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author yazmi
 */
@WebServlet(name = "LogInServlet", urlPatterns = {"/LogInServlet"})
public class LogInServlet extends HttpServlet {

    
    DAOUsuario daoUsu = new DAOUsuario();
   //Metodos HTTP del LogIn Servlet
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            System.out.println("LOGIN SERVLET GET");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("LOGIN SERVLET POST");
            String user = request.getParameter("username");
            String pass = request.getParameter("password");
            System.out.println(user + pass);
            Usuario form = new Usuario(user, pass);
            Usuario usu= new Usuario(); //este vato guarda
            
            Object loginObj = daoUsu.login(form); //este vato se la rifa y consigue la info
            String pantalla = "";
            usu = (Usuario) loginObj; //pero no podemos usar al vato que se la rifo pq es de tipo Object :(
            if(usu.getIdUsuario()!=0){
            //si entra es que existe :)
                pantalla = "home.jsp";
                request.setAttribute("Usuario", usu);
                request.setAttribute("error", 0);
            }
            else{
            //si llega aqui no existe :(
                pantalla="login.jsp";
                request.setAttribute("error", 1);
            
            }
            RequestDispatcher rs =request.getRequestDispatcher(pantalla);
            rs.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
