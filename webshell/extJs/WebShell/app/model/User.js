//模型
//用户
Ext.define('app.model.User', {
    extend: 'Ext.data.Model',
    requires: ['Ext.data.proxy.LocalStorage'],
    fields: [
        {
            name: 'ip',
            type: 'int'
        },
        {
            name: 'port',
            type: 'int'
        },
        {
            name: 'userName',
            type: 'string'
        },
        {
            name: 'password',
            type: 'string'
        },
        {
            name: 'persist',
            type: 'boolean',
            defaultValue: false
        }],
    proxy: {
        //本地储存
        type: 'localstorage',
        id: 'demo-login-user'
    }
});