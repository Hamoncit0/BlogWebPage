<%-- 
    Document   : profile
    Created on : Apr 23, 2024, 12:30:20 AM
    Author     : yazmi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="entidades.Usuario"%>
<%@page import="entidades.Publicacion"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>K-Connect:Perfil</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
        <link rel="stylesheet" href="./css/scss/custom.css">
        <link rel="stylesheet" href="./css/style.css">
    </head>
    <body
        <% 
            Usuario uwu = (Usuario) request.getSession().getAttribute("UserObj");
            System.out.println("usuario conseguido del http en el Misesion uwu PROFILE:"+uwu.getUsuario());
        %>
        <!-- barra de navegacion de arriba -->
 <nav class="navbar navbar-expand-sm bg-primary navbar-dark barra-nav">

    <div class="container-fluid">
      <!-- Links -->
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link active" href="#">K-connect</a>
        </li>
        
      </ul>
      <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" href="#">Para ti</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="./advanced_search.jsp">Explora</a>
          </li>
      </ul>
      <ul class="navbar-nav justify-contend-end">
        <li>
          <form class="d-flex" action="search.jsp" method="get" >
            <input class="form-control me-2" type="text" placeholder="Buscar">
            <button class="btn btn-primary" type="submit" >Buscar</button>
          </form>
        </li>
        <li>
          <a class="navbar-brand" href="#">
            <img src="./imgs/empty.png" alt="Avatar Logo" style="width:40px;" class="rounded-pill" onclick="settingsMenuToggle()"> 
          </a>
        </li>
        <p class="navbar-username"><%= request.getSession().getAttribute("Username")%></p>
      </ul>
      
     
     
    </div>
  
    <div class="settings-menu">
      <div class="settings-menu-inner">

        <div class="user-profile">
          <img src="imgs/empty.png" class="rounded-pill" alt="">
          <div>
            <p><%= request.getSession().getAttribute("Username")%></p>
            <a href="GetProfilePostsServlet">Ver Perfil</a>
          </div>
        </div>
        <div class="user-profile">
          <img src="imgs//box-arrow-right.svg"  alt="">
          <div>
            <p>Cerrar Sesion?</p>
            <a href="./logIn.jsp">Salir</a>
          </div>
        </div>
        

        </div>

      </div>
      
    </div>

  </nav>
 
  <div class="main">
    <div class="section" id="left">
        <!-- barra de navegacion de la izquierda -->
           <nav class="nav flex-column nav-izquierda">
             <a class="nav-link active" aria-current="page" href="GetPostsServlet"><i class="bi bi-house-door-fill"></i> Inicio</a>
             <a class="nav-link" href="./advanced_search.jsp"><i class="bi bi-compass"></i> Explorar</a>
             <a class="nav-link" aria-disabled="true"><i class="bi bi-person-circle"></i> Perfil</a>
         </nav>
     </div>
     <div class="section-middle" id="middle">
        <div class="profile">
            <div class="profile-avatar">
                <img src="imgs/empty.png" alt="" class="rounded-pill profile-picture">
                <h2 class="profile-name"><%= request.getSession().getAttribute("Username")%></h2>
                <button type="button" class="btn btn-primary"  data-bs-toggle="modal" data-bs-target="#myModal">Editar perfil</button>
            </div>
            <div class="profile-info">
               
                <p>Nombre Completo: <%out.println(uwu.getNombre());%> <%out.println(uwu.getSNombre());%> <%out.println(uwu.getApPaterno());%> <%out.println(uwu.getApMaterno());%>   </p>
                <p>Correo: <%out.println(uwu.getCorreo());%> </p>
                <p>Edad: <%out.println(uwu.getEdad());%>  </p>
                <p>Fecha de nacimiento: <%out.println(uwu.getFechaNacStr());%>  </p>
            </div>
            <div class="profile-menu btn-group">
                <button type="button" class="btn btn-outline-primary">Publicaciones</button>
                <button type="button" class="btn btn-outline-primary">Fotos</button>
            </div>
        </div>
         <c:forEach items="${tusPosts}" var="pub">
                 <div class="post-container" value="${pub.getIdPublicacion()}">
          <div class="post-row">
          <div class="user-profile">
            <img src="imgs/empty.png" class="rounded-pill" alt="">
            <div>
              <p><c:out value="${pub.getUsuario()}"></c:out></p>
              <span><c:out value="${pub.getFechaAlta()}"></c:out></span>
              <br>
              <span>Categoria: <c:out value="${pub.getCategoria()}"></c:out></span>
            </div>
          </div>
              
          <c:if test="${pub.getIDUsuario()== UserObj.getIdUsuario()}">
          <div class="dropstart">
            <button class="btn btn-sm btn-light" type="button" data-bs-toggle="dropdown" aria-expanded="false">
              
              <i class="bi bi-three-dots-vertical"></i>
            </button>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#exampleModal">Eliminar publicacion</a></li>
              <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#exampleModal1">Modificar publicacion</a></li>
            </ul>
          </div>
          </c:if>
            
          </div>
          <h6 class="post-title"><c:out value="${pub.getTitulo()}"></c:out></h6>
          <p class="post-text"><c:out value="${pub.getContenido()}"></c:out> </p>
          <c:if test="${not empty pub.getImagen()}">
          <img src="IMGSPFP/${pub.getImagen()}" alt="" class="post-image">
              
          </c:if>
          
         </div>
              </c:forEach>
           <button class="load-button btn btn-light">Cargar mas</button>
     </div>

     
     <div class="section" id="right">
        <div class="categorias-populares">
          <h2>Categorias populares</h2>
          <ol class="list-group list-group-numbered">
             <c:forEach items="${CategoriasBuscadas}" var="catb">
                  <li class="list-group-item d-flex justify-content-between align-items-start">
                    <div class="ms-2 me-auto">
                      <div class="fw-bold"><c:out value="${catb.getCategoria()}"></c:out></div>
                      <c:out value="${catb.getCantidad()}"></c:out> publicaciones.
                    </div>
                  </li>
              </c:forEach>
          </ol>
        </div>
      </div>
     </div>
     <div class="modal" id="myModal">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
          </div>
          <div class="">
              <form id="signUp">
                     <h2>Modificar Datos</h2>
                     <div class="profile-avatar">
                      <img src="imgs/empty.png" alt="" class="rounded-pill profile-picture">
                      <h2 class="profile-name"><%= request.getSession().getAttribute("Username")%></h2>
                      <button class="btn btn-primary">Cambiar foto de perfil</button>
                     </div>
                     <div class="mb-3">
                         <label for="firstName"  class="form-label">Primer Nombre</label>
                         <input type="text" value="Fulanita"  class="form-control" id="firstName" placeholder="ej. Maria, Diego" required>
                     </div>
                     <div class="mb-3">
                      <label for="secondName"  class="form-label">Segundo Nombre(opcional)</label>
                      <input type="text"  class="form-control" id="secondName" placeholder="ej. Maria, Diego" optional>
                      </div>
                     <div class="mb-3">
                         <label for="lastName" class="form-label">Apellidos(s)</label>
                         <input type="text" value="Perez"   class="form-control" id="lastName" placeholder="ej. Perez, Ramirez" required>
                     </div>
                     <div class="mb-3">
                         <label for="usernameSignUp" class="form-label">Nombre de usuario</label>
                         <input type="text" value="Username"  class="form-control" id="usernameSignUp" placeholder="ej. Perlita23, Manganito98" required>
                     </div>
                     <div class="mb-3">
                         <label for="birthday" class="form-label">Fecha de nacimiento</label>
                         <input type="date"  value="1998-01-28" class="form-control" id="birthday" required>
                     </div>
                     <div class="mb-3">
                         <label for="email" class="form-label">Correo</label>
                         <input type="email" value="fulanaP@uwu.com" class="form-control" id="email" placeholder="ejemplo@gmail.com" required>
                     </div>
                     <button type="submit" id="btnRegister" class="btn btn-primary">Editar</button>
              </form>
          </div>
    
        </div>
      </div>
    </div>
  </div>
  <div class="footer">
    <p>Copyright 2024 - K-Connect</p>
    <p>Politicas de privacidad</p>
    <p>Politicas de cookies</p>
   </div>
    </body>
    
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="script.js"></script>
</html>
