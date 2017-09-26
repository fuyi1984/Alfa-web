/**
 * Created by Administrator on 2017/9/20.
 */
$(function () {

    gtoken = ReadCookie("token");

    if (gtoken != "") {
        setCurrentUser();

        initdatagrid();
    } else {
        //window.location.href = platform_url + "/pages/home/login.html";
        top.location.href = platform_url + "/pages/home/login.html";
    }

});

function initdatagrid() {
    $('#openidgrid').datagrid({
        title: '微信用户',
        singleSelect: false,
        iconCls: 'icon-save',
        collapsible: true,
        //pageSize: 6,
        //pageList: [6,12,18],
        nowrap: false,
        striped: true,
        loader: ajaxfindlist,
        sortName: 'createdDt',
        sortOrder: 'desc',
        remoteSort: true,
        fitColumns: true,
        fit: true,

        //region

        /*
         toolbar: ['-', {
         id: 'btnSave',
         text: '添加',
         iconCls: 'icon-add',
         handler: function () {
         alert("add");
         }
         },'-'],
         */

        //endregion

        toolbar: "#tb",

        idField: 'Id',

        frozenColumns: [[
            {field: 'Id', checkbox: true}
        ]],

        columns: [[

            {field: 'openid', title: 'openid', width: 150, align: 'center'},

            {
                field: 'headimgurl', title: '微信头像', width: 60, align: 'center',
                formatter: function (value, rec) {
                    if (isNull(value)) {
                        return '<img src="' + value + '" width="60" height="60" border="0">';
                    }
                    else{
                        return '';
                    }
                }
            },

            {field: 'nickname', title: '微信昵称', width: 80, align: 'center'},

            {field: 'realname', title: '真实姓名', width: 80, align: 'center'},

            {field: 'roleId', title: '角色名', width: 80, align: 'center',
                formatter: function (value, rec) {
                    if(isNull(value)){
                        switch (value) {
                            case 15:
                                return '网络运营部';
                            case 9:
                                return '收运人员';
                            case 10:
                                return '产废单位';
                            case 28:
                                return '超级管理员';
                            case 27:
                                return '系统管理员';
                            default:
                                return '';
                        }
                    }else{
                        return ''
                    }
                }
            },
            {
                field: 'createdDt', title: '创建时间', width: 100, align: 'center'
            }
        ]],
        pagination: true,
        rownumbers: true

    });

    function ajaxfindlist(param, success, error) {

        console.log(param);

        var pagenum = param.page - 1;
        var pagesize = param.rows;
        var recordstartindex = param.page == 1 ? 0 : param.rows * (param.page - 1);
        var recordendindex = param.rows * param.page;

        $.ajax({
            cache:true,
            url: ws_url + '/rest/OpenId/findlist?token=' + gtoken,
            type: "post",
            data: 'filterscount=0&groupscount=0&pagenum=' + pagenum + '&pagesize=' + pagesize + '&recordstartindex=' + recordstartindex + '&recordendindex=' + recordendindex + '&nickname=' + $("#nickname").val() + '',
            contentType: 'application/json;charset=UTF-8',
            success: function (data) {
                console.log(data);
                $('#openidgrid').datagrid('clearSelections')
                //$('#form3').form('clear');
                success(data);
            },
            error: function (xhr) {
                console.log(xhr);
                error(xhr.responseText);
            }
        });
    }
}