/**
 * Created by Administrator on 2017/7/21.
 */
function messagesubmitValidator(){
    if ($("#titleadd").val() == "") {
        $.messager.alert('提示', '标题不能为空');
        return false;
    }

    if ($("#contentadd").val() == "") {
        $.messager.alert('提示', '内容不能为空');
        return false;
    }

    return true;
}

function messagesubmitForm(){
    if(messagesubmitValidator()){

        var params = {
            "title": $("#titleadd").val(),
            "content": $("#contentadd").val()
        }

        console.log(params);

        $.ajax({
            url: ws_url + '/rest/message/addmessage?token=' + gtoken,
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
                        $('#messageadd').window('close');
                        $('#messagegrid').datagrid("reload");
                    });
                } else if (data.status == 'failure') {
                    if (data.message == '1') {
                        $.messager.alert('提示', '数据已存在！', 'warning', function () {
                            //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                            $('#messageadd').window('close');
                            $('#messagegrid').datagrid("reload");
                        });
                    } else {
                        $.messager.alert('提示', '添加失败！', 'error', function () {
                            //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                            $('#messageadd').window('close');
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