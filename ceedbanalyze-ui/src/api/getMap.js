
import apiClient from "./request";

export const getMap = async () => {
    return apiClient.get("https://geo.datav.aliyun.com/areas_v3/bound/370000.json");
}