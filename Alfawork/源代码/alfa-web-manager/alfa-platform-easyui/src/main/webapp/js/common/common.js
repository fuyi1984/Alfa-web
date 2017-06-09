/**
 * Created by Administrator on 2017/5/26.
 */

var guserid="";
var groleid="";
var grealname="";
var gphone="";
var gmenuitem="";
var gtoken="";

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
    var SiteName = "loongrender.com";
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

//退出
function logoutUser(){
    $.post(ws_url+"/rest/user/logout",function(data){
        //alert("logout:"+data);
        window.location.href=platform_url+"/pages/home/login.html";
    });
}

/** 加载页面判断user是否登陆 */
function setCurrentUser() {
    
    $.ajax({
        url: ws_url+'/rest/user/current',
        type: "get",
        contentType: 'application/json;charset=UTF-8',
        async:false,
        success: function (data) {
            console.log(data);
            if (typeof data != "undefined" && null != data) {
                if(data.id==null){
                    window.location.href=platform_url+"/pages/home/login.html";
                }else if(data.id!=null){
                    // if(urlcustom.indexOf("/home/")>0||urlcustom.indexOf("login.html")>0) {
                        grealname = data.user.realname;
                        gmenuitem = data.user.menuitem;
                        gtoken = data.user.token;
                        guserid = data.user.userId;
                        groleid = data.user.roleId;
                        gphone=data.user.phone;
                    // }else {
                    //     window.location.href=platform_url+"/pages/home/login.html";
                    // }
                }
            }
        },
        error: function(xhr) {
            console.log(xhr);
            window.location.href=platform_url+"/pages/home/login.html";
        }
    });

}