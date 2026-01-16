import axios from 'axios';

export const api = axios.create({
    baseURL: 'https://backend-244230612831.us-east1.run.app'
});

api.interceptors.request.use(config => {
    const token = localStorage.getItem('token');
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});

export function loginRequest(credentials) {
    return api.post('/login', credentials);
}
