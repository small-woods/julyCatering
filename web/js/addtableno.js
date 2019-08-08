$(document).ready(function () {
    addTab();
    $("body").on("click", ".todel",deleteTable);
});


/*
 *动态删除节点
 */
function deleteTable() {
    var btid=$(this).html();
    console.log("btid: "+btid);
    layer.confirm('您确定要删除该餐桌吗?这将无法恢复',{btn: ['确定', '取消'],title:"删除确认"}, function(){
        $.ajax({
            type: "post",
            url: "/updateTab",
            data: {btid:btid,type:"delete"},
            dataType: "text",
            success: function(data){
                console.log("data: "+data);
                if(data==1) {
                    window.location.reload();
                    layer.msg("已成功删除！",{icon:1,time:20000});
                }
                else {
                    // layer.msg("不可删除，该餐桌正在使用中！",{icon:1,time:120});
                    layer.alert('不可删除，该餐桌正在使用中！', {
                        skin: 'layui-layer-molv'
                        ,closeBtn: 1
                        ,anim: 1
                        ,btn: ['确定']
                        ,icon: 6
                        ,yes:function(){
                            location.href='cyadminTable.html'
                        }});
                }
            }
        });

    });
}

/*
 *动态添加节点
 */

function addTab() {
    $.ajax({
        url:"/uploadtabs",
        type:"post",
        dataType:"json",
        success:function (data) {
            console.log(data);
            var con ="";
            $.each(data,function (i,dom) {
                con+='<li><span class="tblNo"><button class="tab todel">'+dom.tableno+'</button></span></li>'
            });
            $("#tableno").append(con);
        }
    })
}