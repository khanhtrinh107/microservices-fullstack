import { createSlice } from "@reduxjs/toolkit";

const userProfile = createSlice({
  name: "userProfile",
  initialState: {},
  reducers: {
    setUserProfile: (state, action) => {
      state = Object.assign(state, action.payload);
    },
  },
});

export const { setUserProfile } = userProfile.actions;
export default userProfile.reducer;
