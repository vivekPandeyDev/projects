const product ={
    "home loan" : [
        "home loan 1",
        "home loan 2",
        "home loan 3",
        "home loan 4",
        "home loan 5",
    ],
    "consumer vehicle loan" :[
        "consumer loan 1",
        "consumer loan 2",
        "consumer loan 3",
        "consumer loan 4",
    ],
    "education loan" : [
        "education loan 1",
        "education loan 2",
        "education loan 3",
    ]
}

const fruit = ["apple","mango","banana","pineapple"]


$(function(){

let id = 0;

    $("#repeater").change((e)=>{
        $("#repeater-label").text("Enter Text to Change: "+e.target.value)
    })

    fruit.forEach((value) =>{
        $("#options").append(new Option(value,value))
    })

    $("#options").change((e)=>{
        $('[data-toggle="tooltip"]').tooltip()
        if(fruit.includes(e.target.value))
            $("#options").attr("data-bs-original-title",e.target.value)
        else
            $("#options").attr("data-bs-original-title","pick select any option!!!")
    })

    $("#date").change( (e) =>{
        $("#date").css('pointer-events', 'none');
//        $("#date").css("pointer-events", "");
    })

    $('#check').click( (e) =>{
        const check = $("#check").prop("checked")
        if( check )
            $("#yes").prop("checked", true);
        else
            $("#no").prop("checked",true);
    })

    $('#yes').click( (e) =>{
        const check = $("#yes").prop("checked")
        if(check){
            $("#check").prop("checked",true)
        }else{
            $("#check").prop("checked",false)
        }
    })

        $('#no').click( (e) =>{
            const check = $("#no").prop("checked")
            if(check){
                $("#check").prop("checked",false)
            }else{
                $("#check").prop("checked",true)
            }
        })



    const generateRow = (id) =>{
    $(
    `
    <div id="grid-row-${id}" class="row mt-1">
    <div class="col-1 mb-3">
        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
    </div>
    <div class="col-1 mb-3">
        <a onclick="modalUpdate(event)" id="modal-opener--${id}" href="#" data-bs-toggle="modal" data-bs-target="#exampleModal">modal</a>
    </div>
    <div class="col-3 mb-3">
        <input type="number" min="0" class="form-control" id="number-grid-${id}" placeholder="Enter Amount">
    </div>
    <div class="col-3 mb-3">
        <select id="option-grid-${id}" onchange="myOption(event)"   class="form-select" aria-label="options select example">
        <option selected>Open this select menu</option>
        </select>
    </div>
    <div class="col-3 mb-3">
            <select id="option-grid-2-${id}" class="form-select" aria-label="options select example">
            <option selected>Open this select menu</option>
            </select>
    </div>
    <div class="col-1 mb-3">
        <button id="remove-${id}" type="button" onclick="remove(event)" class="btn btn-danger">Delete</button>
    </div>
    </div>
     `
    ).appendTo("#grid");






    }

    function generateSelect(id){
            const products = Object.keys(product)
            products.forEach((value) =>{
                $("#option-grid-"+id).append(new Option(value,value))
            })

    }



    $('#add-row').click( function () {
        generateRow(id);
        generateSelect(id)
        id= id+1;

    })

});

function getIndex(str){
    let idArr = str.split("-")
    return idArr[idArr.length - 1]
}


function remove(e){
    let id = getIndex(e.target.id)
    $('#grid-row-'+id).remove()
}

function myOption(event){
//    option-grid-2-${id}
    const index =getIndex(event.target.id)
    $('#option-grid-2-'+index).empty()
    product[event.target.value].forEach( (value) =>{
            $('#option-grid-2-'+index).append(new Option(value,value))
    })

}


function modalUpdate(e){
    const id = getIndex(e.target.id)
    const num = $('#number-grid-'+id).val()
    const option1 = $('#option-grid-'+id).val()
    const option2 = $('#option-grid-2-'+id).val()
    $('.modal-body').empty()
    if(num.length != 0){
        $(`
        <input type="text" class="form-control" value="${num}" readonly>
        `).appendTo('.modal-body')
    }
    if(option1 !== 'Open this select menu'){
            $(`
                <input type="text" class="form-control" value="${option1}" readonly>
                <input type="text" class="form-control" value="${option2}" readonly>
            `).appendTo('.modal-body')
    }
}