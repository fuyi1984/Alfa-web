/**
 * Created by Administrator on 2017/5/26.
 */

function isChecked(rowData) {
    var rows = $('#grid').datagrid('getSelections');
    for (var i = 0; i < rows.length; i++) {
        if(rowData.id==rows[i].id){
            return true;
        }
    }
    return false;
}