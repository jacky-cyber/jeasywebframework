$(document).ready(function () {
    $('.jd-chosen-select').chosen();

    $('#className').change(function () {
        var cn = $(this).val();
        var mid = $('#moduleId').val();

        window.location.href = '/sys/dept/resource/batch-add.html?className=' + cn + '&moduleId=' + mid;
    });


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
        $('#treeContainer').hide();
        $('#pIdName').val(treeNode.name);
        $('#moduleId').val(treeNode.id);
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


    $('#pIdName').click(function () {
        $('#treeContainer').show();
    });

    $('#btn-submit').click(function () {
        var mId = $('#moduleId').val();

        if (mId == -1) {
            alert('请选择对应的模块！')
            return;
        }


        var resourceList = new Array();

        var urls = new Array();
        $('input[name="chb"]:checked').each(function (idx, item) {
            var $tr = $(this).parent().parent();
            var url = $(this).val();
            var name = $tr.find('td.r-name input').val();
            var descp = $tr.find('td.r-descp input').val();
            var method = $tr.find('td.r-method').html();
            var javaMethod = $tr.find('td.r-javaMethod').html();

            var resource = {
                url: url,
                name: name,
                descp: descp,
                method: method,
                javaMethod: javaMethod
            };

            resourceList.push(resource);
        });

        var d = JSON.stringify(resourceList);
        var params = {
            resourceList: d,
            moduleId: mId
        };


        $.ajax({
            url: '/sys/dept/resource/batch-save.ajax',
            method: 'post',
            dataType: 'json',
            data: params,
            success: function (data) {
                if (data._success) {
                    window.location.href = '/sys/dept/resource/list.html?moduleId=' + mId;
                } else {
                    alert(data._msg);
                }
            }
        });
    });


});