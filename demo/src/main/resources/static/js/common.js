// (function ($) {
//
//     window.EWin=function () {
//         var html="";
//         var init=function (options) {
//             this.data={
//                 id:'',
//                 Title:"操作提示",
//                 Message:"提示内容",
//                 btnok: "确定",
//                 btncl: "取消",
//                 width: 200,
//                 auto: false
//             };
//             $.expand({},this.data,options);
//              html="<div class='model fade'>" +
//                 "<div class='modal-dialog' role='document'>" +
//                 "<div class='model-content'>" +
//                 "<div class='modal-header'>" +
//                 "<h4 class='model-title'>"+this.data.title+"</h4>" +
//                 "</div>"+
//                  "<div class='modal-body'>" +
//                  "<span>"+this.data.Message+"</span>"+
//                  "</div>"+
//                 "</div>" +
//                 "</div>"+
//                 "</div>";
//              $('body').append(html);
//             return this.data.id;
//         };
//         return{
//             confrim:function () {
//                 var id=init({id:'hello',message:"hello world"});
//                 $("#"+id).modal();
//
//             }
//         }();
//     }
//
// })(jQuery);
var doSubmit=function(options){
    this.default={
        formId:'',
        url:'',
        type:'post',
        handlerFun: '',
    };
    this.option=$.extend({},this.default,options)
};
doSubmit.prototype={
    run:function () {
        var json={};
        $(".modal-body .form-group").find("input").each(function (index) {
            var name = $(this).attr("name")
            var value = $(this).val();
            json[name]=value;
        });
        if (this.option.handlerFun !=undefined && !this.option.handlerFun(json)){
            return false;
        }
        $.ajax({
            type: this.option.type,
            data: json,
            url: this.option.url,
            success: function (result) {
                console.log(result);
                if (result.code>0){
                    toastr.success(result.msg);
                    // toastr.options.positionClass = 'toast-top-center';
                    // toastr.warning('warning');
                    // toastr.info('info');
                    // toastr.success(result.msg);
                    // toastr.error('error');
                } else {
                    toastr.error(result.msg);
                }
            }
        });
        if (this.option.formId!=''){
            $("#"+this.option.formId).bootstrapTable('refresh');
        }else {
            $("gridTable").bootstrapTable('refresh');
        }

    }
};