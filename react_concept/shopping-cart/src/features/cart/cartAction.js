import { createAsyncThunk } from '@reduxjs/toolkit';
import { fetchItems, addItem, deleteItem,updateItem } from './cartAPI';
import { addItemAction, fetchAllItemsAction, removeItemAction, updateItemAction } from '../../constant/cart/cart-constant';


export const fetchAllItemsAsync = createAsyncThunk(
    fetchAllItemsAction,
    async () => {
      const response = await fetchItems();
      return response.data;
    }
);


export const addItemAsync = createAsyncThunk(
    addItemAction,
    async (item,{getState}) => {

      const myCartItems = getState().carts.items;
      const index =  myCartItems.findIndex(myCartItem => myCartItem.productId === item.id)
      if(index !== -1){
        throw new Error("item already present in the cart change quantity")
      }
      const response = await addItem(item);
      return response.data;
    }
);


export const removeItemAsync = createAsyncThunk(
    removeItemAction,
    async (id) => {
      await deleteItem(id);
      return id;
    }
);


export const updateItemAsync = createAsyncThunk(
    updateItemAction,
    async ({id,change}) => {
      const response = await updateItem(id,change);
      return response.data;
    }
);