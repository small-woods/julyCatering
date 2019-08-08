$(document).ready(function () {
    $("body").on("click", ".todelete",deletefood);
    $("body").on("click","toupdata",updatefood);
    selectFoodinfo(1);
    getPageCount();
});

/*
 *动态删除节点
 */
function deletefood() {
    var btid=$($($($(this).parent()).parent()).children()[1]).html();
    console.log("btid: "+btid);
    layer.confirm('您确定要删除该美食吗?这将无法恢复',{btn: ['确定', '取消'],title:"删除确认"}, function(){
        $.ajax({
            type: "post",
            url: "/updateFood",
            data: {btid:btid,type:"delete"},
            dataType: "text",
            success: function(data){
                console.log("data: "+data);
                if(data==1) {
                    window.location.reload();
                    layer.msg("已成功删除！",{icon:1,time:5});
                }
            }
        });

    });
}

/*
 *更新美食信息
 */
function updatefood() {
    alert("ssss");
    var foodno = $($($($(this).parent()).parent()).children()[1]).html();
    console.log("foodno: "+foodno);
}

/*请求从获得页面总数*/
function getPageCount() {
    $.ajax({
        type: "post",
        url: "/foodpageCount",
        dataType: "text",
        success: function(data) {
            if (data % 5 != 0) {
                $(".maxPageValue").html(parseInt(data /5 ) + 1);
            } else {
                $(".maxPageValue").html(parseInt(data / 5));
            }
        }
    });

}

function nextPage() {
    page =parseInt($(".nowPage").html());
    if(page==parseInt($(".maxPageValue").html())){
        layer.msg("已经是最末页",{icon:2,time:2000});
        return false;
    }else{
        page+=1;
        $(".nowPage").html(page);
    }
    console.log("下一页page: "+page);
    $("#tbodyfood").html("");
    selectFoodinfo(page);
    return false;
}
/*显示上一页*/
function lastPage() {
    var page =parseInt($(".nowPage").html());
    if(page==1){
        layer.msg("已经是最前页",{icon:2,time:2000});
        return false;
    }else{
        page-=1;
        $(".nowPage").html(page);
    }
    console.log("显示上一页page: "+page);
    $("#tbodyfood").html("");
    selectFoodinfo(page);
}

/*跳转到第一页*/
function firstPage() {
    var page =parseInt($(".nowPage").html());
    if(page==1){
        layer.msg("已经是最前页",{icon:2,time:2000});
        return false;
    }else{
        page=1;
        $(".nowPage").html(page);
        console.log("跳转到第一页page: "+page);
    }
    $("#tbodyfood").html("");
    selectFoodinfo(page);
}

/*跳转到末页*/

function endPage() {
    var page =parseInt($(".nowPage").html());
    if(page==parseInt($(".maxPageValue").html())){
        layer.msg("已经是最末页",{icon:2,time:2000});
        return false;
    }else{
        page=parseInt($(".maxPageValue").html());
        $(".nowPage").html(page);
        console.log("跳转到末页page: "+page);
    }
    $("#tbodyfood").html("");
    selectFoodinfo(page);
    return false;
}

/*
 *分页查询数据
 */
function selectFoodinfo(currentpage) {
    $.ajax({
        url:"/uploadfoodimg",
        type:"post",
        dataType:"json",
        data:{currentPage:currentpage},
        success:function (data) {
            console.log(data);
            var con ="";
            $.each(data,function (i,dom) {
                con+='<tr>\n' +
                    '        <td>\n' +
                    '            <img src="'+dom.foodimg+' " alt="">\n' +
                    '        </td>\n' +
                    '        <td>'+ dom.foodname+'</td>\n' +
                    '        <td>'+ dom.foodtype+'</td>\n' +
                    '        <td>'+ dom.foodprice+'</td>\n' +
                    '        <td>'+ dom.foodinfo+'</td>\n' +
                    '        <td class="td-manage">\n' +
                    '        <button class="layui-btn todelete"  title="删除">删除</button>\n' +
                    '        </td>\n' +
                    '        </tr>'

            });
            $("#tbodyfood").append(con);
        }
    })
}
