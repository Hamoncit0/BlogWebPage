<%-- 
    Document   : logIn
    Created on : Apr 11, 2024, 12:03:38 AM
    Author     : yazmi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entidades.Usuario"%>
<%@ page import="java.io.*, java.net.*, java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>K-connect: Inicio de sesión</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" href="./css/scss/custom.css">
        <link rel="stylesheet" href="./css/style.css">
    </head>
    <body>
        <script type="text/javascript">
        
        window.onload = function() {
            var alertMessage = <%= request.getAttribute("idk") %>;
            if (alertMessage === 1) {
                alert("Usuario no encontrado.");
            }
        };
    </script>
   
        <nav class="navbar navbar-expand-sm bg-primary navbar-dark">

            <div class="container-fluid">
              <!-- Links -->
              <ul class="navbar-nav">
                <li class="nav-item">
                  <a class="nav-link active" href="#">K-connect</a>
                </li>
                
              </ul>
              <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="#">.</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#">.</a>
                  </li>
              </ul>
            </div>
          
          </nav>
          
          <div class="fondo bg-light">
            <div class="presentacion">
                <H1>K-Connect</H1>
                <h2>La nueva red social para la chaviza.</h2>
            </div>
            <form id="logIn" action="LogInServlet" method="post">
                <div class="mb-3">
                    <label for="username" class="form-label">Nombre de usuario</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Contraseña</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <% %>
                <div class="boton">
                    <button type="submit" class="btn btn-primary btn-lg inicio" >Iniciar Sesión</button>
                </div>
                <div class="divider"></div>
                <div class="boton">
                    <button type="button" id="createAccount" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#myModal">Crear una cuenta nueva</button>
                </div>
            </form>
          </div>
    
          <!-- The Modal -->
          <div class="modal" id="myModal">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="">
                    <form id="signUp" action="SignUpServlet" method="post" enctype="multipart/form-data">
                           <h2>Registrate</h2>
                           <h3>Es rápido y sencillo</h3>
                           <div class="mb-3">
                               <label for="firstName"  class="form-label">Primer Nombre</label>
                               <input type="text" class="form-control" id="firstName"name="firstName" placeholder="ej. Maria, Diego" required>
                           </div>
                           <div class="mb-3">
                            <label for="secondName"  class="form-label">Segundo Nombre(opcional)</label>
                            <input type="text"  class="form-control" id="secondName" name="secondName" placeholder="ej. Maria, Diego" optional>
                            </div>
                           <div class="mb-3">
                               <label for="lastName" class="form-label">Apellido Materno</label>
                               <input type="text"  class="form-control" id="lastName" name="lastName" placeholder="ej. Perez, Ramirez" required>
                           </div>
                           <div class="mb-3">
                               <label for="lastName2" class="form-label">Apellido Paterno</label>
                               <input type="text"  class="form-control" id="lastName2" name="lastName2" placeholder="ej. Perez, Ramirez" required>
                           </div>
                           <div class="mb-3">
                               <label for="usernameSignUp" class="form-label">Nombre de usuario</label>
                               <input type="text"  class="form-control" id="usernameSignUp" name="usernameSignUp" placeholder="ej. Perlita23, Manganito98" required>
                           </div>
                           <div class="mb-3">
                               <label for="birthday" class="form-label">Fecha de nacimiento</label>
                               <input type="date"  class="form-control" id="birthday" name="birthday" required>
                           </div>
                           <div class="mb-3">
                               <label for="email" class="form-label">Correo</label>
                               <input type="email"  class="form-control" id="email" name="email" placeholder="ejemplo@gmail.com" required>
                           </div>
                           <div class="mb-3">
                               <label for="password" class="form-label">Contraseña</label>
                               <input type="password"  class="form-control" id="passwordSignUp" name="passwordSignUp" aria-describedby="passwordHelpBlock" required>
                               <div id="passwordHelpBlock" class="form-text">
                                   Tu contraseña debe de ser de 8-20 caracteres y debe contener mínimo una mayúscula, una minúscula, un número y un signo de puntación.
                               </div>
                           </div>
                           <div class="mb-3">
                            <label for="passwordConfirm" class="form-label">Confirmar contraseña</label>
                            <input type="password"  class="form-control" id="passwordConfirm" required>
                           
                        </div>
                           
                        <div class="profile-avatar">
                            <img src="imgs/image-feed.png" alt="" class="rounded-pill profile-picture" id="preview">
                            <label for="file-upload" class="btn btn-info">Elegir una foto</label>
                            <input style="display:none;" name="file-upload" id="file-upload" type="file" accept="image/png, image/jpeg" class="btn btn-info boton-input" onchange="previewImage(event)"/>
                           
                           </div>
                           <button type="submit" id="btnRegister" class="btn btn-primary">Registrarse</button>
                    </form>
                </div>
          
              </div>
            </div>
          </div>
          <div class="footer">
            <p>Copyright 2024 - K-Connect</p>
            <p>Politicas de privacidad</p>
            <p>Politicas de cookies</p>
           </div>
           <script>
        document.getElementById("btnRegister").addEventListener("click", function(event) {
            // Validar nombre
            var firstName = document.getElementById("firstName").value;
            var lastName = document.getElementById("lastName").value;
            if (!/^[A-Za-zÁÉÍÓÚáéíóúÑñ\s]+$/.test(firstName) || !/^[A-Za-zÁÉÍÓÚáéíóúÑñ\s]+$/.test(lastName)) {
                alert("Los nombres y apellidos deben contener solo letras.");
                event.preventDefault();
                return;
            }
        
            // Validar fecha de nacimiento
            var birthday = new Date(document.getElementById("birthday").value);
            var today = new Date();
            if (birthday >= today) {
                alert("La fecha de nacimiento debe ser anterior al día de hoy.");
                event.preventDefault();
                return;
            }
        
            // Validar correo electrónico
            var email = document.getElementById("email").value;
            if (!/\S+@\S+\.\S+/.test(email)) {
                alert("El correo electrónico no es válido.");
                event.preventDefault();
                return;
            }
        
            // Validar contraseña
            var password = document.getElementById("passwordSignUp").value;
            var passwordConfirm = document.getElementById("passwordConfirm").value;
            
            // Mínimo 8 caracteres
            if (password.length < 8) {
                alert("La contraseña debe tener al menos 8 caracteres.");
                event.preventDefault();
                return;
            }
        
            // Mínimo una letra mayúscula
            if (!/[A-Z]/.test(password)) {
                alert("La contraseña debe contener al menos una letra mayúscula.");
                event.preventDefault();
                return;
            }
        
            // Mínimo una letra minúscula
            if (!/[a-z]/.test(password)) {
                alert("La contraseña debe contener al menos una letra minúscula.");
                event.preventDefault();
                return;
            }
        
            // Mínimo un número
            if (!/\d/.test(password)) {
                alert("La contraseña debe contener al menos un número.");
                event.preventDefault();
                return;
            }
        
            // Mínimo un signo de puntuación
            if (!/[\W_]/.test(password)) {
                alert("La contraseña debe contener al menos un signo de puntuación.");
                event.preventDefault();
                return;
            }
        
            // Confirmar contraseña
            if (password !== passwordConfirm) {
                alert("La confirmación de la contraseña no coincide.");
                event.preventDefault();
                return;
            }
        
            // Si todas las validaciones pasan
            alert("Usuario registrado con éxito.");
        
            // Si todas las validaciones pasan, se envía el formulario
            document.getElementById("signUp").submit();
        });
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
    </body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="script.js"></script>
</html>
