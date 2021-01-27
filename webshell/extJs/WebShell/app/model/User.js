//模型
//用户
Ext.define('app.model.User', {
    extend: 'Ext.data.Model',
    requires: ['Ext.data.proxy.LocalStorage'],
    fields: [
        {
        name: 'ip',
        type: 'string'
        },
        {
            name: 'port',
            type: 'string'
        },
        {
            name: 'userName',
            type: 'int'
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