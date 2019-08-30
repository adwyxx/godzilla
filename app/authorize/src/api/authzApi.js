import * as http from './http'
import config from './config'

export default {
  // 密码方式获取token
  getTokenByPassword: (user, pass) => {
    let params = {
      grant_type: 'password',
      username: user,
      password: pass
    }
    return http.get(config.api.getToken, params)
  },
  // 授权码方式获取token
  getTokenByCode: (code, clientId, uri) => {
    let params = {
      grant_type: 'authorization_code',
      client_id: clientId,
      code: code,
      redirect_uri: uri
    }
    return http.get(config.api.getToken, params)
  },
  // 获取授权码
  authzCode: params => {
    return http.get(config.api.getToken, params)
  },
  // 验证token
  verifyToken: token => {
    return http.post(config.api.verifyToken, token)
  },
  // 刷新token
  refreshToken: token => {}
}
