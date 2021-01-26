/**
 * The main application class. An instance of this class is created by app.js when it
 * calls Ext.application(). This is the ideal place to handle application launch and
 * initialization details.
 */
Ext.define('app.Application', {
    extend: 'Ext.app.Application',

    name: 'WebShell',

    quickTips: false,
    platformConfig: {
        desktop: {
            quickTips: true
        }
    },

    //应用启动
    launch: function () {
        let loggedIn;
        loggedIn = localStorage.getItem("TutorialLoggedIn");
        Ext.create({
            xtype: loggedIn ? 'app-main' : 'login'
        });
        let eq = util.equals({a: 1}, {b: 2});
        console.log('{ a: 1 } 与 { b: 2 }是否相同：', eq);
        eq = util.equals({a: 1, c: {a: 1}}, {a: 1, c: {a: 1}});
        console.log('{ a: 1, c: { a: 1 } } 与 { a: 1, c: { a: 1 } }是否相同：', eq);
        console.log('当前版本号：', config.ver);
        //移除加载动画
        Ext.fly('loading-mask').destroy();

        var link = document.createElement('link');
        link.type = 'image/x-icon';
        link.rel = 'shortcut icon';
        link.href = 'resources/images/favicon.ico';
        document.getElementsByTagName('head')[0].appendChild(link);
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
