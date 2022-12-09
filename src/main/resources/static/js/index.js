 function moveToProjectOne() {
    $.ajax({
            type: "GET",
            url: "/board/boardOne",

        });
}
function moveToLogIn(){
        document.location.href='/user/logIn.html';
    }
    function moveToProjectMake(){
            document.location.href='/board/projectMake.html';
        }