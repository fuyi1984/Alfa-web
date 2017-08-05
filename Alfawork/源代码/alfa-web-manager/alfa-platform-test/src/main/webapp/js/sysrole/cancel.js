/**
 * Created by Administrator on 2017/6/19.
 */

function rolecancel() {
    $('#roleadd').window('close');
    $('#roleupdate').window('close');
    $('#rolesearch').window('close');
    $("#rolegrid").datagrid("clearSelections");
}