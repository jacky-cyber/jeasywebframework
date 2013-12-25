$(document).ready(function () {
    $('#btn-submit').click(function () {
        $('#addForm').submit();
    });

    $('#addForm').validate({
        rules: {
            cname: {
                required: true
            },
            domainClass: {
                required: true
            },
            domainPkg: {
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
            var params = {
                id: $('#id').val(),
                name: $('#name').val(),
                cname: $('#cname').val(),
                descp: $('#descp').val(),
                domainClass: $('#domainClass').val(),
                domainPkg: $('#domainPkg').val(),
                daoClass: $('#daoClass').val(),
                daoPkg: $('#daoPkg').val(),
                controllerClass: $('#controllerClass').val(),
                controllerPkg: $('#controllerPkg').val(),
                urlPrefix: $('#urlPrefix').val(),
                velocityPathPrefix: $('#velocityPathPrefix').val()
            };

            var columnList = new Array();

            $('.column-data').each(function (idx, item) {
                var $tr = $(this);
                var column = {};
                column.id = $tr.attr('cid');
                column.tableName = $('#name').val();

                column.name = $tr.find('td.column-name').html();
                column.dbType = $tr.find('td.column-dbType').html();
                column.dbKey = $tr.find('td.column-dbKey').html();
                column.dbNull = $tr.find('td.column-dbNull').html();
                column.cname = $tr.find('td.column-cname input').val();
                column.descp = $tr.find('td.column-descp input').val();
                column.javaType = $tr.find('td.column-javaType select').val();
                column.htmlType = $tr.find('td.column-htmlType select').val();
                column.required = $tr.find('td.column-required select').val();
                column.requiredMsg = $tr.find('td.column-requiredMsg input').val();
                column.minLength = $tr.find('td.column-length input:first-child').val();
                column.maxLength = $tr.find('td.column-length input:last-child').val();

                column.minVal = $tr.find('td.column-val input:first-child').val();
                column.maxVal = $tr.find('td.column-val input:last-child').val();

                column.dateFormat = $tr.find('td.column-dateFormat input').val();

                columnList.push(column);

            });
//            params.columnList = columnList;

            var d = JSON.stringify(columnList);
            params.columnList = d;


            $.ajax({
                url: '/dev/dev/table/update.ajax',
                type: 'post',
                dataType: 'json',
                data: params,
                success: function (data) {
                    if (data._success) {
                        var name = $('#name').val();
                        var date = new Date();

                        var dc = date.getUTCSeconds();

                        window.location.href = '/dev/dev/table/edit.html?name=' + name + '&_dc=' + dc;
                    } else {
                        alert(data._msg);
                    }
                }
            });
        }
    });
});