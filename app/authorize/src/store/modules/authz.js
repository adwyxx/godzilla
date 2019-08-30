import authzApi from '../../api/authzApi'

const authz = {
  state: {
    token: null,
    loading: false
  },
  mutations: {
    setToken (state, token) {
      state.token = token
      localStorage.setItem('token', token)
    },
    setLoading (state, isLoading) {
      state.loading = isLoading
    }
  },
  actions: {
    // 根据用户名密码token
    getTokenByPassword ({ commit, state }, username, password) {
      state.loading = true
      authzApi
        .getTokenByPassword(username, password)
        .then(response => {
          if (response.data) {
            commit('setToken', response.data)
          }
        })
        .catch(error => console.log(error))
        .finally(() => {
          state.loading = false
        })
    },
    // 使用Authorization Code的方式获取token
    getTokenByCode ({ commit, state }, code, clientId, uri) {
      state.loading = true
      authzApi
        .getTokenByCode(code, clientId, uri)
        .then(response => {
          if (response.data) {
            commit('setToken', response.data)
          }
        })
        .catch(error => console.log(error))
        .finally(() => {
          state.loading = false
        })
    },
    // 获取Authorization Code
    getAuthorizationCode ({ commit, state }, clientId, uri) {},
    // 验证token
    verifyToken ({ commit, state }, token) {
      state.loading = true
      authzApi
        .verifyToken(token)
        .then(response => {
          if (response.data) {
            return true
          }
          return false
        })
        .catch(error => console.log(error))
        .finally(() => {
          state.loading = false
        })
    }
  }
}
export default authz
