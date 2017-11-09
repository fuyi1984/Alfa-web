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
            data: 'filterscount=0&groupscount=0&pagenum=' + pagenum + '&pagesize=' + pagesize + '&recordstartindex=' + recordstartindex + '&recordendindex=' + recordendindex + '',
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
    $('#newsadd').window('open');
}


function doSearch(){
    initdatagrid();
}

/**
 * 添加新闻
 */
function newssubmitForm() {
    
}

/**
 * 退出
 */
function newscancel(){

}





