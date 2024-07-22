
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
                            )
                        ),
                        $("<div>").addClass("dropstart").append(

                        )
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
            }
            else if(item == "Anterior"){
                item = pagActual - 1;
            }
            pagActual = item;

            var index = (pagActual*cantPublicaciones) - cantPublicaciones;

            limpiarDashboard();
            getPublicaciones(index);
        }
    });
}