var fast_websocket = {
    init: function (host, port, path) {
        if (typeof (WebSocket) == "undefined") {
            console.log("您的浏览器不支持WebSocket")
        } else {
            var socket = new WebSocket('ws://' + host + ':' + port + path);

            //连接成功事件
            socket.onopen = function () {
                console.log("Socket连接成功");
            },

                //连接发生错误事件
                socket.onerror = function () {
                    console.log("Socket发生错误");
                },
                //连接关闭事件
                socket.onclose = function () {
                    console.log("Socket已关闭");
                },

                //监听窗口关闭事件，当窗口关闭时，客户端主动去关闭websocket连接，防止连接还没断开就关闭窗口，服务端会抛异常。
                window.onbeforeunload = function (socket) {
                    socket.close();
                }

            return socket
        }
    }
}
