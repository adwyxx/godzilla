<template>
  <a-row type="flex"
         justify="center"
         align="middle"
         style="padding-top:10%;">
    <a-col :span="10"></a-col>
    <a-col :span="4">
      <a-card title="登录系统"
              :bordered="false">
        <a-form id="form-login"
                :form="form"
                class="login-form"
                @submit="onSubmit">
          <a-form-item>
            <a-input v-decorator="['userName', { rules: [{ required: true, message: '请输入用户名！' }] }]"
                     placeholder="Username">
              <a-icon slot="prefix"
                      type="user"
                      style="color: rgba(0,0,0,.25)" />
            </a-input>
          </a-form-item>
          <a-form-item>
            <a-input v-decorator="['password', { rules: [{ required: true, message: '请输入密码！' }] }]"
                     type="password"
                     placeholder="Password">
              <a-icon slot="prefix"
                      type="lock"
                      style="color: rgba(0,0,0,.25)" />
            </a-input>
          </a-form-item>
          <a-form-item>
            <a-button type="primary"
                      html-type="submit"
                      class="login-form-button">
              登录
            </a-button>
          </a-form-item>
        </a-form>
      </a-card>
    </a-col>
    <a-col :span="10"></a-col>
  </a-row>
</template>

<script>
export default {
  name: 'Login',
  beforeCreate () {
    this.form = this.$form.createForm(this)
  },
  methods: {
    onSubmit (e) {
      e.preventDefault()
      this.form.validateFields((err, values) => {
        if (!err) {
          if (this.$router.query) {
            let query = this.$router.query
            if (query.redirect_url) {
              window.location.href = query.redirect_url
            }
          }
        }
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#form-login .login-form {
  max-width: 100%;
  margin: auto;
}
#form-login .login-form-forgot {
  float: right;
}
#form-login .login-form-button {
  width: 100%;
}
</style>
