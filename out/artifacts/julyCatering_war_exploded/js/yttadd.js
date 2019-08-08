$(function () {
    $(".nav>li").click(function () {
        $(this).addClass("current");
        $(this).siblings().removeClass("current");
        var index = $(this).index();
        var $li = $(".content>li").eq(index);
        $li.addClass("detaileinfo");
        $li.siblings().removeClass("detaileinfo");
    })

});
