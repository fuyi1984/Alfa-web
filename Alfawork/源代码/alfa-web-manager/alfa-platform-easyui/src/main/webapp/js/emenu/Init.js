/**
 * Created by Administrator on 2017/8/2.
 */
$(function () {

    gtoken=ReadCookie("token");

    if(gtoken!="") {
        setCurrentUser();

        initdatagrid();
    }else{
        window.location.href=platform_url+"/pages/home/login.html";
    }

});


function initdatagrid() {
    
}