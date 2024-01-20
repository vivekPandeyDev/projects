$(document).ready(function () {

    function dataConverter(val){
        const {userName,email,leaves,total_attendance,total_hour_completion} = val
        return [userName,email,leaves,total_attendance,total_hour_completion]
    }

    $.ajax({
        type: "GET",
        url: "http://10.0.61.27:8080/report/monthly",
        success: function (response) {
            console.log("monthly response",response)
            response.data.forEach( (data,index)  => {
                data.forEach( (val) => {
                    const oTable = $('#monthlyReport').dataTable()
                    const arrayData  = dataConverter(val)
                    oTable.fnAddData(arrayData)
                })
            })

        },
        error : function(data) {
            console.log(data.responseText)
        }
    });

});