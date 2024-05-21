/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import DAO.DAOCategoria;
import entidades.Publicacion;
import DAO.DAOPublicacion;
import entidades.Categoria;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author yazmi
 */
@WebServlet(name = "GetPostsServlet", urlPatterns = {"/GetPostsServlet"})
public class GetPostsServlet extends HttpServlet {

   
  DAOPublicacion daoPub = new DAOPublicacion();
  DAOCategoria daoCat = new DAOCategoria();

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        HttpSession MiSesion = request.getSession();
        
        List<Categoria>categorias=daoCat.ObtenerCategorias();
        MiSesion.setAttribute("Categorias", categorias);
                
        List<Categoria>categoriasMasP=daoCat.ObtenerCategoriasMasPublicadas();
        MiSesion.setAttribute("CategoriasBuscadas", categoriasMasP);
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
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
