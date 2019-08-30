const config = { env: 'dev', api: {} }

// 获取token api
config.api.getToken = '/authz/token'
// 获取authorization code Api
config.api.getCode = '/authz/code'
// 验证token Api
config.api.verifyToken = '/authz/verifyToken'

export default config
