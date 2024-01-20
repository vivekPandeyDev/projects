import { increment } from "../constants/bonus_constant";



function incrementBonusByOne(){
    return  {type : increment}
}

export {incrementBonusByOne}