$(function () {

    gtoken = ReadCookie("token");

    if (gtoken != "") {
        setCurrentUser();

        //$('#moneyadd').window('close');

        initdatagrid();
    } else {
        //window.location.href = platform_url + "/pages/home/login.html";
        top.location.href = platform_url + "/pages/home/login.html";
    }

});

function initdatagrid() {
    $('#activitiesordergrid').datagrid({
        title: '微信红包活动订单',
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

            {field: 'mobile', title: '手机号', width: 100, align: 'center'},

            {field: 'openid', title: 'openid', width: 120, align: 'center'},

            {field: 'orderno', title: '订单号', width: 100, align: 'center'},

            {field: 'title', title: '活动标题', width: 100, align: 'center'},

            {
                field: 'status', title: '红包发送状态', width: 100, align: 'center'
            },
            {
                field: 'createdDt', title: '创建时间', width: 100, align: 'center'
            },
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
            cache: true,
            url: ws_url + '/rest/activitiesorder/findlist?token=' + gtoken,
            type: "post",
            data: 'filterscount=0&groupscount=0&pagenum=' + pagenum + '&pagesize=' + pagesize + '&recordstartindex=' + recordstartindex + '&recordendindex=' + recordendindex + '&startDt=' + $('#startDt').datebox('getValue') + '&endDt=' + $('#endDt').datebox('getValue') + '',
            contentType: 'application/json;charset=UTF-8',
            success: function (data) {
                console.log(data);
                $('#activitiesordergrid').datagrid('clearSelections')
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
 * 发送红包
 */
function doSendMoney() {
    var rows = $('#activitiesordergrid').datagrid('getSelections');

    if (!rows || rows.length == 0) {
        $.messager.alert('提示', '请选择需要发送的微信红包订单');
        return;
    }

    console.log(rows);
    console.log(rows[0].id);

    var assetList = new Array();
    $.each(rows, function (i, n) {
        assetList.push(n.id);
    });

    /* var assetList="";

     for(var i=0;i<rows.length;i++)
     {
          if(i==0){
              assetList+=rows[i].id;
          }else{
              assetList+=","+rows[i].id;
          }

     }*/

    $.messager.confirm('提示', '是否选择这些微信红包订单?', function (r) {
        if (!r) {
            return;
        }

        $.ajax({
            cache: false,
            datatype: 'json',
            contentType: 'application/json;charset=UTF-8',
            type: "POST",
            url: ws_url + '/rest/activitiesorder/sendmoney?token=' + gtoken,
            //data:'idlist='+assetList+'',
            data: JSON.stringify(assetList),
            success: function (msg) {
                if (msg.status == 'success') {
                    $.messager.alert('提示', '微信红包已经发送,请耐心等待！', "info", function () {
                        $('#activitiesordergrid').datagrid("clearSelections");
                        $('#activitiesordergrid').datagrid("reload");
                    });
                } else {
                    if (msg.message == "3") {
                        $.messager.alert('提示', '上次发送的微信红包没有发送完毕,请耐心等待！', "warning", function () {
                            $('#activitiesordergrid').datagrid("clearSelections");
                            $('#activitiesordergrid').datagrid("reload");
                        });
                    } else {
                        $.messager.alert('错误', '微信红包发送失败！', "error", function () {
                            $('#activitiesordergrid').datagrid("clearSelections");
                            $('#activitiesordergrid').datagrid("reload");
                        });
                    }
                }
            },
            error: function (xhr) {
                console.log(xhr);
                $('#activitiesordergrid').datagrid("clearSelections");
                $.messager.alert('错误', '微信红包发送失败！', "error");
            }
        });
    });
}

/**
 * 查询
 */
function doSearch() {

    var startDt = $('#startDt').datebox('getValue');
    var endDt = $('#endDt').datebox('getValue');


    if (startDt == "") {
        //alert("开始时间不能大于结束时间！");
        $.messager.alert('提示', '开始时间不能为空！');
        return;
    }

    if (endDt == "") {
        //alert("开始时间不能大于结束时间！");
        $.messager.alert('提示', '结束时间不能为空！');
        return;
    }

    var d1 = new Date(startDt.replace(/\-/g, "\/"));
    var d2 = new Date(endDt.replace(/\-/g, "\/"));

    if (d1 > d2) {
        //alert("开始时间不能大于结束时间！");
        $.messager.alert('提示', '开始时间不能大于结束时间！');
        return;
    }

    initdatagrid();

}