
$(document).ready(function () {

    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";


    $(grid_selector).jqGrid({
        url: "/sys/dev/inside/list.ajax",
        datatype: "json",
        height: '100%',
        mtype: "GET",
        postData: {
            deptId: $('#deptId').val(),
            keyword: $('#keyword').val()
        },
        colNames: [ 'ID', 'tag', '开始时间', '结束时间' , '操作' ],
        colModel: [
            {name: 'id', index: 'id', width: 60  },
            {name: 'tag', index: 'tag', width: 100  },
            {name: 'startTime', index: 'startTime', width: 80  },
            {name: 'endTime', index: 'endTime', width: 130  },

            {name: 'ops', index: '', fixed: true, width: 80, sortable: false, resize: false,
                formatter: function (p1, p2, record) {
                    var btn_edit = '<a href="/sys/dev/inside/detail.html?id=' + record.id + '" class="no-underline btn btn-xs btn-info" title="查看详情"><i class="icon-zoom-in bigger-120"></i></a>'
                    return btn_edit;
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