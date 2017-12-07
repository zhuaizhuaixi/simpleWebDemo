$.extend($.fn.validatebox.defaults.rules, {
	comboxRequired : {
		// value 为需要校验的输入框的值 ,
		// param为使用此规则时存入的参数
		validator : function(value, params) {
			if(value=='请选择' || value==null ||value==''){
				return false;
			}else{
				return true;
			}
			
		},
		message : '该选择项为必选项'
	},
	checkPMenu : {
		// value 为需要校验的输入框的值 ,
		// param为使用此规则时存入的参数
		validator : function(value, params) {
			var id = $("input[name='id']").val();
			var name=$(this).next().prop("name");
			var selectValue =$("select[comboname="+name+"]").find("option[value='"+id+"']").html();
			if(value==selectValue&&value!='请选择'){
				return false;
			}else{
				return true;
			}
			
		},
		message : '不能选自己为父菜单'
	},
	idcard: {// 验证身份证
        validator: function (value) {
            return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value);
        },
        message: '身份证号码格式不正确'
    },
    minLength: {
        validator: function (value, param) {
            return value.length >= param[0];
        },
        message: '请输入至少（2）个字符.'
    },
    maxLength: {
        validator: function (value, param) {
            return value.length <= param[0];
        },
        message: '长度不能超过{0}个字符'
    },
    length: { 
    	validator: function (value, param) {
    		var len = $.trim(value).length;
    		return len >= param[0] && len <= param[1];
    	},
        message: "输入内容长度必须介于{0}和{1}之间."
    },
    phone: {// 验证电话号码
        validator: function (value) {
            return /^((\d2,3)|(\d{3}\-))?(0\d2,3|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
        },
        message: '格式不正确,请使用下面格式:020-88888888'
    },
    mobile: {// 验证手机号码
        validator: function (value) {
            return /^1(3|4|5|7|8)\d{9}$/i.test(value);
        },
        message: '手机号码格式不正确'
    },
    intOrFloat: {// 验证整数或小数
        validator: function (value) {
            return value.replace(/^\d+(\.\d+)?$/g,'').length==0;
        },
        message: '请输入数字，并确保格式正确'
    },
    integer: {// 验证整数 可正负数
        validator: function (value,param) {
            return value.replace(/^([+]?[0-9])|([-]?[0-9])+\d*$/g,'').length==0;
        },
        message: '请输入整数'
    },
    age: {// 验证年龄
        validator: function (value) {
            return /^(?:[1-9][0-9]?|1[01][0-9]|120)$/i.test(value);
        },
        message: '年龄必须是0到120之间的整数'
    },
    chinese: {// 验证中文
        validator: function (value) {
            return /^[\Α-\￥]+$/i.test(value);
        },
        message: '请输入中文'
    },
    english: {// 验证英语
        validator: function (value) {
            return /^[A-Za-z]+$/i.test(value);
        },
        message: '请输入英文'
    },
    faxno: {// 验证传真
        validator: function (value) {
            // return /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[
			// ]){1,12})+$/i.test(value);
            return /^((\d2,3)|(\d{3}\-))?(0\d2,3|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
        },
        message: '传真号码不正确'
    },
    zip: {// 验证邮政编码
        validator: function (value) {
            return /^[1-9]\d{5}$/i.test(value);
        },
        message: '邮政编码格式不正确'
    },
    name: {// 验证姓名，可以是中文或英文
        validator: function (value) {
            return /^[\Α-\￥]+$/i.test(value) | /^\w+[\w\s]+\w+$/i.test(value);
        },
        message: '请输入姓名'
    },
    date: {// 验证姓名，可以是中文或英文
        validator: function (value,param) {
            // 格式yyyy-MM-dd或yyyy-M-d
            return /^(?:(?!0000)[0-9]{4}([-]?)(?:(?:0?[1-9]|1[0-2])\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\1(?:29|30)|(?:0?[13578]|1[02])\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-]?)0?2\2(?:29))$/i.test(value);
        },
        message: '请输入合适的日期格式[yyyy-MM-dd]'
    },
    same: {
        validator: function (value, param) {
            if ($("#" + param[0]).val() != "" && value != "") {
                return $("#" + param[0]).val() == value;
            } else {
                return true;
            }
        },
        message: '两次输入的密码不一致！'
    },
    email: {
        validator: function (value, param) {
        	var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/; 
            return reg.test(value); 
        },
        message: '电子邮箱格式不正确'
    }
    
});