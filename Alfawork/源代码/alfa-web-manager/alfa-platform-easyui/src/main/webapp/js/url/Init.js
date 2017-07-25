/**
 * Created by Administrator on 2017/7/16.
 */
$(function () {

    gtoken=ReadCookie("token");

    if(gtoken!="") {
        setCurrentUser();

        $("#urladd").window('close');

        initdatagrid();
    }else{
       window.location.href=platform_url+"/pages/home/login.html";
    }

});


function initdatagrid() {
    $('#urlgrid').datagrid({
        title: gridname,
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

        toolbar: ['-', {
            id: 'btnSave',
            text: '添加',
            iconCls: 'icon-add',
            handler: function () {
                $('#urladd').window('open');
            }
        }, '-', {
            id: 'btnDelete',
            text: '删除',
            disabled: false,
            iconCls: 'icon-cut',
            handler: function () {

                var rows = $('#urlgrid').datagrid('getSelections');

                if (!rows || rows.length == 0) {
                    $.messager.alert('提示', '请选择要删除的数据');
                    return;
                }

                /*if(rows.length>1){
                 $.messager.alert('提示', '请选择一条数据');
                 $('#configgrid').datagrid("clearSelections");
                 return;
                 }

                 console.log(rows);
                 console.log(rows[0].id);

                 var parm = {"id": rows[0].id};*/

                var assetList = new Array();

                $.each(rows, function (i, n) {
                    assetList.push(n.id);
                });

                $.messager.confirm('提示', '是否删除这些数据?', function (r) {
                    if (!r) {
                        return;
                    }

                    $.ajax({
                        cache: false,
                        datatype: 'json',
                        contentType: 'application/json;charset=UTF-8',
                        type: "POST",
                        url: ws_url + '/rest/url/batchdeleteurl?token=' + gtoken,
                        data: JSON.stringify(assetList),
                        success: function (msg) {
                            if (msg.status == 'success') {
                                $.messager.alert('提示', '删除成功！', "info", function () {
                                    $('#urlgrid').datagrid("clearSelections");
                                    $('#urlgrid').datagrid("reload");
                                });
                            } else {
                                $.messager.alert('错误', '删除失败！', "error", function () {
                                    $('#urlgrid').datagrid("clearSelections");
                                    $('#urlgrid').datagrid("reload");
                                });
                            }
                        },
                        error: function (xhr) {
                            console.log(xhr);
                            $('#urlgrid').datagrid("clearSelections");
                            $.messager.alert('错误', '删除失败！', "error");
                        }
                    });
                });
            }
        }, '-'],

        idField: 'id',

        frozenColumns: [[
            {field: 'id', checkbox: true}
        ]],

        columns: [[
            {field: 'apiAddress', title: 'Url', width: 80, align: 'center'},
            {field: 'apiname', title: ' Api名称', width: 80, align: 'center'},
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
            url: ws_url + '/rest/url/findlist?token=' + gtoken,
            type: "post",
            data: 'filterscount=0&groupscount=0&pagenum=' + pagenum + '&pagesize=' + pagesize + '&recordstartindex=' + recordstartindex + '&recordendindex=' + recordendindex + '&types=' + types + '',
            contentType: 'application/json;charset=UTF-8',
            success: function (data) {
                console.log(data);
                $('#urlgrid').datagrid('clearSelections')
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