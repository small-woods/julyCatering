/**
 * Created by  on 2019/6/23.
 */
$(function(){
    $('.menus3 li').each(function(){
        $('.menus3 li').click(
            function(){
                var index=$(this).index();
                $('.menus3 .bg').css('left',(index-1)*500+'px');
                $('.menus3 li').css('color','#000');
                $(this).css('color','#fff');
                $('.tab2').removeClass('show')
            $('.tab2').eq(index-1).addClass('show')
            });
    });


    var url = location.search;
    console.log("url"+url);
    var tableno;
    var tablenum;
    var staffno;
    var code;
    var strs;
    if(url.indexOf("?")!=-1){
        var str = url.substr(1);
        strs = str.split("&");
        tableno = strs[0].match(/\d+/);
        tablenum = strs[1].match(/\d+/);
        staffno = strs[2].match(/\d+/);
        code = strs[3].match(/\d+/);
    }
    $("#userNo").val(staffno);
    $("#userTable").val(tableno);
    $("#users").val(tablenum);
    $("#userNum").val(code);



});