import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';
import { fetchProducts } from './productsAPI';
import { PRODUCT_STATUS } from '../../constant/products/product-constant';

const initialState = {
  products: [],
  status: PRODUCT_STATUS.IDLE,
  error : ''
};

export const fetchAllProductAsync = createAsyncThunk(
  'product/fetchProducts',
  async () => {
    const response = await fetchProducts();
    return response.data;
  }
);

export const productsSlice = createSlice({
  name: 'products',
  initialState,
  reducers: {
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchAllProductAsync.pending, (state) => {
        state.status = PRODUCT_STATUS.LOADING;
        state.error = '';
      })
      .addCase(fetchAllProductAsync.fulfilled, (state, action) => {
        state.status = PRODUCT_STATUS.IDLE;
        state.products = action.payload;
      })
      .addCase(fetchAllProductAsync.rejected, (state,action) => {
        state.status = PRODUCT_STATUS.ERROR;
        state.error = action.error.message;
      })
  },
});

// export const {  } = productsSlice.actions;



export default productsSlice.reducer;
