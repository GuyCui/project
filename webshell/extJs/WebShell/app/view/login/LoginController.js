Ext.define('WebShell.view.login.LoginController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.login',

    onLoginClick: function() {
        Ext.Ajax.request({
                // 被用来向服务器发起请求默认的url
                url: "/webShell",
                // 请求成功时回调函数
                success: function (xhr) {
                    localStorage.setItem("TutorialLoggedIn", true);
                    console.log("xhr", xhr)
                }
                // 请求失败时回调函数
                /*failure: function () {
                    Ext.ux.Toast.msg("信息提示", "信息出错，请重新输入!");
                }*/
            }
        );
        this.getView().destroy();

        Ext.create({
            xtype: 'app-main'
        });

    }
});