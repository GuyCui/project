Ext.define('app.view.login.Login', {
    extend: 'app.view.widget.LockingWindow',
    xtype: 'login',

    requires: [
        'app.view.login.LoginController',
        'Ext.form.Panel',
        'Ext.form.field.Checkbox'
    ],

    controller: 'user',
    bodyPadding: 10,
    width: 400,
    height: 300,
    layout: 'hbox',
    listeners: {
        //监听页面初始化事件
        render: 'onLoginRender'
    },
    title: '登录窗口',
    closable: false,
    autoShow: true,
    onEsc: Ext.emptyFn,
    items: [{
        xtype: 'authdialog',
        //默认提交按钮
        defaultButton: 'loginButton',
        //自动填充
        autoComplete: true,
        bodyPadding: '20 20',
        width: 415,
        layout: {
            type: 'vbox',
            align: 'stretch'
        },
        defaults: {
            margin: '5 0'
        },
        /*bind: {
            html: '<img src="https://up.enterdesk.com/edpic_source/d1/eb/89/d1eb89a77d030d052d0cbe7e494aa6d4.jpg" alt="text">'
        },*/
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
                allowBlank: false,
                triggers: {
                    glyphed: {
                        cls: 'trigger-glyph-noop auth-email-trigger'
                    }
                }
            },
            {
                xtype: 'textfield',
                name: 'port',
                value: 22,
                fieldLabel: '端口',
                allowBlank: false,
                triggers: {
                    glyphed: {
                        cls: 'trigger-glyph-noop auth-email-trigger'
                    }
                }
            },
            {
                xtype: 'textfield',
                name: 'userName',
                fieldLabel: '用户名',
                allowOnlyWhitespace: false,
                style: 'margin-top: 20px;',
                triggers: {
                    glyphed: {
                        cls: 'trigger-glyph-noop auth-email-trigger'
                    }
                }
            },
            {
                xtype: 'textfield',
                name: 'password',
                inputType: 'password',
                fieldLabel: '密码',
                blankText: '请输入密码',
                allowBlank: false,
                style: 'margin-top: 20px;',
                triggers: {
                    glyphed: {
                        cls: 'trigger-glyph-noop auth-password-trigger'
                    }
                }
            },
            {
                xtype: 'container',
                layout: 'hbox',
                items: [{
                    xtype: 'checkboxfield',
                    flex: 1,
                    cls: 'form-panel-font-color rememberMeCheckbox',
                    height: 30,
                    inputValue: 1,
                    name: 'persist',
                    bind: '{persist}',
                    boxLabel: '记住我'
                }]
            },
            {
                xtype: 'button',
                reference: 'loginButton',
                scale: 'large',
                ui: 'soft-green',
                iconAlign: 'right',
                iconCls: 'x-fa fa-angle-right',
                fieldLabel:'登录',
                text: '登录',
                formBind: true,
                listeners: {
                    click: 'onLoginClick'
                }
            }]
    }]
});