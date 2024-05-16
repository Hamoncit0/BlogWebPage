var settingsMenu= document.querySelector(".settings-menu");
function settingsMenuToggle(){
    settingsMenu.classList.toggle("settings-menu-height");
}
document.addEventListener('DOMContentLoaded', function () {
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
});


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