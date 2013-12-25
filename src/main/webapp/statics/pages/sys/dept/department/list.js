$(document).ready(function () {
    var grid_selector = "#grid-table";
    jQuery(grid_selector).jqGrid({
        url: "/sys/dept/department/list.ajax",
        datatype: "json",
        treeGridModel: "adjacency",
        treeGrid: true,
        ExpandColumn: 'name',
        ExpandColClick: true,
        height: "auto",
        pager: false,
        loadui: "disable",
        autowidth: true,
        rowNum: 200,
        mtype: "GET",
        colNames: [ 'ID', '名称', '编码' , '描述', '有效标记', 'PID', '创建人', '创建时间', '修改人', '修改时间', '操作' ],
        colModel: [
            {name: 'id', index: 'id', hidden: true, width: 60, key: true },
            {name: 'name', index: 'name'  },
            {name: 'code', index: 'code'  },
            {name: 'descp', index: 'descp' },
            {name: 'enabled', index: 'enabled' },
            {name: 'parentId', index: 'parentId', hidden: true },
            {name: 'createUserId', index: 'createUserId', hidden: true },
            {name: 'createTime', index: 'createTime', hidden: true},
            {name: 'updateUserId', index: 'updateUserId', hidden: true},
            {name: 'updateTime', index: 'updateTime', hidden: true},
            {
                name: 'ops', index: '', fixed: true, sortable: false, resize: false,
                formatter: function (param1, param2, record) {
                    var edit_btn = '<a class="no-underline btn btn-xs btn-info" href="/sys/dept/department/edit.html?id=' + record.id + '" title="修改"><i class="icon-edit bigger-120"></i></a>';
                    var add_child_btn = '<a class="no-underline btn btn-xs btn-info" href="/sys/dept/department/add.html?parentId=' + record.id + '" title="添加子节点"><i class="icon-plus bigger-120"></i></a>';

                    var nbsp = '<span class="vbar">&nbsp;&nbsp;</span>';

                    return  add_child_btn + nbsp + edit_btn;

                }
            }
        ],
        treeReader: {
            level_field: "level",
            parent_id_field: "parent",
            leaf_field: "isLeaf",
            expanded_field: "expanded"
        },
        viewrecords: true,
        rowNum: -1,
        altRows: true,
        multiselect: true,
        multiboxonly: true,
        loadComplete: function () {


            $('.btn_j_lock').each(function (idx, item) {
                var $this = $(this);
                $this.click(function () {
                    var id = $(this).attr('v');
                    lockData(id, 'N');
                });
            });

            $('.btn_j_unlock').each(function (idx, item) {
                var $this = $(this);
                $this.click(function () {
                    var id = $(this).attr('v');
                    lockData(id, 'Y');
                });
            });

        },
        ondblClickRow: function (rowid, iRow, iCol, e) {
            var rowData = $(grid_selector).jqGrid('getRowData', rowid);
            $('tr[id="' + rowData.id + '"]').find('div.treeclick').click();
        },
        onSelectRow: function (rowid, status) {
            $('tr[id="' + rowid + '"]').find('div.treeclick').click();
        },
        autowidth: true
    });


});
