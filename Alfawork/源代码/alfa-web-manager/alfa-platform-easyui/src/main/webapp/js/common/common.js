/**
 * Created by Administrator on 2017/5/26.
 */

var guserid="";
var groleid="";
var grealname="";
var gphone="";
var gmenuitem="";
var gtoken="";
var gaddress="";
var gorgname="";

function isChecked(rowData) {
    var rows = $('#grid').datagrid('getSelections');
    for (var i = 0; i < rows.length; i++) {
        if(rowData.id==rows[i].id){
            return true;
        }
    }
    return false;
}

function ReadCookie(name){
    var cookieValue='';
    var search=name+'=';
    if(document.cookie.length>0){
        offset=document.cookie.indexOf(search);
        if (offset!=-1){
            offset+=search.length;
            end=document.cookie.indexOf(';', offset);
            if (end == -1){
                end=document.cookie.length;
            }
            cookieValue = unescape(document.cookie.substring(offset,end));
        }
    }
    return cookieValue;
}

function SetCookie(name,value,hours){
    var expire = "";
    var expireNextID = "";
    var expirenow = new Date((new Date()).getTime());
    expirenow = "; expires=" + expirenow.toGMTString();
    var SiteName = window.location.host;
    SiteName=SiteName.substr(0,SiteName.indexOf(":"));
    path="/";
    if(window.location.href.indexOf("localhost") >0){
        SiteName = "localhost";
    }
    if(hours != null){
        expire = new Date((new Date()).getTime() + hours * 3600000);
        expire = "; expires=" + expire.toGMTString();
        expireNextID = new Date((new Date()).getTime() + 30 * 24 * 3600000);
        expireNextID = "; expires=" + expireNextID.toGMTString();
        expireNextID_sem = new Date((new Date()).getTime() + 30 * 24 * 3600000);
        expireNextID_sem = "; expires=" + expireNextID_sem.toGMTString();
    }
    document.cookie = name + "=" + escape(value) + expire + ((path == null) ? "" : (";domain="+SiteName+"; path=" + path));
}

/** 加载页面判断user是否登陆 */
function setCurrentUser() {
    
    $.ajax({
        url: ws_url+'/rest/user/current?token='+gtoken,
        type: "get",
        contentType: 'application/json;charset=UTF-8',
        async:false,
        success: function (data) {
            console.log(data);
            if (typeof data != "undefined" && null != data) {
                if(data.id==null){
                    top.location.href=platform_url + "/pages/home/login.html";
                }else if(data.id!=null){
                    // if(urlcustom.indexOf("/home/")>0||urlcustom.indexOf("login.html")>0) {
                        grealname = data.user.realname;
                        gmenuitem = data.user.menuitem;
                        gtoken = data.user.token;
                        guserid = data.user.userId;
                        groleid = data.user.roleId;
                        gphone=data.user.phone;
                        gaddress=data.user.address;
                        gorgname=data.user.orgname;
                    // }else {
                    //     top.location.href=platform_url + "/pages/home/login.html";
                    // }
                }
            }
        },
        error: function(xhr) {
            console.log(xhr);
            top.location.href=platform_url + "/pages/home/login.html";
        }
    });

}


// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}