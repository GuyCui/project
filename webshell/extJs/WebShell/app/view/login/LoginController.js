Ext.define('WebShell.view.login.LoginController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.login',

    onLoginClick: function(btn) {

        const me = this;

        const form = this.lookupReference('form');
        console.log("form", form.getValues())
        if (form.isValid()) {
            btn.setDisabled(true);
            WebAjax.request({
                url: "/webShell",
                method: 'POST',
                params: form.getValues(),
                success: function (response) {
                    console.log("res", response)
                    btn.setDisabled(false);
                    if (response.responseJson) {
                        localStorage.setItem("TutorialLoggedIn", true);
                        // 删除登录窗口
                        me.getView().destroy();
                        Ext.create({
                            xtype: 'app-main'
                        });
                    } else {
                        WebShell.Msg.error('系统异常');
                    }
                },
                failure: function (response) {
                    console.log("res", response)
                    Ext.Msg.alert('Status', '请求失败.');
                }
            });
        }
    }
});