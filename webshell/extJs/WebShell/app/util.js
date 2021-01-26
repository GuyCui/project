// 公用类
// 公用方法
Ext.define('app.util', {
    // 别名
    alternateClassName: 'util',
    statics: {
        // 比较两个对象是否相等
        equals: function (x, y) {
            let p;
            const me = this;
            if (x === y) {
                return true;
            }

            //如果 x 或者 y 任意一个不是 object 类型
            if (!(x instanceof Object) || !(y instanceof Object)) {
                return false;
            }
            //如果 constructor 不相等
            if (x.constructor !== y.constructor) {
                return false;
            }
            //遍历比较
            for (p in x) {
                //如果 p 是 x 的属性
                if (x.hasOwnProperty(p)) {
                    //如果 y 中没有 p 这个属性
                    if (!y.hasOwnProperty(p)) {
                        return false;
                    }
                }
                //原代码 x[p] === y[p]
                // 这里不进行强制比较
                if (x[p] === y[p]) {
                    continue;
                }
                if (typeof (x[p]) !== "object") {
                    return false;
                }
                if ((!x[p] && y[p]) || (x[p] && !y[p])) {
                    return false;
                }
                //自调用进行比较
                if (!me.equals(x[p], y[p])) {
                    return false;
                }
            }
            for (p in y) {
                if (y.hasOwnProperty(p) && !x.hasOwnProperty(p)) {
                    return false;
                }
            }
            return true;
        }
    }
});