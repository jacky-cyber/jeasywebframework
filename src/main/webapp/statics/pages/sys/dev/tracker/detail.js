$(document).ready(function () {
    var grid_selector = "#grid-table";
    var id = $('#id').val();

    jQuery(grid_selector).jqGrid({
        url: "/sys/dev/tracker/detail.ajax?id=" + id,
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
        colNames: [ 'ID', 'tag', '开始时间', '结束时间', '耗时(ms)', 'Timeline'  ],
        colModel: [
            {name: 'id', index: 'id', hidden: true, width: 60, key: true },
            {name: 'tag', index: 'tag', width: 200 },
            {name: 'startTime', index: 'startTime', width: 80, hidden: true },
            {name: 'endTime', index: 'endTime', width: 130, hidden: true },
            {name: 'cost', index: 'cost', width: 30, hidden: true },
            {
                name: 'timeline', index: 'timeline',
                formatter: function (p1, p2, record) {
                    var title = "开始时间：" + record.startTime + "<br/>"
                        + "结束时间：" + record.endTime + "<br/>"
                        + "耗时：" + record.cost + "(ms)";

                    //<span class="btn btn-sm" data-rel="popover" title=""
                    // data-content="Heads up! This alert needs your attention, but it's not super important."
                    // data-original-title="Default">Default</span>

                    if (record.parentId == 0) {
                        return '<div data-rel="popover" data-placement="bottom" data-original-title="' + record.tag + '" data-content="' + title + '" data-placement="bottom"  class="progress"  cost="' + record.cost + '">'
                            + '<div style="width:100%" class="progress-bar "></div>'
                            + '</div>';
                    } else {
                        return '<div  data-rel="popover"  data-placement="top" data-original-title="' + record.tag + '" data-content="' + title + '"  class="progress" cost="' + record.cost + '" >'
                            + '<div class="progress-bar progress-bar-yellow" style="width: ' + record.w1 + '%;"></div>'
                            + '<div class="progress-bar progress-bar-danger" style="width: ' + record.w2 + '%;"></div>'
                            + '</div>';
                    }
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
            $('[data-rel=popover]').popover({html: true, trigger: 'hover', placement: 'auto'});
            $(grid_selector).jqGrid('collapseRow');
        },
        ondblClickRow: function (rowid, iRow, iCol, e) {
            var rowData = $(grid_selector).jqGrid('getRowData', rowid);
            //  $('tr[id="' + rowData.id + '"]').find('div.treeclick').click();
        },
        onSelectRow: function (rowid, status) {
            //  $('tr[id="' + rowid + '"]').find('div.treeclick').click();
        },
        autowidth: true
    });


});
