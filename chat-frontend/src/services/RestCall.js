/* eslint-disable no-undef */
import axios from 'axios';
import http from 'http';

/**
 * Defines Information about server
 */
const serverConfig = {
  
  apiAddress: process.env.VUE_APP_BACKEND_API
};

/* Set: Axios Instance
 * These are the properties for axios given with to send requests.
 */
const httpClient = new axios.create({
  baseURL: serverConfig.apiAddress,
  httpAgent: new http.Agent({ keepAlive: true }),
  headers: {
    Accept: 'application/json',
    'Content-Type': 'application/json',
  },
});

const apiInterfaces = {
  user: {
    login: '/user/authenticate',
    register: '/user/register'
  },
};

export default {
  
  async login(userCred) {
    return httpClient
      .post(apiInterfaces.user.login, userCred)
      .then((response) => {
        const {data} = response;
        if(data && !!data){
          return data;
        }
      })
      .catch((error) => {
        console.log("LOGIN()", error);
      });
  },

  async newAccount(newUserInfo) {
    return httpClient
      .post(apiInterfaces.user.register, newUserInfo)
      .then((response) => {
        const {data} = response;
        if(data && !!data){
          return data;
        }
      })
      .catch((error) => {
        console.log("NEWACCOUNT()", error);
      });
  },
};
