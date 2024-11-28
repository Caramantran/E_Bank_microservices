import axios from "axios";
import env from "react-dotenv";


const getAuthToken = () => localStorage.getItem("accessToken");

const createHeaders = () => {
  const headers = {};
  const token = getAuthToken();
  if (token) {
    headers["Authorization"] = `Bearer ${token.replace(/"/g, "")}`;
  }
  return headers;
};

export const request = (options) => {
  //const headers = createHeaders();
  //if (options.setContentType !== false) {
  //  headers["Content-Type"] = "application/json";
  //}
  //options.headers = headers;
  
  return axios(options).then((response) => response.data);
};

const authServiceRequest = (path, method = "GET", data) =>
  request({
    url: `${env.API_URL}${path}`,
    method,
    data,
  });

const chatServiceRequest = (path, method = "GET") =>
  request({
    url: `${env.API_URL}${path}`,
    method,
  });

const compServiceRequest = (path, method = "POST", data) =>
  request({
    url: `${env.API_URL}${path}`,
    method,
    data,
  });

export const login = (loginRequest) =>
  authServiceRequest("/users/authenticate", "POST", loginRequest);

export const signup = (signupRequest) =>
  authServiceRequest("/users/register", "POST", signupRequest);

export const getCurrentUser = () => {
  if (!getAuthToken()) {
    return Promise.reject("No access token set.");
  }
  return authServiceRequest("/users/users/me");
};

export const getUsers = () => {
  if (!getAuthToken()) {
    return Promise.reject("No access token set.");
  }
  return authServiceRequest("/users/users");
};

export const countNewMessages = (senderId, recipientId) => {
  if (!getAuthToken()) {
    return Promise.reject("No access token set.");
  }
  return chatServiceRequest(`/messages/${senderId}/${recipientId}/count`);
};

export const findChatMessages = (senderId, recipientId) => {
  if (!getAuthToken()) {
    return Promise.reject("No access token set.");
  }
  return chatServiceRequest(`/messages/${senderId}/${recipientId}`);
};

export const findChatMessage = (id) => {
  if (!getAuthToken()) {
    return Promise.reject("No access token set.");
  }
  return chatServiceRequest(`/messages/${id}`);
};

export const getConcreteMedicaldata = (cmRequest) => {
  if (!getAuthToken()) {
    return Promise.reject("No access token set.");
  }
  return compServiceRequest("/medicaldata/concretedata", "POST", cmRequest);
};