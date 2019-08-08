$(function () {
    $("body").on("click", ".addcar",addtomycar);
});
//第一步，先往订单详情表中添加订单号，美食名称，美食数量（美食数量第一次默认为1）

//第三部：为在购物车表里面为加减数量添加事件，修改数据库内容信息
function addtomycar() {
    var orderno = $('#userNum', parent.document).val();
    var foodname =$($($($($(this).parent()).parent()).children()[1]).children()[0]).html();
    var foodprice =$($($($(this).parent()).children()[0]).children()[0]).html();
    $.ajax({
        url:'ToCar',
        type:"post",
        dataType:"text",
        data:{
            type:'1',orderno:orderno,foodname:foodname
        },
        success:function (data) {
            //插入成功返回1
            if(data == 1){
                layer.msg("已经添加到购物车啦",{icon:6,time:2000});
            }
            else if(data == 0){
                layer.msg("加入失败，可以再次选择添加！",{icon:5,time:2000});
            }
            //插入失败3，则弹出消息框，已经添加，请前往购物车修改数量
            else if(data ==2){
                layer.msg("已经添加，请前往购物车修改数量",{icon:5,time:2000});
            }
        }
    })

}
