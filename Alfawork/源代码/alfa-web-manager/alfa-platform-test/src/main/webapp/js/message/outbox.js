/**
 * Created by Administrator on 2017/7/22.
 */
$(function () {
    gtoken = ReadCookie("token");

    if (gtoken != "") {
        setCurrentUser();

        initdatagrid();
    } else {
       window.location.href=platform_url+"/pages/home/login.html";
    }
});


function initdatagrid() {
    $('#outboxgrid').datagrid({
        title: '发件箱',
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
            id: 'btnDelete',
            text: '删除',
            disabled: false,
            iconCls: 'icon-cut',
            handler: function () {

                //region 删除消息

                var rows = $('#outboxgrid').datagrid('getSelections');

                if (!rows || rows.length == 0) {
                    $.messager.alert('提示', '请选择需要删除的消息');
                    return;
                }

                var assetList = new Array();

                $.each(rows, function (i, n) {
                    assetList.push(n.id);
                });

                $.messager.confirm('提示', '是否删除这些消息?', function (r) {
                    if (!r) {
                        return;
                    }

                    $.ajax({
                        cache: false,
                        datatype: 'json',
                        contentType: 'application/json;charset=UTF-8',
                        type: "POST",
                        url: ws_url + '/rest/message/batchdeletemessageuserbyid?token=' + gtoken,
                        data: JSON.stringify(assetList),
                        success: function (msg) {
                            if (msg.status == 'success') {
                                $.messager.alert('提示', '删除成功！', "info", function () {
                                    $('#outboxgrid').datagrid("clearSelections");
                                    $('#outboxgrid').datagrid("reload");
                                });
                            } else {
                                $.messager.alert('错误', '删除失败！', "error", function () {
                                    $('#outboxgrid').datagrid("clearSelections");
                                    $('#outboxgrid').datagrid("reload");
                                });
                            }
                        },
                        error: function (xhr) {
                            console.log(xhr);
                            $('#outboxgrid').datagrid("clearSelections");
                            $.messager.alert('错误', '删除失败！', "error");
                        }
                    });
                });

                //endregion
            }
        }, '-'],

        idField: 'id',

        frozenColumns: [[
            {field: 'id', checkbox: true}
        ]],

        columns: [[
            {field: 'realname', title: '收件人姓名', width: 80, align: 'center'},
            {field: 'phone', title: '收件人电话', width: 80, align: 'center'},
            {field: 'title', title: '标题', width: 80, align: 'center'},
            {field: 'content', title: '内容', width: 200, align: 'center'},
            {
                field: 'createdDt', title: '发送时间', width: 100, align: 'center'
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
            url: ws_url + '/rest/message/findlistoutbox?token=' + gtoken,
            type: "post",
            data: 'filterscount=0&groupscount=0&pagenum=' + pagenum + '&pagesize=' + pagesize + '&recordstartindex=' + recordstartindex + '&recordendindex=' + recordendindex + '',
            contentType: 'application/json;charset=UTF-8',
            success: function (data) {
                console.log(data);
                $('#outboxgrid').datagrid('clearSelections')
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