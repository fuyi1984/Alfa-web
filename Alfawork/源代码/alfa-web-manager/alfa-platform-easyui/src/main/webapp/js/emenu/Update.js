/**
 * Created by Administrator on 2017/8/14.
 */

function menueditValidator()
{
    if ($("#CascadeId_add").val() == "") {
        $.messager.alert('提示', '节点不能为空');
        return false;
    }

    if ($("#MenuName_add").val() == "") {
        $.messager.alert('提示', '菜单名称不能为空');
        return false;
    }

    if ($("#Url_add").val() == "") {
        $.messager.alert('提示', 'Url不能为空');
        return false;
    }

    if ($("#ParentId_add").val() == "") {
        $.messager.alert('提示', '父节点不能为空');
        return false;
    }

    return true;
}

function menueditForm(){

    if(menueditValidator()){

    }
}