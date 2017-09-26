/**
 * Created by Administrator on 2017/6/19.
 */


function ordercancel(){
    $('#orderadd').window('close');
    $('#orderAllocating').window('close');
    $("#ordergrid").datagrid("clearSelections");
}