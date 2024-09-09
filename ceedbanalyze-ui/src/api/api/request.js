import axios from 'axios';

const apiClient = axios.create({
  baseURL: import.meta.env.VITE_APP_BASE_URL, 
  headers: {
    'Content-Type': 'application/json',
    'Accept': 'application/json'
  }
});

export default apiClient;