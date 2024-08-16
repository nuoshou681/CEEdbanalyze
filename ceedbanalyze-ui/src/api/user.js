import apiClient from "./request";

export default {
    // 获取所有专业信息
    getByPage(page, size) {
        return apiClient.get('/major/getByPage', {
            params: { page: page, size: size }
        });
    },
    // 根据专业名字获取专业信息
    fingByName(name, page, size) {
        return apiClient.get('/major/findByName', {
            params: { name: name, page: page, size: size }
        });
    },
    // 根据专业层次和专业分类获取专业信息
    search(data) {
        return apiClient.get('/major/search', {
            params: { secondLayer: data, page: 1, size: 10 }
        });
    }
};