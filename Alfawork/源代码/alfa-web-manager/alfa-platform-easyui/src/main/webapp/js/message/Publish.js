/**
 * Created by Administrator on 2017/7/21.
 */

function messagepublishValidator(){

    if ($("#phone").val() == "") {
        $.messager.alert('提示', '手机号不能为空');
        return false;
    }

    return true;
}

function messagepublishForm(){
    if(messagepublishValidator()){

        var params = {
            "phone":$('#phone').val(),
            "messageid": $("#IdPublish").val(),
            "isread": "0"
        }

        console.log(params);

        $.ajax({
            url: ws_url + '/rest/message/insertmessageuser?token=' + gtoken,
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
                    $.messager.alert('提示', '发布成功！', 'info', function () {
                        //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                        $('#messagepublish').window('close');
                        $('#messagegrid').datagrid("clearSelections");
                        $('#messagegrid').datagrid("reload");
                    });
                } else if (data.status == 'failure') {
                    if (data.message == '3') {
                        $.messager.alert('提示', '手机号不存在！', 'warning', function () {
                            //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                            $('#messagepublish').window('close');
                            $('#messagegrid').datagrid("clearSelections");
                            $('#messagegrid').datagrid("reload");
                        });
                    }else if(data.message == '4'){
                        $.messager.alert('提示', '此手机号不能发送消息通知！', 'warning', function () {
                            //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                            $('#messagepublish').window('close');
                            $('#messagegrid').datagrid("clearSelections");
                            $('#messagegrid').datagrid("reload");
                        });
                    }else if(data.message == '5'){
                        $.messager.alert('提示', '消息已经发送过此手机号,无需重复发送！', 'warning', function () {
                            //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                            $('#messagepublish').window('close');
                            $('#messagegrid').datagrid("clearSelections");
                            $('#messagegrid').datagrid("reload");
                        });
                    }
                    else {
                        $.messager.alert('提示', '发布失败！', 'error', function () {
                            //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                            $('#messagepublish').window('close');
                            $('#messagegrid').datagrid("clearSelections");
                            $('#messagegrid').datagrid("reload");
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