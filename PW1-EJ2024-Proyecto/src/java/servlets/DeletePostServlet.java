/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import DAO.DAOPublicacion;
import entidades.Publicacion;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "DeletePostServlet", urlPatterns = {"/DeletePostServlet"})
public class DeletePostServlet extends HttpServlet {

    DAOPublicacion daoPub = new DAOPublicacion();
  

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("ESTA EN EL SERVLET PARA BORRAR POSTS");
        String ID = request.getParameter("IDPublicacion");
        int IDPub = Integer.parseInt(ID);
        Publicacion pub = new Publicacion();
        pub.setIdPublicacion(IDPub);
        Boolean SeBorro = daoPub.BorrarPublicacion(pub);
        if(SeBorro){
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

}
