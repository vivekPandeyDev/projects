import { useDispatch, useSelector } from "react-redux";
import { decrementAmountByOne, incrementAmountByOne, incrementAmountByValue } from "../actions/account_action";
import { useState } from "react";

  
function Account() {
  const account =  useSelector(state => state.account)
  const dispatch =  useDispatch()
  const [value,setValue] = useState(0)
  return (
    <div className="card">
      <div className="container">
        <h4>
          <b>Account Component</b>
        </h4>
        <h3>Amount:${account.amount}</h3>
        <button onClick={() => dispatch(incrementAmountByOne()) }>Increment +</button>
        <button onClick={() => dispatch(decrementAmountByOne()) }>Decrement -</button>
        <input type="number" onChange={(e) => setValue(+e.target.value)}></input>
        <button onClick={() => dispatch(incrementAmountByValue(value))}>
          Increment By {value} +
        </button>
      </div>
    </div>
  );
}

export default Account;
