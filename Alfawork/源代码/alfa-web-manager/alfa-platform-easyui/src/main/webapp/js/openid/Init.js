/**
 * Created by Administrator on 2017/9/7.
 */
$(function () {

    gtoken = ReadCookie("token");

    if (gtoken != "") {
        setCurrentUser();

        $('#bindadmin').window('close');

        initcombobox();

        initdatagrid();
    } else {
        //window.location.href = platform_url + "/pages/home/login.html";
        top.location.href = platform_url + "/pages/home/login.html";
    }

});


function initdatagrid() {
    $('#openidgrid').datagrid({
        title: '微信用户',
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

        idField: 'Id',

        frozenColumns: [[
            {field: 'Id', checkbox: true}
        ]],

        columns: [[

            {field: 'openid', title: 'openid', width: 150, align: 'center'},

            {
                field: 'headimgurl', title: '微信头像', width: 60, align: 'center',
                formatter: function (value, rec) {
                    if (isNull(value)) {
                        return '<img src="' + value + '" width="60" height="60" border="0">';
                    }
                    else{
                        return '';
                    }
                }
            },

            {field: 'nickname', title: '微信昵称', width: 80, align: 'center'},

            {field: 'realname', title: '真实姓名', width: 80, align: 'center'},

            {field: 'roleId', title: '角色名', width: 80, align: 'center',
                formatter: function (value, rec) {
                    if(isNull(value)){
                        switch (value) {
                            case 15:
                                return '网络运营部';
                            case 9:
                                return '收运人员';
                            case 10:
                                return '产废单位';
                            case 28:
                                return '超级管理员';
                            case 27:
                                return '系统管理员';
                            default:
                                return '';
                        }
                    }else{
                        return ''
                    }
                }
            },
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
            cache:true,
            url: ws_url + '/rest/OpenId/findlist?token=' + gtoken,
            type: "post",
            data: 'filterscount=0&groupscount=0&pagenum=' + pagenum + '&pagesize=' + pagesize + '&recordstartindex=' + recordstartindex + '&recordendindex=' + recordendindex + '&nickname=' + $("#nickname").val() + '',
            contentType: 'application/json;charset=UTF-8',
            success: function (data) {
                console.log(data);
                $('#openidgrid').datagrid('clearSelections')
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

function isNull(data){
    return (data == "" || data == undefined || data == null) ? false: true;
}

/**
 * 查询
 */
function doSearch() {
    initdatagrid();
}

function initcombobox() {
    $('#adminlist').combogrid({
            url: ws_url + '/rest/user/findAllAdmin?token=' + gtoken,
            method: 'get',
            idField: 'phone',
            textField: 'realname',
            panelWidth: 450,
            panelHeight: 300,
            columns: [[
                {field: 'realname', title: '真实姓名', width: 150},
                {field: 'phone', title: ' 联系电话', width: 100},
                {field: 'address', title: '单位地址', width: 200},
            ]]
        }
    )
}

/**
 * 弹出绑定界面
 */
function doIsCheck() {

    var rows = $('#openidgrid').datagrid('getSelections');

    if (!rows || rows.length == 0) {
        $.messager.alert('提示', '请选择需要绑定的微信用户');
        return;
    }else{
        if (rows.length > 1) {
            $.messager.alert('提示', '请选择一条数据');
            $('#openidgrid').datagrid("clearSelections");
            return;
        }else{
            $('#form2').form('load', {
                id: rows[0].openid
            });

            $('#bindadmin').window('open');
        }
    }
}

/**
 * 解绑
 */
function doNotIsCheck(){
    var rows = $('#openidgrid').datagrid('getSelections');

    if (!rows || rows.length == 0) {
        $.messager.alert('提示', '请选择需要绑定的微信用户');
        return;
    }else{

        if (rows.length > 1) {
            $.messager.alert('提示', '请选择一条数据');
            $('#openidgrid').datagrid("clearSelections");
            return;
        }else{
            $.messager.confirm('提示', '是否解绑这些微信用户?', function (r) {
                if (!r) {
                    return;
                }

                //region 解绑

                var params = {
                    "openid": rows[0].openid,
                    "mobile": "",
                    "mobiletoken": ""
                };

                $.ajax({
                    url: ws_url + '/rest/OpenId/updateOpenId?token=' + gtoken,
                    contentType: 'application/json;charset=UTF-8',
                    type: 'post',
                    datatype: 'json',
                    data: JSON.stringify(params),
                    /*data:'orderidlist='+$("#orderid_allocating").val()+'&worker='+$('#workerlist').combogrid('getValue')+'&status=2',*/
                    cache: false,
                    success: function (data) {

                        console.log(data.status);
                        console.log(data.message);

                        $('#form2').form('clear');

                        if (data.status == 'success') {
                            $.messager.alert('提示', '修改成功！', 'info', function () {
                                //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                                $('#bindadmin').window('close');
                                $('#openidgrid').datagrid("clearSelections");
                                $('#openidgrid').datagrid("reload");
                            });
                        } else if (data.status == 'failure') {
                            if (data.message == '3') {
                                $.messager.alert('提示', '数据不存在！', 'warning', function () {
                                    //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                                    $('#bindadmin').window('close');
                                    $('#openidgrid').datagrid("clearSelections");
                                    $('#openidgrid').datagrid("reload");
                                });
                            } else {
                                $.messager.alert('提示', '修改失败！', 'error', function () {
                                    //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                                    $('#bindadmin').window('close');
                                    $('#openidgrid').datagrid("clearSelections");
                                    $('#openidgrid').datagrid("reload");
                                });
                            }
                        }
                    },
                    error: function (xhr) {
                        console.log(xhr);
                    }
                });

                //endregion

            });
        }
    }
}

function bindAdminValidator(){

    var admin=$('#adminlist').combogrid('getValue');

    if(admin=="")
    {
        $.messager.alert('提示', '管理人员不能为空');
        return false;
    }

    return true;
}

/**
 * 绑定管理员
 */
function bindadmin() {
   if(bindAdminValidator()){

       var params={
        "openid":$("#id").val(),
        "mobile":$('#adminlist').combogrid('getValue'),
        };


       $.ajax({
           url: ws_url+'/rest/OpenId/updateOpenId?token='+gtoken,
           contentType: 'application/json;charset=UTF-8',
           type: 'post',
           datatype: 'json',
           data:JSON.stringify(params),
           /*data:'orderidlist='+$("#orderid_allocating").val()+'&worker='+$('#workerlist').combogrid('getValue')+'&status=2',*/
           cache:false,
           success: function (data) {

               console.log(data.status);
               console.log(data.message);

               $('#form2').form('clear');

               if(data.status=='success'){
                   $.messager.alert('提示', '修改成功！', 'info', function () {
                       //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                       $('#bindadmin').window('close');
                       $('#openidgrid').datagrid("clearSelections");
                       $('#openidgrid').datagrid("reload");
                   });
               }else if(data.status=='failure'){
                   if (data.message == '3') {
                       $.messager.alert('提示', '数据不存在！', 'warning', function () {
                           //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                           $('#bindadmin').window('close');
                           $('#openidgrid').datagrid("clearSelections");
                           $('#openidgrid').datagrid("reload");
                       });
                   }else {
                       $.messager.alert('提示', '修改失败！', 'error', function () {
                           //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                           $('#bindadmin').window('close');
                           $('#openidgrid').datagrid("clearSelections");
                           $('#openidgrid').datagrid("reload");
                       });
                   }
               }
           },
           error: function (xhr) {
               console.log(xhr);
           }
       });
   }
}

/**
 * 退出
 */
function bindcancel() {
    $('#bindadmin').window('close');
    $("#openidgrid").datagrid("clearSelections");
}