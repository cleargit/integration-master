(function ($) {

    window.Win=function () {
        var html="";
        var init=function (options) {
            $.expand({},{
                Title:"操作提示",
                Message:"提示内容",
                btnok: "确定",
                btncl: "取消",
                width: 200,
                auto: false
            },options)
        }
    }

}(jQuery)