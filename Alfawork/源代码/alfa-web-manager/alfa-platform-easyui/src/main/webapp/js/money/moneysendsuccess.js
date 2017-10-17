$(function () {

    gtoken = ReadCookie("token");

    if (gtoken != "") {
        setCurrentUser();

        //$('#moneyadd').window('close');

        initdatagrid();
    } else {
        //window.location.href = platform_url + "/pages/home/login.html";
        top.location.href = platform_url + "/pages/home/login.html";
    }

});

function initdatagrid() {
    $('#moneysendsuccessgrid').datagrid({
        title: '微信红包发送成功记录表',
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

        idField: 'id',

        frozenColumns: [[
            {field: 'id', checkbox: true}
        ]],

        columns: [[

            {field: 'mobile', title: '手机号', width: 100, align: 'center'},

            {field: 'openid', title: 'openid', width: 120, align: 'center'},

            {field: 'orderno', title: '订单号', width: 100, align: 'center'},

            {field: 'title', title: '活动标题', width: 100, align: 'center'},

            {
                field: 'money', title: '红包发送金额(元)', width: 100, align: 'center'
            },
            {
                field: 'createdDt', title: '创建时间', width: 100, align: 'center'
            },
            {
                field: 'updatedDt', title: '更新时间', width: 100, align: 'center',
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
            cache: true,
            url: ws_url + '/rest/aftersendmoney/findlist?token=' + gtoken,
            type: "post",
            data: 'filterscount=0&groupscount=0&pagenum=' + pagenum + '&pagesize=' + pagesize + '&recordstartindex=' + recordstartindex + '&recordendindex=' + recordendindex + '&startDt=' + $('#startDt').datebox('getValue') + '&endDt=' + $('#endDt').datebox('getValue') + '&mobile=' + $('#mobile').val() + '&title=' + $('#title').val() + '',
            contentType: 'application/json;charset=UTF-8',
            success: function (data) {
                console.log(data);
                $('#moneysendsuccessgrid').datagrid('clearSelections')
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

/**
 * 查询
 */
function doSearch() {

    var startDt = $('#startDt').datebox('getValue');
    var endDt = $('#endDt').datebox('getValue');

    if (startDt == "" && endDt != "") {
        //alert("开始时间不能大于结束时间！");
        $.messager.alert('提示', '开始时间不能为空！');
        return;
    }

    if (endDt == "" && startDt != "") {
        //alert("开始时间不能大于结束时间！");
        $.messager.alert('提示', '结束时间不能为空！');
        return;
    }

    var d1 = new Date(startDt.replace(/\-/g, "\/"));
    var d2 = new Date(endDt.replace(/\-/g, "\/"));

    if (startDt != "" && endDt != "" & d1 > d2) {
        //alert("开始时间不能大于结束时间！");
        $.messager.alert('提示', '开始时间不能大于结束时间！');
        return;
    }

    initdatagrid();

}