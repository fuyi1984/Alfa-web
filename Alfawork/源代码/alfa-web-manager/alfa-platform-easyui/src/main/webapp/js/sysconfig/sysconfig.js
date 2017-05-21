/**
 * Created by Administrator on 2017/5/18.
 */

$(function () {

    $('#grid').datagrid({
        title: '系统配置 ',
        iconCls: 'icon-save',
        nowrap: false,
        striped: true,
        loader:ajaxfindlist,
        sortName: 'createdDt',
        sortOrder: 'desc',
        remoteSort: true,
        fitColumns: true,
        fit: true,

        idField: 'Id',

        frozenColumns: [[
            {field: 'Id', checkbox: true}
        ]],

        columns: [[
            {field: 'configName', title: '配置名称', width: 80,align:'center'},
            {field: 'configKey', title: ' 配置Key', width: 80,align:'center'},
            {field: 'configValue', title: ' 配置值', width: 80,align:'center'},
            {field: 'description', title: ' 配置描述', width: 80,align:'center'},
            {field: 'createdBy', title: '创建人', width: 80,align:'center'},
            {
                field: 'createdDt', title: '创建时间', width: 100,align:'center'
            },
            {field: 'updatedBy', title: '更新人', width: 80,align:'center'},
            {
                field: 'updatedDt', title: '更新时间', width: 100,align:'center',
            }
        ]],
        pagination: true,
        rownumbers: true,
        toolbar: ['-', {
            id: 'btnSave',
            text: '添加',
            iconCls: 'icon-add',
            handler: function () {
                window.location.href = "/";
            }
        }, '-', {
            id: 'btnUpdate',
            text: '修改',
            iconCls: 'icon-save',
            handler: function () {

                var row = $('#grid').datagrid('getSelected');
                if (row) {
                    window.location.href = "/UserInfo/View/" + row.ID;
                }
                else {
                    $.messager.alert('提示', '请选择要修改的数据');
                    return;
                }

            }
        }, '-', {
            id: 'btnDelete',
            text: '删除',
            disabled: false,
            iconCls: 'icon-cut',
            handler: function () {

                var rows = $('#grid').datagrid('getSelections');
                if (!rows || rows.length == 0) {
                    $.messager.alert('提示', '请选择要删除的数据');
                    return;
                }
                var parm;
                $.each(rows, function (i, n) {
                    if (i == 0) {
                        parm = "idList=" + n.ID;
                    }
                    else {
                        parm += "&idList=" + n.ID;
                    }
                });
                $.messager.confirm('提示', '是否删除这些数据?', function (r) {
                    if (!r) {
                        return;
                    }

                    $.ajax({
                        type: "POST",
                        url: "/UserInfo/Delete/",
                        data: parm,
                        success: function (msg) {
                            if (msg.IsSuccess) {
                                $.messager.alert('提示', '删除成功！', "info", function () {
                                    $('#grid').datagrid("reload");
                                });
                            }
                        },
                        error: function () {
                            $.messager.alert('错误', '删除失败！', "error");
                        }
                    });
                });
            }
        }, '-']
    });

    function ajaxfindlist(param,success,error){
        console.log(param);
        $.ajax({
            url:'/alfa-ws/rest/Sysconfig/findlist',
            type:"post",
            contentType:'application/json;charset=UTF-8',
            success: function(data){
                console.log(data);
                success(data);
            },
            error:function (xhr) {
                console.log(xhr);
                error(xhr.responseText);
            }
        })
    }

});