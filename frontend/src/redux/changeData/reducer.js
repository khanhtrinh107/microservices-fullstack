import { createSlice } from "@reduxjs/toolkit";

const changeDataSlice = createSlice({
  name: "changeData",
  initialState: {
    isChangeData: false,
  },
  reducers: {
    setChangeData: (state, action) => {
      state.isChangeData = action.payload;
    },
  },
});

export const { setChangeData } = changeDataSlice.actions;
export default changeDataSlice.reducer;
