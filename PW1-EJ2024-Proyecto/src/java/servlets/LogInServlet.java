/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import DAO.DAOCategoria;
import DAO.DAOUsuario;
import entidades.Usuario;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import DAO.DAOPublicacion;
import entidades.Categoria;
import entidades.Publicacion;
import java.util.List;

@WebServlet(name = "LogInServlet", urlPatterns = {"/LogInServlet"})
public class LogInServlet extends HttpServlet {

    
    DAOUsuario daoUsu = new DAOUsuario();
    DAOPublicacion daoPub = new DAOPublicacion();
    DAOCategoria daoCat = new DAOCategoria();
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
                HttpSession MiSesion = request.getSession();
                MiSesion.setAttribute("Username", usu.getUsuario());
                List<Publicacion> publicaciones = daoPub.consultarRecientes();
                request.setAttribute("Recientes", publicaciones);
                MiSesion.setAttribute("UserObj", usu);
                
                
                List<Categoria>categorias=daoCat.ObtenerCategorias();
                MiSesion.setAttribute("Categorias", categorias);
                
                List<Categoria>categoriasMasP=daoCat.ObtenerCategoriasMasPublicadas();
                MiSesion.setAttribute("CategoriasBuscadas", categoriasMasP);
                
                System.out.println(usu.getUsuario());
                pantalla = "home.jsp";
                request.setAttribute("Usuario", usu);
                request.setAttribute("error", 0);
                
                //publicaciones
                //JTSL es una libreria para manejar objetos :)
            }
            else{
            //si llega aqui no existe :(
                request.setAttribute("idk", 1);
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

}
