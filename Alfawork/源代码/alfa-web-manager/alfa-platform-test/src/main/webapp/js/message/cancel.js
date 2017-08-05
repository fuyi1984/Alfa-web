/**
 * Created by Administrator on 2017/7/21.
 */
function messagecancel(){
    $('#messageadd').window('close');
    $('#messageedit').window('close');
    $('#messagepublish').window('close');
    $("#messagegrid").datagrid("clearSelections");
}