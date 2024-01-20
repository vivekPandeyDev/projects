import { accountError, accountFulfilled, accountPending, decrement, increment, incrementByAmount } from "../constants/account_constant";


function incrementAmountByOne(){
    return {type : increment}
}

function decrementAmountByOne(){
    return {type : decrement}
}


function incrementAmountByValue(value){
    return {type : incrementByAmount, payload : value}
}

function initAccountError(error){
    return {type :accountError , error : error}
}

function initAccountFulfilled(amount){
    return {type : accountFulfilled, payload : amount }
}

function initAccountPending(){
    return {type : accountPending}
}


function initAccount(id){
    return async (dispatch,getState) => {
        try{
            dispatch(initAccountPending())
            const res = await fetch(`http://localhost:8080/accounts/${id}`);
            const data = await res.json()
            dispatch(initAccountFulfilled(data.amount))
        }catch(error){
            console.log('❎' ,error)
            dispatch(initAccountError(error.message))
        }
    }    
}


export {incrementAmountByOne,decrementAmountByOne,incrementAmountByValue,initAccount}