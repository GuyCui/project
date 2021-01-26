Ext.define('app.view.login.LoginController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.login',

    onLoginClick: function(btn) {

        const me = this;
        // const showIndex = new WebSSH();
        const form = this.lookupReference('form');
        if (form.isValid()) {
            btn.setDisabled(true);
            WebAjax.request({
                url: "/webShell",
                method: 'POST',
                params: form.getValues(),
                success: function () {
                    btn.setDisabled(false);
                    // showIndex.showIndex(form.getValues());
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