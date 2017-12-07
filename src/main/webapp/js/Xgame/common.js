/**
 * Created by Administrator on 2017/6/20.
 */
var _def_conf = {
    width: "800px",
    height: "400px",
    modal: true,
    collapsible: false,
    minimizable: false,
    maximizable: false

}
var openIframe = function (config, url, id) {
    if (id == null) {
        id = "iframeId";
    }
    var thisConifg = $.extend(_def_conf, config);
    $ifDiv = $('#' + id);
    if ($ifDiv.length != 0) {
        $ifDiv.parent().remove();
    }
    var height = '100%';
    if ($.isIE()) {
        height='100%';
    }else{
        height='100%';
    }
    $("body").append('<div id="' + id + '" style="overflow: hidden"><iframe id="' + id + 'Page" name="' + id + 'Page" src=""  frameborder="0" width="100%" height="'+height+'"></iframe></div>')
    $ifDiv = $('#' + id);
    $ifDiv.window(thisConifg);
    return $("#" + id + "Page").attr("src", url);
}
var closeIframe = function (id) {
    if (id == null) {
        id = "iframeId";
    }
    $ifDiv = $('#' + id);
    $ifDiv.window('close');
}
var getIframeHeight = function (id) {
    if (id == null) {
        id = "iframeId";
    }
    $ifDiv = $('#' + id);
    //需要减去弹出框标题以及边框高度
    var height = 0;
    if ($.isIE()) {
        height = $ifDiv.height();
    } else {
        height = $ifDiv.height();
    }
    return height;
}

var comboxOnBeforeLoad = function (param) {
    var $this = $(this);
    param.code = $("input[name='" + $this.attr("textboxname") + "']").val();
    param.type = $this.attr("dictType") != undefined ? $this.attr("dictType") : "";
    param.pType = $this.attr("pType") != undefined ? $this.attr("pType") : "";
    param.pCode = $this.attr("pCode") != undefined ? $this.attr("pCode") : "";
}
var comboxpOnSelect = function (record) {
    var $this = $(this);
    var childField = $this.attr("childField");
    if (childField != undefined && childField != null) {
        var childcombox = $("input[textboxname='" + childField + "']");
        childcombox.attr("dictType",record.value);
        childcombox.combobox('reload');
    }
}
var linkView = function(value,row,index){
    if(row != undefined){
        return "<a href='#' onclick=viewData('"+row.id+"')>"+value+"</a>";
    }
}
;(function($, window, document,undefined){
    if(!window.browser){

        var userAgent = navigator.userAgent.toLowerCase(),uaMatch;
        window.browser = {}

        /**
         * 判断是否为ie
         */
        function isIE(){
            return ("ActiveXObject" in window);
        }
        /**
         * 判断是否为谷歌浏览器
         */
        if(!uaMatch){
            uaMatch = userAgent.match(/chrome\/([\d.]+)/);
            if(uaMatch!=null){
                window.browser['name'] = 'chrome';
                window.browser['version'] = uaMatch[1];
            }
        }
        /**
         * 判断是否为火狐浏览器
         */
        if(!uaMatch){
            uaMatch = userAgent.match(/firefox\/([\d.]+)/);
            if(uaMatch!=null){
                window.browser['name'] = 'firefox';
                window.browser['version'] = uaMatch[1];
            }
        }
        /**
         * 判断是否为opera浏览器
         */
        if(!uaMatch){
            uaMatch = userAgent.match(/opera.([\d.]+)/);
            if(uaMatch!=null){
                window.browser['name'] = 'opera';
                window.browser['version'] = uaMatch[1];
            }
        }
        /**
         * 判断是否为Safari浏览器
         */
        if(!uaMatch){
            uaMatch = userAgent.match(/safari\/([\d.]+)/);
            if(uaMatch!=null){
                window.browser['name'] = 'safari';
                window.browser['version'] = uaMatch[1];
            }
        }
        /**
         * 最后判断是否为IE
         */
        if(!uaMatch){
            if(userAgent.match(/msie ([\d.]+)/)!=null){
                uaMatch = userAgent.match(/msie ([\d.]+)/);
                window.browser['name'] = 'ie';
                window.browser['version'] = uaMatch[1];
            }else{
                /**
                 * IE10
                 */
                if(isIE() && !!document.attachEvent && (function(){"use strict";return !this;}())){
                    window.browser['name'] = 'ie';
                    window.browser['version'] = '10';
                }
                /**
                 * IE11
                 */
                if(isIE() && !document.attachEvent){
                    window.browser['name'] = 'ie';
                    window.browser['version'] = '11';
                }
            }
        }

        /**
         * 注册判断方法
         */
        if(!$.isIE){
            $.extend({
                isIE:function(){
                    return (window.browser.name == 'ie');
                }
            });
        }
        if(!$.isChrome){
            $.extend({
                isChrome:function(){
                    return (window.browser.name == 'chrome');
                }
            });
        }
        if(!$.isFirefox){
            $.extend({
                isFirefox:function(){
                    return (window.browser.name == 'firefox');
                }
            });
        }
        if(!$.isOpera){
            $.extend({
                isOpera:function(){
                    return (window.browser.name == 'opera');
                }
            });
        }
        if(!$.isSafari){
            $.extend({
                isSafari:function(){
                    return (window.browser.name == 'safari');
                }
            });
        }
    }
})(jQuery, window, document);