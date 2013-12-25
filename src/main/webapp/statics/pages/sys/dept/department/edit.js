$(document).ready(function () {
    var v = $('#oorder').val();
    $('#oorder').ace_spinner({ value: v, min: 0, max: 200, step: 1, btn_up_class: 'btn-info', btn_down_class: 'btn-info'});

    $('.jd-chosen-select').chosen();


    $('#addForm').validate({
        rules: {
            name: {
                required: true
            },
            code: {
                required: true
            }
        },
        messages: {
            name: {
                required: '机构名称不能为空值！'
            },
            code: {
                required: '机构编码不能为空值！'
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
                url: '/sys/dept/department/update.ajax',
                type: 'post',
                dataType: 'json',
                success: function (json, statusText, xhr, $form) {
                    if (json._success) {
                        window.location.href = "/sys/dept/department/list.html";
                    } else {
                        alert(json._msg);
                    }

                }
            });
        }
    });


});
