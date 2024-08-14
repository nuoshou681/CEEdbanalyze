import axios from 'axios';

const apiClient = axios.create({
  baseURL: 'https://localhost:5173', // 替换为你的 API 基础 URL
  headers: {
    'Content-Type': 'application/json',
    'Accept': 'application/json'
  }
});

export default apiClient;