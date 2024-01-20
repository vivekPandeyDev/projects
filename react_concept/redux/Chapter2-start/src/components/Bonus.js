import { useDispatch, useSelector } from 'react-redux';
import { incrementBonusByOne } from '../actions/bonus_action';

function Bonus() {

  const point = useSelector(state => state.bonus.point)
  const dispatch = useDispatch()
  return (
    <div className="card">
      <div className="container">
        <h4>
          <b>Bonus Component</b>
        </h4>
        <h3>Total Point : ${point}</h3>

        <button onClick={() => dispatch(incrementBonusByOne())}>Increment +</button>
      </div>
    </div>
  );
}

export default Bonus;
