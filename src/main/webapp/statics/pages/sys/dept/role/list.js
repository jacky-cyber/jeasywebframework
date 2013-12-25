$(document).ready(function () {
    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";


    $(grid_selector).jqGrid({
        url: "/sys/dept/role/list.ajax",
        datatype: "json",
        height: '100%',
        mtype: "GET",
        colNames: [ 'ID', '名称', '备注', '是否有效', '操作' ],
        colModel: [
            {name: 'id', index: 'id', width: 60  },
            {name: 'name', index: 'name', width: 80  },
            {name: 'descp', index: 'descp', width: 100  },
            {name: 'enabled', index: 'enabled', width: 40 },
            {name: 'ops', index: '', fixed: true, width: 80, sortable: false, resize: false,
                formatter: function (p1, p2, record) {
                    var btn_edit = '<a href="/sys/dept/role/edit.html?id=' + record.id + '" class="no-underline btn btn-xs btn-info" title="修改"><i class="icon-edit bigger-120"></i></a>'


                    var nbsp = '<span class="vbar">&nbsp;&nbsp;</span>';

                    return btn_edit + nbsp;
                }
            }
        ],
        viewrecords: true,
        rowNum: 10,
        rowList: [10, 20, 50, 100],
        pager: pager_selector,
        altRows: true,
        multiselect: true,
        multiboxonly: true,
        // caption: "用户列表",
        autowidth: true,
        loadComplete: function () {
            var table = this;
            setTimeout(function () {
                var replacement = {
                    'ui-icon-seek-first': 'icon-double-angle-left bigger-140',
                    'ui-icon-seek-prev': 'icon-angle-left bigger-140',
                    'ui-icon-seek-next': 'icon-angle-right bigger-140',
                    'ui-icon-seek-end': 'icon-double-angle-right bigger-140'
                };
                $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function () {
                    var icon = $(this);
                    var _class = $.trim(icon.attr('class').replace('ui-icon', ''));

                    if (_class in replacement) {
                        icon.attr('class', 'ui-icon ' + replacement[_class]);
                    }
                });
            }, 0);

        }
    });


});