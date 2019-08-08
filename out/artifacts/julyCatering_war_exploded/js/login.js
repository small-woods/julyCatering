/**
 * Created by ting on 2019/6/23.
 */
$(document).ready(function(){
    $("#loginButton").click(function(){
        login();
    });
    $("#loginButton").keydown(function(event){
        if(event.keyCode==13){
            login();
        }
    });
    $("#password").keydown(function(event){
        if(event.keyCode==13){
            login();
        }
    });
    $("#id").keydown(function(event){
        if(event.keyCode==13){
            login();
        }
    });

});

function login(){

    $("#emptyId").addClass("hidden");
    $("#emptyPassword").addClass("hidden");
    $("#tip").addClass("hidden");

    var id=$("#id").val();
    var password=$("#password").val();
    var type=$("#type").val();
    if(id==""){
        $("#emptyId").removeClass("hidden");
        return 0;
    }
    if(password==""){
        $("#emptyPassword").removeClass("hidden");
        return 0;
    }
    console.log('login');

    $.ajax({
        url: "/Login",
        type: "post",
        data: {id:id,password:password,type:type},
        dataType: "text",
        success: function(code){
            if(code=="error"){
                $("#tip").removeClass("hidden");

            }else if(code.split(",")[0]=="staff"){
                window.location.href=('staff.html?id='+code.split(",")[1]);
            }else if(code.split(",")[0]=="admin"){
                window.location.href=('admin.html?id='+code.split(",")[1]);
            }
        }
    });
}

