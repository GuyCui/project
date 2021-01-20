//数据仓库
// 导航菜单
Ext.define('WebShell.store.NavigationTree', {
    extend: 'Ext.data.TreeStore',
//全局 id 可以通过 Ext.getStore('navigationTree')找到这个对象
    storeId: 'navigationTree', fields: [{name: 'text'}], root: {
        expanded: true, children: [{
            //标题
            text: '首页',
            // 图标
            iconCls: 'x-fa fa-desktop',
            //指向视图
            viewType: 'home',
            //是否有子节点
            leaf: true
        }, {
            text: '页面',
            iconCls: 'x-fa fa-leanpub',
            expanded: false,
            selectable: false,
            children: [{text: '空白页', iconCls: 'x-fa fa-file-o', viewType: 'pageblank', leaf: true}, {
                text: '404 页',
                iconCls: 'x-fa fa-exclamation-triangle',
                viewType: 'page404',
                leaf: true
            }, {
                text: '500 页', iconCls: 'x-fa fa-times-circle', viewType: 'page500',
                leaf: true
            }]
        }]
    }
});