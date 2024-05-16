var settingsMenu= document.querySelector(".settings-menu")
function settingsMenuToggle(){
    settingsMenu.classList.toggle("settings-menu-height");
}
/*function getPublicaciones(index){

    console.log("GET PUBLICACIONES CON INDEX");
    $.ajax({
        url:"/PublicacionServlet"
        , type: "GET"
        , dataType: "JSON"
        , success: function(){
            console.log("data", data);
            ${"#paginador"}.empty();
            ${"#paginador"}.append(
                $("li").addClass("page-item").append(
                    ${"a"}.addClass("page-link").text("Anterior")
                )
            );
            var totalPaginas = Math.ceil(data[0].TotalPub);
            for(var i=0; i<totalPaginas; i++){

            }
            ${"#paginador"}.append(
                $("li").addClass("page-item").append(
                    $("a").addClass("page-link").text("Siguiente")
                )

            );

        }
        , error:function(){

        }
    });
}*/