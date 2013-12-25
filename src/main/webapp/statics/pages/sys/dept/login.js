$(document).ready(function () {


    $('#btn-login').click(function () {
        var rem = "0";
        var checked = $('#cb1').attr('checked');

        if (checked != undefined) {
            rem = "1";
        }

        $.ajax({
            url: '/sys/login.ajax',
            method: 'post',
            dataType: 'json',
            data: {
                returnUrl: $('#returnUrl').val(),
                username: $('#username').val(),
                password: $('#password').val(),
                remember: rem
            },
            error: function (jqXHR, textStatus, errorThown) {
                alert('发送请求失败，请刷新页面后重试！');
            },
            success: function (data, textStatus, jqXHR) {
                if (data._success) {
                    var returnUrl = $('#returnUrl').val();
                    if (returnUrl != '') {
                        window.location.href = returnUrl;
                    } else {
                        window.location.href = '/sys/index.html';
                    }
                } else {
                    alert(data._msg);
                }
            }
        });

    })
    ;


})
;