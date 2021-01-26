Ext.define('app.view.pages.Blank', {
    extend: 'Ext.container.Container',
    xtype: 'pageblank',
    // 引入需要的类
    requires: ['Ext.container.Container'],
    anchor: '100% -1',
    //竖向布局，内容居中显示
    layout: {
        type: 'vbox',
        pack: 'center',
        align: 'center'
    }, items: [{
        xtype: 'box',
        cls: 'blank-page-container',
        html: '<div class="fa-outer-class">' +
            '<span class="x-fafa-clock-o"></span></div>' +
            '<h1>该页面正在建设中!</h1>' +
            '<span class="blank-page-text">请 耐心等待</span>'
    }]
});