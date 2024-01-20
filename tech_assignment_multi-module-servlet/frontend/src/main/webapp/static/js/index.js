const firstName = document.getElementById("validationServer02")
const lastName = document.getElementById("validationServer03")
const fullName = document.getElementById("validationServer04")

firstName.addEventListener('change',(e) =>{
        fullName.value = firstName.value + " " + lastName.value;
} )
lastName.addEventListener('change',(e) =>{
        fullName.value = firstName.value + " " + lastName.value;
})

function matchName(e){
       if(e.target.value.match(/^[A-Za-z]+(?:[ _-][A-Za-z]+)*$/) == null || e.target.value.length < 3){
                e.target.classList.remove('is-valid')
                e.target.classList.add('is-invalid')
       }else{
                e.target.classList.remove('is-invalid')
                e.target.classList.add('is-valid')
       }
}

firstName.addEventListener('change',matchName)
lastName.addEventListener('change',matchName)