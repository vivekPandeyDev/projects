import { decrement, increment, incrementByAmount, accountError, accountFulfilled, accountPending } from "../constants/account_constant"

const initialState = {
    amount : 10,
    isLoading : false,
    error : ""
}

export function accountReducer(state =initialState ,action){
    switch(action.type){
        case accountPending : 
            return {...state,isLoading : true, error : ""}
        case accountFulfilled : 
            return {...state,isLoading : false, amount : action.payload}
        case accountError : 
            return {...state,isLoading : false, error : action.error}        
        case increment :
           return  {...state, amount : state.amount + 1}
        case decrement : 
            return  {...state, amount : state.amount - 1}   
        case incrementByAmount : 
            return  {...state, amount : state.amount + action.payload}       
        default : 
            return {...state}    
    }
}