/**
 * Created by Administrator on 2017/5/23.
 */

$(function () {

    $('#add').window('close');
    $('#update').window('close');
    $('#search').window('close');

    initdatagrid();

});


function initdatagrid()
{
    $('#grid').datagrid({
        title: '系统配置 ',
        singleSelect: true,
        iconCls: 'icon-save',
        collapsible:true,
        nowrap: false,
        striped: true,
        loader: ajaxfindlist,
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
        rownumbers: true,
        toolbar: ['-', {
            id: 'btnSave',
            text: '添加',
            iconCls: 'icon-add',
            handler: function () {
                /**
                 window.location.href = "/";

                 $('#grid').edatagrid('addRow');

                 */

                //this.href = 'alfa-platform-easyui/pages/sysconfig/edit.html';
                $('#add').window('open');
            }
        }, '-', {
            id: 'btnUpdate',
            text: '修改',
            iconCls: 'icon-save',
            handler: function () {

                var row = $('#grid').datagrid('getSelected');

                console.log(row);

                if (row) {

                    //window.location.href = "/UserInfo/View/" + row.ID;

                    $('#form2').form('load',{Id_update:row.id,configName_update:row.configName,configKey_update:row.configKey,configValue_update:row.configValue,description_update:row.description});
                    $('#update').window('open');

                }
                else {
                    $.messager.alert('提示', '请选择要修改的数据');
                    return;
                }

                //$('#update').window('open');

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

                console.log(rows);
                console.log(rows[0].id);

                var parm={"id": rows[0].id};

                $.messager.confirm('提示', '是否删除这些数据?', function (r) {
                    if (!r) {
                        return;
                    }

                    $.ajax({
                        cache:false,
                        datatype: 'json',
                        contentType: 'application/json;charset=UTF-8',
                        type: "POST",
                        url: '/alfa-ws/rest/Sysconfig/deleteConfig',
                        data: JSON.stringify(parm),
                        success: function (msg) {
                            if (msg.status=='success') {
                                $.messager.alert('提示', '删除成功！', "info", function () {
                                    $('#grid').datagrid("clearSelections");
                                    $('#grid').datagrid("reload");
                                });
                            }else{
                                $.messager.alert('错误', '删除失败！', "error",function(){
                                    $('#grid').datagrid("clearSelections");
                                    $('#grid').datagrid("reload");
                                });
                            }
                        },
                        error: function (xhr) {
                            console.log(xhr);
                            $('#grid').datagrid("clearSelections");
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
                $('#search').window('open');
            }
        }, '-'],

        /*onClickRow:function(rowIndex,rowData){
            if(isChecked(rowData)){
                $("#grid").datagrid("unselectRow", rowIndex);
            }else{
				$("#grid").datagrid("unselectRow", rowIndex);
			}
        }*/

    });

    function ajaxfindlist(param, success, error) {

        console.log(param);

        var pagenum=param.page - 1;
        var pagesize=param.rows;
        var recordstartindex=param.page==1?0:param.rows*(param.page-1);
        var recordendindex=param.rows*param.page;

        $.ajax({
            url: '/alfa-ws/rest/Sysconfig/findlist',
            type: "post",
            data: 'filterscount=0&groupscount=0&pagenum=' + pagenum + '&pagesize=' + pagesize + '&recordstartindex=' + recordstartindex + '&recordendindex=' + recordendindex + '&configName='+$('#configName_search').val()+'&configKey='+$('#configKey_search').val()+'',
            contentType: 'application/json;charset=UTF-8',
            success: function (data) {
                console.log(data);
                $('#grid').datagrid('clearSelections')
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