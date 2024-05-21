<%-- 
    Document   : profile
    Created on : Apr 23, 2024, 12:30:20 AM
    Author     : yazmi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="entidades.Usuario"%>
<%@page import="entidades.Publicacion"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="entidades.Categoria"%>
<%@page import="DAO.DAOPublicacion"%>
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
            Usuario usuario = new Usuario(); 
            usuario = (Usuario) request.getAttribute("Usuario");
            Usuario uwu = (Usuario) request.getSession().getAttribute("UserObj");
            System.out.println("usuario conseguido del http en el Misesion uwu HOME:"+uwu.getUsuario());
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
          <form class="d-flex" action="SearchServlet" method="post" >
                <input name="palabraBuscador" class="form-control me-2" type="text" placeholder="Buscar">
                <button class="btn btn-primary" type="submit" >Buscar</button>
          </form>
        </li>
        <li>
          <a class="navbar-brand" href="#">
              <c:if test="${not empty UserObj.getFoto()}">
                   <img src="./IMGSPFP/${UserObj.getFoto()}" alt="Avatar Logo" style="width:40px;" class="rounded-pill" onclick="settingsMenuToggle()"> 
                </c:if>
               <c:if test="${empty UserObj.getFoto()}">
                   <img src="./imgs/empty.png"alt="Avatar Logo" style="width:40px;" class="rounded-pill" onclick="settingsMenuToggle()"> 
               </c:if>
            
          </a>
        </li>
        <p class="navbar-username"><%= request.getSession().getAttribute("Username")%></p>
      </ul>
      
     
     
    </div>
  
    <div class="settings-menu">
      <div class="settings-menu-inner">

        <div class="user-profile">
                 <c:if test="${not empty UserObj.getFoto()}">
                   <img src="./IMGSPFP/${UserObj.getFoto()}" alt="Avatar Logo"  class="rounded-pill"> 
                </c:if>
               <c:if test="${empty UserObj.getFoto()}">
                   <img src="./imgs/empty.png" alt="Avatar Logo" class="rounded-pill"> 
               </c:if>
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
                <c:if test="${not empty UserObj.getFoto()}">
                   <img src="./IMGSPFP/${UserObj.getFoto()}" alt="" class="rounded-pill profile-picture"> 
                </c:if>
               <c:if test="${empty UserObj.getFoto()}">
                   <img src="./imgs/empty.png" alt="" class="rounded-pill profile-picture"> 
               </c:if>
                
                <h2 class="profile-name"><%= request.getSession().getAttribute("Username")%></h2>
                <button type="button" class="btn btn-primary"  data-bs-toggle="modal" data-bs-target="#myModal">Editar perfil</button>
            </div>
            <div class="profile-info">
               
                <p>Nombre Completo: <%out.println(uwu.getNombre());%> <%out.println(uwu.getSNombre());%> <%out.println(uwu.getApPaterno());%> <%out.println(uwu.getApMaterno());%>   </p>
                <p>Correo: <%out.println(uwu.getCorreo());%> </p>
                <p>Edad: <%out.println(uwu.getEdad());%>  </p>
                <p>Fecha de nacimiento: <%out.println(uwu.getFechaNacStr());%>  </p>
            </div>
        </div>
         <c:forEach items="${tusPosts}" var="pub">
                 <div class="post-container" value="${pub.getIdPublicacion()}">
          <div class="post-row">
          <div class="user-profile">
              <c:if test="${not empty UserObj.getFoto()}">
                   <img src="./IMGSPFP/${UserObj.getFoto()}" class="rounded-pill" alt=""> 
                </c:if>
               <c:if test="${empty UserObj.getFoto()}">
                   <img src="./imgs/empty.png" class="rounded-pill" alt=""> 
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
              <form id="signUp" action="ChangeUserDataServlet" method="post" enctype="multipart/form-data">
                     <h2>Modificar Datos</h2>
                     <div class="profile-avatar">
                      <c:if test="${not empty UserObj.getFoto()}">
                        <img src="./IMGSPFP/${UserObj.getFoto()}" alt="" class="rounded-pill profile-picture" id="preview"> 
                      </c:if>
                      <c:if test="${empty UserObj.getFoto()}">
                        <img src="./imgs/empty.png" alt="" class="rounded-pill profile-picture" id="preview"> 
                      </c:if>   
                      <input name="fotoIlegal" value="${UserObj.getFoto()}" hidden/>
                      <h2 class="profile-name">${UserObj.getUsuario()}</h2>
                      <label for="file-upload" class="btn btn-info">Cambiar foto de perfil.</label>
                      <input name="file-uploadCU" style="display:none;" id="file-upload" type="file" accept="image/png, image/jpeg" class="btn btn-info boton-input" onchange="previewImage(event)"/>
                     </div>
                     <div class="mb-3">
                         <label for="firstName"  class="form-label">Primer Nombre</label>
                         <input type="text" name="firstNameCU" value="${UserObj.getNombre()}"  class="form-control" id="firstName" placeholder="ej. Maria, Diego" required>
                     </div>
                     <div class="mb-3">
                      <label for="secondName"  class="form-label">Segundo Nombre(opcional)</label>
                      <input type="text" name="secondNameCU" value="${UserObj.getSNombre()}" class="form-control" id="secondName" placeholder="ej. Maria, Diego" optional>
                      </div>
                     <div class="mb-3">
                         <label for="lastName" class="form-label">Apellido Paterno:</label>
                         <input type="text" name="lastNameCU" value="${UserObj.getApPaterno()}"   class="form-control" id="lastName" placeholder="ej. Perez, Ramirez" required>
                     </div>
                     <div class="mb-3">
                         <label for="lastName" class="form-label">Apellidos Materno:</label>
                         <input type="text" name="lastName2CU" value="${UserObj.getApMaterno()}"   class="form-control" id="lastName" placeholder="ej. Perez, Ramirez" required>
                     </div>
                     <div class="mb-3">
                         <label for="usernameSignUp" class="form-label">Nombre de usuario</label>
                         <input type="text"name="usernameCU" value="${UserObj.getUsuario()}"  class="form-control" id="usernameSignUp" placeholder="ej. Perlita23, Manganito98" required>
                     </div>
                     <div class="mb-3">
                         <label for="birthday" class="form-label">Fecha de nacimiento</label>
                         <input type="date" name="birthdayCU" value="${UserObj.getFechaNac()}" class="form-control" id="birthday" required>
                     </div>
                     <div class="mb-3">
                         <label for="email" class="form-label">Correo</label>
                         <input type="email" name="emailCU" value="${UserObj.getCorreo()}" class="form-control" id="email" placeholder="ejemplo@gmail.com" required>
                     </div>
                     <button type="submit" id="btnRegister" class="btn btn-primary">Guardar Cambios</button>
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
   <!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Eliminar publicación</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <form action="DeletePostServlet" method="post">
      <div class="modal-body">
        ¿Estas seguro de que quieres eliminar esta publicación?
        <input name="IDPublicacion" type="hidden" id="deleteModalPostId">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
        <button type="Submit" class="btn btn-primary">Si</button>
      </div>
      </form>
    </div>
  </div>
</div>

 <!-- Modal Modificar publicacion-->
 <div class="modal fade" id="exampleModal1" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Modificar publicación</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body" >
       <form action="ChangePostServlet" method="post" enctype="multipart/form-data">
        <div class="make-a-Post">
          <div class="make-a-Post">
            <div class="user-profile">
              <img src="imgs/empty.png" class="rounded-pill" alt="" id="modal-postUserPfp">
              <div>
                <input name="ModalID" id="modal-postId" type="hidden"/>
                <p id="modal-postUsername">Username</p>
                <select style="margin-top: 5px;" class="form-select" id="modal-postCategory" name="ModalCategoria">
                <option selected>Categoria</option>
                <c:forEach items="${Categorias}" var="cat">
                    <option value="${cat.getIDCategoria()}"><c:out value="${cat.getCategoria()}"></c:out></option>
                </c:forEach>
              </select>
              </div>
            </div>
            
            <div class="input-container form-floating">
              <input type="text" name="ModalTitulo" id="modal-postTitle" class="input-titulo" placeholder="Titulo">
              <textarea name="ModalContenido" rows="3" placeholder="Contenido" id="modal-postContent"" style="height: 100px" ></textarea>
              <img src="" style="max-height: 300px; max-width: 300px;" alt="" class="" id="previewModal">
              <div class="add-to-post">
              <label for="modal-file-upload"><a style="color: #6610F2">Agregar una foto <i class="bi bi-image-fill"></i></a></label>
            <input value="Elegir foto." name="Modalfile-upload" id="modal-file-upload" type="file" accept="image/png, image/jpeg" class="btn btn-info boton-input" onchange="previewImageModal(event)"/>
            <input name="fotoOG" value="${UserObj.getFoto()}" style="display: none"/>
            </div>
          </div>
  
           </div>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
        <button type="submit" class="btn btn-primary">Modificar</button>
      </div>
        </form>
    </div>
  </div>
  </div>
 </div>
  
  
  
 
    </body>
    <script>
        
          function previewImageModal(event) {
            var input = event.target;
            var reader = new FileReader();

            reader.onload = function() {
                var img = document.getElementById('previewModal');
                img.src = reader.result;
            };

            reader.readAsDataURL(input.files[0]);
        }

        
    </script>
     <script>
              function previewImage(event) {
                  var input = event.target;
                  var reader = new FileReader();
                  
                  reader.onload = function(){
                      var img = document.getElementById('preview');
                      img.src = reader.result;
                  };
                  
                  reader.readAsDataURL(input.files[0]);
              }
          </script>
    <script>
        
         // Nueva función para llenar el modal con los datos de la publicación
  function loadPostDataToModal(post) {
      
    document.getElementById('modal-postId').value = post.id;
    document.getElementById('modal-postUsername').textContent = post.username;
    document.getElementById('modal-postTitle').value = post.title;
    document.getElementById('modal-postContent').value = post.content;
    document.getElementById('previewModal').src = post.image;
    // Seleccionar la categoría en el select
    let categorySelect = document.getElementById('modal-postCategory');
    for (let i = 0; i < categorySelect.options.length; i++) {
      if (categorySelect.options[i].value == post.categoryId) {
        categorySelect.selectedIndex = i;
        break;
      }
    }

    // Cambiar la imagen del usuario en el modal
    document.getElementById('modal-postUserPfp').src = post.userImage;
  }

  // Asocia la función a los botones de "Modificar publicación"
 // Asocia la función a los botones de "Modificar publicación"
  document.querySelectorAll('.dropdown-item.modify').forEach(button => {
    button.addEventListener('click', function() {
      // Simula obtener los datos de la publicación (esto se haría realmente mediante una solicitud AJAX o similar)
      let post = {
        id: this.dataset.id,
        username: this.dataset.username,
        title: this.dataset.title,
        content: this.dataset.content,
        image: this.dataset.image,
        categoryId: this.dataset.categoryId,
        userImage: this.dataset.userImage
      };
      loadPostDataToModal(post);
    });
  });
    </script>
    <script>
        document.querySelectorAll('.dropdown-item.delete').forEach(button => {
    button.addEventListener('click', function() {
        // Retrieve the ID from the data-id attribute
        let postId = this.dataset.id;
        
        // Set the ID to a hidden input in the modal or any other element
        document.getElementById('deleteModalPostId').value = postId;

       
    });
});
    </script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="script.js"></script>
</html>
