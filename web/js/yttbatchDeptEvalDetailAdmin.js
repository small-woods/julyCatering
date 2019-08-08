$(document).ready(function () {

    //--------------------------
    //动态绑定为按钮添加事件
    $("body").on("click", ".toDetails",showDetail );
    $("body").on("click", ".deleteEval",deleteEval );
    //自动加载第一页数据
    loadEvalListDate(1);
});

function showDetail(){

    var hrid=getId();
    var btid=$($($($(this).parent()).parent()).children()[0]).html();
    var url="batchDeptEvalDetailAdmin.html?id="+hrid+"&btid="+btid ;

    window.location.href=url;
}

function deleteEval() {
    var btid=$($($($(this).parent()).parent()).children()[0]).html();
    layer.confirm('您确定要删除该任务么吗?这将无法恢复',{btn: ['确定', '取消'],title:"删除确认"}, function(){
        var url="EvalTaskManage";
        $.ajax({
            type: "post",
            url: url,
            data: {btid:btid,type:"delete",id:getId()},
            dataType: "text",
            success: function(data){
                if(data=="deleted") {
                    window.location.reload();
                    layer.msg("已成功删除！",{icon:1,time:5});
                }
            }
        });
    });
}

function loadEvalListDate(page) {
    $(".nowPage").html(page.toString());
    $("tbody").html("");
    var id=getId();
    var url="EvalTaskManage?page="+page+"&hrid="+id;
    $.ajax({
        type: "post",
        url: url,
        data: {page:page,type:"deptEval",id:getId()},
        dataType: "text",
        success: function(data){
            var list=$.parseJSON(data.split("|")[0]);
            var page=data.split("|")[1];
            $(".maxPageValue").html(page);
            $.each(list, function(idx, eval){


                var evalItem='<tr>\n' +
                    '            <td>'+eval.btid+'</td>\n' +
                    '            <td>'+eval.batchname+'</td>\n' +
                    '            <td class="td-manage">\n' +
                    '                <button class="layui-btn toDetails" title="查看明细">查看明细</button>\n' +
                    '                <button class="layui-btn layui-btn-danger deleteEval" title="删除任务">删除任务</button>\n' +
                    '            </td>\n' +
                    '        </tr>';

                $("tbody").append(evalItem);
            });
        }
    });
}

function nextPage() {
    var page =parseInt($(".nowPage").html());
    if(page==parseInt($(".maxPageValue").html())){
        layer.msg("已经是最末页",{icon:2,time:2000});
        return false;
    }else{
        page+=1;
    }
    loadEvalListDate(page)
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
    }
    loadEvalListDate(page)
    return false;
}

/*跳转到第一页*/
function firstPage() {
    var page =parseInt($(".nowPage").html());
    if(page==1){
        layer.msg("已经是最前页",{icon:2,time:2000});
        return false;
    }else{
        page=1;
    }
    loadEvalListDate(page)
    return false;
}

/*跳转到末页*/

function endPage() {
    var page =parseInt($(".nowPage").html());
    if(page==parseInt($(".maxPageValue").html())){
        layer.msg("已经是最末页",{icon:2,time:2000});
        return false;
    }else{
        page=parseInt($(".maxPageValue").html());
    }
    loadEvalListDate(page)
    return false;
}

function getId() {
    return $('#id', parent.document).html();
}

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}