
package servlets;

import DAO.DAOPublicacion;
import entidades.Publicacion;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import org.json.simple.JSONObject;

/**
 *
 * @author yazmi
 */
@WebServlet(name = "GetPublicacionesServlet", urlPatterns = {"/GetPublicacionesServlet"})
public class GetPublicacionesServlet extends HttpServlet {

   DAOPublicacion daoPub = new DAOPublicacion();
  

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("GET PUBLICACIONES");
        PrintWriter out = response.getWriter();
        
        String index = request.getParameter("index");
        
        List<Publicacion> pubs = daoPub.consultarPorIndex(index);
        
        int total = daoPub.getTotalPublicaciones();
        
        JSONObject json = new JSONObject();
        if(pubs != null){
            for(int i = 0; i < pubs.size(); i++){
                JSONObject j = new JSONObject();
                j.put("TotalPublicaciones", total);
                j.put("IdPublicacion", pubs.get(i).getIdPublicacion());
                j.put("Titulo", pubs.get(i).getTitulo());
                j.put("Contenido", pubs.get(i).getContenido());
                j.put("Categoria", pubs.get(i).getCategoria());
                j.put("Imagen", pubs.get(i).getImagen());
                
                
                json.put(i, j);
            }
        }        
        out.print(json);
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
