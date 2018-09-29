(function ($) {

    window.Ewin = function () {
        var html = '<div id="[Id]" class="modal fade" role="dialog" aria-labelledby="modalLabel">' +
            '<div class="modal-dialog modal-sm">' +
            '<div class="modal-content">' +
            '<div class="modal-header">' +
            '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>' +
            '<h4 class="modal-title" id="modalLabel">[Title]</h4>' +
            '</div>' +
            '<div class="modal-body">' +
            '<p>[Message]</p>' +
            '</div>' +
            '<div class="modal-footer">' +
            '<button type="button" class="btn btn-default cancel" data-dismiss="modal">[BtnCancel]</button>' +
            '<button type="button" class="btn btn-primary ok" data-dismiss="modal">[BtnOk]</button>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>';

        var reg = new RegExp("\\[([^\\[\\]]*?)\\]", 'igm');
        var generateId = function () {
            var date = new Date();
            return 'mdl' + date.valueOf();
        };
        var init = function (options) {
            options = $.extend({}, {
                title: "操作提示",
                message: "提示内容",
                btnok: "确定",
                btncl: "取消",
                width: 200,
                auto: false
            }, options || {});
            var modalId = generateId();
            var content = html.replace(reg, function (node, key) {
                return {
                    Id: modalId,
                    Title: options.title,
                    Message: options.message,
                    BtnOk: options.btnok,
                    BtnCancel: options.btncl
                }[key];
            });
            $('body').append(content);
            $('#' + modalId).modal({
                width: options.width,
                backdrop: 'static'
            });
            $('#' + modalId).on('hide.bs.modal', function (e) {
                $('body').find('#' + modalId).remove();
            });
            return modalId;
        }

        return {
            alert: function (options) {
                if (typeof options == 'string') {
                    options = {
                        message: options
                    };
                }
                var id = init(options);
                var modal = $('#' + id);
                modal.find('.ok').removeClass('btn-success').addClass('btn-primary');
                modal.find('.cancel').hide();

                return {
                    id: id,
                    on: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.find('.ok').click(function () { callback(true); });
                        }
                    },
                    hide: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.on('hide.bs.modal', function (e) {
                                callback(e);
                            });
                        }
                    }
                };
            },
            confirm: function (options) {
                var id = init(options);
                var modal = $('#' + id);
                modal.find('.ok').removeClass('btn-primary').addClass('btn-success');
                modal.find('.cancel').show();
                return {
                    id: id,
                    on: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.find('.ok').click(function () { callback(true); });
                            modal.find('.cancel').click(function () { callback(false); });
                        }
                    },
                    hide: function (callback) {
                        if (callback && callback instanceof Function) {
                            modal.on('hide.bs.modal', function (e) {
                                callback(e);
                            });
                        }
                    }
                };
            }
        }
    }();
})(jQuery);
var newDate = new Date();
Date.prototype.format = function (format) {
    var date = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1
                ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
        }
    }
    return format;
};
fmtDateTime = function (value, row, index) {
    var val = "";
    if (value != 0 && value != null) {
        var timestamp = value;
        newDate.setTime(timestamp * 1000);
        val = newDate.format('yyyy-MM-dd hh:mm:ss');
    }
    return val;
};
fmtDate = function (value, row, index) {
    var val = "";
    if (value != 0 && value != null) {
        var timestamp = value;
        newDate.setTime(timestamp * 1000);
        val = newDate.format('yyyy-MM-dd');
    }
    return val;
};
var doSubmit = function (options) {
    this.default = {
        formId: '',
        url: '',
        type: 'post',
        handlerFun: '',
    };
    this.option = $.extend({}, this.default, options)
};
doSubmit.prototype = {
    run: function () {
        var json = {};
        $(".modal-body .form-group").find("input").each(function (index) {
            var name = $(this).attr("name");
            var value = $(this).val();
            json[name] = value;
        });
        if (this.option.handlerFun != undefined && !this.option.handlerFun(json)) {
            return false;
        }
        $.ajax({
            type: this.option.type,
            data: json,
            url: this.option.url,
            success: function (result) {
                console.log(result);
                if (result.code > 0) {
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
        var formId = this.option.formId;
        setTimeout(function () {
            if (formId != '') {
                $("#" + formId).bootstrapTable('refresh', {});
            } else {
                $("gridTable").bootstrapTable('refresh', {});
            }
        }, 100);

    }
};
var doSearch=function(options){
    this.default = {
        searchId:'',
        formId: '',
        handlerFun:''
    };
    this.option = $.extend({}, this.default, options)
};
doSearch.prototype={
    run:function () {
        var json = {};
        $(".modal-body .form-group").find("input").each(function (index) {
            var name = $(this).attr("name");
            var value = $(this).val();
            json[name] = value;
        });
        if (this.option.handlerFun != undefined && !this.option.handlerFun(json)) {
            return false;
        }
    }
};
getselect = function (formId) {
    if (formId == undefined) {
        formId = 'gridTable';
    }
    var rows = $('#' + formId).bootstrapTable('getSelections');
    var json = [];
    for (item in rows) {
        var id = rows[item].id;
        json.push(id);
    }
    var ids = JSON.stringify(json);
    ids=ids.replace("[","");
    ids=ids.replace("]","");
    return ids;
};
deleteData = function (ids, url, formId) {
    Ewin.confirm({ message: "确认要删除选择的数据吗？" }).on(function (e) {
       if (e){
           $.ajax({
               url: url,
               data: {ids: ids},
               type: "post",
               success: function (result) {
                   if (result.code > 0) {
                       toastr.info(result.msg);
                       $("#" + formId).bootstrapTable('refresh', {});
                   } else {
                       toastr.error(result.msg);
                   }
               }
           });
       }
    });
};

initDom=function (modalId,url,handleFun) {
    if (modalId==""){
        modalId="modal"
    }
    $('#'+modalId).on('show.bs.modal',function(event){
        var modal = $(this);
        var btnThis = $(event.relatedTarget);
        var id=btnThis.data('id');
        console.log(id);
        if (btnThis.data('id')==0){
            var target= $(".modal input").each(function (e) {
               $(this)[0].value='';
            });
        }else {
            $.ajax({
                url:url,
                type:"get",
                data:{id:id},
                success:function (msg) {
                    handleFun(msg);
                    console.log(msg);
                    for (var item in msg){
                        var target= $(".modal input[name="+item+"]");
                        if (target[0]!=undefined){
                            target[0].value=msg[item];
                        }
                    }
                }
            });
        }
})};