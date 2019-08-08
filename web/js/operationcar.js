$(function () {
    //type =4 表示删除购物车该行的内容
    $("body").on("click",".delnums",delnum);
    $("body").on("click",".addnums",addnum);

});

function check() {
    var tableno = $('#userTable', parent.document).val();
    var orderno = $('#userNum', parent.document).val();
   var money = $("#payprice").html();
    $.ajax({
        url: "/ToCar",
        type: "post",
        dataType: "text",
        data: {type: '5',orderno:orderno,money:money,tableno:tableno},
        success: function (data) {
            console.log(data);
            if(data == 1){
                window.location.href='cystl.html';
            }
            else if(data ==0){
                layer.msg("操作失败",{icon:5,time:2000});
            }
        }
    });
}
function addnum() {
    var foodname = $($($($($(this).parent()).parent()).parent()).children()[0]).html();
    var num = $($($(this).parent()).children()[1]).val();
    $.ajax({
        url: "/ToCar",
        type: "post",
        dataType: "text",
        data: {type: '3', foodname: foodname, op: 0},
        success: function (data) {
            console.log(data);
        }
    });
    $($($(this).parent()).children()[1]).val(parseInt(num) + 1);
    window.location.reload();
}

function delnum() {
    var foodname = $($($($($(this).parent()).parent()).parent()).children()[0]).html();
    var num = $($($(this).parent()).children()[1]).val();
    if(num>0){
        $.ajax({
            url: "/ToCar",
            type: "post",
            dataType: "text",
            data: {type: '3', foodname: foodname,op:1},
            success: function (data) {
                window.location.reload();
                console.log(data);
            }
        });
        $($($(this).parent()).children()[1]).val(parseInt(num)-1);
        window.location.reload();
    }else {
        layer.msg("不能再减少了哦！", {icon: 5, time: 2000});
    }


}