import axios from "axios";
import { getAuthToken } from "../storage/localStorage"

axios.defaults.baseURL = "";
axios.defaults.headers.post["Content-Type"] = "application/json";


export const request = (method, url, data) => {
    let headers = {};

    if (getAuthToken() !== null && getAuthToken() !== "null") {
        headers = { Authorization: `Bearer ${getAuthToken()}` };
    }

    return axios({
        method: method,
        headers: headers,
        url: url,
        data: data,
    });
};

export const getRequest = (url) => {
    let headers = {};

    if (getAuthToken() !== null && getAuthToken() !== "null") {
        headers = { Authorization: `Bearer ${getAuthToken()}` };
    }

    return axios({
        method: "GET",
        headers: headers,
        url: url
    });
}
