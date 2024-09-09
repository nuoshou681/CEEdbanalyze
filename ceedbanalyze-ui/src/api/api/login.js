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