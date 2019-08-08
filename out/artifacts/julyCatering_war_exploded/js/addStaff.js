$(document).ready(function () {
    $("#file").change(function () {
        var path=$("#file").val().split("\\");
        var fileName=path[path.length-1];
        var format="^.*\.(?:xls|xl|xla|xlt|xlm|xlc|xlw|xlsx)$";
        if(!fileName.match(format)){
            layer.msg('仅支持xls、xl、xla等表格类型文件!',{icon: 5,time:2000});
            reset();
        }
        $("#fileInfo").val(fileName);
    });
});

function check() {
    if( $("#file").val()==undefined|| $("#file").val()==null|| $("#file").val()==""){
        layer.msg('请先选择文件！',{icon: 6,time:2000});
        return false;
    }
    layer.msg('正在上传数据！请稍后...',{icon:3,time:1000*60*60});
    $.ajax({
        url:"/uploadFile",
        type:"post",
        dataType:"text",
        async:false,
        contentType:"multipart/form-data",
        success:function (data) {
            console.log(data);
        }
    });
    return true;
}


function reset() {
    $("#file").val(null);
    $("#fileInfo").val(null);

    return false;
}
function cancel() {
    return reset();
}