Ext.define('WebShell.view.login.LoginController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.login',

    onLoginClick: function(btn) {

        const me = this;

        const form = this.lookupReference('form');
        if (form.isValid()) {
            btn.setDisabled(true);
            Ext.Ajax.request({
                url: "/webShell",
                params: form.getValues(),
                success: function (res) {
                    console.log("res", res)
                    btn.setDisabled(false);
                    localStorage.setItem("TutorialLoggedIn", true);
                    // 删除登录窗口
                    me.getView().destroy();
                    Ext.create({
                        xtype: 'app-main'
                    });
                }
            });
        }
    }
});