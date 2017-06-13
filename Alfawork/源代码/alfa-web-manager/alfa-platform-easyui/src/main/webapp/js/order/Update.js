/**
 * Created by Administrator on 2017/6/8.
 */

function allocatValidator(){

    var worker=$('#workerlist').combobox('getValue');

    if(worker=="")
    {
        $.messager.alert('提示', '收运人员不能为空');
        return false;
    }

    return true;
}

function orderallocatform(){
    if(allocatValidator()){

        var params={
            "orderid":$("#orderid_allocating").val(),
            "workerid":$('#workerlist').combobox('getValue'),
            "orgstatus":"分配"
        };


        console.log(params);

        $.ajax({
            url: ws_url+'/rest/order/updateorders?token='+gtoken,
            contentType: 'application/json;charset=UTF-8',
            type: 'post',
            datatype: 'json',
            data:JSON.stringify(params),
            cache:false,
            success: function (data) {

                console.log(data.status);
                console.log(data.message);

                $('#form2').form('clear');

                if(data.status=='success'){
                    $.messager.alert('提示', '修改成功！', 'info', function () {
                        //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                        $('#orderAllocating').window('close');
                        $('#ordergrid').datagrid("clearSelections");
                        $('#ordergrid').datagrid("reload");
                    });
                }else if(data.status=='failure'){

                    $.messager.alert('提示', '修改失败！', 'error', function () {
                        //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                        $('#orderAllocating').window('close');
                        $('#ordergrid').datagrid("clearSelections");
                        $('#ordergrid').datagrid("reload");
                    });

                }
            },
            error: function (xhr) {
                console.log(xhr);
            }
        });

    }
}