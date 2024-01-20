import { createAsyncThunk } from "@reduxjs/toolkit";

export const fetchHostVansAsync = createAsyncThunk("host/fetchByHost", async () => {
  const response = await fetch("/api/host/vans");
  return await response.json();
});




