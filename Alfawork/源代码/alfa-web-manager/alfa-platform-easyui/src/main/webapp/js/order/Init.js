/**
 * Created by Administrator on 2017/6/8.
 */
$(function () {
    gtoken = ReadCookie("token");

    if (gtoken != "") {
        setCurrentUser();
        $('#orderadd').window('close');
        $('#orderAllocating').window('close');

        // var tab = $('#tbs').tabs('getSelected');
        // var index = $('#tbs').tabs('getTabIndex',tab);
        // alert(index);

        initdatagrid();
        initcombobox();
        //Accesscontrol();
    } else {
        top.location.href=platform_url + "/pages/home/login.html";
    }
});

function initdatagrid() {

    $('#ordergrid').datagrid({
        title: '废油回收',
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
            id: 'btnSave',
            text: '添加',
            iconCls: 'icon-add',
            handler: function () {

                //region 添加订单

                if (groleid == 10) {
                    $('#form1').form('load', {
                        username_add: grealname,
                        iphone_add: gphone,
                        address_add: gaddress,
                        orgname_add: gorgname,
                    });
                    $('#username_add').textbox('textbox').attr('readonly', true);  //设置输入框为禁用
                    $('#iphone_add').textbox('textbox').attr('readonly', true);  //设置输入框为禁用
                    $('#address_add').textbox('textbox').attr('readonly', true);
                    $('#orgname_add').textbox('textbox').attr('readonly', true);
                }

                $('#orderadd').window('open');

                //endregion
            }
        }, '-', {
            id: 'btnAllocating',
            text: '分配',
            iconCls: 'icon-save',
            handler: function () {

                //region 分配订单

                //var row = $('#ordergrid').datagrid('getSelected');

                var rows = $('#ordergrid').datagrid('getSelections');

                //console.log(row);

                if (!rows || rows.length == 0) {
                    $.messager.alert('提示', '请选择需要分配的订单');
                    return;
                } else {

                    var orderidlist = "";

                    for (var i = 0; i < rows.length; i++) {
                        if (i == 0) {
                            orderidlist += rows[i].orderid;
                        } else {
                            orderidlist += "," + rows[i].orderid;
                        }
                    }

                    //alert(orderidlist);

                    // if (rows.length > 1) {
                    //     $.messager.alert('提示', '请选择一条订单');
                    //     $('#ordergrid').datagrid("clearSelections");
                    //     return;
                    // } else {

                    $("#workerlist").combobox({
                        reload: ws_url + '/rest/user/findAllTransporter?token=' + gtoken
                    });

                    // $('#form2').form('load', {
                    //     orderid_allocating: rows[0].orderid
                    // });

                    $('#form2').form('load', {
                        orderid_allocating: orderidlist
                    });

                    $('#orderAllocating').window('open');

                    //}

                }

                //endregion
            }
        }, '-', {
            id: 'btnDelete',
            text: '删除',
            disabled: false,
            iconCls: 'icon-cut',
            handler: function () {

                //region 删除订单

                var rows = $('#ordergrid').datagrid('getSelections');

                if (!rows || rows.length == 0) {
                    $.messager.alert('提示', '请选择需要删除的订单');
                    return;
                }

                /!*

                 if(rows.length>1){
                 $.messager.alert('提示', '请选择一条订单');
                 $('#ordergrid').datagrid("clearSelections");
                 return;
                 }

                 console.log(rows);
                 console.log(rows[0].orderid);

                 var parm = {"orderid": rows[0].orderid};

                 *!/

                var assetList = new Array();

                $.each(rows, function (i, n) {
                    assetList.push(n.orderid);
                });

                $.messager.confirm('提示', '是否删除这些订单?', function (r) {
                    if (!r) {
                        return;
                    }

                    $.ajax({
                        cache: false,
                        datatype: 'json',
                        contentType: 'application/json;charset=UTF-8',
                        type: "POST",
                        url: ws_url + '/rest/order/batchdeleteorders?token=' + gtoken,
                        data: JSON.stringify(assetList),
                        success: function (msg) {
                            if (msg.status == 'success') {
                                $.messager.alert('提示', '删除成功！', "info", function () {
                                    $('#ordergrid').datagrid("clearSelections");
                                    $('#ordergrid').datagrid("reload");
                                });
                            } else {
                                $.messager.alert('错误', '删除失败！', "error", function () {
                                    $('#ordergrid').datagrid("clearSelections");
                                    $('#ordergrid').datagrid("reload");
                                });
                            }
                        },
                        error: function (xhr) {
                            console.log(xhr);
                            $('#ordergrid').datagrid("clearSelections");
                            $.messager.alert('错误', '删除失败！', "error");
                        }
                    });
                });

                //endregion

            }
        }, '-', {
            id: 'btnConfirm',
            text: '确认',
            disabled: false,
            iconCls: 'icon-save',
            handler: function () {

                //region 确认订单

                var rows = $('#ordergrid').datagrid('getSelections');

                if (!rows || rows.length == 0) {
                    $.messager.alert('提示', '请选择需要确认的订单');
                    return;
                }

                /!*if(rows.length>1){
                 $.messager.alert('提示', '请选择一条订单');
                 $('#ordergrid').datagrid("clearSelections");
                 return;
                 }

                 var params = {"orderid": rows[0].orderid,"orgstatus":"完成"};

                 console.log(params);*!/

                var assetList = new Array();

                $.each(rows, function (i, n) {
                    assetList.push(n.orderid);
                });

                $.messager.confirm('提示', '是否确认这些订单?', function (r) {
                    if (!r) {
                        return;
                    }
                    $.ajax({
                        url: ws_url + '/rest/order/batchupdateorderstatus?token=' + gtoken,
                        contentType: 'application/json;charset=UTF-8',
                        type: 'post',
                        datatype: 'json',
                        data: JSON.stringify(assetList),
                        cache: false,
                        success: function (data) {

                            console.log(data.status);
                            console.log(data.message);

                            if (data.status == 'success') {
                                $.messager.alert('提示', '确认成功！', 'info', function () {
                                    //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                                    $('#ordergrid').datagrid("clearSelections");
                                    $('#ordergrid').datagrid("reload");
                                });
                            } else if (data.status == 'failure') {

                                $.messager.alert('提示', '确认失败！', 'error', function () {
                                    //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                                    $('#ordergrid').datagrid("clearSelections");
                                    $('#ordergrid').datagrid("reload");
                                });

                            }
                        },
                        error: function (xhr) {
                            console.log(xhr);
                            $('#ordergrid').datagrid("clearSelections");
                            $.messager.alert('错误', '确认失败！', "error");
                        }
                    });
                });

                //endregion
            }
        }, '-', {
            id: 'btnComplete',
            text: '完成',
            disabled: false,
            iconCls: 'icon-save',
            handler: function () {
                //region 完成订单
                var rows = $('#ordergrid').datagrid('getSelections');

                if (!rows || rows.length == 0) {
                    $.messager.alert('提示', '请选择需要确认完成的订单');
                    return;
                }

                /!*if(rows.length>1){
                 $.messager.alert('提示', '请选择一条订单');
                 $('#ordergrid').datagrid("clearSelections");
                 return;
                 }

                 var params = {"orderid": rows[0].orderid,"orgstatus":"完成"};

                 console.log(params);*!/

                //region 选择的订单数大于1

                var assetList = new Array();

                $.each(rows, function (i, n) {
                    assetList.push(n.orderid);
                });

                $.messager.confirm('提示', '是否确认完成这些订单?', function (r) {
                    if (!r) {
                        return;
                    }
                    $.ajax({
                        url: ws_url + '/rest/order/batchcompleteorderStatus?token=' + gtoken,
                        contentType: 'application/json;charset=UTF-8',
                        type: 'post',
                        datatype: 'json',
                        data: JSON.stringify(assetList),
                        cache: false,
                        success: function (data) {

                            console.log(data.status);
                            console.log(data.message);

                            if (data.status == 'success') {
                                $.messager.alert('提示', '确认成功！', 'info', function () {
                                    //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                                    $('#ordergrid').datagrid("clearSelections");
                                    $('#ordergrid').datagrid("reload");
                                });
                            } else if (data.status == 'failure') {

                                $.messager.alert('提示', '确认失败！', 'error', function () {
                                    //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                                    $('#ordergrid').datagrid("clearSelections");
                                    $('#ordergrid').datagrid("reload");
                                });

                            }
                        },
                        error: function (xhr) {
                            console.log(xhr);
                            $('#ordergrid').datagrid("clearSelections");
                            $.messager.alert('错误', '确认失败！', "error");
                        }
                    });
                });

                //endregion

                //endregion
            }
        }, '-', {
            id: 'btnSearch',
            text: '查询',
            disabled: false,
            iconCls: 'icon-search',
            handler: function () {
                /!* $('#configsearch').window('open');*!/
                alert("功能未开放,请耐心等待!!!");
            }
        }, '-'],
*/
        //endregion

        toolbar:"#tb",

        idField: 'orderid',

        frozenColumns: [[
            {field: 'orderid', checkbox: true}
        ]],

        columns: [[
            {field: 'orderno', title: '订单号', width: 80, align: 'center'},
            {field: 'username', title: '申报人姓名', width: 80, align: 'center'},
            {field: 'iphone', title: '申报人电话', width: 80, align: 'center'},
            {field: 'address', title: '收油地址', width: 80, align: 'center'},
            {field: 'num', title: '预收数量(桶)', width: 80, align: 'center'},
            {field: 'realnum', title: '实收数量(桶)', width: 80, align: 'center'},
            {field: 'orgname', title: '单位名称', width: 80, align: 'center'},
            {
                field: 'orgstatus', title: '订单状态', width: 80, align: 'center', formatter: function (value, rec) {
                switch (value) {
                    //订单提交
                    case "1":
                        return '<span style="color:red;">订单提交</span>';
                    //订单分配
                    case "2":
                        return '<span style="color:blue;">订单分配</span>';
                    //订单确认
                    case "3":
                        return '<span style="color:blueviolet;">订单确认</span>';
                    //订单完成
                    case "4":
                        return '<span style="color:green;">订单完成</span>';
                }
            }
            },
            {field: 'realname', title: '收运人员姓名', width: 80, align: 'center'},
            {field: 'phone', title: '收运人员电话', width: 80, align: 'center'},
            /* {field: 'createdBy', title: '创建人', width: 80, align: 'center'},*/
            {
                field: 'createdDt', title: '申报时间', width: 100, align: 'center'
            },
            {
                field: 'confirmDt', title: '确认时间', width: 100, align: 'center'
            },
            {
                field: 'updatedDt', title: '完成时间', width: 100, align: 'center'
            },
            {
                field: 'remark', title: '备注', width: 100, align: 'center'
            }
            /*{field: 'updatedBy', title: '更新人', width: 80, align: 'center'},
            {
                field: 'updatedDt', title: '更新时间', width: 100, align: 'center',
            }
             */
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
            url: ws_url + '/rest/order/findlist?token=' + gtoken,
            type: "post",
            data: 'filterscount=0&groupscount=0&pagenum=' + pagenum + '&pagesize=' + pagesize + '&recordstartindex=' + recordstartindex + '&recordendindex=' + recordendindex + '&roleId=' + groleid + '&phone=' + gphone + '&iphone=' + gphone + '&realname='+$('#brealname').val()+'&orgstatus='+$('#orderstatus').combobox('getValue')+'&startDt='+$('#startDt').datebox('getValue')+'&endDt='+$('#endDt').datebox('getValue')+'&sendername='+$('#sendername').val()+'&orgname='+$('#orgname').val()+'',
            contentType: 'application/json;charset=UTF-8',
            success: function (data) {
                console.log(data);
                $('#ordergrid').datagrid('clearSelections')
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

function initcombobox() {
    $("#workerlist").combogrid({
        url: ws_url + '/rest/user/findAllTransporter?token=' + gtoken,
        method: 'get',
        idField: 'userId',
        textField: 'realname',
        panelWidth:450,
        panelHeight:400,
        columns: [[
            {field: 'realname', title: '真实姓名', width: 150},
            {field: 'phone', title: ' 联系电话', width: 100},
            {field: 'address', title: '单位地址', width: 200},
        ]]
    })
}

//页面权限控制
function Accesscontrol() {

    switch (groleid) {
        //收运人员
        case 9:
            $('#btnSave').linkbutton('disable');
            $('#btnAllocating').linkbutton('disable');
            $('#btnDelete').linkbutton('disable');
            $('#btnSearch').linkbutton('disable');
            //$('#btnComplete').linkbutton('disable');
            break;
        //产废单位
        case 10:
            $('#btnAllocating').linkbutton('disable');
            $('#btnDelete').linkbutton('disable');
            $('#btnSearch').linkbutton('disable');
            $('#btnConfirm').linkbutton('disable');
            $('#btnComplete').linkbutton('disable');
            break;
        //网络运营部
        case 15:
        case 27:
            $('#btnSave').linkbutton('disable');
            $('#btnSearch').linkbutton('disable');
            $('#btnConfirm').linkbutton('disable');
            $('#btnComplete').linkbutton('disable');
            break;
        //库管人员
        case 26:
            $('#btnSave').linkbutton('disable');
            $('#btnAllocating').linkbutton('disable');
            $('#btnDelete').linkbutton('disable');
            $('#btnSearch').linkbutton('disable');
            $('#btnConfirm').linkbutton('disable');
            break;
    }
}

/**
 * 添加
 */
function doAdd(){
    //region 添加订单

    if (groleid == 10) {
        $('#form1').form('load', {
            username_add: grealname,
            iphone_add: gphone,
            address_add: gaddress,
            orgname_add: gorgname,
        });
        $('#username_add').textbox('textbox').attr('readonly', true);  //设置输入框为禁用
        $('#iphone_add').textbox('textbox').attr('readonly', true);  //设置输入框为禁用
        $('#address_add').textbox('textbox').attr('readonly', true);
        $('#orgname_add').textbox('textbox').attr('readonly', true);
    }

    $('#orderadd').window('open');

    //endregion
}

/**
 * 删除
 */
function doDelete(){
    //region 删除订单

    var rows = $('#ordergrid').datagrid('getSelections');

    if (!rows || rows.length == 0) {
        $.messager.alert('提示', '请选择需要删除的订单');
        return;
    }

    /*

     if(rows.length>1){
     $.messager.alert('提示', '请选择一条订单');
     $('#ordergrid').datagrid("clearSelections");
     return;
     }

     console.log(rows);
     console.log(rows[0].orderid);

     var parm = {"orderid": rows[0].orderid};

     */

    var assetList = new Array();

    $.each(rows, function (i, n) {
        assetList.push(n.orderid);
    });

    $.messager.confirm('提示', '是否删除这些订单?', function (r) {
        if (!r) {
            return;
        }

        $.ajax({
            cache: false,
            datatype: 'json',
            contentType: 'application/json;charset=UTF-8',
            type: "POST",
            url: ws_url + '/rest/order/batchdeleteorders?token=' + gtoken,
            data: JSON.stringify(assetList),
            success: function (msg) {
                if (msg.status == 'success') {
                    $.messager.alert('提示', '删除成功！', "info", function () {
                        $('#ordergrid').datagrid("clearSelections");
                        $('#ordergrid').datagrid("reload");
                    });
                } else {
                    $.messager.alert('错误', '删除失败！', "error", function () {
                        $('#ordergrid').datagrid("clearSelections");
                        $('#ordergrid').datagrid("reload");
                    });
                }
            },
            error: function (xhr) {
                console.log(xhr);
                $('#ordergrid').datagrid("clearSelections");
                $.messager.alert('错误', '删除失败！', "error");
            }
        });
    });

    //endregion
}

/**
 * 分配
 */
function doAllocat(){
    //region 分配订单

    //var row = $('#ordergrid').datagrid('getSelected');

    var rows = $('#ordergrid').datagrid('getSelections');

    //console.log(row);

    if (!rows || rows.length == 0) {
        $.messager.alert('提示', '请选择需要分配的订单');
        return;
    } else {

        var orderidlist = "";

        for (var i = 0; i < rows.length; i++) {
            if (i == 0) {
                orderidlist += rows[i].orderid;
            } else {
                orderidlist += "," + rows[i].orderid;
            }
        }

        //alert(orderidlist);

        // if (rows.length > 1) {
        //     $.messager.alert('提示', '请选择一条订单');
        //     $('#ordergrid').datagrid("clearSelections");
        //     return;
        // } else {

        //$("#workerlist").combobox({
        //    reload: ws_url + '/rest/user/findAllTransporter?token=' + gtoken
        //});

        // $('#form2').form('load', {
        //     orderid_allocating: rows[0].orderid
        // });

        $('#form2').form('load', {
            orderid_allocating: orderidlist
        });

        $('#orderAllocating').window('open');

        //}

    }

    //endregion
}

/**
 * 确认
 */
function doConfirm() {
    //region 确认订单

    var rows = $('#ordergrid').datagrid('getSelections');

    if (!rows || rows.length == 0) {
        $.messager.alert('提示', '请选择需要确认的订单');
        return;
    }

    /*if(rows.length>1){
     $.messager.alert('提示', '请选择一条订单');
     $('#ordergrid').datagrid("clearSelections");
     return;
     }

     var params = {"orderid": rows[0].orderid,"orgstatus":"完成"};

     console.log(params);*/

    var assetList = new Array();

    $.each(rows, function (i, n) {
        assetList.push(n.orderid);
    });

    $.messager.confirm('提示', '是否确认这些订单?', function (r) {
        if (!r) {
            return;
        }
        $.ajax({
            url: ws_url + '/rest/order/batchupdateorderstatus?token=' + gtoken,
            contentType: 'application/json;charset=UTF-8',
            type: 'post',
            datatype: 'json',
            data: JSON.stringify(assetList),
            cache: false,
            success: function (data) {

                console.log(data.status);
                console.log(data.message);

                if (data.status == 'success') {
                    $.messager.alert('提示', '确认成功！', 'info', function () {
                        //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                        $('#ordergrid').datagrid("clearSelections");
                        $('#ordergrid').datagrid("reload");
                    });
                } else if (data.status == 'failure') {

                    $.messager.alert('提示', '确认失败！', 'error', function () {
                        //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                        $('#ordergrid').datagrid("clearSelections");
                        $('#ordergrid').datagrid("reload");
                    });

                }
            },
            error: function (xhr) {
                console.log(xhr);
                $('#ordergrid').datagrid("clearSelections");
                $.messager.alert('错误', '确认失败！', "error");
            }
        });
    });

    //endregion
}

/**
 * 完成
 */
function doComplete(){
    //region 完成订单
    var rows = $('#ordergrid').datagrid('getSelections');

    if (!rows || rows.length == 0) {
        $.messager.alert('提示', '请选择需要确认完成的订单');
        return;
    }

    /*if(rows.length>1){
     $.messager.alert('提示', '请选择一条订单');
     $('#ordergrid').datagrid("clearSelections");
     return;
     }

     var params = {"orderid": rows[0].orderid,"orgstatus":"完成"};

     console.log(params);*/

    //region 选择的订单数大于1

    var assetList = new Array();

    $.each(rows, function (i, n) {
        assetList.push(n.orderid);
    });

    $.messager.confirm('提示', '是否确认完成这些订单?', function (r) {
        if (!r) {
            return;
        }
        $.ajax({
            url: ws_url + '/rest/order/batchcompleteorderStatus?token=' + gtoken,
            contentType: 'application/json;charset=UTF-8',
            type: 'post',
            datatype: 'json',
            data: JSON.stringify(assetList),
            cache: false,
            success: function (data) {

                console.log(data.status);
                console.log(data.message);

                if (data.status == 'success') {
                    $.messager.alert('提示', '确认成功！', 'info', function () {
                        //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                        $('#ordergrid').datagrid("clearSelections");
                        $('#ordergrid').datagrid("reload");
                    });
                } else if (data.status == 'failure') {

                    $.messager.alert('提示', '确认失败！', 'error', function () {
                        //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                        $('#ordergrid').datagrid("clearSelections");
                        $('#ordergrid').datagrid("reload");
                    });

                }
            },
            error: function (xhr) {
                console.log(xhr);
                $('#ordergrid').datagrid("clearSelections");
                $.messager.alert('错误', '确认失败！', "error");
            }
        });
    });

    //endregion

    //endregion
}

/**
 * 查询
 */
function doSearch(){
    initdatagrid();
}