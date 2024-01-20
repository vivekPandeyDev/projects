import { increment } from "../constants/bonus_constant"

const initialState = {
    point : 0
}

export function bonusReducer(state =initialState ,action){
    switch(action.type){
        case increment :
           return  {...state, point : state.point + 1} 
        default : 
            return {...state}    
    }
}