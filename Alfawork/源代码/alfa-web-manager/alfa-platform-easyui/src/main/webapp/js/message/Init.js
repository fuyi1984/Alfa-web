/**
 * Created by Administrator on 2017/7/21.
 */
$(function () {
    gtoken = ReadCookie("token");

    if (gtoken != "") {
        setCurrentUser();
        $('#messageadd').window('close');
        $('#messageedit').window('close');
        $('#messagepublish').window('close');
        initdatagrid();
    } else {
        window.location.href = platform_url + "/pages/home/login.html";
    }
});


function initdatagrid() {
    $('#messagegrid').datagrid({
        title: '消息通知',
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
                $('#messageadd').window('open');
            }
        }, '-', {
            id: 'btnedit',
            text: '修改',
            iconCls: 'icon-save',
            handler: function () {

                var rows = $('#messagegrid').datagrid('getSelections');

                if (!rows || rows.length == 0) {
                    $.messager.alert('提示', '请选择要修改的数据');
                    return;
                }
                else{

                    if(rows.length>1){
                        $.messager.alert('提示', '请选择一条数据');
                        $('#messagegrid').datagrid("clearSelections");
                        return;
                    }else {
                        $('#form2').form('load', {
                            Idedit: rows[0].id,
                            titleedit: rows[0].title,
                            contentedit: rows[0].content
                        });
                        $('#messageedit').window('open');
                    }
                }
            }
        }, '-', {
            id: 'btnDelete',
            text: '删除',
            disabled: false,
            iconCls: 'icon-cut',
            handler: function () {

                //region 删除消息

                var rows = $('#messagegrid').datagrid('getSelections');

                if (!rows || rows.length == 0) {
                    $.messager.alert('提示', '请选择需要删除的消息通知');
                    return;
                }

                var assetList = new Array();

                $.each(rows, function (i, n) {
                    assetList.push(n.id);
                });

                $.messager.confirm('提示', '是否删除这些消息通知?', function (r) {
                    if (!r) {
                        return;
                    }

                    $.ajax({
                        cache: false,
                        datatype: 'json',
                        contentType: 'application/json;charset=UTF-8',
                        type: "POST",
                        url: ws_url + '/rest/message/batchdelmessage?token=' + gtoken,
                        data: JSON.stringify(assetList),
                        success: function (msg) {
                            if (msg.status == 'success') {
                                $.messager.alert('提示', '删除成功！', "info", function () {
                                    $('#messagegrid').datagrid("clearSelections");
                                    $('#messagegrid').datagrid("reload");
                                });
                            } else {
                                $.messager.alert('错误', '删除失败！', "error", function () {
                                    $('#messagegrid').datagrid("clearSelections");
                                    $('#messagegrid').datagrid("reload");
                                });
                            }
                        },
                        error: function (xhr) {
                            console.log(xhr);
                            $('#messagegrid').datagrid("clearSelections");
                            $.messager.alert('错误', '删除失败！', "error");
                        }
                    });
                });

                //endregion
            }
        }, '-', {
            id: 'btnPublish',
            text: '发送',
            disabled: false,
            iconCls: 'icon-search',
            handler: function () {

                //region 发送消息

                var rows = $('#messagegrid').datagrid('getSelections');

                if (!rows || rows.length == 0) {
                    $.messager.alert('提示', '请选择需要发送的消息');
                    return;
                }
                else{

                    if(rows.length>1){
                        $.messager.alert('提示', '请选择一条数据');
                        $('#messagegrid').datagrid("clearSelections");
                        return;
                    }else {
                        $('#form3').form('load', {
                            IdPublish: rows[0].id
                        });
                        $('#messagepublish').window('open');
                    }
                }
                //endregion
            }
        }, '-'],

        idField: 'id',

        frozenColumns: [[
            {field: 'id', checkbox: true}
        ]],

        columns: [[
            {field: 'title', title: '标题', width: 80, align: 'center'},
            {field: 'content', title: '内容', width: 200, align: 'center'},
            {field: 'createdBy', title: '创建人', width: 80, align: 'center'},
            {
                field: 'createdDt', title: '创建时间', width: 100, align: 'center'
            },
            {field: 'updatedBy', title: '更新人', width: 80, align: 'center'},
            {
                field: 'updatedDt', title: '更新时间', width: 100, align: 'center'
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
            url: ws_url + '/rest/message/findlist?token=' + gtoken,
            type: "post",
            data: 'filterscount=0&groupscount=0&pagenum=' + pagenum + '&pagesize=' + pagesize + '&recordstartindex=' + recordstartindex + '&recordendindex=' + recordendindex + '',
            contentType: 'application/json;charset=UTF-8',
            success: function (data) {
                console.log(data);
                $('#messagegrid').datagrid('clearSelections')
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