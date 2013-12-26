
$(document).ready(function () {
    var nodeId = $('#deptId').val();

    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";
    var settings = {
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: onClick
        }
    };

    function onClick(event, treeId, treeNode, clickFlag) {
        nodeId = treeNode.id;
        $('#deptId').val(nodeId);


        $('#btn-search').click();
    }

    $.ajax({
        url: '/sys/dept/department/list-tree.ajax',
        method: 'get',
        dataType: 'json',
        success: function (data) {
            $.fn.zTree.init($('#tree1'), settings, data);
            var ztree = $.fn.zTree.getZTreeObj("tree1");
            ztree.expandAll(true);
        }
    });

    $(grid_selector).jqGrid({
        url: "/sys/dept/user/list.ajax",
        datatype: "json",
        height: '100%',
        mtype: "GET",
        postData: {
            deptId: $('#deptId').val(),
            keyword: $('#keyword').val()
        },
        colNames: [ 'ID', '账号', '姓名', '邮箱', '手机', '是否有效', '操作' ],
        colModel: [
            {name: 'id', index: 'id', width: 60  },
            {name: 'username', index: 'username', width: 100  },
            {name: 'name', index: 'name', width: 80  },
            {name: 'email', index: 'email', width: 130  },
            {name: 'mobile', index: 'mobile', width: 100  },
            {name: 'enabled', index: 'enabled', width: 40 },
            {name: 'ops', index: '', fixed: true, width: 80, sortable: false, resize: false,
                formatter: function (p1, p2, record) {
                    var btn_edit = '<a href="/sys/dept/user/edit.html?id=' + record.id + '" class="no-underline btn btn-xs btn-info" title="修改"><i class="icon-edit bigger-120"></i></a>'

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

    $('#btn-add').click(function () {
        window.location.href = "/sys/dept/user/add.html?deptId=" + nodeId;
    });


    $('#btn-search').click(function () {
        $(grid_selector).jqGrid('setGridParam', {
            postData: {
                deptId: $('#deptId').val(),
                keyword: $('#keyword').val()
            },
            page: 1
        }).trigger("reloadGrid");
    });

    $('#keyword').keydown(function (event) {
        if (event.keyCode == 13) {
            $('#btn-search').click();
        }
    });


    $('#btn-delete').click(function () {
        var ids = $(grid_selector).jqGrid('getGridParam', 'selarrrow');
        if (ids == null || ids.length == 0) {
            alert('请选择要删除的记录！');
            return false;
        }
        if (confirm("确定要删除数据？")) {
            $.ajax({
                url: '/sys/dept/user/delete.ajax',
                type: 'post',
                dataType: 'json',
                data: {
                    ids: ids.join(',')
                },
                success: function (json, statusText, xhr, $form) {
                    if (json._success) {
                        $('#btn-search').click();
                    } else {
                        alert(json._msg);
                    }

                }
            })
        }
    });


});