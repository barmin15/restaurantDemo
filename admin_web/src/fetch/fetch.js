import axios from "axios";
import { getAuthToken } from "../storage/localStorage"

export const request = (method, url, data) => {
    axios.defaults.baseURL = "";
    axios.defaults.headers.post["Content-Type"] = "application/json";
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
    axios.defaults.baseURL = "";
    axios.defaults.headers.post["Content-Type"] = "application/json";
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

export const fileImagePostRequest = (method, url, data) => {
    axios.defaults.baseURL = "";
    axios.defaults.headers.post["Content-Type"] = "image/jpeg";
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
}
