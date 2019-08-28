const restaurantAjaxUrl = "rest/profile/restaurants";

// $(document).ready(function () {
$(function () {
    makeEditable({
            ajaxUrl: restaurantAjaxUrl,
            datatableApi: $("#datatable").DataTable({
                "ajax": {
                    "url": restaurantAjaxUrl,
                    "dataSrc": ""
                },
                "paging": false,
                "info": true,
                "columns": [
                    {
                        "data": "name"
                    },
                    {
                        "data": "address",
                    },
                    {
                        "orderable": false,
                        "defaultContent": "",

                    },
                    {
                        "orderable": false,
                        "defaultContent": "",
                        "render":renderEditBtn

                    },
                    {
                        "orderable": false,
                        "defaultContent": "",
                        "render":renderDeleteBtn
                    }
                ],
                "order": [
                    [
                        0,
                        "asc"
                    ]
                ],
            }),
            updateTable: function () {
                $.get(userAjaxUrl, updateTableByData);
            }
        }
    );
});