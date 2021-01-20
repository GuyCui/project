Ext.define('WebShell.view.login.Login', {
    extend: 'Ext.window.Window',
    xtype: 'login',

    requires: [
        'WebShell.view.login.LoginController',
        'Ext.form.Panel'
    ],

    controller: 'login',
    bodyPadding: 10,
    title: '登录窗口',
    closable: false,
    autoShow: true,

    items: {
        xtype: 'form',
        reference: 'form',
        items: [
            {
                xtype: 'textfield',
                name: 'ip',
                fieldLabel: 'IP地址',
                allowBlank: false
            },
            {
                xtype: 'textfield',
                name: 'port',
                value: 22,
                fieldLabel: '端口',
                allowBlank: false
            },
            {
            xtype: 'textfield',
            name: 'userName',
            fieldLabel: '用户名',
            allowBlank: false
            },
            {
            xtype: 'textfield',
            name: 'Password',
            inputType: 'password',
            fieldLabel: '密码',
            allowBlank: false
            }
        ],
        buttons: [
            {
            text: '登录',
            formBind: true,
            listeners: {click: 'onLoginClick'}
            }
        ]
    }
});