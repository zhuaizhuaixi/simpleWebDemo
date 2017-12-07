/**************************************************************************************/
/*************************************数字的验证*****************************************/
/**************************************************************************************/

/**
 * 检查输入的字符是否为空 
 * 输入:str 字符串 
 * 返回:true 或 flase; true表示为空
 */
function isNull(value) {
	if ((value == null) || (value == undefined) || (value == "")
			|| (value == "")) {
		return true;
	} else {
		return false;
	}
}

/**
 * 检查输入的一串字符是否全部是数字
 * 输入:str  字符串
 * 返回:true 或 flase; true表示为数字
 */
function isNum(str) {
	return str.match(/\D/) == null;
}

/**
 * 检查输入的一串字符是否为小数
 * 输入:str  字符串
 * 返回:true 或 flase; true表示为小数
 */
function isDecimal(str) {
	if (str.match(/^-?\d+(\.\d+)?$/g) == null) {
		return false;
	} else {
		return true;
	}
}

/**
 * 检查输入的一串字符是否为整型数据
 * 输入:str  字符串
 * 返回:true 或 flase; true表示为小数
 */
function isInteger(str) {
	if (str.match(/^[-+]?\d*$/) == null) {
		return false;
	} else {
		return true;
	}
}

function isFloat(value) {
	var regex = /^(\d)+(\.?\d+)?$/;
	return regex.test(value);
}

/**************************************************************************************/
/*************************************字符的验证*****************************************/
/**************************************************************************************/
/**
 * 检查输入的一串字符是否是字符
 * 输入:str  字符串
 * 返回:true 或 flase; true表示为全部为字符 不包含汉字
 */
function isStr(str) {
	if (/[^\x00-\xff]/g.test(str)) {
		return false;
	} else {
		return true;
	}
}

/**
 * 检查输入的一串字符是否是英文或数字
 * 输入:str  字符串
 * 返回:true 或 flase; true表示为全部为字符 不包含汉字
 */
function isEngOrNum(str) {
	if (/[\W]/g.test(str)) {
		return false;
	} else {
		return true;
	}
}

/**
 * 检查输入的一串字符是否包含汉字
 * 输入:str  字符串
 * 返回:true 或 flase; true表示包含汉字
 */
function isChinese(str) {
	if (escape(str).indexOf("%u") != -1) {
		return true;
	} else {
		return false;
	}
}

/**
 * 检查输入的邮箱格式是否正确
 * 输入:str  字符串
 * 返回:true 或 flase; true表示格式正确
 */
function isEmail(str) {
	if (str
			.match(/[A-Za-z0-9_-]+[@](\S*)(net|com|cn|org|cc|tv|[0-9]{1,3})(\S*)/g) == null) {
		return false;
	} else {
		return true;
	}
}

/**
 * 检查输入的手机号码格式是否正确
 * 输入:str  字符串
 * 返回:true 或 flase; true表示格式正确
 */
function isMobilePhone(str) {
	if (str.match(/^(?:13\d|15[89])-?\d{5}(\d{3}|\*{3})$/) == null) {
		return false;
	} else {
		return true;
	}
}

/**
 * 检查输入的固定电话号码是否正确
 * 输入:str  字符串
 * 返回:true 或 flase; true表示格式正确
 */
function isTelephone(str) {
	if (str.match(/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/) == null) {
		return false;
	} else {
		return true;
	}
}

/**
 * 检查QQ的格式是否正确
 * 输入:str  字符串
 *  返回:true 或 flase; true表示格式正确
 */
function isQQ(str) {
	if (str.match(/^\d{5,10}$/) == null) {
		return false;
	} else {
		return true;
	}
}

/**
 * 检查输入的身份证号是否正确
 * 输入:str  字符串
 *  返回:true 或 flase; true表示格式正确
 */
function isCard(str) {
	//15位数身份证正则表达式
	var arg1 = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
	//18位数身份证正则表达式
	var arg2 = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[A-Z])$/;
	if (str.match(arg1) == null && str.match(arg2) == null) {
		return false;
	} else {
		return true;
	}
}

/**
 * 检查输入的IP地址是否正确
 * 输入:str  字符串
 *  返回:true 或 flase; true表示格式正确
 */
function isIP(str) {
	var arg = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
	if (str.match(arg) == null) {
		return false;
	} else {
		return true;
	}
}

/**
 * 检查输入的URL地址是否正确
 * 输入:str  字符串
 *  返回:true 或 flase; true表示格式正确
 */
function isURL(str) {
	if (str.match(/(http[s]?|ftp):\/\/[^\/\.]+?\..+\w$/i) == null) {
		return false
	} else {
		return true;
	}
}

/**
 * 检查输入的字符是否具有特殊字符
 * 输入:str  字符串
 * 返回:true 或 flase; true表示包含特殊字符
 * 主要用于注册信息的时候验证
 */
function isQuote(str) {
	var items = new Array("~", "`", "!", "@", "#", "$", "%", "^", "&", "*",
			"{", "}", "[", "]", "(", ")");
	items.push(":", ";", "'", "|", "\\", "<", ">", "?", "/", "<<", ">>", "||",
			"//");
	items.push("admin", "administrators", "administrator", "管理员", "系统管理员");
	items.push("select", "delete", "update", "insert", "create", "drop",
			"alter", "trancate");
	str = str.toLowerCase();
	for (var i = 0; i < items.length; i++) {
		if (str.indexOf(items[i]) >= 0) {
			return true;
		}
	}
	return false;
}
/**
 * 字符长度范围
 * @param isRequire 是否必须
 * @param dataId 元素id
 * @param tipName 
 * @param min 
 * @param max
 * @returns {Boolean}
 */
function checkData(isRequire, dataId, tipName, min, max) {
	var data = document.getElementById(dataId).value;
	var reg = eval("/^[a-zA-Z0-9\\u4E00-\\u9FFF]{" + min + "," + max + "}$/");
	if (isRequire && isNull(trim(data))) {
		ldDialog.alert(tipName + "\u4e0d\u80fd\u4e3a\u7a7a","warning",function(){
			document.getElementById(dataId).focus();
		});
		return false;
	}
	if (!isNull(trim(data))) {
		if (!reg.test(trim(data))) {
			ldDialog.alert(tipName + "\u5fc5\u987b\u7531" + min + "\u81f3" + max + "\u4f4d\u5b57\u6bcd\u3001\u6570\u5b57\u6216\u6c49\u5b57\u7ec4\u6210!","warning",function(){
				document.getElementById(dataId).focus();
			});
			return false;
		}
	}
	return true;
}

/**************************************************************************************/
/*************************************时间的验证*****************************************/
/**************************************************************************************/

/**
 * 检查日期格式是否正确
 * 输入:str  字符串
 * 返回:true 或 flase; true表示格式正确
 * 注意：此处不能验证中文日期格式
 * 验证短日期（2007-06-05）
 */
function isDate(str) {
	//var value=str.match(/((^((1[8-9]\d{2})|([2-9]\d{3}))(-)(10|12|0?[13578])(-)(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(11|0?[469])(-)(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(0?2)(-)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)(-)(0?2)(-)(29)$)|(^([3579][26]00)(-)(0?2)(-)(29)$)|(^([1][89][0][48])(-)(0?2)(-)(29)$)|(^([2-9][0-9][0][48])(-)(0?2)(-)(29)$)|(^([1][89][2468][048])(-)(0?2)(-)(29)$)|(^([2-9][0-9][2468][048])(-)(0?2)(-)(29)$)|(^([1][89][13579][26])(-)(0?2)(-)(29)$)|(^([2-9][0-9][13579][26])(-)(0?2)(-)(29)$))/);
	var value = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
	if (value == null) {
		return false;
	} else {
		var date = new Date(value[1], value[3] - 1, value[4]);
		return (date.getFullYear() == value[1]
				&& (date.getMonth() + 1) == value[3] && date.getDate() == value[4]);
	}
}

/**
 * 检查时间格式是否正确
 * 输入:str  字符串
 * 返回:true 或 flase; true表示格式正确
 * 验证时间(10:57:10)
 */
function isTime(str) {
	var value = str.match(/^(\d{1,2})(:)?(\d{1,2})\2(\d{1,2})$/)
	if (value == null) {
		return false;
	} else {
		if (value[1] > 24 || value[3] > 60 || value[4] > 60) {
			return false
		} else {
			return true;
		}
	}
}

/**
 * 检查全日期时间格式是否正确
 * 输入:str  字符串
 * 返回:true 或 flase; true表示格式正确
 * (2007-06-05 10:57:10)
 */
function isFullTime(str) {
	//var value = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/);
	var value = str
			.match(/^(?:19|20)[0-9][0-9]-(?:(?:0[1-9])|(?:1[0-2]))-(?:(?:[0-2][1-9])|(?:[1-3][0-1])) (?:(?:[0-2][0-3])|(?:[0-1][0-9])):[0-5][0-9]:[0-5][0-9]$/);
	if (value == null) {
		return false;
	} else {
		//var date = new Date(isFullTime[1], isFullTime[3] - 1, isFullTime[4], isFullTime[5], isFullTime[6], isFullTime[7]);
		//return (date.getFullYear() == value[1] && (date.getMonth() + 1) == value[3] && date.getDate() == value[4] && date.getHours() == value[5] && date.getMinutes() == value[6] && date.getSeconds() == value[7]);
		return true;
	}

}

/**************************************************************************************/
/************************************身份证号码的验证*************************************/
/**************************************************************************************/

/**  
 * 身份证15位编码规则：dddddd yymmdd xx p
 * dddddd：地区码
 * yymmdd: 出生年月日
 * xx: 顺序类编码，无法确定
 * p: 性别，奇数为男，偶数为女
 * <p />
 * 身份证18位编码规则：dddddd yyyymmdd xxx y
 * dddddd：地区码
 * yyyymmdd: 出生年月日
 * xxx:顺序类编码，无法确定，奇数为男，偶数为女
 * y: 校验码，该位数值可通过前17位计算获得
 * <p />
 * 18位号码加权因子为(从右到左) Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2,1 ]
 * 验证位 Y = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ]
 * 校验位计算公式：Y_P = mod( ∑(Ai×Wi),11 )
 * i为身份证号码从右往左数的 2...18 位; Y_P为脚丫校验码所在校验码数组位置
 *
 */
var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];// 加权因子   
var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];// 身份证验证位值.10代表X   
function IdCardValidate(idCard) {
	idCard = trim(idCard.replace(/ /g, ""));
	if (idCard.length == 15) {
		return isValidityBrithBy15IdCard(idCard);
	} else if (idCard.length == 18) {
		var a_idCard = idCard.split("");// 得到身份证数组   
		if (isValidityBrithBy18IdCard(idCard)
				&& isTrueValidateCodeBy18IdCard(a_idCard)) {
			return true;
		} else {
			return false;
		}
	} else {
		return false;
	}
}

/**  
 * 判断身份证号码为18位时最后的验证位是否正确
 * @param a_idCard 身份证号码数组
 * @return
 */
function isTrueValidateCodeBy18IdCard(a_idCard) {
	var sum = 0; // 声明加权求和变量   
	if (a_idCard[17].toLowerCase() == 'x') {
		a_idCard[17] = 10;// 将最后位为x的验证码替换为10方便后续操作   
	}
	for (var i = 0; i < 17; i++) {
		sum += Wi[i] * a_idCard[i];// 加权求和   
	}
	valCodePosition = sum % 11;// 得到验证码所位置   
	if (a_idCard[17] == ValideCode[valCodePosition]) {
		return true;
	} else {
		return false;
	}
}

/**  
 * 通过身份证判断是男是女
 * @param idCard 15/18位身份证号码
 * @return 'female'-女、'male'-男
 */
function maleOrFemalByIdCard(idCard) {
	idCard = trim(idCard.replace(/ /g, ""));// 对身份证号码做处理。包括字符间有空格。   
	if (idCard.length == 15) {
		if (idCard.substring(14, 15) % 2 == 0) {
			return 'female';
		} else {
			return 'male';
		}
	} else if (idCard.length == 18) {
		if (idCard.substring(14, 17) % 2 == 0) {
			return 'female';
		} else {
			return 'male';
		}
	} else {
		return null;
	}
}

/**  
 * 验证18位数身份证号码中的生日是否是有效生日
 * @param idCard 18位书身份证字符串
 * @return
 */
function isValidityBrithBy18IdCard(idCard18) {
	var year = idCard18.substring(6, 10);
	var month = idCard18.substring(10, 12);
	var day = idCard18.substring(12, 14);
	var temp_date = new Date(year, parseFloat(month) - 1, parseFloat(day));
	// 这里用getFullYear()获取年份，避免千年虫问题   
	if (temp_date.getFullYear() != parseFloat(year)
			|| temp_date.getMonth() != parseFloat(month) - 1
			|| temp_date.getDate() != parseFloat(day)) {
		return false;
	} else {
		return true;
	}
}

/**  
 * 验证15位数身份证号码中的生日是否是有效生日
 * @param idCard15 15位书身份证字符串
 * @return
 */
function isValidityBrithBy15IdCard(idCard15) {
	var year = idCard15.substring(6, 8);
	var month = idCard15.substring(8, 10);
	var day = idCard15.substring(10, 12);
	var temp_date = new Date(year, parseFloat(month) - 1, parseFloat(day));
	// 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法   
	if (temp_date.getYear() != parseFloat(year)
			|| temp_date.getMonth() != parseFloat(month) - 1
			|| temp_date.getDate() != parseFloat(day)) {
		return false;
	} else {
		return true;
	}
}

//去掉字符串头尾空格   
function trim(str) {
	return str.replace(/(^\s*)|(\s*$)/g, "");
}

//检验字符串S是否为有效长度
function isValidLen(s, min, max) {
	var minLen = 0;
	var maxLen = 16;
	if (parseInt(min) != 0) {
		minLen = parseInt(min);
	}
	if (parseInt(max) > 0) {
		maxLen = parseInt(max);
	}
	if (s.length < minLen) {
		return false;
	}
	if (s.length > maxLen) {
		return false;
	}
	return true;
}
function isMaxLimit(s, max) {
	var maxLen = 16;
	//alert("length:"+s.length+" max:"+max);
	if (parseInt(max) > 0) {
		maxLen = parseInt(max);
	}
	if (s.length > maxLen) {
		return false;
	}
	return true;
}
function isMinLimit(s, min) {
	var minLen = 0;
	if (parseInt(min)) {
		minLen = parseInt(min);
	}
	if (s.length < minLen) {
		return false;
	}
	return true;
}

//判断是否为一定宽度的数字
function isLimitNumber(value, min, max) {
	if (typeof (min) != "number") {
		min = 0;
	}
	var regStr;
	if (typeof (max) != "number") {
		regStr = "^[0-9]{" + min + "}$";
	} else {
		regStr = "^[0-9]{" + min + "," + max + "}$";
	}
	var reg = new RegExp(regStr);
	return reg.test(value);
}

//只允许输入浮点数
function onlyDouble(elem) {
	elem.value = elem.value.replace(/^\./g,"");  //验证第一个字符是数字而不是. 
	var regStrs = [
       ['^0(\\d+)$', '$1'], //禁止录入整数部分两位以上，但首位为0
       ['[^\\d\\.]+$', ''], //禁止录入任何非数字和点
       ['\\.(\\d?)\\.+', '.$1'] //禁止录入两个以上的点
    ];
    for(i=0; i<regStrs.length; i++){
        var reg = new RegExp(regStrs[i][0]);
        elem.value = elem.value.replace(reg, regStrs[i][1]);
    }
    return true;
}

//只允许输入金额数值
function onlyAmount(elem){
	elem.value = elem.value.replace(/^\./g,"");  //验证第一个字符是数字而不是. 
	var regStrs = [
       ['^0(\\d+)$', '$1'], //禁止录入整数部分两位以上，但首位为0
       ['[^\\d\\.]+$', ''], //禁止录入任何非数字和点
       ['\\.(\\d?)\\.+', '.$1'], //禁止录入两个以上的点
       ['^(\\d+\\.\\d{2}).+', '$1'] //禁止录入小数点后两位以上
    ];
    for(i=0; i<regStrs.length; i++){
        var reg = new RegExp(regStrs[i][0]);
        elem.value = elem.value.replace(reg, regStrs[i][1]);
    }
}

//只允许输入数字
function onlyNum() {
	var keycode = window.event.keyCode;
	if (keycode == 8 || keycode == 9 || keycode == 35 || keycode == 36 || keycode == 37 || keycode == 39 || keycode == 46) {
		return true;
	}
	if (!((window.event.keyCode >= 48 && window.event.keyCode <= 57) || (window.event.keyCode >= 96 && window.event.keyCode <= 105))) {
		window.event.returnValue = false;
	}
}

//只允许输入英文字母
function onlyEng() {
	var keycode = window.event.keyCode;
	if (keycode == 8 || keycode == 9 || keycode == 35 || keycode == 36 || keycode == 37 || keycode == 39 || keycode == 46) {
		return true;
	}
	if (!(window.event.keyCode >= 65 && window.event.keyCode <= 90)) {
		window.event.returnValue = false;
	}
}

//只允许输入英文字母和数字的组合
function onlyEngNum() {
	//alert(window.event.keyCode);
	var keycode = window.event.keyCode;
	if (keycode == 8 || keycode == 9 || keycode == 35 || keycode == 36 || keycode == 37 || keycode == 39 || keycode == 46) {
		return true;
	}
	if (!((keycode >= 48 && keycode <= 57) || (keycode >= 65 && keycode <= 90) || (keycode >= 96 && keycode <= 105))) {
		window.event.returnValue = false;
	}
}

//只允许输入英文字母和数字以及-_的组合
function onlyW() {
	//alert(window.event.keyCode);
	var keycode = window.event.keyCode;
	if (keycode == 8 || keycode == 9 || keycode == 35 || keycode == 36 || keycode == 37 || keycode == 39 || keycode == 46 || keycode == 189) {
		return true;
	}
	if (!((keycode >= 48 && keycode <= 57) || (keycode >= 65 && keycode <= 90) || (keycode >= 96 && keycode <= 105))) {
		window.event.returnValue = false;
	}
}

//是否为有效的昵称
function isValidNickname(nickname) {
	var regex = /^([\u4E00-\u9FA5]|[0-9]|[a-z]|[A-Z]){2,20}$/;
	if (regex.test(nickname) == false) {
		return false;
	}
	var chinesePattern = /^[\u4E00-\u9FA5]$/;
	var len = 0;
	for (var index = 0; index < nickname.length; index++) {
		var c = nickname.charAt(index);
		if (chinesePattern.test(c)) {
			len += 2;
		} else {
			len += 1;
		}
	}
	if ((len > 20) || (len < 4)) {
		return false;
	}
	return true;
}

function ismoney(value) {
	var mny = /^\d+(\.\d{1,2})?$/;
	return mny.test(value);
}
function isRate(value) {
	var mny = /^\d{1}(\.\d{1,4})?$/;
	return mny.test(value);
}