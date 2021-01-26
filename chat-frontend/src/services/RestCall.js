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


async function rcGet(restPath, params, pathParam) {
  if (params) {
    return httpClient
      .get(restPath, { params: params })
      .then((response) => {
        console.log("RCGET()",response);
        const { data, status } = response;
        return { data, status };
      })
      .catch((error) => {
        console.log("RCGET()", error);
      });
  } else if(pathParam){
    return httpClient
    .get(`${restPath}/${pathParam._id}`)
    .then((response) => {
      console.log("RCGET()",response);
      const { data, status } = response;
      return { data, status };
    })
    .catch((error) => {
      console.log("RCGET()", error);
    });
  } else {
    return httpClient
      .get(restPath)
      .then((response) => {
        console.log("RCGET()", response);
        const { data, status } = response;
        return { data, status };
      })
      .catch((error) => {
        console.log("RCGET()", error);
      });
  }
}

async function rcPost(restPath, params, body) {
  if (params) {
    return httpClient
      .post(restPath, { params: params })
      .then((response) => {
        console.log(response);
        const { data, status } = response;
        return { data, status };
      })
      .catch((error) => {
        console.log("RCPOST()", error);
      });
  }
  if (body) {
    return httpClient
      .post(restPath, body)
      .then((response) => {
        console.log(response);
        const { data, status } = response;
        return { data, status };
      })
      .catch((error) => {
        console.log("RCPOST()", error);
      });
  }
}

async function rcPut(restPath, body) {
  return httpClient
    .put(restPath, body)
    .then((response) => {
      console.log(response);
      const { data, status } = response;
      return { data, status };
    })
    .catch((error) => {
      console.log("RCPUT()", error);
    });
}

async function rcPatch(restPath, {_id, update}) {
  console.log("patch call")
  return httpClient
    .patch(`${restPath}/${_id}`, update)
    .then((response) => {
      console.log(response);
      const { data, status } = response;
      return { data, status };
    })
    .catch((error) => {
      console.log("RCPUT()", error);
    });
}



async function rcDelete(restPath, param) {
  return httpClient
    .delete(restPath, { params: param })
    .then((response) => {
      console.log(response);
      const { data, status } = response;
      return { data, status };
    })
    .catch((error) => {
      console.log("RCDELETE()", error);
    });
}


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
  async rcRequest(restPath, methodType, hasParams, hasBody, hasPathVariable) {
    switch (String(methodType).toLowerCase()) {
      case "get":
        if (restPath && hasParams) {
          return await rcGet(restPath, hasParams);
        } 
        if (restPath && hasPathVariable) {
          return await rcGet(restPath, null, hasPathVariable);
        }  
        if(restPath) {
          return await rcGet(restPath, null, null);
        }
        break;
      case "post":
        if (restPath && hasParams) {
          return await rcPost(restPath, hasParams, null);
        }
        if (restPath && hasBody) {
          return await rcPost(restPath, null, hasBody);
        }
        break;

      case "put":
        if (restPath && hasBody) {
          return rcPut(restPath, hasBody);
        }
        break;
      case "patch":
          console.log("patch restcalls", hasBody)
          if (restPath && hasBody) {
            return rcPatch(restPath, hasBody);
          }
          break;
      case "delete":
        if (restPath && hasBody) {
          return rcDelete(restPath, hasBody);
        }
        break;
      default:
        console.warn(
          "rcRequest() no call ",
          restPath,
          methodType,
          hasParams,
          hasBody
        );
        break;
    }
  },
};
