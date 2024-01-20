import React, { useEffect } from "react";

import { useDispatch, useSelector } from "react-redux";
import { CART_STATUS } from "../../constant/cart/cart-constant";
import { addItemAsync } from "../cart/cartAction";
import "./Products.css";
import { fetchAllProductAsync } from "./productsSlice";

export function Products() {
  const products = useSelector((state) => state.products.products);
  const { error, status } = useSelector((state) => state.carts);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchAllProductAsync());
  }, [dispatch]);

  return (
    <div>
      {status === CART_STATUS.ERROR && <h5 style={{color : 'red', position : 'fixed', left : '40%'}}>{error}</h5>}
      <div className='container'>
        {products.map((product) => (
          <div className='card' key={product.id}>
            <img
              src={product.thumbnail}
              alt={product.title}
              style={{ width: "100%" }}
            />
            <h1>{product.title}</h1>
            <p className='price'>${product.price}</p>
            <p>{product.description}</p>
            <p>
              <button onClick={() => dispatch(addItemAsync(product))}>
                Add to Cart
              </button>
            </p>
          </div>
        ))}
      </div>
    </div>
  );
}
