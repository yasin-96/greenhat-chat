/* eslint-disable no-undef */
import axios from 'axios';
import http from 'http';

/**
 * Defines Information about server
 */
const serverConfig = {
  apiAddress: process.env.VUE_APP_BACKEND_API,
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
    register: '/user/register',
  },
  message: '/message',
};

export default {
  async login({ username, password }) {
    return httpClient
      .get(apiInterfaces.user.login, { params: { username, password } })
      .then((response) => {
        console.log(response);
        const { data, status } = response;
        return { data, status };
      })
      .catch((error) => {
        console.log('LOGIN()', error);
      });
  },

  async newAccount(newUserInfo) {
    return httpClient
      .post(apiInterfaces.user.register, newUserInfo)
      .then((response) => {
        const { data, status } = response;
        return { data, status };
      })
      .catch((error) => {
        console.log('NEWACCOUNT()', error);
      });
  },

  async sendMessage(newMessage) {
    return httpClient
      .post(apiInterfaces.message, newMessage)
      .then((response) => {
        console.log("send msg",response)
        const { status } = response;
        return status;
      })
      .catch((error) => {
        console.log('SENDMESSAGE()', error);
      });
  },
};
