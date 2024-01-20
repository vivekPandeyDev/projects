import { createSlice } from "@reduxjs/toolkit";

import { fetchVansAsync } from "./vansAction";
import { VansState } from "../../interface/props";




// Define the initial state using that type
const initialState: VansState = {
  vans: [],
  status: "IDLE",
  error: "",
};

export const vanSlice = createSlice({
  name: "vans",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      //fetch all vans
      .addCase(fetchVansAsync.pending, (state) => {
        state.status = "LOADING";
        state.error = "";
      })
      .addCase(fetchVansAsync.fulfilled, (state, action) => {
        state.status = "IDLE";
        state.vans = action.payload.vans;
      })
      .addCase(fetchVansAsync.rejected, (state, action) => {
        state.status = "ERROR";
        state.error = action.error.message || "";
      });
  },
});

// export const {  } = counterSlice.actions

export default vanSlice.reducer;
