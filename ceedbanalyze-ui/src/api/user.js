import apiClient from "./request";

export default {
    // 获取所有专业信息
    getAllMajorInformation() {
        return apiClient.get('/major/all');
    },
    // 根据专业名字获取专业信息
    getMajorByName(data) {
        return apiClient.get('/major/getByName',{name : data });
    },
    // 根据专业层次和专业分类获取专业信息
    getByType() {
        return apiClient.post('/major/getByType', { type, category } );
    }
};