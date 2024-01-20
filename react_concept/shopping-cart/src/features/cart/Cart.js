import { useDispatch, useSelector } from "react-redux";
import "./Cart.css";
import { removeItemAsync, updateItemAsync } from "./cartAction";
import { useState } from "react";

export function Cart() {
  
  const [quantity, setQuantity] = useState(1)
  const dispatch = useDispatch();
  const { items } = useSelector((state) => state.carts);

  const handleChange = (e, id) => {
    setQuantity(+e.target.value)
    dispatch(updateItemAsync({ id, change: { quantity: +e.target.value } }));
  };

  return (
    <div>
      <div>
        {items.map((item) => (
          <div className='cart-item' key={item.id}>
            <img className='img-fluid' src={item.thumbnail} alt='' />
            <div className='description'>
              <p>{item.title}</p>
              <span>{item.brand}</span>
              <strong>${item.price*quantity}</strong>
            </div>
            <div className='quantity'>
              Quantity
              <select
                name="quantity"
                value={item.quantity}
                onChange={(e) => handleChange(e, item.id)}
              >
                <option value={1}>1</option>
                <option value={2}>2</option>
                <option value={3}>3</option>
              </select>
            </div>
            <div className='close'>
              <button onClick={() => dispatch(removeItemAsync(item.id))}>
                X
              </button>
            </div>
          </div>
        ))}
      </div>
      <h1>
        Total:{items.reduce((acc, item) => item.price * item.quantity + acc, 0)}
      </h1>
    </div>
  );
}
