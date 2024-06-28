import axios from "axios";
import Cookies from "js-cookie";
const baseAPI = axios.create();

baseAPI.defaults.baseURL = "http://localhost:8080";

baseAPI.interceptors.request.use(
  (config) => {
    const token = Cookies.get("token");
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    config.headers["Content-Type"] = "application/json";
    return config;
  },
  function (err) {
    return Promise.reject(err);
  }
);

baseAPI.interceptors.response.use(
  function (response) {
    return response.data;
  },
  async (err) => {
    console.log(err)
    // Cookies.remove("token");
    // window.location.href = "/login";
  }
);

export default baseAPI;
