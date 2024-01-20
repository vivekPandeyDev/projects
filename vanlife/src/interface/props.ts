import { FETCH_ERROR, FETCH_IDLE, FETCH_LOADING } from "../features/constants";


export type FETCH_STATE = FETCH_ERROR | FETCH_LOADING | FETCH_IDLE;

export interface LoginInput {
  email : string;
  password : string;
}

export interface VansProps {
  vansState: VansState;
}

export type VanContextType = {
  van : Van
}

export interface Van {
  description: string;
  id: number;
  imageUrl: string;
  name: string;
  price: number;
  type: string;
}

export interface VansState {
  vans: Van[];
  status: FETCH_STATE;
  error: string;
}




