/**
 * Created by Administrator on 2017/5/26.
 */

// WebService请求地址
var web_service_name = "/alfa-ws";
var ws_url = "http://" + window.location.host + web_service_name;// 发布使用的url

//后台地址
var platform_name = "/alfa-platform";
var platform_url="http://" + window.location.host + platform_name;

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