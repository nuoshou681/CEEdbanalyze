import apiClient from "./request";
export function userlogin(username, password) {
    return apiClient.post("/user/login2", null,{params:{username:username,password:password}});
}
