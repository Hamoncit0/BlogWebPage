/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import DAO.DAOPublicacion;
import entidades.Publicacion;
import entidades.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MakeAPostServlet", urlPatterns = {"/MakeAPostServlet"})
@MultipartConfig
public class MakeAPostServlet extends HttpServlet {

    DAOPublicacion daoPub = new DAOPublicacion();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Una vez actualizada la página, hace una consulta otra vez
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
        // Hacer datos para mandar al constructor de Usuario
        String Titulo = request.getParameter("Titulo");
        String Contenido = request.getParameter("Contenido");
        String CategoriaSTR = request.getParameter("Categoria");

        // Validaciones básicas
        if (Titulo == null || Titulo.trim().isEmpty() ||
            Contenido == null || Contenido.trim().isEmpty() ||
            CategoriaSTR == null || CategoriaSTR.trim().isEmpty()) {
            
            request.setAttribute("error", "Todos los campos son obligatorios.");
            RequestDispatcher rs = request.getRequestDispatcher("error.jsp");
            rs.forward(request, response);
            return;
        }

        int Categoria;
        try {
            Categoria = Integer.parseInt(CategoriaSTR);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "La categoría debe ser un número válido.");
            RequestDispatcher rs = request.getRequestDispatcher("error.jsp");
            rs.forward(request, response);
            return;
        }

        // Procesar la imagen si se ha subido una
        String fileName = null;
        Part part = request.getPart("file-upload");
        if (part != null) {
            fileName = getFileName(part);
            if (fileName != null && !fileName.isEmpty()) {
                String uploadPath = "C:\\Users\\yazmi\\OneDrive\\Documents\\Facu\\6to semestre\\Programacion web Gp. 51\\PW1-EJ2024-Proyecto-branch_backEnd\\PW1-EJ2024-Proyecto\\web\\IMGSPFP";
                File fileSaveDir = new File(uploadPath);
                if (!fileSaveDir.exists()) {
                    fileSaveDir.mkdirs();
                }
                part.write(uploadPath + File.separator + fileName);
            }
        }

        // Crear y configurar la publicación
        Publicacion publicacion = new Publicacion();
        publicacion.setIDCategoria(Categoria);
        publicacion.setTitulo(Titulo);
        publicacion.setContenido(Contenido);
        publicacion.setImagen(fileName);

        // Obtener el usuario de la sesión
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("UserObj");
        publicacion.setIDUsuario(usuario.getIdUsuario());

        // Publicar y redirigir según el resultado
        Boolean SePublico = daoPub.Publicar(publicacion);
        if (SePublico) {
            response.sendRedirect(request.getContextPath() + "/MakeAPostServlet");
        } else {
            request.setAttribute("error", "Hubo un error al intentar publicar.");
            RequestDispatcher rs = request.getRequestDispatcher("error.jsp");
            rs.forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
    }
}