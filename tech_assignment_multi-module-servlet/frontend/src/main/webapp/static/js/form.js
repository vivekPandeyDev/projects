$(document).ready(function (){

    function dataConverter(formData){
        const {isPrimary,userName,email,dateOfBirth,addressLine1,addressLine2,country,state,zipcode,city} = formData
        const primaryAddress = {addressLine1,addressLine2,city,state,zipcode,country}
        var table = $('#myTable').tableToJSON();
        if(table[0].degreeType === "No data available in table"){

            alert("enter at least one education field")
            return {}
        }
        if(isPrimary !== undefined){
            return {
                userName,
                email,
                dateOfBirth,
                primaryAddress,
                educationList : table
            }
        }else{
            const {secAddressLine1,secAddressLine2,secCity,secState,secZipcode,secCountry} = formData
            const secondaryAddress = {secAddressLine1,secAddressLine2,secCity,secState,secZipcode,secCountry}
            if(!secAddressLine1 || !secAddressLine2 || !secCity || !secZipcode || !secCountry || !secState){
                alert("fill all details of secondary address")
                return {}
            }
            return {
                userName,
                email,
                dateOfBirth,
                primaryAddress,
                secondaryAddress,
                educationList : table
            }
        }

    }

    $("#myform").submit(function (e) {
        e.preventDefault()
        var formData = new FormData(e.target)
        let data = Object.fromEntries(formData)
        let reqData = dataConverter(data)
        if(reqData && Object.keys(reqData).length === 0 && Object.getPrototypeOf(reqData) === Object.prototype){
            return;
        }
        reqData = JSON.stringify(reqData)
        $.ajax({
            url : "http://localhost:8081/frontend/user/register",
            type : "POST",
            data : "para=" + reqData,
            success : function (data) {
                alert(data)
                console.log(data)
            },
            error : function (data){
                alert(data.responseText)
                console.log(data.responseText)
            }
        })

    })


})