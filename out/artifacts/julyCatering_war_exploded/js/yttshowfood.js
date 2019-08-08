$(document).ready(function () {
    gettype1foodPageCount("招牌热菜");
    showFoodinfo(1,"招牌热菜","type1");
    $("#showtype1").on("click",function () {
        var foodType = $(this).html();
        console.log("foodType: "+foodType);
        gettype1foodPageCount(foodType);
        $("#type1").html("");
        showFoodinfo(1,"招牌热菜","type1");
    });
    $("#showtype2").on("click",function () {
        var foodType = $(this).html();
        console.log("foodType: "+foodType);
        gettype1foodPageCount(foodType);
        $("#type2").html("");
        showFoodinfo(1,"凉菜","type2");
    });
    $("#showtype3").on("click",function () {
        var foodType = $(this).html();
        console.log("foodType: "+foodType);
        gettype1foodPageCount(foodType);
        $("#type3").html("");
        showFoodinfo(1,"酒水饮料","type3");
    });
    $("#showtype4").on("click",function () {
        var foodType = $(this).html();
        console.log("foodType: "+foodType);
        gettype1foodPageCount(foodType);
        $("#type4").html("");
        showFoodinfo(1,"其他","type3");
    });
});

/*
 *动态删除节点
 */
// function deletefood() {
//     var btid=$($($($(this).parent()).parent()).children()[1]).html();
//     console.log("btid: "+btid);
//     layer.confirm('您确定要删除该美食吗?这将无法恢复',{btn: ['确定', '取消'],title:"删除确认"}, function(){
//         $.ajax({
//             type: "post",
//             url: "/updateFood",
//             data: {btid:btid,type:"delete"},
//             dataType: "text",
//             success: function(data){
//                 console.log("data: "+data);
//                 if(data==1) {
//                     window.location.reload();
//                     layer.msg("已成功删除！",{icon:1,time:5});
//                 }
//             }
//         });
//         // });
//
//     });
// }


/*请求从获得页面总数*/
function gettype1foodPageCount(foodType) {
    $.ajax({
        type: "post",
        url: "/typefoodPageCount",
        dataType: "text",
        data:{type:foodType},
        success: function(data){
            if(data%8 != 0){
                $(".maxPageValue").html(parseInt(data/8)+1);
            }else{
                $(".maxPageValue").html(parseInt(data/8));
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
    $("#type1").html("");
    showFoodinfo(page,"招牌热菜","type1");
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
    $("#type1").html("");
    showFoodinfo(page,"招牌热菜","type1");
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
    $("#type1").html("");
    showFoodinfo(page,"招牌热菜","type1");
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
    $("#type1").html("");
    showFoodinfo(page,"招牌热菜","type1");
    return false;
}

/*
 *分页查询数据
 */
function showFoodinfo(currentpage,foodType,type) {
    console.log("分页查询数据: "+currentpage,foodType,type);
    $.ajax({
        url:"/showfoodimg",
        type:"post",
        dataType:"json",
        data:{currentPage:currentpage,foodType:foodType},
        success:function (data) {
            console.log(data);
            var con ="";
            $.each(data,function (i,dom) {
                con+='<a class="card" href="#">\n' +
                    '\t\t\t\t\t\t\t<img src="'+dom.foodimg+'" alt="">\n' +
                    '\t\t\t\t\t\t\t<div class="card-heading"><strong id="xfoodname">'+dom.foodname+'</strong></div>\n' +
                    '\t\t\t\t\t\t\t<div class="card-content text-muted">'+dom.foodinfo+'</div>\n' +
                    '\t\t\t\t\t\t\t<div class="card-actions">\n' +
                    '\t\t\t\t\t\t\t\t<span id="price" style="color: red;font-size: 16px;">\n' +
                    '\t\t\t\t\t\t\t\t\tRMB&nbsp;<span id="xfoodprice">'+dom.foodprice+'</span>/份\n' +
                    '\t\t\t\t\t\t\t\t</span>\n' +
                    '\t\t\t\t\t\t\t\t<button type="button" class="btn btn-danger addcar"><i class="icon-plus"></i>来一份</button>\n' +
                    '\t\t\t\t\t\t\t</div>\n' +
                    '\t\t\t\t\t\t</a>'

            });
            $("#"+type).append(con);
        }
    })
}

function nextPage2() {
    page =parseInt($(".nowPage2").html());
    if(page==parseInt($(".maxPageValue2").html())){
        layer.msg("已经是最末页",{icon:2,time:2000});
        return false;
    }else{
        page+=1;
        $(".nowPage2").html(page);
    }
    console.log("下一页page: "+page);
    $("#type2").html("");
    showFoodinfo(page,"凉菜","type2");
    return false;
}
/*显示上一页*/
function lastPage2() {
    var page =parseInt($(".nowPage2").html());
    if(page==1){
        layer.msg("已经是最前页",{icon:2,time:2000});
        return false;
    }else{
        page-=1;
        $(".nowPage2").html(page);
    }
    console.log("显示上一页page: "+page);
    $("#type2").html("");
    showFoodinfo(page,"凉菜","type2");
}

/*跳转到第一页*/
function firstPage2() {
    var page =parseInt($(".nowPage2").html());
    if(page==1){
        layer.msg("已经是最前页",{icon:2,time:2000});
        return false;
    }else{
        page=1;
        $(".nowPage2").html(page);
        console.log("跳转到第一页page: "+page);
    }
    $("#type2").html("");
    showFoodinfo(page,"凉菜","type2");
}

/*跳转到末页*/

function endPage2() {
    var page =parseInt($(".nowPage2").html());
    if(page==parseInt($(".maxPageValue2").html())){
        layer.msg("已经是最末页",{icon:2,time:2000});
        return false;
    }else{
        page=parseInt($(".maxPageValue2").html());
        $(".nowPage2").html(page);
        console.log("跳转到末页page: "+page);
    }
    $("#type2").html("");
    showFoodinfo(page,"凉菜","type2");
    return false;
}
function nextPage3() {
    page =parseInt($(".nowPage3").html());
    if(page==parseInt($(".maxPageValue3").html())){
        layer.msg("已经是最末页",{icon:2,time:2000});
        return false;
    }else{
        page+=1;
        $(".nowPage3").html(page);
    }
    console.log("下一页page: "+page);
    $("#type3").html("");
    showFoodinfo(page,"酒水饮料","type3");
    return false;
}
/*显示上一页*/
function lastPage3() {
    var page =parseInt($(".nowPage3").html());
    if(page==1){
        layer.msg("已经是最前页",{icon:2,time:2000});
        return false;
    }else{
        page-=1;
        $(".nowPage3").html(page);
    }
    console.log("显示上一页page: "+page);
    $("#type3").html("");
    showFoodinfo(page,"酒水饮料","type3");
}

/*跳转到第一页*/
function firstPage3() {
    var page =parseInt($(".nowPage3").html());
    if(page==1){
        layer.msg("已经是最前页",{icon:2,time:2000});
        return false;
    }else{
        page=1;
        $(".nowPage3").html(page);
        console.log("跳转到第一页page: "+page);
    }
    $("#type3").html("");
    showFoodinfo(page,"酒水饮料","type3");
}

/*跳转到末页*/

function endPage3() {
    var page =parseInt($(".nowPage3").html());
    if(page==parseInt($(".maxPageValue3").html())){
        layer.msg("已经是最末页",{icon:2,time:2000});
        return false;
    }else{
        page=parseInt($(".maxPageValue3").html());
        $(".nowPage3").html(page);
        console.log("跳转到末页page: "+page);
    }
    $("#type3").html("");
    showFoodinfo(page,"酒水饮料","type3");
    return false;
}
function nextPage4() {
    page =parseInt($(".nowPage4").html());
    if(page==parseInt($(".maxPageValue4").html())){
        layer.msg("已经是最末页",{icon:2,time:2000});
        return false;
    }else{
        page+=1;
        $(".nowPage4").html(page);
    }
    $("#type4").html("");
    showFoodinfo(page,"其他","type4");
    return false;
}
/*显示上一页*/
function lastPage4() {
    var page =parseInt($(".nowPage4").html());
    if(page==1){
        layer.msg("已经是最前页",{icon:2,time:2000});
        return false;
    }else{
        page-=1;
        $(".nowPage4").html(page);
    }
    $("#type4").html("");
    showFoodinfo(page,"其他","type4");
}

/*跳转到第一页*/
function firstPage4() {
    var page =parseInt($(".nowPage4").html());
    if(page==1){
        layer.msg("已经是最前页",{icon:2,time:2000});
        return false;
    }else{
        page=1;
        $(".nowPage4").html(page);
        console.log("跳转到第一页page: "+page);
    }
    $("#type4").html("");
    showFoodinfo(page,"其他","type4");
}

/*跳转到末页*/

function endPage4() {
    var page =parseInt($(".nowPage4").html());
    if(page==parseInt($(".maxPageValue4").html())){
        layer.msg("已经是最末页",{icon:2,time:2000});
        return false;
    }else{
        page=parseInt($(".maxPageValue4").html());
        $(".nowPage4").html(page);
        console.log("跳转到末页page: "+page);
    }
    $("#type4").html("");
    showFoodinfo(page,"其他","type4");
    return false;
}


