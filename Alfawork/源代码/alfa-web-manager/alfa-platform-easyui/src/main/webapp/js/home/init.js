/**
 * Created by Administrator on 2017/5/31.
 */
$(function(){
    InitLeftMenu();
    tabClose();
    tabCloseEven();

    $('#tabs').tabs({
        onSelect: function (title) {
            var currTab = $('#tabs').tabs('getTab', title);
            var iframe = $(currTab.panel('options').content);

            var src = iframe.attr('src');
            if (src)
                $('#tabs').tabs('update', { tab: currTab, options: { content: createFrame(src)} });

        }
    });
})

function showChangePasswordWin() {
    $('#winPassword').window('open');
}

function add(e) {
    //alert(e);
    var tabTitle = $(e).children('.nav').text();
    //alert(tabTitle);
    var url = $(e).attr("rel");
    //alert(url);
    var menuid = $(e).attr("ref");
    //alert(menuid);
    var icon = getIcon(menuid);
    //alert(icon);
    addTab(tabTitle, url, icon);
    $('.easyui-accordion li div').removeClass("selected");
}

function changePassword() {
    var oldPassword = $("#iptOldPassword").val();
    if (oldPassword == "") {
        $.messager.alert('提示', '请输入旧密码！');
        return;
    }

    var password = $("#iptPassword").val();
    if (password == "") {
        $.messager.alert('提示', '请输入新密码！');
        return;
    }

    var newPassword = $("#iptNewPassword").val();
    if (newPassword == "") {
        $.messager.alert('提示', '请确认密码！');
        return;
    }

    if (newPassword != password) {
        $.messager.alert('提示', '两次密码不一致，请重新输入！');
        return;
    }
    var parm = { password: password, oldPassword: oldPassword };
    $.ajax({
        type: "POST",
        url: "/Home/ChangedPassword/",
        data: parm,
        success: function (msg) {
            if (msg.IsSuccess) {
                $.messager.alert('提示', '修改成功！', "info", function () {
                    $('#winPassword').window('close');
                    $("#iptOldPassword").val("");
                    $("#iptPassword").val("");
                    $("#iptNewPassword").val("");
                    window.location.href = "/";
                });
            } else {
                $.messager.alert('提示', '密码错误，请重新输入！', "info");
            }
        },
        error: function () {
            $.messager.alert('错误', '修改失败！', "error");
        }
    });
}

var _menus={};

//初始化左侧
function InitLeftMenu() {

    $("#nav").accordion({animate:false});

    $.post("/Home/GetMenuInfo/",function(data){

        _menus=data;

        $.each(data,function(i,n){

            var menulist='';
            menulist+='<ul>';

            $.each(n.MenuInfos, function (j, o) {
                menulist += '<li><div><a ref="' + o.MenuId + '" href="#" rel="' + o.Url + '"  onclick="add(this)" >' +
                    '<span class="icon ' + o.Icon + '" >&nbsp;</span><span class="nav">'
                    + o.MenuNames + '</span></a></div></li> ';
            });

            menulist += '</ul>';

            $('#nav').accordion('add', {
                title: n.MenuName,
                content: menulist,
                iconCls: 'icon ' + n.Icon
            });
        });
    });

    //选中第一个
    var panels = $('#nav').accordion('panels');
    var t = panels[0].panel('options').title;
    $('#nav').accordion('select', t);
}

//获取左侧导航的图标
function getIcon(menuid) {
    var icon = 'icon ';
    $.each(_menus, function (i, n) {
        $.each(n.MenuInfos, function (j, o) {
            if (o.MenuId == menuid) {
                icon += o.Icon;
            }
        });
    });

    return icon;
}

function addTab(subtitle, url, icon) {
    if (!$('#tabs').tabs('exists', subtitle)) {
        $('#tabs').tabs('add', {
            title: subtitle,
            content: createFrame(url),
            closable: true,
            icon: icon
        });
    } else {
        $('#tabs').tabs('select', subtitle);
        $('#mm-tabupdate').click();
    }
    tabClose();
}

function createFrame(url) {
    var s = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
    return s;
}

function tabClose() {
    /*双击关闭TAB选项卡*/
    $(".tabs-inner").dblclick(function () {
        var subtitle = $(this).children(".tabs-closable").text();
        $('#tabs').tabs('close', subtitle);
    })
    /*为选项卡绑定右键*/
    $(".tabs-inner").bind('contextmenu', function (e) {
        $('#mm').menu('show', {
            left: e.pageX,
            top: e.pageY
        });

        var subtitle = $(this).children(".tabs-closable").text();

        $('#mm').data("currtab", subtitle);
        $('#tabs').tabs('select', subtitle);
        return false;
    });
}

//绑定右键菜单事件
function tabCloseEven() {
    //刷新
    $('#mm-tabupdate').click(function () {
        var currTab = $('#tabs').tabs('getSelected');
        var url = $(currTab.panel('options').content).attr('src');
        $('#tabs').tabs('update', {
            tab: currTab,
            options: {
                content: createFrame(url)
            }
        });
    });
    //关闭当前
    $('#mm-tabclose').click(function () {
        var currtab_title = $('#mm').data("currtab");
        $('#tabs').tabs('close', currtab_title);
    });
    //全部关闭
    $('#mm-tabcloseall').click(function () {
        $('.tabs-inner span').each(function (i, n) {
            var t = $(n).text();
            $('#tabs').tabs('close', t);
        });
    });
    //关闭除当前之外的TAB
    $('#mm-tabcloseother').click(function () {
        $('#mm-tabcloseright').click();
        $('#mm-tabcloseleft').click();
    });
    //关闭当前右侧的TAB
    $('#mm-tabcloseright').click(function () {
        var nextall = $('.tabs-selected').nextAll();
        if (nextall.length == 0) {
            //msgShow('系统提示','后边没有啦~~','error');
            alert('后边没有啦~~');
            return false;
        }
        nextall.each(function (i, n) {
            var t = $('a:eq(0) span', $(n)).text();
            $('#tabs').tabs('close', t);
        });
        return false;
    });
    //关闭当前左侧的TAB
    $('#mm-tabcloseleft').click(function () {
        var prevall = $('.tabs-selected').prevAll();
        if (prevall.length == 0) {
            alert('到头了，前边没有啦~~');
            return false;
        }
        prevall.each(function (i, n) {
            var t = $('a:eq(0) span', $(n)).text();
            $('#tabs').tabs('close', t);
        });
        return false;
    });

    //退出
    $("#mm-exit").click(function () {
        $('#mm').menu('hide');
    });
}

//弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
    $.messager.alert(title, msgString, msgType);
}

