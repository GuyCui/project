//指定ux起调目录
Ext.Loader.setPath({
    'ux': 'app/ux'
});

/**
 * 项目入口文件
 */
Ext.define('app.Application', {
    extend: 'Ext.app.Application',
    //应用命名空间
    name: 'app',
    quickTips: false,
    platformConfig: {
        desktop: {
            quickTips: true
        }
    },
    //应用启动
    launch: function () {
        //移除加载动画
        Ext.fly('loading-mask').destroy();
        console.log('当前版本号：', config.ver);
        let loggedIn;
        loggedIn = localStorage.getItem("TutorialLoggedIn");
        const link = document.createElement('link');
        link.type = 'image/x-icon';
        link.rel = 'shortcut icon';
        link.href = 'resources/images/favicon.ico';
        document.getElementsByTagName('head')[0].appendChild(link);
        //this.redirectTo(loggedIn ? 'user.home' : 'view.login');
        Ext.create({
            xtype: loggedIn ? 'user.home' : 'view.login'
        });
    },
    //应用有更新就会触发
    onAppUpdate: function () {
        window.location.reload();
    }
});
