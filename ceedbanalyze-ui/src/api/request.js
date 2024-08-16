import axios from 'axios';

const apiClient = axios.create({
  baseURL: 'http://localhost:8080', // 替换为你的 API 基础 URL
  headers: {
    'Content-Type': 'application/json',
    'Accept': 'application/json'
  }
});

export default apiClient;