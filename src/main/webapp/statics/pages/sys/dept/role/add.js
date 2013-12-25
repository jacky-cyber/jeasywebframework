$(document).ready(function () {
    var v = $('#oorder').val();
    $('#oorder').ace_spinner({value: v, min: 0, max: 200, step: 1, btn_up_class: 'btn-info', btn_down_class: 'btn-info'});


    var settings = {
        check: {
            enable: true
        },
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
//        $('#treeContainer').hide();
//        $('#pIdName').val(treeNode.name);
//        $('#moduleId').val(treeNode.id);
    }

    jQuery.ajax({
        url: '/sys/dept/resource/list-tree.ajax',
        method: 'get',
        dataType: 'json',
        success: function (data) {
            jQuery.fn.zTree.init(jQuery('#tree1'), settings, data);
            var ztree = jQuery.fn.zTree.getZTreeObj("tree1");
            ztree.expandAll(true);
        }
    });

    $('#btn-submit').click(function () {
        var tree1 = $.fn.zTree.getZTreeObj('tree1');

        var nodes = tree1.getCheckedNodes();
        var moduleIds = new Array();
        for (var j = 0; j < nodes.length; j++) {
            var node = nodes[j];
            moduleIds.push(node.id);
        }

        var ids = moduleIds.join(',');

        $.ajax({
            url: '/sys/dept/role/save.ajax',
            type: 'post',
            dataType: 'json',
            data: {
                resourceIds: ids,
                name: $('#name').val(),
                descp: $('#descp').val(),
                oorder: $('#oorder').val()
            },
            success: function (data) {
                if (data._success) {
                    window.location.href = '/sys/dept/role/list.html';
                } else {
                    alert(data._msg);
                }
            }
        });


    });


});