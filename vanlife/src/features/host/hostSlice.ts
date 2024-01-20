import { createSlice } from "@reduxjs/toolkit";
import { fetchHostVansAsync } from "./hostAction";
import { VansState } from "../../interface/props";




// Define the initial state using that type
const initialState: VansState = {
  vans: [],
  status: "IDLE",
  error: "",
};

export const hostSlice = createSlice({
  name: "vans",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      //fetch all vans owned by the host
      .addCase(fetchHostVansAsync.pending, (state) => {
        state.status = "LOADING";
        state.error = "";
      })
      .addCase(fetchHostVansAsync.fulfilled, (state, action) => {
        state.status = "IDLE";
        state.vans = action.payload.vans;
      })
      .addCase(fetchHostVansAsync.rejected, (state, action) => {
        state.status = "ERROR";
        state.error = action.error.message || "";
      });
  },
});

// export const {  } = counterSlice.actions

export default hostSlice.reducer;
