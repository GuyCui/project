//视图
//首页
Ext.define('WebShell.view.Home', {
    extend: 'Ext.container.Container',
    xtype: 'home',
    padding: 10,
    html: '首页',

    showIndex: function(options) {
        const client = new WSSHClient();
        const term = new Terminal({
            cols: 97,
            rows: 37,
            cursorBlink: true, // 光标闪烁
            cursorStyle: "block", // 光标样式  null | 'block' | 'underline' | 'bar'
            scrollback: 800, //回滚
            tabStopWidth: 8, //制表宽度
            screenKeys: true
        });
        term.on('data', function (data) {
        //键盘输入时的回调函数
        client.sendClientData(data);
    });
    term.open(document.getElementById('terminal'));
    //在页面上显示连接中...
    term.write('玩命连接...' + '\r\n');
    //执行连接操作
    client.connect({
        onError: function (error) {
            //连接失败回调
            term.write('错误: ' + error + '\r\n');
        },
        onConnect: function () {
            //连接成功回调
            client.sendInitData(options);
        },
        onClose: function () {
            //连接关闭回调
            term.write("\r连接 关闭");
        },
        onData: function (data) {
            //收到数据时回调
            term.write(data);
        }
    });
}
});