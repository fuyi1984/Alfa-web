/**
 * Created by Administrator on 2017/8/10.
 */

function menusubmitValidator(){
    if ($("#CascadeId_add").val() == "") {
        $.messager.alert('提示', '节点不能为空');
        return false;
    }

    if ($("#MenuName_add").val() == "") {
        $.messager.alert('提示', '菜单名称不能为空');
        return false;
    }

    /*if ($("#Url_add").val() == "") {
        $.messager.alert('提示', 'Url不能为空');
        return false;
    }*/

    if ($("#ParentId_add").val() == "") {
        $.messager.alert('提示', '父节点不能为空');
        return false;
    }

    return true;
}

function menusubmitForm(){
    if(menusubmitValidator()){

        var params = {
            "cascadeid": $("#CascadeId_add").val(),
            "menuname": $("#MenuName_add").val(),
            "url":$("#Url_add").val(),
            "parentid":$("#ParentId_add").val(),
            "icon":$("#Icon_add").val()
        }

        console.log(params);

        $.ajax({
            url: ws_url + '/rest/menu/insertmenu?token=' + gtoken,
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
                        $('#menuadd').window('close');
                        $('#menugrid').datagrid("reload");
                    });
                } else if (data.status == 'failure') {
                    if (data.message == '1') {
                        $.messager.alert('提示', '数据已存在！', 'warning', function () {
                            //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                            $('#menuadd').window('close');
                            $('#menugrid').datagrid("reload");
                        });
                    } else {
                        $.messager.alert('提示', '添加失败！', 'error', function () {
                            //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                            $('#menuadd').window('close');
                            $('#menugrid').datagrid("reload");
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