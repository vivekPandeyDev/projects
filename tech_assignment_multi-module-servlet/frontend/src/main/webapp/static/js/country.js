const countrySelect =document.getElementById("country");
const stateSelect =document.getElementById("state");
const citySelect =document.getElementById("city");

const fetchCountry = async () => {
 const data =await fetch("http://localhost:8080/rest_api/webapi/geo/countries")
 return data.json()
}

const fetchState = async (json) => {
    const data = await fetch("http://localhost:8080/rest_api/webapi/geo/countries/" + json.country)
    return data.json()
}

const fetchCities = async (json) => {
    const data = await fetch("http://localhost:8080/rest_api/webapi/geo/states/" + json.state)
    return data.json()
}

fetchCountry().then( (data) => {


   data.data.forEach( (countryName, index) => {
            const node = document.createElement("option" );
            node.setAttribute("value",countryName)
            const textnode = document.createTextNode(countryName);
            node.appendChild(textnode);
            countrySelect.appendChild(node)
    })
})



function removeAllChildNodes(parent) {
    while (parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }
}


countrySelect.addEventListener('change',(e) => {
    removeAllChildNodes(stateSelect)
   fetchState({
                   "country": e.target.value
               }).then( (data) => {

        if(data.error){
            alert("no state found")
        }else{
            data.data.forEach( (currentValue,index) => {
                            const node = document.createElement("option" );
                            node.setAttribute("value",currentValue)
                            const textnode = document.createTextNode(currentValue);
                            node.appendChild(textnode);
                            stateSelect.appendChild(node)
            })
        }
    })
})


stateSelect.addEventListener('change', (e) => {
   removeAllChildNodes(citySelect)
   fetchCities({
                   "state": e.target.value
               }).then( (data) => {

        if(data.error){
            alert("no city found")
        }else{
              data.data.forEach( (currentValue,index) => {
              const node = document.createElement("option" );
              node.setAttribute("value",currentValue)
              const textnode = document.createTextNode(currentValue);
              node.appendChild(textnode);
              citySelect.appendChild(node)
            })
        }
    })
})



const sec_countrySelect =document.getElementById("sec_country");
const sec_stateSelect =document.getElementById("sec_state");
const sec_citySelect =document.getElementById("sec_city");

fetchCountry().then( (data) => {


   data.data.forEach( (countryName, index) => {
            const node = document.createElement("option" );
            node.setAttribute("value",countryName)
            const textnode = document.createTextNode(countryName);
            node.appendChild(textnode);
            sec_countrySelect.appendChild(node)
    })
})


sec_countrySelect.addEventListener('change',(e) => {
    removeAllChildNodes(sec_stateSelect)
   fetchState({
                   "country": e.target.value
               }).then( (data) => {

        if(data.error){
            alert("no state found")
        }else{
            data.data.forEach( (currentValue,index) => {
                            const node = document.createElement("option" );
                            node.setAttribute("value",currentValue)
                            const textnode = document.createTextNode(currentValue);
                            node.appendChild(textnode);
                            sec_stateSelect.appendChild(node)
            })
        }
    })
})


sec_stateSelect.addEventListener('change', (e) => {
   removeAllChildNodes(sec_citySelect)
   fetchCities({
                   "state": e.target.value
               }).then( (data) => {

        if(data.error){
            alert("no city found")
        }else{
              data.data.forEach( (currentValue,index) => {
              const node = document.createElement("option" );
              node.setAttribute("value",currentValue)
              const textnode = document.createTextNode(currentValue);
              node.appendChild(textnode);
              sec_citySelect.appendChild(node)
            })
        }
    })
})