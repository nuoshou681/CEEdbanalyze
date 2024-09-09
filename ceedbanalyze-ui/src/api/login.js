import apiClient from "./request";
export function userlogin(username, password) {
    return apiClient.post("/user/login2", null, { params: { username: username, password: password } });
}
export function GetCaptcha() {
    return apiClient.get("/verify/generateImageCode", { responseType: 'blob' });
}
export function UserRegister(username,password,phone,score,object1,object2,object3) {
    return apiClient.post("/user/register", {username:username,password:password,phone:phone,score:score,object1:object1,object2:object2,object3:object3});
}
export function StoreuserRank(id,score){
    return apiClient.post("/user/saveUserRanking",null,{params:{id:id,score:score}})
}
export function StoreuserInformation(id,subject1,subject2,subject3){
    return apiClient.post("/user/saveSubject",null,{params:{id:id,subject1:subject1,subject2:subject2,subject3:subject3}})
}