//公用类
// 用于存放各种配置
Ext.define('app.config', {
//别名，为了方便调用，这样通过 config.参数名 就能直接使用
// 如 config.ver
    alternateClassName: 'config', statics: {
//临时配置参数
        tmpConfig: null,
//版本号
        ver: '1.0.0'
    },
    //无须登陆检测就可以访问的页面
    // 例如登录页的 xtype 为 login，就配置为 login:true
    // 注意 xtype 只能小写
    unCheck: {
        userlock: true,
        userreset: true,
        login: true,
        register: true },
    //用户
    user: {
        //注册
        register: '~api/user/login',
        //重置密码
        reset: '~api/user/reset',
        //解除绑定
        unLock: '~api/user/login',
        // 登录
        login: '~api/user/login',
        //导航菜单
        navigation: '~api/user/navigation' },
});
