/**
 * Created by Administrator on 2017/9/7.
 */
$(function () {

    /*gtoken = ReadCookie("token");

    if (gtoken != "") {
        setCurrentUser();*/

        initdatagrid();
   /* } else {
        //window.location.href = platform_url + "/pages/home/login.html";
        top.location.href=platform_url + "/pages/home/login.html";
    }*/

});


function initdatagrid() {
    $('#openidgrid').datagrid({
        title: '微信用户',
        singleSelect: false,
        iconCls: 'icon-save',
        collapsible: true,
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

        toolbar:"#tb",

        idField: 'Id',

        frozenColumns: [[
            {field: 'Id', checkbox: true}
        ]],

        columns: [[

            {field: 'openid', title: 'openid', width: 150, align: 'center'},

            {field: 'headimgurl', title: '微信头像', width: 50, align: 'center',
                formatter: function (value, rec) {
                    /*switch (value) {
                     case "0":
                     return '<span style="color:red;">未审核</span>';
                     case "1":
                     return '<span style="color:green;">已审核</span>';
                     case "-1":
                     return '<span style="color:orangered;">数据不完整</span>';
                     }*/

                    return '<img src="'+value+'" width="60" height="60" border="0">';
                }
            },

            {field: 'nickname', title: '微信昵称', width: 80, align: 'center'},

            {field: 'realname', title: '真实姓名', width: 80, align: 'center'},

            {field: 'role_name', title: '角色名', width: 80, align: 'center'},


            {
                field: 'createdDt', title: '创建时间', width: 100, align: 'center'
            },
            
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
            url: ws_url + '/rest/OpenId/findlist?token=' + gtoken,
            type: "post",
            data: 'filterscount=0&groupscount=0&pagenum=' + pagenum + '&pagesize=' + pagesize + '&recordstartindex=' + recordstartindex + '&recordendindex=' + recordendindex + '&nickname='+$("#nickname").val()+'',
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


function doSearch(){
    initdatagrid();
}