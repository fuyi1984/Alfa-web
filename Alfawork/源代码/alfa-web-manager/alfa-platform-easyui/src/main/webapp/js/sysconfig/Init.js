/**
 * Created by Administrator on 2017/6/5.
 */

$(function () {

    gtoken=ReadCookie("token");

    if(gtoken!="") {
        setCurrentUser();

        $('#configadd').window('close');
        $('#configupdate').window('close');
        $('#configsearch').window('close');

        initdatagrid();
    }else{
        window.location.href=platform_url+"/pages/home/login.html";
    }


});


function initdatagrid() {

    $('#configgrid').datagrid({
        title: '系统配置 ',
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
                $('#configadd').window('open');
            }
        }, '-', {
            id: 'btnUpdate',
            text: '修改',
            iconCls: 'icon-save',
            handler: function () {

                var row = $('#configgrid').datagrid('getSelected');

                console.log(row);

                if (row) {

                    $('#form2').form('load', {
                        Id_update: row.id,
                        configName_update: row.configName,
                        configKey_update: row.configKey,
                        configValue_update: row.configValue,
                        description_update: row.description
                    });
                    $('#configupdate').window('open');

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

                var rows = $('#configgrid').datagrid('getSelections');
                if (!rows || rows.length == 0) {
                    $.messager.alert('提示', '请选择要删除的数据');
                    return;
                }

                console.log(rows);
                console.log(rows[0].id);

                var parm = {"id": rows[0].id};

                $.messager.confirm('提示', '是否删除这些数据?', function (r) {
                    if (!r) {
                        return;
                    }

                    $.ajax({
                        cache: false,
                        datatype: 'json',
                        contentType: 'application/json;charset=UTF-8',
                        type: "POST",
                        url: ws_url + '/rest/Sysconfig/deleteConfig?token=' + gtoken,
                        data: JSON.stringify(parm),
                        success: function (msg) {
                            if (msg.status == 'success') {
                                $.messager.alert('提示', '删除成功！', "info", function () {
                                    $('#configgrid').datagrid("clearSelections");
                                    $('#configgrid').datagrid("reload");
                                });
                            } else {
                                $.messager.alert('错误', '删除失败！', "error", function () {
                                    $('#configgrid').datagrid("clearSelections");
                                    $('#configgrid').datagrid("reload");
                                });
                            }
                        },
                        error: function (xhr) {
                            console.log(xhr);
                            $('#configgrid').datagrid("clearSelections");
                            $.messager.alert('错误', '删除失败！', "error");
                        }
                    });
                });
            }
        }, '-', {
            id: 'btnSearch',
            text: '查询',
            disabled: false,
            iconCls: 'icon-search',
            handler: function () {
                $('#configsearch').window('open');
            }
        }, '-'],

        idField: 'Id',

        frozenColumns: [[
            {field: 'Id', checkbox: true}
        ]],

        columns: [[
            {field: 'configName', title: '配置项名称', width: 80, align: 'center'},
            {field: 'configKey', title: ' 配置项代码', width: 80, align: 'center'},
            {field: 'configValue', title: ' 配置项值', width: 80, align: 'center'},
            {field: 'description', title: ' 配置项描述', width: 80, align: 'center'},
            {field: 'createdBy', title: '创建人', width: 80, align: 'center'},
            {
                field: 'createdDt', title: '创建时间', width: 100, align: 'center'
            },
            {field: 'updatedBy', title: '更新人', width: 80, align: 'center'},
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
            url: ws_url + '/rest/Sysconfig/findlist?token=' + gtoken,
            type: "post",
            data: 'filterscount=0&groupscount=0&pagenum=' + pagenum + '&pagesize=' + pagesize + '&recordstartindex=' + recordstartindex + '&recordendindex=' + recordendindex + '&configName=' + $('#configName_search').val() + '&configKey=' + $('#configKey_search').val() + '',
            contentType: 'application/json;charset=UTF-8',
            success: function (data) {
                console.log(data);
                $('#configgrid').datagrid('clearSelections')
                $('#form3').form('clear');
                success(data);
            },
            error: function (xhr) {
                console.log(xhr);
                error(xhr.responseText);
            }
        });
    }

}






