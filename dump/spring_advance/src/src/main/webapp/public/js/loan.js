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

let productPromise = new Promise((resolve,reject) =>{
    setTimeout(() => resolve(product), 1000);
})

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

loanNumber.addEventListener('change',matchLoan)