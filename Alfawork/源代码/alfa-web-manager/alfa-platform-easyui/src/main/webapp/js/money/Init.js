/**
 * Created by Administrator on 2017/9/20.
 */
$(function () {

    gtoken = ReadCookie("token");

    if (gtoken != "") {
        setCurrentUser();

        $('#moneyadd').window('close');

        initdatagrid();
    } else {
        //window.location.href = platform_url + "/pages/home/login.html";
        top.location.href = platform_url + "/pages/home/login.html";
    }

});

function initdatagrid() {
    $('#moneygrid').datagrid({
        title: '红包活动',
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

            {field: 'title', title: '标题', width: 150, align: 'center'},

            {field: 'content', title: '内容', width: 200, align: 'center'},

            {
                field: 'status', title: '状态', width: 50, align: 'center',
                formatter: function (value, rec) {
                    switch (value) {
                        case "0":
                            return '<span style="color:red;">停用</span>';
                        case "1":
                            return '<span style="color:green;">启用</span>';
                    }
                }
            },

            {field: 'money', title: '总金额', width: 80, align: 'center'},

            {field: 'minprice', title: '每笔红包最小金额', width: 80, align: 'center'},

            {field: 'maxprice', title: '每笔红包最大金额', width: 80, align: 'center'},

            {field: 'totalnum', title: '红包总数', width: 80, align: 'center'},

            {field: 'sendednum', title: '已发送红包数', width: 100, align: 'center'},

            {field: 'remainingnum', title: '剩余红包数', width: 80, align: 'center'},

            {
                field: 'starttime', title: '活动开始时间', width: 100, align: 'center'
            },

            {
                field: 'endtime', title: '活动结束时间', width: 100, align: 'center'
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
            url: ws_url + '/rest/money/findlist?token=' + gtoken,
            type: "post",
            data: 'filterscount=0&groupscount=0&pagenum=' + pagenum + '&pagesize=' + pagesize + '&recordstartindex=' + recordstartindex + '&recordendindex=' + recordendindex + '',
            contentType: 'application/json;charset=UTF-8',
            success: function (data) {
                console.log(data);
                $('#moneygrid').datagrid('clearSelections')
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
 * 新增
 */
function doAdd() {
    $('#moneyadd').panel({title:'添加红包活动'});
    $('#moneyadd').window('open');
}

/**
 * 修改
 */
function doEdit() {
    $('#moneyadd').panel({title:'修改红包活动'});
    $('#moneyadd').window('open');
}

/**
 * 删除
 */
function doDelete() {
    var rows = $('#moneygrid').datagrid('getSelections');

    if (!rows || rows.length == 0) {
        $.messager.alert('提示', '请选择要删除的数据');
        return;
    }

    console.log(rows);
    console.log(rows[0].id);

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
            url: ws_url + '/rest/money/batchdeletemoneyactivities?token=' + gtoken,
            data: JSON.stringify(assetList),
            success: function (msg) {
                if (msg.status == 'success') {
                    $.messager.alert('提示', '删除成功！', "info", function () {
                        $('#moneygrid').datagrid("clearSelections");
                        $('#moneygrid').datagrid("reload");
                    });
                } else {
                    $.messager.alert('错误', '删除失败！', "error", function () {
                        $('#moneygrid').datagrid("clearSelections");
                        $('#moneygrid').datagrid("reload");
                    });
                }
            },
            error: function (xhr) {
                console.log(xhr);
                $('#moneygrid').datagrid("clearSelections");
                $.messager.alert('错误', '删除失败！', "error");
            }
        });
    });
}

/**
 * 停用
 */
function doStop() {

}

/**
 * 启用
 */
function doStart() {

}


function cancel(){
    $('#moneyadd').window('close');
}

function submitForm(){

}