/**
 * The main application class. An instance of this class is created by app.js when it
 * calls Ext.application(). This is the ideal place to handle application launch and
 * initialization details.
 */
Ext.define('WebShell.Application', {
    extend: 'Ext.app.Application',

    name: 'WebShell',

    quickTips: false,
    platformConfig: {
        desktop: {
            quickTips: true
        }
    },

    /*launch: function () {

        // It's important to note that this type of application could use
        // any type of storage, i.e., Cookies, LocalStorage, etc.
        let loggedIn;

        // Check to see the current value of the localStorage key
        loggedIn = localStorage.getItem("TutorialLoggedIn");

        // This ternary operator determines the value of the TutorialLoggedIn key.
        // If TutorialLoggedIn isn't true, we display the login window,
        // otherwise, we display the main view
        Ext.create({
            xtype: loggedIn ? 'app-main' : 'login'
        });

    },*/
    //应用启动
    launch: function () {
        //移除加载动画
        Ext.fly('loading-mask').destroy();
        let eq = util.equals({a: 1}, {b: 2});
        console.log('{ a: 1 } 与 { b: 2 }是否相同：', eq);
        eq = util.equals({a: 1, c: {a: 1}}, {a: 1, c: {a: 1}});
        console.log('{ a: 1, c: { a: 1 } } 与 { a: 1, c: { a: 1 } }是否相同：', eq);
        console.log('当前版本号：', config.ver);
    },


    onAppUpdate: function () {
        Ext.Msg.confirm('应用程序更新', '当前应用程序有新版本，是否更新?',
            function (choice) {
                if (choice === 'yes') {
                    window.location.reload();
                }
            }
        );
    }
});
