$(document).ready(function () {
    var v = $('#oorder').val();
    $('#oorder').ace_spinner({value: v, min: 0, max: 200, step: 1, btn_up_class: 'btn-info', btn_down_class: 'btn-info'});

    $('.jd-chosen-select').chosen();


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



    $('#addForm').validate({
        rules: {
            moduleId: {
                required: true
            },
            name: {
                required: true
            },
            url: {
                required: true
            }
        },

        errorElement: 'div',
        errorClass: 'help-block',
        focusInvalid: false,
        invalidHandler: function (event, validator) { //display error alert on form submit
            $('.alert-danger', $('.login-form')).show();
        },
        highlight: function (e) {
            $(e).closest('.form-group').removeClass('has-info').addClass('has-error');
        },
        success: function (e) {
            $(e).closest('.form-group').removeClass('has-error').addClass('has-info');
            $(e).remove();
        },
        submitHandler: function (form) {
            $('#addForm').ajaxSubmit({
                url: '/sys/dept/resource/update.ajax',
                type: 'post',
                dataType: 'json',
                success: function (json, statusText, xhr, $form) {
                    if (json._success) {
                        window.location.href = "/sys/dept/resource/list.html";
                    } else {
                        alert(json._msg);
                    }

                }
            });
        }
    });


});
