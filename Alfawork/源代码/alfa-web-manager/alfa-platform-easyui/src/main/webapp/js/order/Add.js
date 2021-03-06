/**
 * Created by Administrator on 2017/6/8.
 */

function AddValidator(){
    if ($("#username_add").val() == "") {
        $.messager.alert('提示', '申报人姓名不能为空');
        return false;
    }

    if ($("#iphone_add").val() == "") {
        $.messager.alert('提示', '申报人电话不能为空');
        return false;
    }

    if ($("#address_add").val() == "") {
        $.messager.alert('提示', '收油地址不能为空');
        return false;
    }

    if ($("#num_add").val() == "") {
        $.messager.alert('提示', '预收数量不能为空');
        return false;
    }

    if ($("#orgname_add").val() == "") {
        $.messager.alert('提示', '单位名称不能为空');
        return false;
    }

    return true;
}

function ordersubmitForm() {
    if(AddValidator()){

        var params = {
            "username": $("#username_add").val(),
            "iphone": $("#iphone_add").val(),
            "address": $("#address_add").val(),
            "num": $("#num_add").val(),
            "orgname":$("#orgname_add").val(),
            "remark":$("#remark_add").val(),
            "orgstatus":"1" //提交
        }

        console.log(params);

        $.ajax({
            url: ws_url + '/rest/order/insertorders?token=' + gtoken,
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
                        $('#orderadd').window('close');
                        $('#ordergrid').datagrid("reload");
                    });
                } else if (data.status == 'failure') {
                    if (data.message == '3') {
                        $.messager.alert('提示', '提交订单数超出了当天最大限制数,请联系平台管理员！', 'warning', function () {
                            //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                            $('#orderadd').window('close');
                            $('#ordergrid').datagrid("reload");
                        });
                    } else {
                        $.messager.alert('提示', '添加失败！', 'error', function () {
                            //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                            $('#orderadd').window('close');
                            $('#ordergrid').datagrid("reload");
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

