/**
 * Created by Administrator on 2017/7/12.
 */

$(function () {

    gtoken=ReadCookie("token");

    if(gtoken!="") {
        setCurrentUser();

        initdatagrid();
    }else{
       window.location.href=platform_url+"/pages/home/login.html";
    }

});


function initdatagrid() {

    $('#OrderCommentgrid').datagrid({
        title: '订单评论 ',
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

        toolbar: ['-',{
            id: 'btnDelete',
            text: '删除',
            disabled: false,
            iconCls: 'icon-cut',
            handler: function () {

                var rows = $('#OrderCommentgrid').datagrid('getSelections');

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
                        url: ws_url + '/rest/ordercomment/batchdeleteordercomment?token=' + gtoken,
                        data: JSON.stringify(assetList),
                        success: function (msg) {
                            if (msg.status == 'success') {
                                $.messager.alert('提示', '删除成功！', "info", function () {
                                    $('#OrderCommentgrid').datagrid("clearSelections");
                                    $('#OrderCommentgrid').datagrid("reload");
                                });
                            } else {
                                $.messager.alert('错误', '删除失败！', "error", function () {
                                    $('#OrderCommentgrid').datagrid("clearSelections");
                                    $('#OrderCommentgrid').datagrid("reload");
                                });
                            }
                        },
                        error: function (xhr) {
                            console.log(xhr);
                            $('#OrderCommentgrid').datagrid("clearSelections");
                            $.messager.alert('错误', '删除失败！', "error");
                        }
                    });
                });
            }
        }, '-'],

        idField: 'Id',

        frozenColumns: [[
            {field: 'Id', checkbox: true}
        ]],

        columns: [[
            {field: 'phonerealname', title: '评论人姓名', width: 80, align: 'center'},
            {field: 'phone', title: '评论人电话', width: 80, align: 'center'},
            {field: 'one', title: '服务态度(分)', width: 50, align: 'center'},
            {field: 'two', title: '收油速度(分)', width: 50, align: 'center'},
            {field: 'three', title: '收油价格(分)', width: 50, align: 'center'},
            {field: 'four', title: '评论内容', width: 100, align: 'center'},
            {field: 'mobilerealname', title: '被评论人姓名', width: 80, align: 'center'},
            {field: 'mobile', title: '被评论人电话', width: 80, align: 'center'},
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
            url: ws_url + '/rest/ordercomment/findlist?token=' + gtoken,
            type: "post",
            data: 'filterscount=0&groupscount=0&pagenum=' + pagenum + '&pagesize=' + pagesize + '&recordstartindex=' + recordstartindex + '&recordendindex=' + recordendindex+'&orderId=',
            contentType: 'application/json;charset=UTF-8',
            success: function (data) {
                console.log(data);
                $('#CommonCommentgrid').datagrid('clearSelections')
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