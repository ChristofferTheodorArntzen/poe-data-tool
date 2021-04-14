import Axios from 'axios';

function returnAxiosInstance() {
    return Axios.create();
}

export async function get(url) {
    const axios = returnAxiosInstance();
    return await axios.get(url);
}

export function post(url, requestData) {
    const axios = returnAxiosInstance();
    return axios.post(url, requestData);
}

export function deleteRequest(url, requestData) {
    const axios = returnAxiosInstance();
    return axios.delete(url, requestData);
}

export function put(url, requestData) {
    const axios = returnAxiosInstance();
    return axios.put(url, requestData);
}

export function patch(url, requestData) {
    const axios = returnAxiosInstance();
    return axios.patch(url, requestData);
}