Ext.define('app.view.login.LoginController', {
    extend: 'ux.app.ViewController',
    alias: 'controller.user',

    onLoginClick: function(btn) {
        const me = this,
            view = me.getView(),
            form = view.down('form'),
            values = form.getValues();
        console.log("form", form)
        if (form.isValid()) {
            btn.setDisabled(true);
            WebAjax.request({
                url: "/webShell",
                method: 'POST',
                params: values,
                success: function () {
                    btn.setDisabled(false);
                    localStorage.setItem("TutorialLoggedIn", true);
                    // 删除登录窗口
                    me.getView().destroy();
                    Ext.create({
                        xtype: 'app-main'
                    });
                    me.keepUser(values);
                    me.loginSuccess(response.data);
                },
                failure: function () {
                    btn.setDisabled(false);
                    Ext.Msg.alert('请求错误', '请检查输入是否正确.');
                }
            });
        }
    },
    onLoginRender: function () {
        const me = this;
        app.model.User.load( {
            success: function (user) {
                console.log("读取到本地用户信息:", user)
                //如果读取到本地用户信息，自动填充到表单
                me.getViewModel().setData(user.getData());
                console.log("读取到本地:", me.getViewModel())
            }
        });
    },
    //登录成功
    loginSuccess: function (data) {
        //全局变量写入用户信息
        config.userData = data;
        console.log("全局变量本地用户信息:", data)
        //关闭弹窗
        this.getView().close();
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