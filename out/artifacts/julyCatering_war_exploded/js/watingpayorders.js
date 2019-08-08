$(document).ready(function () {
    getordePageCount(0);
    showOrderinfo(1,"0");
});


/*请求从获得页面总数*/
function getordePageCount(ordeType) {
    $.ajax({
        type: "post",
        url: "/typeorderspagecount",
        dataType: "text",
        data:{ordeType:ordeType},
        success: function(data) {
            if (data % 8 != 0) {
                $(".maxPageValue").html(parseInt(data / 8) + 1);
            } else {
                $(".maxPageValue").html(parseInt(data / 8));
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
    $("#myorder").html("");
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
    $("#myorder").html("");
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
    $("#myorder").html("");
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
    $("#myorder").html("");
    selectFoodinfo(page);
    return false;
}

/*
 *分组分页查询数据
 */
function showOrderinfo(currentpage,ordetype) {
    console.log("分页查询数据: "+currentpage,ordetype);
    $.ajax({
        url:"/oploadorder",
        type:"post",
        dataType:"json",
        data:{currentPage:currentpage,ordetype:ordetype},
        success:function (data) {
            console.log(data);
            var con ="";
            $.each(data,function (i,dom) {
                con+='<tr>\n' +
                    '\t\t\t\t\t\t\t<td class="user-name">'+dom.tableno+'</td>\n' +
                    '\t\t\t\t\t\t\t<td class="user-email">'+dom.orderno+'</td>\n' +
                    '\t\t\t\t\t\t\t<td class="user-phone">'+dom.orderpeople+'</td>\n' +
                    '\t\t\t\t\t\t\t<td class="user-mobile">'+dom.orderallmoney+'</td>\n' +
                    '\t\t\t\t\t\t</tr>'

            });
            $("#myorder").append(con);
        }
    })
}



