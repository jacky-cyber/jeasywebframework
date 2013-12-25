$(document).ready(function () {

    $('.date-picker').datepicker({autoclose: true});
    $(".jd-chosen-select").chosen();
    var v = $('#oorder').val();
    $('#oorder').ace_spinner({value: v, min: 0, max: 200, step: 1, btn_up_class: 'btn-info', btn_down_class: 'btn-info'});


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
        $('#departmentId').val(treeNode.id);
    }

    jQuery.ajax({
        url: '/sys/dept/department/list-tree.ajax',
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
            departmentId: {
                required: true
            },
            name: {
                required: true
            },
            email: {
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
                url: '/sys/dept/user/update.ajax',
                type: 'post',
                dataType: 'json',
                success: function (json, statusText, xhr, $form) {
                    if (json._success) {
                        window.location.href = "/sys/dept/user/list.html";
                    } else {
                        alert(json._msg);
                    }

                }
            });
        }
    });

});