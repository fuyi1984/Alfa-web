/**
 * Created by Administrator on 2017/6/19.
 */
function usercancel(){
    $('#useradd').window('close');
    $('#userupdate').window('close');
    $('#usersearch').window('close');
    $("#usergrid").datagrid("clearSelections");
}