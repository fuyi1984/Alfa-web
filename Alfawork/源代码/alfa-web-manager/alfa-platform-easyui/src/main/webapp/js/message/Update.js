/**
 * Created by Administrator on 2017/7/21.
 */

function messageeditValidator(){
    if ($("#titleedit").val() == "") {
        $.messager.alert('提示', '标题不能为空');
        return false;
    }

    if ($("#contentedit").val() == "") {
        $.messager.alert('提示', '内容不能为空');
        return false;
    }

    return true;
}

function messageeditValidator()
{
     if(AddValidator()){

         var params = {
             "id":$("#Idedit").val(),
             "title": $("#titleedit").val(),
             "content": $("#contentedit").val()
         }

         console.log(params);

         $.ajax({
             url: ws_url + '/rest/message/editmessage?token=' + gtoken,
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
                     $.messager.alert('提示', '添加成功！', 'info', function () {
                         //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                         $('#messageedit').window('close');
                         $('#messagegrid').datagrid("clearSelections");
                         $('#messagegrid').datagrid("reload");
                     });
                 } else if (data.status == 'failure') {
                     if (data.message == '3') {
                         $.messager.alert('提示', '数据不存在！', 'warning', function () {
                             //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                             $('#messageedit').window('close');
                             $('#messagegrid').datagrid("clearSelections");
                             $('#messagegrid').datagrid("reload");
                         });
                     } else {
                         $.messager.alert('提示', '添加失败！', 'error', function () {
                             //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                             $('#messageedit').window('close');
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