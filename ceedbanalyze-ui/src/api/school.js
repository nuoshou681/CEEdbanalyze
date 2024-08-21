import apiClient from "./request";
export function getAllSchool() {
    return apiClient.get('/school/getAll');
}
export function getSchoolByName(data) {
    return apiClient.get('/school/searchbyname',{params:{data}} );
}
export function getSchool(page,size){
    return apiClient.get('/school/getByPage',{params: { page: page, size: size }});

}
export function SchoolSearch(name,city,type2,type3,page,pageSize){
    return apiClient.get('/school/search',{params:{name:name,city:city,type2:type2,type3:type3,page:page,size:pageSize}});

}
export function getMajorById(id){
    return apiClient.get('/schoolMajorScore/getBySchoolId',{params:{schoolId:id}});
}
export function getMajorBySchoolId(id,name){
    return apiClient.get('/schoolMajorScore/getBySchoolldAndMajorName',{params:{schoolId:id,majorName:name}});
}