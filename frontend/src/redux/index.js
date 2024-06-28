import { combineReducers, configureStore } from "@reduxjs/toolkit";
import profileReducer from "./profile/reducer";
import userProfileReducer from "./userProfile/reducer";
import loginReducer from "./login/reducer";
import changeDataReducer from "./changeData/reducer";
const reducer = combineReducers({
  profile: profileReducer,
  userProfile: userProfileReducer,
  login: loginReducer,
  changeData: changeDataReducer,
});

const store = configureStore({
  reducer,
});

export default store;
