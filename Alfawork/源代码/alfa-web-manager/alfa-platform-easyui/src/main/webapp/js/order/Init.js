/**
 * Created by Administrator on 2017/6/8.
 */
$(function () {
    setCurrentUser();
    $('#orderadd').window('close');
    $('#orderAllocating').window('close');
    initdatagrid();
    initcombobox();
    Accesscontrol();
});

function initdatagrid() {

    $('#ordergrid').datagrid({
        title: '废油回收',
        singleSelect: true,
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

        toolbar: ['-', {
            id: 'btnSave',
            text: '添加',
            iconCls: 'icon-add',
            handler: function () {

                $('#orderadd').window('open');
            }
        }, '-', {
            id: 'btnAllocating',
            text: '分配',
            iconCls: 'icon-save',
            handler: function () {

                var row = $('#ordergrid').datagrid('getSelected');

                console.log(row);

                if (row) {

                    $("#workerlist").combobox({
                        reload: ws_url + '/rest/user/findAllTransporter?token='+gtoken
                    });

                    $('#form2').form('load', {
                        orderid_allocating: row.orderid
                    });
                    $('#orderAllocating').window('open');

                }
                else {
                    $.messager.alert('提示', '请选择需要分配的订单');
                    return;
                }

            }
        }, '-', {
            id: 'btnDelete',
            text: '删除',
            disabled: false,
            iconCls: 'icon-cut',
            handler: function () {

                var rows = $('#ordergrid').datagrid('getSelections');

                if (!rows || rows.length == 0) {
                    $.messager.alert('提示', '请选择需要删除的订单');
                    return;
                }

                console.log(rows);
                console.log(rows[0].id);

                var parm = {"orderid": rows[0].orderid};

                $.messager.confirm('提示', '是否删除这些订单?', function (r) {
                    if (!r) {
                        return;
                    }

                    $.ajax({
                        cache: false,
                        datatype: 'json',
                        contentType: 'application/json;charset=UTF-8',
                        type: "POST",
                        url: ws_url + '/rest/order/deleteorders?token=' + gtoken,
                        data: JSON.stringify(parm),
                        success: function (msg) {
                            if (msg.status == 'success') {
                                $.messager.alert('提示', '删除成功！', "info", function () {
                                    $('#ordergrid').datagrid("clearSelections");
                                    $('#ordergrid').datagrid("reload");
                                });
                            } else {
                                $.messager.alert('错误', '删除失败！', "error", function () {
                                    $('#ordergrid').datagrid("clearSelections");
                                    $('#ordergrid').datagrid("reload");
                                });
                            }
                        },
                        error: function (xhr) {
                            console.log(xhr);
                            $('#ordergrid').datagrid("clearSelections");
                            $.messager.alert('错误', '删除失败！', "error");
                        }
                    });
                });

            }
        }, '-',{
            id:'btnConfirm',
            text:'确认',
            disabled: false,
            iconCls: 'icon-save',
            handler:function(){

            }
        },'-', {
            id: 'btnSearch',
            text: '查询',
            disabled: false,
            iconCls: 'icon-search',
            handler: function () {
                /* $('#configsearch').window('open');*/
                alert("功能未开放,请耐心等待!!!");
            }
        }, '-'],

        idField: 'orderid',

        frozenColumns: [[
            {field: 'orderid', checkbox: true}
        ]],

        columns: [[
            {field: 'username', title: '姓名', width: 80, align: 'center'},
            {field: 'iphone', title: '电话', width: 80, align: 'center'},
            {field: 'address', title: '地址', width: 80, align: 'center'},
            {field: 'num', title: '数量', width: 80, align: 'center'},
            {field: 'orgname', title: '单位名称', width: 80, align: 'center'},
            {field: 'orgstatus', title: '订单状态', width: 80, align: 'center'},
            {field: 'realname', title: '收运人员姓名', width: 80, align: 'center'},
            {field: 'phone', title: '收运人员电话', width: 80, align: 'center'},
            /* {field: 'createdBy', title: '创建人', width: 80, align: 'center'},*/
            {
                field: 'createdDt', title: '创建时间', width: 100, align: 'center'
            },
            /*{field: 'updatedBy', title: '更新人', width: 80, align: 'center'},*/
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
            url: ws_url + '/rest/order/findlist?token=' + gtoken,
            type: "post",
            data: 'filterscount=0&groupscount=0&pagenum=' + pagenum + '&pagesize=' + pagesize + '&recordstartindex=' + recordstartindex + '&recordendindex=' + recordendindex + '&roleId=' + groleid + '&phone=' + gphone + '&iphone=' + gphone + '',
            contentType: 'application/json;charset=UTF-8',
            success: function (data) {
                console.log(data);
                $('#ordergrid').datagrid('clearSelections')
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

function initcombobox(){
    $("#workerlist").combobox({
        url: ws_url + '/rest/user/findAllTransporter?token='+gtoken,
        method: 'get',
        valueField: 'userId',
        textField: 'realname'
    })
}

//页面权限控制
function Accesscontrol(){

    switch(groleid){
        //收运人员
        case 9:
            $('#btnSave').linkbutton('disable');
            $('#btnAllocating').linkbutton('disable');
            $('#btnDelete').linkbutton('disable');
            $('#btnSearch').linkbutton('disable');
            break;
    }
}