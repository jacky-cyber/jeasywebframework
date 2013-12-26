$(document).ready(function () {
    var grid_selector = "#grid-table";
    var id = $('#id').val();

    jQuery(grid_selector).jqGrid({
        url: "/sys/dev/inside/detail.ajax?id=" + id,
        datatype: "json",
        treeGridModel: "adjacency",
        treeGrid: true,
        ExpandColumn: 'tag',
        ExpandColClick: true,
        height: "auto",
        pager: false,
        loadui: "disable",
        autowidth: true,
        rowNum: 200,
        mtype: "GET",
        colNames: [ 'ID', 'tag', '开始时间', '结束时间', '耗时(ms)'  ],
        colModel: [
            {name: 'id', index: 'id', hidden: true, width: 60, key: true },
            {name: 'tag', index: 'tag'  },
            {name: 'startTime', index: 'startTime', width: 80  },
            {name: 'endTime', index: 'endTime', width: 130, hidden: true },
            {name: 'cost', index: 'cost', width: 130  }


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
