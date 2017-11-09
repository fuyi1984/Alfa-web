$(function () {

    gtoken = ReadCookie("token");

    if (gtoken != "") {

        setCurrentUser();

        $('#newsadd').window('close');

        initdatagrid();
    } else {
        top.location.href=platform_url + "/pages/home/login.html";
    }
});

function initdatagrid() {
    $('#newsgrid').datagrid({
        title: '新闻头条',
        singleSelect: false,
        iconCls: 'icon-save',
        collapsible: true,
        nowrap: false,
        striped: true,
        loader: ajaxfindlist,
        sortName: 'createdDt',
        sortOrder: 'desc',
        remoteSort: true,
        fitColumns: true,
        fit: true,

        toolbar:"#tb",

        idField: 'id',

        frozenColumns: [[
            {field: 'id', checkbox: true}
        ]],

        columns: [[
            {field: 'title', title: '新闻标题', width: 80, align: 'center'},
            {field: 'content', title: ' 新闻内容', width: 200, align: 'center'},
            {field: 'publishDt', title: '发布日期', width: 80, align: 'center'},
            {
                field: 'createdDt', title: '创建时间', width: 100, align: 'center'
            }
        ]],
        pagination: true,
        rownumbers: true

    });

    function ajaxfindlist(param, success, error) {

        console.log(param);

        var pagenum = param.page - 1;
        var pagesize = param.rows;
        var recordstartindex = param.page == 1 ? 0 : param.rows * (param.page - 1);
        var recordendindex = param.rows * param.page;

        $.ajax({
            url: ws_url + '/rest/news/findlist?token=' + gtoken,
            type: "post",
            data: 'filterscount=0&groupscount=0&pagenum=' + pagenum + '&pagesize=' + pagesize + '&recordstartindex=' + recordstartindex + '&recordendindex=' + recordendindex +'&titlelike='+$('#busername').val()+ '',
            contentType: 'application/json;charset=UTF-8',
            success: function (data) {
                console.log(data);
                $('#newsgrid').datagrid('clearSelections')
                success(data);
            },
            error: function (xhr) {
                console.log(xhr);
                error(xhr.responseText);
            }
        });
    }
}

/**
 * 打开添加新闻窗口
 */
function doAdd(){
    editor.sync();
    editor.html('');
    $('#title').attr("value",'');
    $('#publishDt').datebox('setValue','');
    $('#newsadd').window('open');
}


function doSearch(){
    initdatagrid();
}

/**
 * 添加新闻
 */
function newssubmitForm() {

    if(Validator()) {

        editor.sync();

        //alert(editor.html());

        var params = {
            "title": $("#title").val(),
            "content": editor.html(),
            "publishDt":$("#publishDt").datebox('getValue')
        }

        console.log(params);

        $.ajax({
            url: ws_url + '/rest/news/insertnews?token=' + gtoken,
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
                        $('#newsadd').window('close');
                        $('#newsgrid').datagrid("reload");
                    });
                } else if (data.status == 'failure') {
                    if (data.message == '1') {
                        $.messager.alert('提示', '数据已存在！', 'warning', function () {
                            //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                            $('#newsadd').window('close');
                            $('#newsgrid').datagrid("reload");
                        });
                    } else {
                        $.messager.alert('提示', '添加失败！', 'error', function () {
                            //this.href = 'alfa-platform-easyui/pages/sysconfig/index.html';
                            $('#newsadd').window('close');
                            $('#newsgrid').datagrid("reload");
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

/**
 * 退出
 */
function newscancel(){
    $('#newsadd').window('close');
    $("#newsgrid").datagrid("clearSelections");
}

/**
 * 验证
 * @constructor
 */
function Validator(){
    if($('#title').val()==''){
        $.messager.alert('提示', '新闻标题不能为空');
        return false;
    }
    return true;
}

/**
 * 删除
 */
function doDelete(){
    var rows = $('#newsgrid').datagrid('getSelections');

    if (!rows || rows.length == 0) {
        $.messager.alert('提示', '请选择要删除的数据');
        return;
    }

    console.log(rows);
    console.log(rows[0].id);

    var assetList = new Array();

    $.each(rows, function (i, n) {
        assetList.push(n.id);
    });

    $.messager.confirm('提示', '是否删除这些数据?', function (r) {
        if (!r) {
            return;
        }

        $.ajax({
            cache: false,
            datatype: 'json',
            contentType: 'application/json;charset=UTF-8',
            type: "POST",
            url: ws_url + '/rest/news/batchdeletenews?token=' + gtoken,
            data: JSON.stringify(assetList),
            success: function (msg) {
                if (msg.status == 'success') {
                    $.messager.alert('提示', '删除成功！', "info", function () {
                        $('#newsgrid').datagrid("clearSelections");
                        $('#newsgrid').datagrid("reload");
                    });
                } else {
                    $.messager.alert('错误', '删除失败！', "error", function () {
                        $('#newsgrid').datagrid("clearSelections");
                        $('#newsgrid').datagrid("reload");
                    });
                }
            },
            error: function (xhr) {
                console.log(xhr);
                $('#newsgrid').datagrid("clearSelections");
                $.messager.alert('错误', '删除失败！', "error");
            }
        });
    });
}





