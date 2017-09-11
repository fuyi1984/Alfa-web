/**
 * Created by Administrator on 2017/7/27.
 */
$(function () {
    gtoken = ReadCookie("token");

    if (gtoken != "") {
        setCurrentUser();

        initdatagrid();
    } else {
        window.location.href = platform_url + "/pages/home/login.html";
    }
});

function initdatagrid() {
    $('#registerbehaviorgrid').datagrid({
        title: '注册统计 ',
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

        /*toolbar: ['-', {
         id: 'btnDelete',
         text: '删除',
         disabled: false,
         iconCls: 'icon-cut',
         handler: function () {
         //region

         var rows = $('#registerbehaviorgrid').datagrid('getSelections');

         if (!rows || rows.length == 0) {
         $.messager.alert('提示', '请选择要删除的数据');
         return;
         }

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
         url: ws_url + '/rest/registerbehavior/batchdeleteUser?token=' + gtoken,
         data: JSON.stringify(assetList),
         success: function (msg) {
         if (msg.status == 'success') {
         $.messager.alert('提示', '删除成功！', "info", function () {
         $('#registerbehaviorgrid').datagrid("clearSelections");
         $('#registerbehaviorgrid').datagrid("reload");
         });
         } else {
         $.messager.alert('错误', '删除失败！', "error", function () {
         $('#registerbehaviorgrid').datagrid("clearSelections");
         $('#registerbehaviorgrid').datagrid("reload");
         });
         }
         },
         error: function (xhr) {
         console.log(xhr);
         $('#registerbehaviorgrid').datagrid("clearSelections");
         $.messager.alert('错误', '删除失败！', "error");
         }
         });
         });

         //endregion
         }
         }, '-'],*/

        //endregion

        toolbar: "#tb",

        idField: 'id',

        frozenColumns: [[
            {field: 'id', checkbox: true}
        ]],

        columns: [[
            {
                title: '业务人员',
                align: 'center',
                colspan: 2
            }, {
                title: '产废单位',
                align: 'center',
                colspan: 7
            }],
            [
                {field: 'businessrealname', title: '真实姓名', width: 80, align: 'center'},
                {field: 'businessphone', title: '电话', width: 80, align: 'center'},
                {field: 'regrealname', title: '真实姓名', width: 80, align: 'center'},
                {field: 'regphone', title: '电话', width: 80, align: 'center'},
                {field: 'regorgname', title: '单位名称', width: 80, align: 'center'},
                {field: 'regaddress', title: '单位地址', width: 80, align: 'center'},
                {
                    field: 'regstatus', title: '状态', width: 80, align: 'center',
                    formatter: function (value, rec) {
                        switch (value) {
                            case "0":
                                return '<span style="color:red;">未审核</span>';
                            case "1":
                                return '<span style="color:green;">已审核</span>';
                        }
                    }
                },
                {
                    field: 'regurl', title: '照片', width: 150, align: 'center',
                    formatter: function (value, rec) {
                        /*switch (value) {
                         case "0":
                         return '<span style="color:red;">未审核</span>';
                         case "1":
                         return '<span style="color:green;">已审核</span>';
                         case "-1":
                         return '<span style="color:orangered;">数据不完整</span>';
                         }*/

                        return '<img src="'+"http://"+window.location.host+"/" + value + '" width="150" height="150" border="0">';
                    }
                },
                {
                    field: 'createdDt', title: '创建时间', width: 100, align: 'center'
                },
                /*{field: 'updatedBy', title: '更新人', width: 80, align: 'center'},
                 {
                 field: 'updatedDt', title: '更新时间', width: 100, align: 'center',
                 }*/
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
            url: ws_url + '/rest/registerbehavior/findlist?token=' + gtoken,
            type: "post",
            /*data: 'filterscount=0&groupscount=0&pagenum=' + pagenum + '&pagesize=' + pagesize + '&recordstartindex=' + recordstartindex + '&recordendindex=' + recordendindex + '&configName=' + $('#configName_search').val() + '&configKey=' + $('#configKey_search').val() + '',*/
            data: 'filterscount=0&groupscount=0&pagenum=' + pagenum + '&pagesize=' + pagesize + '&recordstartindex=' + recordstartindex + '&recordendindex=' + recordendindex + '&businessphone=' + $('#bphone').val() + '&businessrealname=' + $('#brealname').val() + '&regaddress=' + $('#address').val() + '&startDt=' + $('#startDt').datebox('getValue') + '&endDt=' + $('#endDt').datebox('getValue') + '',
            contentType: 'application/json;charset=UTF-8',
            success: function (data) {
                console.log(data);
                $('#registerbehaviorgrid').datagrid('clearSelections')
                success(data);
            },
            error: function (xhr) {
                console.log(xhr);
                error(xhr.responseText);
            }
        });
    }
}

function doDelete() {

    var rows = $('#registerbehaviorgrid').datagrid('getSelections');

    if (!rows || rows.length == 0) {
        $.messager.alert('提示', '请选择要删除的数据');
        return;
    }

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
            url: ws_url + '/rest/registerbehavior/batchdeleteUser?token=' + gtoken,
            data: JSON.stringify(assetList),
            success: function (msg) {
                if (msg.status == 'success') {
                    $.messager.alert('提示', '删除成功！', "info", function () {
                        $('#registerbehaviorgrid').datagrid("clearSelections");
                        $('#registerbehaviorgrid').datagrid("reload");
                    });
                } else {
                    $.messager.alert('错误', '删除失败！', "error", function () {
                        $('#registerbehaviorgrid').datagrid("clearSelections");
                        $('#registerbehaviorgrid').datagrid("reload");
                    });
                }
            },
            error: function (xhr) {
                console.log(xhr);
                $('#registerbehaviorgrid').datagrid("clearSelections");
                $.messager.alert('错误', '删除失败！', "error");
            }
        });
    });
}