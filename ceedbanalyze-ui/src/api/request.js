import axios from 'axios';

const apiClient = axios.create({
  baseURL: "http://124.223.63.238:5173", 
  headers: {
    'Content-Type': 'application/json',
    'Accept': 'application/json'
  }
});

export default apiClient;