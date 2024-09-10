import apiClient from "./request";
export function start(score,subjects) {
    return apiClient.get('/prediction',{params:{score:score,subjects:subjects}});
}
export function getPrediction() {
    return apiClient.get('/prediction/data');
}