import { createSlice } from "@reduxjs/toolkit";

const profileSlice = createSlice({
  name: "profile",
  initialState: {
    isShowProfile: false,
  },
  reducers: {
    showProfile: (state, action) => {
      state.isShowProfile = action.payload;
    },
  },
});

export const { showProfile } = profileSlice.actions;
export default profileSlice.reducer;
