/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;
import java.time.*;
/**
 *
 * @author yazmi
 */
public class Publicacion {
    private int IdPublicacion;
    private String Titulo;
    private String Contenido;
    private String Imagen;
    private String Usuario;
    private int IDCategoria;
    private int IDUsuario;
    private String Categoria;
    private String FechaAlta;
    private int Estatus;
    private String FotoUsu;
    
    public Publicacion(){}

    public int getIdPublicacion() {
        return IdPublicacion;
    }

    public void setIdPublicacion(int IdPublicacion) {
        this.IdPublicacion = IdPublicacion;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getContenido() {
        return Contenido;
    }

    public void setContenido(String Contenido) {
        this.Contenido = Contenido;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String Imagen) {
        this.Imagen = Imagen;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public int getIDCategoria() {
        return IDCategoria;
    }

    public void setIDCategoria(int IDCategoria) {
        this.IDCategoria = IDCategoria;
    }

    public String getFechaAlta() {
        return FechaAlta;
    }

    public void setFechaAlta(String FechaAlta) {
        this.FechaAlta = FechaAlta;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public int getIDUsuario() {
        return IDUsuario;
    }

    public void setIDUsuario(int IDUsuario) {
        this.IDUsuario = IDUsuario;
    }

    public int getEstatus() {
        return Estatus;
    }

    public void setEstatus(int Estatus) {
        this.Estatus = Estatus;
    }

    public String getFotoUsu() {
        return FotoUsu;
    }

    public void setFotoUsu(String FotoUsu) {
        this.FotoUsu = FotoUsu;
    }
    
}
