<%-- 
    Document   : search
    Created on : Apr 23, 2024, 11:06:54 AM
    Author     : yazmi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entidades.Usuario"%>
<%@page import="entidades.Categoria"%>
<%@page import="DAO.DAOPublicacion"%>
<%--@taglib prefix="c" uri="jakarta.tag.core"--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>K-connect</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
        <link rel="stylesheet" href="./css/scss/custom.css">
        <link rel="stylesheet" href="./css/style.css">
    </head>
    <body>
        <% 
            Usuario uwu = (Usuario) request.getSession().getAttribute("UserObj");
            System.out.println("usuario conseguido del http en el Misesion uwu Search:"+uwu.getUsuario());
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
                <p class="navbar-username"><%out.println(uwu.getUsuario());%></p>
              </ul>
              
             
             
            </div>
          
            <div class="settings-menu">
              <div class="settings-menu-inner">
    
                <div class="user-profile">
                  <img src="imgs/empty.png" class="rounded-pill" alt="">
                  <div>
                    <p><%out.println(uwu.getUsuario());%> </p>
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
                  <a class="nav-link" href="GetProfilePostsServlet"><i class="bi bi-person-circle"></i> Perfil</a>
              </nav>
          </div>
          <div class="section-middle" id="middle">
              <p class="search-title">Buscaste: <span><c:out value="${busquedaWord}"></c:out></span></p>
             
              <div class="publicaciones">
                 <c:forEach items="${busquedaPub}" var="pub">
                 <div class="post-container" value="${pub.getIdPublicacion()}">
          <div class="post-row">
          <div class="user-profile">
              <c:if test="${not empty pub.getFotoUsu()}">
                   <img src="./IMGSPFP/${pub.getFotoUsu()}" alt="Avatar Logo"  class="rounded-pill"> 
                </c:if>
               <c:if test="${empty pub.getFotoUsu()}">
                   <img src="./imgs/empty.png" alt="Avatar Logo" class="rounded-pill"> 
               </c:if>
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
              <li><a class="dropdown-item delete" href="#" data-bs-toggle="modal" data-bs-target="#exampleModal" data-id="${pub.getIdPublicacion()}">Eliminar publicacion</a></li>
              <li><a class="dropdown-item modify" href="#" data-bs-toggle="modal" data-bs-target="#exampleModal1"
              data-username="${pub.getUsuario()}" data-id="${pub.getIdPublicacion()}"
              data-title="${pub.getTitulo()}"
              data-content="${pub.getContenido()}"
              data-image="IMGSPFP/${pub.getImagen()}"
              data-category-id="${pub.getIDCategoria()}"
              data-user-image="IMGSPFP/${pub.getFotoUsu()}">Modificar publicacion</a></li>
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
            </div>
          </div>
          <div class="section" id="right">
            <div class="categorias-populares">
              <h2>Categorias populares</h2>
              <ol class="list-group list-group-numbered">
                <li class="list-group-item d-flex justify-content-between align-items-start">
                  <div class="ms-2 me-auto">
                    <div class="fw-bold">Crochet</div>
                    19k publicaciones
                  </div>
                </li>
                <li class="list-group-item d-flex justify-content-between align-items-start">
                  <div class="ms-2 me-auto">
                    <div class="fw-bold">Dibujo</div>
                    16k publicaciones
                  </div>
                </li>
                <li class="list-group-item d-flex justify-content-between align-items-start">
                  <div class="ms-2 me-auto">
                    <div class="fw-bold">Música</div>
                    12k publicaciones
                  </div>
                </li>
                <li class="list-group-item d-flex justify-content-between align-items-start">
                  <div class="ms-2 me-auto">
                    <div class="fw-bold">Puzzles</div>
                    10k publicaciones
                  </div>
                </li>
                <li class="list-group-item d-flex justify-content-between align-items-start">
                  <div class="ms-2 me-auto">
                    <div class="fw-bold">Ropa</div>
                  8k publicaciones
                  </div>
                </li>
              </ol>
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
