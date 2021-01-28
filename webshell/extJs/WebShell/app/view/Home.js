//视图
//首页
Ext.define('app.view.Home', {
    extend: 'Ext.container.Container',
    xtype: 'home',
    width: '100%',
    height: '100%',
    layout: 'column',
    // padding: 10,
    html: '<head><link href="../../sass/src/view/Home.css" rel="stylesheet"/><title>webssh</title></head>\n' +
        '<body><div id="terminal" style="width: 100%;height: 100%"></div>\n' +
        '<script src="../utils/jquery-3.4.1.min.js"></script>\n' +
        '<script charset="utf-8" src="../utils/xterm.js"></script>\n' +
        '<script charset="utf-8" src="../utils/webssh.js"></script></body>',
});
openTerminal({
    operate: 'connect',
    ip: '127.0.0.1',//IP
    port: '22',//端口号
    userName: 'cuimingzhi',//用户名
    password: '0511'//密码
});

function openTerminal(options) {
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
    term.write('Connecting...');
    //执行连接操作
    client.connect({
        onError: function (error) {
            //连接失败回调
            term.write('Error: ' + error + '\r\n');
        },
        onConnect: function () {
            //连接成功回调
            client.sendInitData(options);
        },
        onClose: function () {
            //连接关闭回调
            term.write("\rconnection closed");
        },
        onData: function (data) {
            //收到数据时回调
            term.write(data);
        }
    });
}