Ext.define('WebShell.view.login.Login', {
    extend: 'Ext.window.Window',
    xtype: 'login',

    requires: [
        'WebShell.view.login.LoginController',
        'Ext.form.Panel'
    ],

    controller: 'login',
    bodyPadding: 10,
    width: 400,
    height: 300,
    layout: 'hbox',
    title: '登录窗口',
    closable: false,
    autoShow: true,
    onEsc: Ext.emptyFn,
    items: [{
        xtype: 'container',
        bind: {
            html: '<img src="{system.loginLogoUrl}">'
        },
        flex: 1
    }, {
        xtype: 'form',
        reference: 'form',
        flex: 4,
        defaults: {
            listeners: {
                specialKey: 'onSpecialKey'
            }
        },
        items: [
            {
                xtype: 'textfield',
                name: 'ip',
                inputType: 'numberDecimal',
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
                allowOnlyWhitespace: false,
                style: 'margin-top: 20px;'
            },
            {
                xtype: 'textfield',
                name: 'Password',
                inputType: 'password',
                fieldLabel: '密码',
                blankText: '请输入密码',
                allowBlank: false,
                style: 'margin-top: 20px;'
            }]
    }],
    buttons: [{
        text: '登录',
        formBind: true,
        iconCls: 'fa fa-sign-in',
        listeners: {
            click: 'onLoginClick'
        }
    }]
});