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
                        case "2":
                            return '<span style="color:red;">停用</span>';

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

/**
 * 退出
 */
function cancel(){
    $('#moneyadd').window('close');
    $("#moneygrid").datagrid("clearSelections");
}

/**
 * 提交
 */
function submitForm(){

    if(Validator()){
        if($('#id').val()==""){

            var params = {
                "title": $("#title").val(),
                "content": $("#content").val(),
                "money": $("#money").val(),
                "minprice": $("#minprice").val(),
                "maxprice":$("#maxprice").val(),
                "totalnum":$("#totalnum").val(),
                "starttime":$("#starttime").datebox('getValue'),
                "endtime":$("#endtime").datebox('getValue'),
                "status":"0" //提交
            }


            console.log(params);

            $.ajax({
                url: ws_url + '/rest/money/insertmoneyactivities?token=' + gtoken,
                contentType: 'application/json;charset=UTF-8',
                type: 'post',
                datatype: 'json',
                data: JSON.stringify(params),
                cache: false,
                success: function (data) {

                    console.log(data.status);
                    console.log(data.message);

                    $('#form1').form('clear');

                    if (data.status == 'success') {
                        $.messager.alert('提示', '添加成功！', 'info', function () {
                            //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                            $('#moneyadd').window('close');
                            $('#moneygrid').datagrid("clearSelections");
                            $('#moneygrid').datagrid("reload");
                        });
                    } else if (data.status == 'failure') {
                        if (data.message == '1') {
                            $.messager.alert('提示', '数据已经存在！', 'warning', function () {
                                //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                                $('#moneyadd').window('close');
                                $('#moneygrid').datagrid("clearSelections");
                                $('#moneygrid').datagrid("reload");
                            });
                        } else {
                            $.messager.alert('提示', '添加失败！', 'error', function () {
                                //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                                $('#moneyadd').window('close');
                                $('#moneygrid').datagrid("clearSelections");
                                $('#moneygrid').datagrid("reload");
                            });
                        }
                    }
                },
                error: function (xhr) {
                    console.log(xhr);
                }
            });

        }else{

        }
    }

}

/**
 * 判断
 * @constructor
 */
function Validator(){

    if ($("#title").val() == "") {
        $.messager.alert('提示', '活动标题不能为空');
        return false;
    }

    /*if ($("#content").val() == "") {
        $.messager.alert('提示', '活动内容不能为空');
        return false;
    }
*/
    if ($("#money").val() == "") {
        $.messager.alert('提示', '总金额不能为空');
        return false;
    }

    if ($("#minprice").val() == "") {
        $.messager.alert('提示', '每笔红包最小金额不能为空');
        return false;
    }

    if ($("#maxprice").val() == "") {
        $.messager.alert('提示', '每笔红包最大金额不能为空');
        return false;
    }

    if ($("#totalnum").val() == "") {
        $.messager.alert('提示', '红包总数不能为空');
        return false;
    }

    if(!ValidatorMoneyTotalnum()){
        $.messager.alert('提示', '红包总数非正整数');
        return false;
    }

    if ($("#starttime").val() == "") {
        $.messager.alert('提示', '活动开始日期不能为空');
        return false;
    }

    if ($("#endtime").val() == "") {
        $.messager.alert('提示', '活动结束日期不能为空');
        return false;
    }

    return true;

}

/**
 * 验证红包总数是否为正整数
 * @constructor
 */
function ValidatorMoneyTotalnum(){

    var a = /^[1-9]\d*$/;

    if(!$("#totalnum").val().match(a))
    {
        return false;
    }

    return true;
}

/**
 * 验证金额格式
 * @constructor
 */
function ValidatorMoney(v){

}