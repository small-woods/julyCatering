$(document).ready(function () {
    selectordeinfo(1);
    getPageCount();
});



/*请求从获得页面总数*/
function getPageCount() {
    $.ajax({
        type: "post",
        url: "/orderCountpage",
        dataType: "text",
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
    $("#bodyorder").html("");
    selectordeinfo(page);
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
    $("#bodyorder").html("");
    selectordeinfo(page);
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
    $("#bodyorder").html("");
    selectordeinfo(page);
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
    $("#bodyorder").html("");
    selectordeinfo(page);
    return false;
}

/*
 *分页查询数据
 */
function selectordeinfo(currentpage) {
    $.ajax({
        url:"/uploadorderinfo",
        type:"post",
        dataType:"json",
        data:{currentPage:currentpage},
        success:function (data) {
            console.log(data);
            var con ="";
            $.each(data,function (i,dom) {
                con+=' <tr>\n' +
                    '        <td>'+dom.orderno+'</td>\n' +
                    '        <td>'+dom.tableno+'</td>\n' +
                    '        <td>'+dom.orderpeople+'</td>\n' +
                    '        <td>'+dom.ordertime+'</td>\n' +
                    '        <td>'+dom.orderallmoney+'</td>\n' +
                    '        <td>'+dom.ordertype+'</td>   \n' +
                    '        </tr>'

            });
            $("#bodyorder").append(con);
        }
    })
}


