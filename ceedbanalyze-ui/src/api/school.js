import apiClient from "./request";
export function getAllSchool() {
    return apiClient.get('/school/getAll');
}
export function getSchoolByName(data) {
    return apiClient.get('/school/searchbyname',{ data });
}
export function getSchool(page,pageSize){
    return apiClient.get('/school/getByPage',{page,pageSize});

}
export function SchoolSearch(name,city,type2,type3,page,pageSize){
    return apiClient.post('/school/search',{name,city,type2,type3,page,pageSize});
}