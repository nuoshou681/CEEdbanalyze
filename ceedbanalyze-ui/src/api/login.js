import apiClient from "./request";
export function userlogin(username, password) {
    return apiClient.post("/user/login2", null, { params: { username: username, password: password } });
}
export function GetCaptcha() {
    return apiClient.get("/verify/generateImageCode", { responseType: 'blob' });
}
export function userregister(username, password, code) {
    return apiClient.post("/user/login", { username: username, password: password, inputCode: code }, {
        hearders: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        withCredentials: true 
    });
}