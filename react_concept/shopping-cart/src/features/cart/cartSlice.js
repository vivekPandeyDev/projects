import { createSlice } from "@reduxjs/toolkit";

import { CART_STATUS } from "../../constant/cart/cart-constant";
import {
  addItemAsync,
  fetchAllItemsAsync,
  removeItemAsync,
  updateItemAsync,
} from "./cartAction";

const initialState = {
  items: [],
  status: CART_STATUS.IDLE,
  error: "",
};

export const cartSlice = createSlice({
  name: "cart",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      //fetch all cart items
      .addCase(fetchAllItemsAsync.pending, (state) => {
        state.status = CART_STATUS.LOADING;
        state.error = "";
      })
      .addCase(fetchAllItemsAsync.fulfilled, (state, action) => {
        state.status = CART_STATUS.IDLE;
        state.items = action.payload;
      })
      .addCase(fetchAllItemsAsync.rejected, (state, action) => {
        state.status = CART_STATUS.ERROR;
        state.error = action.error.message;
      })
      //adding item to cart
      .addCase(addItemAsync.pending, (state) => {
        state.status = CART_STATUS.LOADING;
        state.error = "";
      })
      .addCase(addItemAsync.fulfilled, (state, action) => {
        state.status = CART_STATUS.IDLE;
        state.items.push(action.payload);
      })
      .addCase(addItemAsync.rejected, (state, action) => {
        state.status = CART_STATUS.ERROR;
        state.error = action.error.message;
      })
      //remove item to cart
      .addCase(removeItemAsync.pending, (state) => {
        state.status = CART_STATUS.LOADING;
        state.error = "";
      })
      .addCase(removeItemAsync.fulfilled, (state, action) => {
        state.status = CART_STATUS.IDLE;
        state.items = state.items.filter((item) => item.id !== action.payload);
      })
      .addCase(removeItemAsync.rejected, (state, action) => {
        state.status = CART_STATUS.ERROR;
        state.error = action.error.message;
      })
      //updating item in cart
      .addCase(updateItemAsync.pending, (state) => {
        state.status = CART_STATUS.LOADING;
        state.error = "";
      })
      .addCase(updateItemAsync.fulfilled, (state, action) => {
        state.status = CART_STATUS.IDLE;
        const index =  state.items.findIndex(item => item.productId === action.payload.productId)
        state.items[index] = {...state.items[index],...action.payload}
      })
      .addCase(updateItemAsync.rejected, (state, action) => {
        state.status = CART_STATUS.ERROR;
        state.error = action.error.message;
      });
  },
});

// export const { } = cartSlice.actions;

export default cartSlice.reducer;
