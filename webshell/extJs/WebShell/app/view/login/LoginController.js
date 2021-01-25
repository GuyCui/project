Ext.define('WebShell.view.login.LoginController', {
    extend: 'Ext.app.ViewController',
    extend: 'WebShell.view.Home',
    alias: 'controller.login',

    requires: [
        'WebShell.view.Home'
    ],

    onLoginClick: function(btn) {

        const me = this;

        const form = this.lookupReference('form');
        if (form.isValid()) {
            btn.setDisabled(true);
            WebAjax.request({
                url: "/webShell",
                method: 'POST',
                params: form.getValues(),
                success: function () {
                    home.showIndex(form.getValues());
                    btn.setDisabled(false);
                        localStorage.setItem("TutorialLoggedIn", true);
                        // 删除登录窗口
                        me.getView().destroy();
                        Ext.create({
                            xtype: 'app-main'
                        });

                },
                failure: function () {
                    btn.setDisabled(false);
                    home.showIndex(form.getValues());
                    Ext.Msg.alert('请求错误', '请检查输入是否正确.');
                }
            });
        }
    }
});