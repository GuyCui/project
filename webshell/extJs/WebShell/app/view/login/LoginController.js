Ext.define('WebShell.view.login.LoginController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.login',

    onLoginClick: function() {
        Ext.Ajax.request({
            // 被用来向服务器发起请求默认的url
            url: "http://localhost:8081/webShell",
            // 请求成功时回调函数
            success: function (ehr) {
                console.log("ehr", ehr)
                localStorage.setItem("TutorialLoggedIn", true);

                this.getView().destroy();

                Ext.create({
                    xtype: 'app-main'
                });
            },
            // 请求失败时回调函数
            /*failure: function () {
                Ext.ux.Toast.msg("信息提示", "信息出错，请重新输入!");
            }*/
            }
        );

    }
});