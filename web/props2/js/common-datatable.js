/* 29/03/2016 */

//initializes DataTables
$('#models').dataTable({
    "aLengthMenu": [
        [10, 20, 50, 100, -1],
        [10, 20, 50, 100, "Semua"]
    ],
    //"scrollY": "200px",
    "scrollCollapse": true,
    "iDisplayLength": 20,
    "oLanguage": {
        "sLengthMenu": "Paparkan _MENU_ rekod",
        "sSearch": "Saring : ",
        "sEmptyTable": "Tiada rekod dijumpai",
        "sInfo": "Memaparkan _START_ hingga _END_ rekod daripada _TOTAL_ rekod",
        "sInfoFiltered": "(tapisan daripada _MAX_ rekod)",
        "sZeroRecords": "Tiada rekod yang sepadan ditemui",
        "sInfoEmpty": "Memaparkan 0 hingga 0 daripada 0 rekod",
        "oPaginate": {
            "sNext": "Seterusnya",
            "sPrevious": "Sebelum"
        }
    },
    "bSort": false,
    "aoColumnDefs": [{
            'bSortable': true,
            'aTargets': [0]
        }
    ]

});

/* 29/03/2016 */
