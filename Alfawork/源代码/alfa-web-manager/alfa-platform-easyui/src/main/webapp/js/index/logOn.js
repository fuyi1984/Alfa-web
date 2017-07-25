/**
 * Created by Administrator on 2017/5/31.
 */

$(function () {
    createCode();
});

//注册验证码
var logCode = "";

function createCode() {
    logCode = new Array();
    var codeLength = 4;//验证码的长度

    var selectChar = new Array(2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');

    for (var i = 0; i < codeLength; i++) {
        var charIndex = Math.floor(Math.random() * 32);
        logCode += selectChar[charIndex];
    }
    if (logCode.length != codeLength) {
        createCode();
    }
    $("#checkCode").val(logCode);
}

//登录
function logOn() {

    if (loginValidator()) {

        var div = $("#msg");
        var account = $("#txtAccount").val();
        var password = $("#txtPassword").val();

        var loading = "<img alt='载入中，请稍候...' height='28' width='28' src='../../images/loading.gif' />";
        div.html(loading);

        var params = {username: encode64(account), password: encode64(password), token: -1};

		console.log(params);
		
		console.log(ws_url + "/rest/user/verify");


        $.ajax({
            type: "POST",
            url: ws_url + "/rest/user/verify",
            contentType: 'application/json;charset=UTF-8',
            /*data: $.param(params),*/
            data:JSON.stringify(params),
            async:false,
            success: function (data) {

                console.log(data);

                if (data.status == "success") {

                    div.html("登陆成功");

                    SetCookie("token",data.data.user.token,1);
                    //window.location.href = platformUrl+"/pages/jsp/redirect.jsp?token="+data.data.user.token;
                    window.location.href=platform_url+"/pages/home/index.html";
                }
                else {
                    if(data.message=="error.users.wrong.password"){
                        div.html("密码错误，请重试!");
                    }else if(data.message=="error.users.name.notexist"){
                        div.html("帐号不存在，请重试!");
                    }else if(data.message=="1"){
                        div.html("帐号未审核,无法登录!");
                    } else {
                        div.html("登录失败，请重试!");
                    }
                }
            },
            error: function (xhr) {
                console.log(xhr);
            }
        });
    }
}

function loginValidator(inputId) {
    var isSub = true;
    inputId = inputId === undefined ? "" : inputId;
    if (inputId == "" || inputId == "txtAccount") {
        if ($("#txtAccount").val() == "") {
            $("#msg").html("用户账号不能为空");
            isSub = false;
            return isSub;
        } else {
            $("#msg").html("");
        }
    }
    if (inputId == "" || inputId == "txtPassword") {
        if ($("#txtPassword").val() == "") {
            $("#msg").html("用户密码不能为空");
            isSub = false;
            return isSub;
        } else {
            $("#msg").html("");
        }
    }
    if (inputId == "" || inputId == "txtCode") {
        if ($("#txtCode").val() == "") {
            $("#msg").html("验证码不能为空");
            isSub = false;
            return isSub;
        } else if ($("#txtCode").val().toUpperCase() != logCode) {
            $("#msg").html("验证码输入有误");
            isSub = false;
            return isSub;
        } else {
            $("#msg").html("");
        }
    }
    return isSub;
}

function keySub(event) {
    if (event.keyCode == 13) {
        logOn();
    }
}