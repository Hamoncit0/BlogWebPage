
package entidades;

import java.time.*;


public class Usuario {
    private int IdUsuario;
    private String Correo;
    private String Usuario;
    private String Foto;
    private LocalDate FechaNac;
    private String FechaNacStr;
    private int Edad;
    private String Nombre;
    private String SNombre;
    private String ApPaterno;
    private String ApMaterno;
    private LocalDateTime FechaAlta;
    private String Pass;

    public Usuario(){

    }
    //login bien basico
    public Usuario(String Usuario, String Pass){
        this.Usuario = Usuario;
        this.Pass = Pass;
    }
    //sign up :/
    public Usuario(String Correo, String Usuario, LocalDate FechaNac, String Nombre, String SNombre, String ApMaterno, String ApPaterno, String Pass){
        this.Correo = Correo;
        this.Usuario = Usuario;
        this.FechaNac = FechaNac;
        this.Nombre = Nombre;
        this.SNombre = SNombre;
        this.ApMaterno = ApMaterno;
        this.ApPaterno = ApPaterno;
        this.Pass = Pass;
        //ahorita
        LocalDate ahorita = LocalDate.now();
        //checa la diferencia
        Period period = Period.between(FechaNac, ahorita);
        //los pasa a numero de años
        int años = period.getYears();
        
        this.Edad=años; 
        this.FechaAlta = LocalDateTime.now();
    }
    //sign up con foto :/
    public Usuario(String Correo, String Usuario, LocalDate FechaNac, String Nombre, String SNombre, String ApMaterno, String ApPaterno, String Pass, String Foto){
        this.Correo = Correo;
        this.Usuario = Usuario;
        this.FechaNac = FechaNac;
        this.Nombre = Nombre;
        this.SNombre = SNombre;
        this.ApMaterno = ApMaterno;
        this.ApPaterno = ApPaterno;
        this.Pass = Pass;
        this.Foto = Foto;
        //ahorita
        LocalDate ahorita = LocalDate.now();
        //checa la diferencia
        Period period = Period.between(FechaNac, ahorita);
        //los pasa a numero de años
        int años = period.getYears();
        
        this.Edad=años; 
        this.FechaAlta = LocalDateTime.now();
    }
    //login chido 
    public Usuario(String Correo, String Usuario, LocalDate FechaNac, String Nombre, String SNombre, String ApMaterno,String ApPaterno, String Pass, LocalDateTime FechaAlta, String Foto){
        this.Correo = Correo;
        this.Usuario = Usuario;
        this.FechaNac = FechaNac;
        this.SNombre = SNombre;
        this.ApPaterno=ApPaterno;
        this.ApMaterno = ApMaterno;
        this.Pass = Pass;
        this.FechaAlta = FechaAlta;
        this.Foto = Foto;
    
    }
    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String Foto) {
        this.Foto = Foto;
    }

    public LocalDate getFechaNac() {
        return FechaNac;
    }

    public void setFechaNac(LocalDate FechaNac) {
        this.FechaNac = FechaNac;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int Edad) {
        this.Edad = Edad;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getSNombre() {
        return SNombre;
    }

    public void setSNombre(String SNombre) {
        this.SNombre = SNombre;
    }

    public String getApPaterno() {
        return ApPaterno;
    }

    public void setApPaterno(String ApPaterno) {
        this.ApPaterno = ApPaterno;
    }

    public String getApMaterno() {
        return ApMaterno;
    }

    public void setApMaterno(String ApMaterno) {
        this.ApMaterno = ApMaterno;
    }

    public LocalDateTime getFechaAlta() {
        return FechaAlta;
    }

    public void setFechaAlta(LocalDateTime FechaAlta) {
        this.FechaAlta = FechaAlta;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }

    public String getFechaNacStr() {
        return FechaNacStr;
    }

    public void setFechaNacStr(String FechaNacStr) {
        this.FechaNacStr = FechaNacStr;
    }
    
}
