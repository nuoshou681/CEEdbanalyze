import apiClient from "./request";
export const getNum = async () =>{
    return apiClient.get('/yifenyiduan/data');
}
