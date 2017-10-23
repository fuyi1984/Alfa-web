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
        title: '微信红包活动',
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
            data: 'filterscount=0&groupscount=0&pagenum=' + pagenum + '&pagesize=' + pagesize + '&recordstartindex=' + recordstartindex + '&recordendindex=' + recordendindex + '&startDt=' + $('#startDt').datebox('getValue') + '&endDt=' + $('#endDt').datebox('getValue') + '&title=' + $('#activitiestitle').val() + '',
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
    $('#moneygrid').datagrid("clearSelections");
    $('#form1').form('clear');
    $('#moneyadd').panel({title: '添加红包活动'});
    $('#title').textbox('textbox').attr('readonly', false);
    $('#title').textbox({disabled: false});
    $('#moneyadd').window('open');
}

/**
 * 修改
 */
function doEdit() {

    var rows = $('#moneygrid').datagrid('getSelections');

    if (!rows || rows.length == 0) {
        $.messager.alert('提示', '请选择要修改的数据');
        return;
    } else {
        if (rows.length > 1) {
            $.messager.alert('提示', '请选择一条数据');
            $('#moneygrid').datagrid("clearSelections");
            return;
        } else {

            $('#form1').form('load', {
                id: rows[0].id,
                title: rows[0].title,
                content: rows[0].content,
                money: rows[0].money,
                minprice: rows[0].minprice,
                maxprice: rows[0].maxprice,
                totalnum: rows[0].totalnum,
                starttime: rows[0].starttime,
                endtime: rows[0].endtime
            });

            $('#moneyadd').panel({title: '修改红包活动'});
            $('#title').textbox('textbox').attr('readonly', true);
            $('#title').textbox({disabled: true});
            $('#moneyadd').window('open');
        }
    }
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

    var rows = $('#moneygrid').datagrid('getSelections');

    if (!rows || rows.length == 0) {
        $.messager.alert('提示', '请选择需要停用的微信红包活动');
        return;
    }

    console.log(rows);
    console.log(rows[0].id);

    var assetList = new Array();

    $.each(rows, function (i, n) {
        assetList.push(n.id);
    });


    $.messager.confirm('提示', '是否停用这些微信红包活动?', function (r) {
        if (!r) {
            return;
        }

        $.ajax({
            cache: false,
            datatype: 'json',
            contentType: 'application/json;charset=UTF-8',
            type: "POST",
            url: ws_url + '/rest/money/batchStopmoneyactivities?token=' + gtoken,
            data: JSON.stringify(assetList),
            success: function (msg) {
                if (msg.status == 'success') {
                    $.messager.alert('提示', '停用成功！', "info", function () {
                        $('#moneygrid').datagrid("clearSelections");
                        $('#moneygrid').datagrid("reload");
                    });
                } else {
                    $.messager.alert('错误', '停用失败！', "error", function () {
                        $('#moneygrid').datagrid("clearSelections");
                        $('#moneygrid').datagrid("reload");
                    });
                }
            },
            error: function (xhr) {
                console.log(xhr);
                $('#moneygrid').datagrid("clearSelections");
                $.messager.alert('错误', '停用失败！', "error");
            }
        });
    });
}

/**
 * 启用
 */
function doStart() {

    var rows = $('#moneygrid').datagrid('getSelections');

    if (!rows || rows.length == 0) {
        $.messager.alert('提示', '请选择需要启用的微信红包活动');
        return;
    }

    console.log(rows);
    console.log(rows[0].id);

    var assetList = new Array();

    $.each(rows, function (i, n) {
        assetList.push(n.id);
    });


    $.messager.confirm('提示', '是否启用这些微信红包活动?', function (r) {
        if (!r) {
            return;
        }

        $.ajax({
            cache: false,
            datatype: 'json',
            contentType: 'application/json;charset=UTF-8',
            type: "POST",
            url: ws_url + '/rest/money/batchStartmoneyactivities?token=' + gtoken,
            data: JSON.stringify(assetList),
            success: function (msg) {
                if (msg.status == 'success') {
                    $.messager.alert('提示', '启用成功！', "info", function () {
                        $('#moneygrid').datagrid("clearSelections");
                        $('#moneygrid').datagrid("reload");
                    });
                } else {
                    $.messager.alert('错误', '启用失败！', "error", function () {
                        $('#moneygrid').datagrid("clearSelections");
                        $('#moneygrid').datagrid("reload");
                    });
                }
            },
            error: function (xhr) {
                console.log(xhr);
                $('#moneygrid').datagrid("clearSelections");
                $.messager.alert('错误', '启用失败！', "error");
            }
        });
    });
}

/**
 * 退出
 */
function cancel() {
    $('#moneyadd').window('close');
    $("#moneygrid").datagrid("clearSelections");
}

/**
 * 提交
 */
function submitForm() {
    if (ValidatorIsNotNull()) {

        if ($('#id').val() == "") {

            //region 添加

            var params = {
                "title": $("#title").val(),
                "content": $("#content").val(),
                "money": parseFloat($("#money").val()).toFixed(2),
                "minprice": parseFloat($("#minprice").val()).toFixed(2),
                "maxprice": parseFloat($("#maxprice").val()).toFixed(2),
                "totalnum": $("#totalnum").val(),
                "starttime": $("#starttime").datebox('getValue'),
                "endtime": $("#endtime").datebox('getValue'),
                "status": "0" //停用
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

            //endregion

        } else {

            //region 修改

            var params = {
                "id": $('#id').val(),
                "title": $("#title").val(),
                "content": $("#content").val(),
                "money": parseFloat($("#money").val()).toFixed(2),
                "minprice": parseFloat($("#minprice").val()).toFixed(2),
                "maxprice": parseFloat($("#maxprice").val()).toFixed(2),
                "totalnum": $("#totalnum").val(),
                "starttime": $("#starttime").datebox('getValue'),
                "endtime": $("#endtime").datebox('getValue'),
                "status": "2" //手动停用
            }

            console.log(params);

            $.ajax({
                url: ws_url + '/rest/money/updatemoneyactivities?token=' + gtoken,
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
                        $.messager.alert('提示', '修改成功！', 'info', function () {
                            //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                            $('#moneyadd').window('close');
                            $('#moneygrid').datagrid("clearSelections");
                            $('#moneygrid').datagrid("reload");
                        });
                    } else if (data.status == 'failure') {
                        if (data.message == '3'){
                            $.messager.alert('提示', '数据不存在！', 'warning', function () {
                                //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                                $('#moneyadd').window('close');
                                $('#moneygrid').datagrid("clearSelections");
                                $('#moneygrid').datagrid("reload");
                            });
                        }
                        if (data.message == '4'){
                            $.messager.alert('提示', '红包总数小于已发送的红包数！', 'warning', function () {
                                //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                                $('#moneyadd').window('close');
                                $('#moneygrid').datagrid("clearSelections");
                                $('#moneygrid').datagrid("reload");
                            });
                        }
                        else {
                            $.messager.alert('提示', '修改失败！', 'error', function () {
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

            //endregion

        }

    }
}

/**
 * 判断
 * @constructor
 */
function ValidatorIsNotNull() {

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

    if ($("#money").val() != "") {
        if (!ValidatorMoney($("#money").val())) {
            $.messager.alert('提示', '总金额的输入格式不正确');
            return false;
        }
    }

    if ($("#minprice").val() == "") {
        $.messager.alert('提示', '每笔红包最小金额不能为空');
        return false;
    }

    if ($("#minprice").val() != "") {
        if (!ValidatorMoney($("#minprice").val())) {
            $.messager.alert('提示', '每笔红包最小金额的输入格式不正确');
            return false;
        }
    }

    if ($("#maxprice").val() == "") {
        $.messager.alert('提示', '每笔红包最大金额不能为空');
        return false;
    }

    if ($("#maxprice").val() != "") {
        if (!ValidatorMoney($("#maxprice").val())) {
            $.messager.alert('提示', '每笔红包最大金额的输入格式不正确');
            return false;
        }
    }

    if ($("#totalnum").val() != "" && $("#minprice").val() != "" && $("#maxprice").val() != "") {
        if (!validatorMaxMoneyandMinMoney()) {
            return false;
        }
    }

    if ($("#totalnum").val() == "") {
        $.messager.alert('提示', '红包总数不能为空');
        return false;
    }

    if ($("#totalnum").val() != "") {
        if (!ValidatorMoneyTotalnum($("#totalnum").val())) {
            $.messager.alert('提示', '红包总数的输入格式不正确');
            return false;
        }
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
function ValidatorMoneyTotalnum(v) {

    var a = /^[1-9]\d*$/;

    if (!v.match(a)) {
        //$.messager.alert('提示', '红包总数的输入格式不正确');
        return false;
    }

    return true;
}

/**
 * 验证金额格式
 * @constructor
 */
function ValidatorMoney(v) {
    var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;

    if (!v.match(reg)) {
        return false;
    }

    /*if (!$("#money").val().match(reg)) {
        $.messager.alert('提示', '总金额的输入格式不正确');
        return false;
    }

    if (!$("#minprice").val().match(reg)) {
        $.messager.alert('提示', '每笔红包最小金额的输入格式不正确');
        return false;
    }

    if (!$("#maxprice").val().match(reg)) {
        $.messager.alert('提示', '每笔红包最大金额的输入格式不正确');
        return false;
    }*/

    return true;
}

/**
 * 判断金额符合腾讯标准不
 */
function validatorMaxMoneyandMinMoney() {

    var totalMoney = parseFloat($("#money").val());
    var minprice = parseFloat($("#minprice").val());
    var maxprice = parseFloat($("#maxprice").val());

    if (minprice > totalMoney) {
        $.messager.alert('提示', '每笔红包最小金额不能大于金额总数');
        return false;
    }

    if (maxprice > totalMoney) {
        $.messager.alert('提示', '每笔红包最大金额不能大于金额总数');
        return false;
    }

    if (minprice >= maxprice) {
        $.messager.alert('提示', '每笔红包最小金额不能大于等于每笔红包最大金额');
        return false;
    }

    if (minprice < 1.00 || minprice > 200.00) {
        $.messager.alert('提示', '每笔红包最小金额没有在1元到200元的范围内');
        return false;
    }

    if (maxprice < 1.00 || maxprice > 200.00) {
        $.messager.alert('提示', '每笔红包最大金额没有在1元到200元的范围内');
        return false;
    }

    return true;
}

/**
 * 查询
 */
function doSearch(){
    var startDt = $('#startDt').datebox('getValue');
    var endDt = $('#endDt').datebox('getValue');

    if (startDt == "" && endDt != "") {
        //alert("开始时间不能大于结束时间！");
        $.messager.alert('提示', '开始时间不能为空！');
        return;
    }

    if (endDt == "" && startDt != "") {
        //alert("开始时间不能大于结束时间！");
        $.messager.alert('提示', '结束时间不能为空！');
        return;
    }

    var d1 = new Date(startDt.replace(/\-/g, "\/"));
    var d2 = new Date(endDt.replace(/\-/g, "\/"));

    if (startDt != "" && endDt != "" & d1 > d2) {
        //alert("开始时间不能大于结束时间！");
        $.messager.alert('提示', '开始时间不能大于结束时间！');
        return;
    }

    initdatagrid();
}