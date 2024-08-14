import apiClient from "./request";
export function getAllSchool() {
    return apiClient.get('/school/getAll');
}
export function getSchoolByName(data) {
    return apiClient.post('/school/getByName',{name : data });
}
export function getSchool(page,pageSize){
    return apiClient.post('/school/getByPage',{page:1,pageSize:10});

}
export function SchoolSearch(name,city,type2,type3,page,pageSize){
    return apiClient.post('/school/search',{name,city,type2,type3,page,pageSize});
}