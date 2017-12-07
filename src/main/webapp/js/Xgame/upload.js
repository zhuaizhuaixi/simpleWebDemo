var Uploader = function (form, delBtn) {
    this.$form = $(form);
    this.$delBtn = $(delBtn);
    this.successHandle = function (json) {};
    this.iframeHtml = "<iframe name='iframe' width='0' height='0' frameborder='0'></iframe>";
    this.deleteHtml = "<input type='hidden' name='delFile' value='false'/>";
    var $iframe = $("iframe", this.$form);
    if ($iframe.length == 0) {
        this.$form.append(this.iframeHtml);
        this.$form.append(this.deleteHtml);
    }
    this.$form.attr("method", "POST");
    this.$form.attr("target", "iframe");
    this.$form.attr("enctype", "multipart/form-data");
    var that =this;
    this.$delBtn.unbind("onclick").bind("click", function () {
        if($("input[name='delFile']", that.$form).val()=="false"){
            $("input[name='delFile']", that.$form).val(true);
            $(".l-btn-text",that.$delBtn).text("已标记删除")
        }else{
            $("input[name='delFile']", that.$form).val(false);
            $(".l-btn-text",that.$delBtn).text("删除")
        }


    });

}
Uploader.prototype.submit = function (url, success) {
    this.$form.attr("action", url);
    if (success != null) {
        this.successHandle = success;
    }
    $.messager.progress({title: "", msg: "处理中,请稍等...", text: "", interval: 300});
    this.$form.submit();
}
Uploader.prototype.success = function (json) {
    $.messager.progress('close');
    this.successHandle(json);
}
