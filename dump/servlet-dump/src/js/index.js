import {product} from './products.js'

let productPromise = new Promise((resolve,reject) =>{
    setTimeout(() => resolve(product), 1000);
})
const firstName = document.getElementById("validationServer02")
const lastName = document.getElementById("validationServer03")
const fullName = document.getElementById("validationServer04")
const loanNumber = document.getElementById("validationServer06")
const loanType = document.getElementById("loanType")
const productType = document.getElementById("product")

productPromise.then( (data) => {
    const products =Object.keys(data)
    products.forEach( (val) => {
                            const node = document.createElement("option" );
                            node.setAttribute("value",val)
                            const textnode = document.createTextNode(val);
                            node.appendChild(textnode);
                            loanType.appendChild(node)
    })
})

function removeAllChildNodes(parent) {
    while (parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }
}

loanType.addEventListener('change', (e) => {
    productPromise.then( (data) => {
            const product = data[e.target.value]
            removeAllChildNodes(productType)
            product.forEach( (val) => {
                            const node = document.createElement("option" );
                            node.setAttribute("value",val)
                            const textnode = document.createTextNode(val);
                            node.appendChild(textnode);
                            productType.appendChild(node)

            })

    })
})


function matchLoan(e){
       if(e.target.value.match(/^[A-Za-z0-9]+(?:[ _-][A-Za-z0-9]+)*$/) == null || e.target.value.length < 3){
                e.target.classList.remove('is-valid')
                e.target.classList.add('is-invalid')
       }else{
                e.target.classList.remove('is-invalid')
                e.target.classList.add('is-valid')
       }
}

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
loanNumber.addEventListener('change',matchLoan)
firstName.addEventListener('change',(e) =>{
        fullName.value = firstName.value + " " + lastName.value;
} )
lastName.addEventListener('change',(e) =>{
        fullName.value = firstName.value + " " + lastName.value;
})



