var settingsMenu= document.querySelector(".settings-menu");
function settingsMenuToggle(){
    settingsMenu.classList.toggle("settings-menu-height");
}
/*document.addEventListener('DOMContentLoaded', function () {
    // Function to get post data based on post ID
    function getPostData(postId) {
        var postContainer = document.querySelector('.post-container[data-id="' + postId + '"]');
        return {
            title: postContainer.querySelector('.post-title').textContent,
            content: postContainer.querySelector('.post-text').textContent
        };
    }

    // Modify modal event listener
    var modifyModal = document.getElementById('modifyModal');
    modifyModal.addEventListener('show.bs.modal', function (event) {
        var button = event.relatedTarget;  // Button that triggered the modal
        var postId = button.getAttribute('data-id');  // Extract post ID from data-id attribute

        // Get post data
        var postData = getPostData(postId);

        // Update modal content
        modifyModal.querySelector('#modifyPostId').value = postId;
        modifyModal.querySelector('#modifyPostTitle').value = postData.title;
        modifyModal.querySelector('#modifyPostContent').value = postData.content;
    });

    // Delete modal event listener
    var deleteModal = document.getElementById('deleteModal');
    deleteModal.addEventListener('show.bs.modal', function (event) {
        var button = event.relatedTarget;  // Button that triggered the modal
        var postId = button.getAttribute('data-id');  // Extract post ID from data-id attribute

        // Update modal content
        deleteModal.querySelector('#deletePostId').value = postId;
    });
});*/

/*function getPublicaciones(index){
            console.log("GET PUBLICACIONES CON INDEX");
            $.ajax({
                url:"PublicacionServlet" + index
                , type: "GET"
                , dataType: "JSON"
                , success:function(data){
                    console.log("data", data);
                    //PAGINADOR
                    ${"#paginador"}.empty();
                    //PONER BOTONES  
                     ${"#paginador"}.append(
                                ${"<li>"}.addClass("page-item").append(
                                 ${"<li>"}.addClass("page-link").text("Antes")
                                             )
                    );
            
                    const totalPublicaciones = data[0].TotalPublicaciones;
                    const numPaginas = Math.ceil(totalPublicaciones/ cantPublicaciones);
                    for(var i = 0; i<numPaginas; i++){
                        var ClaseActual = '';
                        if(pagActual = ClaseActual){
                            
                            
                        }
                         ${"#paginador"}.append(
                                ${"<li>"}.addClass("page-item").append(
                                 ${"<li>"}.addClass("page-link").text(i+1)
                                             )
                         );
                    }
                    ${"#paginador"}.append(
                                ${"<li>"}.addClass("page-item").append(
                                 ${"<li>"}.addClass("page-link").text("Siguiente")
                                             )
                    );
            //row publicaciones
            for(i = 0; i<Object.keys(data).length; i++){
                
            }
            
                }
                , error:function(err){
                    console.log("data", err);
                }
            });
        }
        
        function getPaginadorClicks(){
            ${".page-items"}.on("click", function(){
                    var item = ${this}.text();
                    if(item == "siguiente"){
                        item = pagActual+1;
                    }
                    else if(item == "anterior"){
                        item = pagActual-1;
                    }
                    pagActual = item;
                        
                    
                    var index = (pagActual*cantPublicaciones - cantPublicaciones);
                    limpiarDashboard(); 
                    getPublicaciones(index);
            })
        }
*/
var boolBqAvanzada = false;
var editarPerfil = false;
var pagActual = 1;
var cantPublicaciones = 4;

function limpiarDashboard(){
    $("#rowPublicaciones").empty();
}

function getPublicaciones(index){
    console.log("GET PUBLICACIONES CON INDEX");
    $.ajax({
        url: "PublicacionServlet?index="+index
        , type: "GET"
        , dataType: "JSON"
        , success: function(data){
            console.log("data: "+ data);

            //PAGINADOR
            limpiarDashboard();
            $("#paginador").empty();
            
            var claseAnt = "";
            if(pagActual == 1) claseAnt = "disabled"
            //sirve para crear botones del paginador
            $("#paginador").append(
                $("<li>").addClass("page-item").addClass(claseAnt).append(
                    $("<a>").addClass("page-link").text("Anterior")
                )
            );

            //para los botones de numeros
            const totalPublicaciones = data[0].TotalPublicaciones;
            const numPaginas = Math.ceil(totalPublicaciones/cantPublicaciones);

            for(var i=0; i<numPaginas; i++){
                var claseActual = '';
                if(pagActual== i+1) claseActual = "active"
                //sirve para crear botones del paginador
                $("#paginador").append(
                    $("<li>").addClass("page-item").addClass(claseActual).append(
                        $("<a>").addClass("page-link").text(i+1)
                    )
                );

            }

            var claseSig = "";
            if(pagActual == numPaginas) claseSig = "disabled"
            //sirve para crear botones del paginador
            $("#paginador").append(
                $("<li>").addClass("page-item").addClass(claseSig).append(
                    $("<a>").addClass("page-link").text("Siguiente")
                )
            );


            //ROW PUBLICACIONES
            for(var i=0; i<Object.keys(data).length; i++){
                //esta es la plantilla del post :(
                $("#rowPublicaciones").append(
                    $("<div>").addClass("post-container").attr("value", data[i].IdPublicacion).append(
                        $("<div>").addClass("post-row").append(
                            $("<div>").addClass("user-profile").append(
                                $("<img>").addClass("rounded-pill").attr("src", "./IMGSPFP/" + data[i].FotoUsu),
                                $("<div>").append(
                                    $("<p>").text(data[i].Usuario),
                                    $("<span>").text(data[i].FechaAlta),
                                    $("<br>"),
                                    $("<span>").text("Categoría:" + data[i].Categoria)
                                )
                            ),
                            $("<div>").addClass("dropstart").append(
                                $("<button>").addClass("btn").addClass("btn-sm").addClass("btn-light").attr("type", "button").attr("data-bs-toggle", "dropdown").append(
                                    $("<i>").addClass("bi").addClass("bi-three-dots-vertical")
                                ),
                                $("<ul>").addClass("dropdown-menu").append(
                                    $("<li>").append( $("<a>").addClass("dropdown-item").addClass("delete").attr("data-bs-toggle", "modal").attr("data-bs-target", "#exampleModal").attr("data-id", data[i].IdPublicacion).text("Eliminar publicacion.")),
                                    
                                    $("<li>").append( $("<a>").addClass("dropdown-item").addClass("modify").attr("data-bs-toggle", "modal").attr("data-bs-target", "#exampleModal1").attr("data-id", data[i].IdPublicacion).attr("data-username", data[i].Usuario).attr("data-title", data[i].Titulo).attr("data-content", data[i].Contenido).attr("data-image", data[i].Imagen).attr("data-category-id", data[i].IDCategoria).attr("data-user-image", data[i].FotoUsu).text("Modificar publicacion."))
                                )
                            )
                        ),
                        $("<h6>").addClass("post-title").text(data[i].Titulo),
                        $("<p>").addClass("post-text").text(data[i].Contenido),
                        $("<img>").addClass("post-image").attr("src", "./IMGSPFP/" + data[i].Imagen)
                        
                    )
                );
            }
            getPaginadorClicks();

        }
        , error: function(err){
            console.log("ERR: "+ err);
        }
    })
}
function getPaginadorClicks(){
    $(".page-item").on("click", function(){
        var item = $(this).text();

        if(!$(this).hasClass("disabled")){


            if(item == "Siguiente"){
                item = pagActual + 1;
                console.log("pagActual en paginador clicks:" + pagActual);
            }
            else if(item == "Anterior"){
                item = pagActual - 1;
                console.log("pagActual en paginador clicks:" + pagActual);
            }else{
                console.log("pagActual en paginador clicks:" + pagActual);
            }
            pagActual = item;

            var indice = 0;
            
            indice = (pagActual * cantPublicaciones) - cantPublicaciones;
            
            limpiarDashboard();
            getPublicaciones(indice);
        }
    });
}