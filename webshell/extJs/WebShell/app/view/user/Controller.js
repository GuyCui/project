Ext.define('app.view.user.Controller', {
    extend: 'ux.app.ViewController',
    alias: 'controller.user',

    onLoginRender: function () {
        const me = this;
        app.model.User.load({
            success: function (user) {
                console.log("读取到本地用户信息:", user)
                //如果读取到本地用户信息，自动填充到表单
                me.getViewModel().setData(user.getData());
                console.log("读取到本地:", me.getViewModel())
            }
        });
    },
    onSpecialkey: function (f, e) {
        var me = this;
        if (e.getKey() === e.ENTER) {
            //按回车时自动提交数据
            me.onLoginClick();
        }
    },

    onLoginClick: function (btn) {
        const me = this,
            view = me.getView(),
            form = view.down('form'),
            values = form.getValues();
        console.log("form", form)
        if (form.isValid()) {
            btn.setDisabled(true);
            util.ajaxB(config.user.login, values, 'POST').then(function (response) {
                console.log("返回值：",response)
                if (response.success) {
                    btn.setDisabled(false);
                    me.keepUser(values);
                    //登录成功
                    me.loginSuccess(response.data);
                } else {
                    btn.setDisabled(false);
                    //登录失败
                    form.getForm().setValues({
                        password: ''
                    });
                }
                //提示消息
                Ext.toast(response.message);
            });
        }
    },
    //登录成功
      loginSuccess: function (data) {
        //全局变量写入用户信息
        config.userData = data;
        console.log("全局变量本地用户信息:", data)
          localStorage.setItem("TutorialLoggedIn", true);
        //关闭弹窗
          this.getView().destroy();
        //触发路由
        //由核心控制器接收路由，处理登录成功流程
        this.redirectTo('user.home');
    },
    //保存用户信息
    keepUser: function (user) {
        if (!user.persist) {
            user.password = '';
        }
        console.log("保存用户信息:", user)
        const logUser = Ext.create('app.model.User', user);
        //储存到本地
        logUser.save();
    },
});