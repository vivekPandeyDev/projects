$(document).ready(function (){
    $('#myTable').DataTable();
    function dataConverter(formData){
        const {degreeType,institution,year,percentage} = formData
        return [degreeType,institution,year,percentage]
    }

    $("#education").submit(function (e) {
        e.preventDefault()
        var formData = new FormData(e.target)
        let data = Object.fromEntries(formData)
        let reqData = dataConverter(data)
         $("#close").click();
        const oTable = $('#myTable').dataTable()
        oTable.fnAddData(reqData)

    })


})