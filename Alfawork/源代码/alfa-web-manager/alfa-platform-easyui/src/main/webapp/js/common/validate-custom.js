/**
 * 是否是英文或数字
 */
function IsEnOrNum(input, commit) {
	if(/[\u4E00-\u9FA5]/g.test(input.val())){
		return false;
	}else{
		return true;
	}
}

/**
 * 判断是否email
 * @param str
 * @returns
 */
function isEmail(str,commit){
	  var reg = /^([a-zA-Z0-9_-](\.[a-zA-Z0-9_-])*)+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
	  return reg.test(str);
}

/**
 * 是否是数字, 包括整数、小数
 * @param input
 * @param commit
 * @returns {Boolean}
 * 
 * add by qct
 */
function IsNum(input, commit) {
	if(/^(\-?\d+)(\.\d+)?$/g.exec(input.val())) {
		return true;
	}else {
		return false;
	}
}

/**
 *  是否是符合对应的整数位数和小数位数的数字,只能为正数
 * @param maxLengthOfInt 最大整数位数
 * @param maxLengthOfDec 最大小数位数
 * @param input
 * @param commit
 * @returns {Boolean}
 * 
 */
function isPositiveNumWithDecimal(input, maxLengthOfInt, maxLengthOfDec, commit) {
	var pattern = new RegExp('^(\\d{1,'+maxLengthOfInt+'})(\\.\\d{1,'+maxLengthOfDec+'})?$', 'g');
	if(pattern.exec(input.val())){
		return true;
	}else{
		return false;
	}
}

/**
 * 是否是正整数
 */
function IsPositiveInteger(input, commit) {
	var patrn = /^\d*$/;
	if(patrn.exec(input.val())){
		return true;
	}else{
		return false;
	}
}

/**
 * 0到100的正浮点数，小数部分最多保留两位
 */
function IsFloat(input, commit) {
	var patrn = /^([1-9]\d?(\.\d{1,2})?|0\.\d{1,2}|100|100.0|100.00)$/;
	if(patrn.exec(input.val())){
		return true;
	}else{
		return false;
	}
}

/**
 * 是否是电话号码
 * 
 * edit by qct 2014-7-28
 */
function IsMobile(input, commit) {
	var patrn = /^0?(13[0-9]|15[012356789]|18[0-9]|14[57])[0-9]{8}$/;
	if(patrn.exec(input.val())){
		return true;
	}else{
		return false;
	}
}
function isMobile(v) {
	var patrn = /^0?(13[0-9]|15[012356789]|18[0-9]|14[57])[0-9]{8}$/;
	if(patrn.exec(v)){
		return true;
	}else{
		return false;
	}
}
/**
 * 是否相等
 */
function IsEquals(input1, input2) {
	if(input1.val()==input2.val()){
		return true;
	}else{
		return false;
	}
}
/**
 * 是否超过最大长度
 */
function isBeyondLeng(input,maxLen){
	var str = input.val();
	var len = 0;
    for (var i = 0; i < str.length; i++) {
        var c = str.charCodeAt(i);
        //单字节加1 
        if ((c >= 0x0001 && c <= 0x007e) || (0xff60 <= c && c <= 0xff9f)) {
            len++;
        }
        else {
            len += 2;
        }
    }
    if(len<=maxLen){
    	return true;
	}else{
		return false;
	}
}

/**
 *  是否是符合对应的整数位数和小数位数的数字
 * @param maxLengthOfInt 最大整数位数
 * @param maxLengthOfDec 最大小数位数
 * @param input
 * @param commit
 * @returns {Boolean}
 * 
 * add by qct 2014.07.21
 */
function isNumWithDecimal(input, maxLengthOfInt, maxLengthOfDec, commit) {
	var pattern = new RegExp('^(\\-?\\d{1,'+maxLengthOfInt+'})(\\.\\d{1,'+maxLengthOfDec+'})?$', 'g');
	if(pattern.exec(input.val())){
		return true;
	}else{
		return false;
	}
}

/**
 * 是否MAC地址
 */
function IsMac(input, commit) {
	var patrn = /[0-9A-Fa-f]{2}-[0-9A-Fa-f]{2}-[0-9A-Fa-f]{2}-[0-9A-Fa-f]{2}-[0-9A-Fa-f]{2}-[0-9A-Fa-f]{2}/;
	if(patrn.exec(input.val())){
		return true;
	}else{
		return false;
	}
}

/**
 *  开始时间不能大于结束时间
 * @param input
 * @param commit
 * @param endtimeSelector
 * @returns {Boolean}
 */
function timeCompare(input, commit, endtimeSelector) {
	if(!$(endtimeSelector).val() || input.val() <= $(endtimeSelector).val()){
		return true;
	}else{
		return false;
	}
}

/**
 *  结束时间不能小于结束时间
 * @param input
 * @param commit
 * @param endtimeSelector
 * @returns {Boolean}
 */
function timeCompareEnd(input, commit, starttimeSelector) {
	if(!$(starttimeSelector).val() || input.val() >= $(starttimeSelector).val()){
		return true;
	}else{
		return false;
	}
}

/**
 * 是否代码分类
 */
function IsCodeCategory(input, commit) {
	var patrn = /^([A-Z]+_?)*[A-Z]$/;
	if(patrn.exec(input.val())){
		return true;
	}else{
		return false;
	}
}

/**
 * 是否6位优惠码
 */
function IsCouponCode(input, commit) {
	var patrn = /^([a-z]|[A-Z]|[0-9]){6}$/;
	if(patrn.exec(input.val())){
		return true;
	}else{
		return false;
	}
}