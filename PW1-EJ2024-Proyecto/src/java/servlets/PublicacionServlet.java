/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import DAO.DAOPublicacion;
import entidades.Publicacion;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

@WebServlet(name = "PublicacionServlet", urlPatterns = {"/PublicacionServlet"})
public class PublicacionServlet extends HttpServlet {
    DAOPublicacion daoPub = new DAOPublicacion();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("SERVLET PUBLICACION GET");
        PrintWriter out = response.getWriter();
        String index = request.getParameter("index");
        List<Publicacion> pubs = daoPub.consultarPorIndex(index, 4);
        int total = daoPub.getTotalPublicaciones();

        JSONObject json = new JSONObject();
        if (pubs != null) {
            for (int i = 0; i < pubs.size(); i++) {
                JSONObject j = new JSONObject();
                j.put("TotalPublicaciones", total);
                j.put("IdPublicacion", pubs.get(i).getIdPublicacion());
                j.put("Titulo", pubs.get(i).getTitulo());
                j.put("Contenido", pubs.get(i).getContenido());
                j.put("IDCategoria", pubs.get(i).getIDCategoria());
                j.put("Categoria", pubs.get(i).getCategoria());
                j.put("Imagen", pubs.get(i).getImagen());
                j.put("FechaAlta", pubs.get(i).getFechaAlta());
                j.put("FotoUsu", pubs.get(i).getFotoUsu());
                j.put("Usuario", pubs.get(i).getUsuario());

                json.put(i, j);
            }
        }
        out.print(json);
        System.out.println("Se cookeo el servlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("SERVLET PUBLICACION POST");
        request.setCharacterEncoding("UTF-8");

        String titulo = request.getParameter("titulo");
        String contenido = request.getParameter("contenido");
        String categoriaStr = request.getParameter("categoria");

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject jsonResponse = new JSONObject();

        // Validación de campos obligatorios
        if (titulo == null || titulo.trim().isEmpty() ||
            contenido == null || contenido.trim().isEmpty() ||
            categoriaStr == null || categoriaStr.trim().isEmpty()) {
            jsonResponse.put("status", "error");
            jsonResponse.put("message", "Los campos Título, Contenido y Categoría son obligatorios.");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print(jsonResponse);
            return;
        }

        try {
            int categoria = Integer.parseInt(categoriaStr);

            // Aquí puedes obtener el ID del usuario de la sesión o algún otro método
            int idUsuario = 1; // Esto es un ejemplo, debes obtener el ID del usuario actual de alguna manera

            Publicacion publicacion = new Publicacion();
            publicacion.setTitulo(titulo);
            publicacion.setContenido(contenido);
            publicacion.setIDCategoria(categoria);
            publicacion.setIDUsuario(idUsuario); // Establecer el ID del usuario

            boolean success = daoPub.Publicar(publicacion);
            if (success) {
                jsonResponse.put("status", "success");
                jsonResponse.put("message", "Publicación creada exitosamente.");
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                jsonResponse.put("status", "error");
                jsonResponse.put("message", "Error al crear la publicación.");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (NumberFormatException e) {
            jsonResponse.put("status", "error");
            jsonResponse.put("message", "La categoría debe ser un número válido.");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        out.print(jsonResponse);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}