/**
 * Created by Administrator on 2017/7/7.
 */

function AddValidator()
{
    if ($("#Content_add").val() == "") {
        $.messager.alert('提示', '评语内容不能为空');
        return false;
    }

    return true;
}

function CommonCommentsubmitForm(){

    if(AddValidator()){

        var params = {
            "content": $("#Content_add").val()
        }

        console.log(params);

        $.ajax({
            url: ws_url + '/rest/CommonComment/insertCommonComment?token=' + gtoken,
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
                        $('#CommonCommentadd').window('close');
                        $('#CommonCommentgrid').datagrid("reload");
                    });
                } else if (data.status == 'failure') {
                    if (data.message == '3') {
                        $.messager.alert('提示', '评语内容已存在！', 'warning', function () {
                            //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                            $('#CommonCommentadd').window('close');
                            $('#CommonCommentgrid').datagrid("reload");
                        });
                    }else if(data.message=="4"){
                        $.messager.alert('提示', '评语内容数量不能超出最大数量！', 'warning', function () {
                            //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                            $('#CommonCommentadd').window('close');
                            $('#CommonCommentgrid').datagrid("reload");
                        });

                    } else {
                        $.messager.alert('提示', '添加失败！', 'error', function () {
                            //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                            $('#CommonCommentadd').window('close');
                            $('#CommonCommentgrid').datagrid("reload");
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