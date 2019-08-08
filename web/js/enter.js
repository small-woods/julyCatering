$(document).ready(function(){
    $("#ok").click(function(){
        enter();
    });
    $("#ok").keydown(function(event){
        if(event.keyCode==13){
            enter();
        }
    });
    $("#tableno").keydown(function(event){
        if(event.keyCode==13){
            enter();
        }
    });
    $("#tablenum").keydown(function(event){
        if(event.keyCode==13){
            enter();
        }
    });

    $("#no").click(function(){
        resert();
    });

});
function enter() {
    var tableno = $("#tableno").val();
    var tablenum = $("#tablenum").val();
    var url = location.search;
    var staffno;
    var result = new Object();
    if(url.indexOf("?")!=-1){
        var str = url.substr(1);
        staffno = str.match(/\d+/);
        console.log("staffno  "+staffno);
        console.log("str  "+str);
    }
    console.log(staffno[0]);

    $.ajax({
        url:"/EnterStaff",
        type: "post",
        data: {tableno:tableno,tablenum:tablenum,staffno:staffno[0]},
        dataType: "text",
        sync:true,
        success:function (result) {
            var rse = result.split('&');
            var code;
            if(rse[0] ==1){
                window.location.href = ('yttstaffindex.html?tableno='+tableno+'&tablenum='+tablenum+'&staffno='+staffno[0]+'&code='+ rse[1]);
            }
            //餐桌有人使用中....
            else{
                layer.confirm('该餐桌已有用户在用餐，是否要继续进入？',{btn: ['确定', '取消'],title:"进入确认"}, function(){
                    window.location.href = ('yttstaffindex.html?tableno='+rse[2]+'&tablenum='+rse[3]+'&staffno='+ staffno[0]+'&code='+rse[1]);
                })


            }
        }
    })
}

function resert() {
    $("#tableno").val("");
   $("#tablenum").val("");
}
