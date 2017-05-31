/**
 * Created by Administrator on 2017/5/31.
 */

$(function(){
   createCode();
});

//注册验证码
var logCode = "";

function createCode(){
    logCode = new Array();
    var codeLength = 4;//验证码的长度

    var selectChar = new Array(2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','S','T','U','V','W','X','Y','Z');

    for(var i=0;i<codeLength;i++) {
        var charIndex = Math.floor(Math.random()*32);
        logCode +=selectChar[charIndex];
    }
    if(logCode.length != codeLength){
        createCode();
    }
    $("#checkCode").val(logCode);
}

//登录
function logOn() {

    if(loginValidator()) {

        var div = $("#msg");
        var account = $("#txtAccount").val();
        var password = $("#txtPassword").val();
        var code = $("#txtCode").val();

        if (account == "" || password == "" || code == "") {
            div.html("请输入用户名，密码和验证码");
            return;
        }


        var loading = "<img alt='载入中，请稍候...' height='28' width='28' src='/Images/loading.gif' />";
        div.html(loading + "载入中，请稍候...");
        var params = {account: account, password: password, code: code};

        $.ajax({
            type: "POST",
            url: "/Home/LogOnByAndPassword/",
            data: $.param(params),
            success: function (msg) {
                if (msg) {
                    if (msg.IsSuccess) {
                        div.html("登陆成功");
                        var href = unescape(request("ReturnUrl"));
                        if (href == "/" || href == "") {
                            href = "/Home/Index/";
                        }

                        window.location.href = href;
                    }
                    else {
                        div.html(msg.Message);
                    }
                }
                else {
                    div.html("为载入相关数据，请重试");
                }
            }
        });
    }
}

function loginValidator(inputId){
    var isSub = true;
    inputId = inputId === undefined ? "" : inputId;
    if(inputId=="" || inputId=="txtAccount"){
        if($("#txtAccount").val()==""){
            $("#msg").html("用户账号不能为空");
            isSub = false;
            return isSub;
        }else{
            $("#msg").html("");
        }
    }
    if(inputId=="" || inputId=="txtPassword"){
        if($("#txtPassword").val()==""){
            $("#msg").html("用户密码不能为空");
            isSub = false;
            return isSub;
        }else{
            $("#msg").html("");
        }
    }
    if(inputId=="" || inputId=="txtCode"){
        if($("#txtCode").val()==""){
            $("#msg").html("验证码不能为空");
            isSub = false;
            return isSub;
        }else if($("#txtCode").val().toUpperCase()!=logCode){
            $("#msg").html("验证码输入有误");
            isSub = false;
            return isSub;
        }else{
            $("#msg").html("");
        }
    }
    return isSub;
}

function keySub(event){
    if(event.keyCode == 13){
        userLoginSubmit();
    }
}