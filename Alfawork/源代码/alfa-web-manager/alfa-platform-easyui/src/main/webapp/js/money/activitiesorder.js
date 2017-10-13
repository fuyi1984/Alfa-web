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

            {field: 'title', title: '标题', width: 100, align: 'center'},

            {field: 'content', title: '内容', width: 100, align: 'center'},

            {
                field: 'status', title: '状态', width: 50, align: 'center',
                formatter: function (value, rec) {
                    switch (value) {
                        case "0":
                            return '<span style="color:red;">停用</span>';
                        case "1":
                            return '<span style="color:green;">启用</span>';
                        case "2":
                            return '<span style="color:red;">停用</span>';

                    }
                }
            },

            {field: 'money', title: '总金额(元)', width: 80, align: 'center'},

            {field: 'minprice', title: '每笔红包最小金额(元)', width: 80, align: 'center'},

            {field: 'maxprice', title: '每笔红包最大金额(元)', width: 80, align: 'center'},

            {field: 'totalnum', title: '红包总数', width: 50, align: 'center'},

            {field: 'sendednum', title: '已发送红包数', width: 60, align: 'center'},

            {field: 'remainingnum', title: '剩余红包数', width: 50, align: 'center'},

            {
                field: 'starttime', title: '活动开始时间', width: 70, align: 'center'
            },

            {
                field: 'endtime', title: '活动结束时间', width: 70, align: 'center'
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