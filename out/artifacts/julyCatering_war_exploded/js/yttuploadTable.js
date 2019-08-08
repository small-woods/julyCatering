$(document).ready(function () {
    $("body").on("click", ".deleteStaff",deleteStaff);
    selectStaffinfo(1);
    getPageCount();
    });

/*
 *动态删除节点
 */
function deleteStaff() {
    var btid=$($($($(this).parent()).parent()).children()[1]).html();
    layer.confirm('您确定要删除该员工吗?这将无法恢复',{btn: ['确定', '取消'],title:"删除确认"}, function(){
            $.ajax({
                type: "post",
                url: "/updateStaff",
                data: {btid:btid,type:"delete"},
                dataType: "text",
                success: function(data){
                    if(data==1) {
                        window.location.reload();
                        layer.msg("已成功删除！",{icon:1,time:5});
                    }
                }
            });
    });
}


/*请求从获得页面总数*/
function getPageCount() {
    $.ajax({
        type: "post",
        url: "/PageCount",
        dataType: "text",
        success: function(data){
            if(data%7 != 0){
                $(".maxPageValue").html(parseInt(data/7)+1);
            }else{
                $(".maxPageValue").html(parseInt(data/7));
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
    $("#tbodystaff").html("");
    selectStaffinfo(page);
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
    $("#tbodystaff").html("");
    selectStaffinfo(page);
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
    $("#tbodystaff").html("");
    selectStaffinfo(page);
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
    $("#tbodystaff").html("");
    selectStaffinfo(page);
    return false;
}

/*
 *分页查询数据
 */
function selectStaffinfo(currentpage) {
    $.ajax({
        url:"/uploadTable",
        type:"post",
        dataType:"json",
        data:{currentPage:currentpage},
        success:function (data) {
            var con ="";
            $.each(data,function (i,dom) {
                con+=' <tr>\n' +
                    '            <td>'+dom.staffname+'</td>\n' +
                    '            <td>'+dom.staffno+'</td>\n' +
                    '            <td>'+dom.password+'</td>\n' +
                    '            <td>'+dom.idcard+'</td>\n' +
                    '            <td>'+dom.degree+'</td>\n' +
                    '            <td>'+dom.birthday+'</td>\n' +
                    '            <td>'+dom.deptname+'</td>\n' +
                    '            <td class="td-manage">\n' +
                    '                <button class="layui-btn deleteStaff"  title="删除">删除</button>\n' +
                    '            </td>';

            });
            $("#tbodystaff").append(con);
        }
    })
}


