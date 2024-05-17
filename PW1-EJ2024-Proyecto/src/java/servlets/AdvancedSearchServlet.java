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
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "AdvancedSearchServlet", urlPatterns = {"/AdvancedSearchServlet"})
public class AdvancedSearchServlet extends HttpServlet {

    DAOPublicacion daoPub = new DAOPublicacion();
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        // Y ya lo muestra
        RequestDispatcher rs = request.getRequestDispatcher("advanced_search.jsp");
        rs.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("ADVANCED SEARCH POST");
       //Una vez actualizada la pagina hace una consulta otra vez
        String palabra = request.getParameter("Palabra");
        String IDCatStr = request.getParameter("Categoria");
        int idCategoria = Integer.parseInt(IDCatStr);
        String fechaInicio = request.getParameter("fechaInicio");
        String fechaFin = request.getParameter("fechaFin");
        List<Publicacion> publicaciones = daoPub.BusquedaAvanzada(palabra, idCategoria, fechaInicio, fechaFin);
        request.setAttribute("busquedaAvanzada", publicaciones);
        String pantalla = "";
        if(publicaciones!=null){
            //si es true si se pudo hacer este pedo :)
            pantalla = "advanced_search.jsp";
              
            }
            else{
            //Si hubo un error se viene para aca
            request.setAttribute("error", 1);
            pantalla="error.jsp";
            
            }
        RequestDispatcher rs =request.getRequestDispatcher(pantalla);
            rs.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
