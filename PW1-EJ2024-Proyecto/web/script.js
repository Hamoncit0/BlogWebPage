var settingsMenu= document.querySelector(".settings-menu");
function settingsMenuToggle(){
    settingsMenu.classList.toggle("settings-menu-height");
}

var boolBqAvanzada = false;
var editarPerfil = false;
var pagActual = 1;
var cantPublicaciones = 4;

function limpiarDashboard(){
    $("#rowPublicaciones").empty();
}
function getPublicaciones(index) {
    console.log("GET PUBLICACIONES CON INDEX");
    $.ajax({
        url: "PublicacionServlet?index=" + index,
        type: "GET",
        dataType: "JSON",
        success: function(data) {
            console.log("data: " + data);

            // PAGINADOR
            limpiarDashboard();
            $("#paginador").empty();

            var claseAnt = "";
            if (pagActual == 1) claseAnt = "disabled";
            // Sirve para crear botones del paginador
            $("#paginador").append(
                $("<li>").addClass("page-item").addClass(claseAnt).append(
                    $("<a>").addClass("page-link").attr("href", "#").text("Anterior")
                )
            );

            // Para los botones de números
            const totalPublicaciones = data[0].TotalPublicaciones;
            const numPaginas = Math.ceil(totalPublicaciones / cantPublicaciones);

            for (var i = 0; i < numPaginas; i++) {
                var claseActual = '';
                if (pagActual == i + 1) claseActual = "active";
                // Sirve para crear botones del paginador
                $("#paginador").append(
                    $("<li>").addClass("page-item").addClass(claseActual).append(
                        $("<a>").addClass("page-link").attr("href", "#").text(i + 1)
                    )
                );
            }

            var claseSig = "";
            if (pagActual == numPaginas) claseSig = "disabled";
            // Sirve para crear botones del paginador
            $("#paginador").append(
                $("<li>").addClass("page-item").addClass(claseSig).append(
                    $("<a>").addClass("page-link").attr("href", "#").text("Siguiente")
                )
            );

            var UsuarioUnicoChido = $("#NombreUsuScriptUnico").data("usuario");
            // ROW PUBLICACIONES
            for (var i = 0; i < Object.keys(data).length; i++) {

                var FOTOUSUARIO = data[i].FotoUsu;
                if (FOTOUSUARIO == null) {
                    FOTOUSUARIO = "imgs/empty.png";
                } else {
                    FOTOUSUARIO = "./IMGSPFP/" + FOTOUSUARIO;
                }

                var ImagenPublicacion = data[i].Imagen;
                var imagen = null;
                if (ImagenPublicacion != null && ImagenPublicacion.trim() !== "") {
                    imagen = $("<img>").addClass("post-image").attr("src", "./IMGSPFP/" + ImagenPublicacion);
                }

                // Esta es la plantilla del post :(
                var postContainer = $("<div>").addClass("post-container").attr("value", data[i].IdPublicacion).append(
                    $("<div>").addClass("post-row").append(
                        $("<div>").addClass("user-profile").append(
                            $("<img>").addClass("rounded-pill").attr("src", FOTOUSUARIO),
                            $("<div>").append(
                                $("<p>").text(data[i].Usuario),
                                $("<span>").text(data[i].FechaAlta),
                                $("<br>"),
                                $("<span>").text("Categoría: " + data[i].Categoria)
                            )
                        )
                    )
                );

                // Condición para mostrar el dropstart solo si el usuario coincide
                if (data[i].Usuario == UsuarioUnicoChido) {
                    postContainer.find(".post-row").append(
                        $("<div>").addClass("dropstart").append(
                            $("<button>").addClass("btn btn-sm btn-light").attr("type", "button").attr("data-bs-toggle", "dropdown").append(
                                $("<i>").addClass("bi bi-three-dots-vertical")
                            ),
                            $("<ul>").addClass("dropdown-menu").append(
                                $("<li>").append(
                                    $("<a>").addClass("dropdown-item delete").attr("data-bs-toggle", "modal").attr("data-bs-target", "#exampleModal").attr("data-id", data[i].IdPublicacion).text("Eliminar publicacion.")
                                ),
                                $("<li>").append(
                                    $("<a>").addClass("dropdown-item modify").attr("data-bs-toggle", "modal").attr("data-bs-target", "#exampleModal1").attr("data-id", data[i].IdPublicacion).attr("data-username", data[i].Usuario).attr("data-title", data[i].Titulo).attr("data-content", data[i].Contenido).attr("data-image", "IMGSPFP/" + data[i].Imagen).attr("data-category-id", data[i].IDCategoria).attr("data-user-image", "IMGSPFP/" + data[i].FotoUsu).text("Modificar publicacion.")
                                )
                            )
                        )
                    );
                }

                postContainer.append(
                    $("<h6>").addClass("post-title").text(data[i].Titulo),
                    $("<p>").addClass("post-text").text(data[i].Contenido)
                );

                if (imagen) {
                    postContainer.append(imagen);
                }

                $("#rowPublicaciones").append(postContainer);
            }
            getPaginadorClicks();
        },
        error: function(err) {
            console.log("ERR: " + err);
        }
    });
}

function getPaginadorClicks() {
    $(".page-item").on("click", function() {
        var item = $(this).text();
        var nuevoIndice = pagActual;

        if (!$(this).hasClass("disabled")) {
            if (item == "Siguiente") {
                nuevoIndice = pagActual + 1;
            } else if (item == "Anterior") {
                nuevoIndice = pagActual - 1;
            } else {
                nuevoIndice = parseInt(item);
            }

            pagActual = nuevoIndice;

            var indice = (pagActual * cantPublicaciones) - cantPublicaciones;
            
            limpiarDashboard();
            getPublicaciones(indice);
        }
    });
}

//codigue chido este estaba en jsp mi vida
function previewImageModal(event) {
    var input = event.target;
    var reader = new FileReader();

    reader.onload = function() {
        var img = document.getElementById('previewModal');
        img.src = reader.result;
    };

    reader.readAsDataURL(input.files[0]);
}

function previewImage(event) {
    var input = event.target;
    var reader = new FileReader();
    
    reader.onload = function(){
        var img = document.getElementById('preview');
        img.src = reader.result;
    };
    
    reader.readAsDataURL(input.files[0]);
}
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
      document.querySelectorAll('.dropdown-item.delete').forEach(button => {
        button.addEventListener('click', function() {
            // Retrieve the ID from the data-id attribute
            let postId = this.dataset.id;
            
            // Set the ID to a hidden input in the modal or any other element
            document.getElementById('deleteModalPostId').value = postId;
    
           
        });
    });