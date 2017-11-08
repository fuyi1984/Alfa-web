/**
 * Created by Administrator on 2017/8/14.
 */

function menueditValidator()
{
    if ($("#CascadeIdedit").val() == "") {
        $.messager.alert('提示', '节点不能为空');
        return false;
    }

    if ($("#MenuNameedit").val() == "") {
        $.messager.alert('提示', '菜单名称不能为空');
        return false;
    }

    /*if ($("#Urledit").val() == "") {
        $.messager.alert('提示', 'Url不能为空');
        return false;
    }*/

    if ($("#ParentIdedit").val() == "") {
        $.messager.alert('提示', '父节点不能为空');
        return false;
    }

    return true;
}

function menueditForm(){

    if(menueditValidator()){

        var params = {
            "menuid":$("#Idedit").val(),
            "cascadeid": $("#CascadeIdedit").val(),
            "menuname": $("#MenuNameedit").val(),
            "url":$("#Urledit").val(),
            "parentid":$("#ParentIdedit").val(),
            "icon":$("#Iconedit").val()
        }

        console.log(params);

        $.ajax({
            url: ws_url + '/rest/menu/editmenu?token=' + gtoken,
            contentType: 'application/json;charset=UTF-8',
            type: 'post',
            datatype: 'json',
            data: JSON.stringify(params),
            cache: false,
            success: function (data) {

                console.log(data.status);
                console.log(data.message);

                $('#form2').form('clear');

                if (data.status == 'success') {
                    $.messager.alert('提示', '修改成功！', 'info', function () {
                        //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                        $('#menuedit').window('close');
                        $('#menugrid').datagrid("clearSelections");
                        $('#menugrid').datagrid("reload");
                    });
                } else if (data.status == 'failure') {

                    if (data.message == '3') {
                        $.messager.alert('提示', '数据不存在！', 'warning', function () {
                            //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                            $('#menuedit').window('close');
                            $('#menugrid').datagrid("clearSelections");
                            $('#menugrid').datagrid("reload");
                        });
                    } else {
                        $.messager.alert('提示', '修改失败！', 'error', function () {
                            //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                            $('#menuedit').window('close');
                            $('#menugrid').datagrid("clearSelections");
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