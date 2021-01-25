Ext.define('WebShell.Ajax', {
    extend: 'Ext.data.Connection',
    alternateClassName: ['WebAjax'],
    mixins: {
        observable: 'Ext.mixin.Observable'
    },
    singleton: true,
    autoAbort: false,
    config: {
        timeout: 30000,
        json: true
    },
    listeners: {
        beforerequest: function (conn, opts) {
            if (opts.json === undefined) {
                opts.json = this.getJson();
            }
            if (!opts.json) {
                return;
            }
            if (opts.success) {
                const callbackFunc = opts.success;
                opts.success = function (response, opts) {
                    try {
                        response.responseJson = Ext.decode(response.responseText);
                    } catch (e) {

                    }
                    callbackFunc(response, opts)
                }
            } else {
                opts.success = function (response) {
                    try {
                        response.responseJson = Ext.decode(response.responseText);
                    } catch (e) {

                    }
                }
            }
            if (opts.failure) {
                var failureFunc = opts.failure;
                opts.failure = function (response, opts) {
                    try {
                        response.responseJson = Ext.decode(response.responseText);
                    } catch (e) {

                    }
                    failureFunc(response, opts);
                }
            } else {
                opts.failure = (response) => {
                    try {
                        response.responseJson = Ext.decode(response.responseText);
                    } catch (e) {

                    }
                }
            }
        }
    },
    constructor: function (config) {
        this.callParent(config)
    }
});