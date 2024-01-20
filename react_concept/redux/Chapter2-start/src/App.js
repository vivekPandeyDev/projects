import { useDispatch, useSelector } from 'react-redux';
import './App.css';
import Account from './components/Account';
import Bonus from './components/Bonus';
import { useEffect } from 'react';
import { initAccount } from './actions/account_action';
function App() {
  const amount =  useSelector(state => state.account.amount)
  const point = useSelector(state => state.bonus.point)
  const account = useSelector(state => state.account)
  const dispatch = useDispatch()
  useEffect(() =>{
    dispatch(initAccount(1))
  },[])
  return (
    <div className="App">
      {account.isLoading ? <h4>loading....</h4> : <h4>App</h4> }
      {account.error.length >0 && <h4>Unable to load value from database {account.error}</h4>}
      <h3>Current Amount : {amount} </h3>
      <h3>Total Bonus : {point}</h3>

      <Account></Account>
      <Bonus></Bonus>
    </div>
  );
}

export default App;
