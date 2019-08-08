$(function () {
    //第二步：查询相关美食信息，显示到订单表里面
    showtocar();
    //type =4 表示删除购物车该行的内容
    $("body").on("click",".todelcar",delmycar);

});

function showtocar() {
    var orderno = $('#userNum', parent.document).val();
    $.ajax({
        url: "/ToCar",
        type: "post",
        dataType: "json",
        data: {type: '2', orderno: orderno},
        success: function (data) {
            var con = "";
            var money=0;
            $.each(data, function (i, dom) {
                money+=parseInt(dom.foodnum * dom.foodprice);
                con += '<tr>\n' +
                    '\t\t<td>' + dom.foodname + '</td>\n' +
                    '\t\t<td>' + dom.foodprice + '</td>\n' +
                    '\t\t<td>\n' +
                    '\t\t\t<div class="content">\n' +
                    '\t\t\t\t<button type="button" class="minus delnums"><i class="fa fa-minus"></i></button>\n' +
                    '\t\t\t\t<input type="text" class="num" value="'+dom.foodnum+'" size="2" maxlength="2" style="width: 24px;">\n' +
                    '\t\t\t\t<button type="button" class="plus addnums"><i class="fa fa-plus" class="plus"></i></button>\n' +
                    '\t\t\t</div>\n' +
                    '\t\t</td>\n' +
                    '\t\t<td class="totalprice">' + dom.foodnum * dom.foodprice + '</td>\n' +
                    '\t\t<td><button class="btn btn-danger todelcar" type="button"><i class="icon icon-trash"></i>删除</button></td>\n' +
                    '\t</tr>'
            });
            $("#mycar").append(con);
            $("#payprice").html(money);
        }
    })

}


//根据美食名称删除美食
function delmycar() {
    var foodname = $($($($(this).parent()).parent()).children()[0]).html();
    $.ajax({
        url: "/ToCar",
        type: "post",
        dataType: "json",
        data: {type: 4, foodname: foodname},
        success: function (data) {
            console.log("删除： "+data);
            layer.msg("已经删除！", {icon: 1, time: 8000});
            window.location.reload();
        }
    })
}
