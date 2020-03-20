/**

 @Name：layuiAdmin 主页控制台
 @Author：贤心
 @Site：http://www.layui.com/admin/
 @License：GPL-2

 */


layui.define(function (exports) {

    /*
      下面通过 layui.use 分段加载不同的模块，实现不同区域的同时渲染，从而保证视图的快速呈现
    */


    //区块轮播切换
    layui.use(['admin', 'carousel'], function () {
        var $ = layui.$
            , admin = layui.admin
            , carousel = layui.carousel
            , element = layui.element
            , device = layui.device();

        //轮播切换
        $('.layadmin-carousel').each(function () {
            var othis = $(this);
            carousel.render({
                elem: this
                , width: '100%'
                , arrow: 'none'
                , interval: othis.data('interval')
                , autoplay: othis.data('autoplay') === true
                , trigger: (device.ios || device.android) ? 'click' : 'hover'
                , anim: othis.data('anim')
            });
        });

        element.render('progress');

    });

    //数据概览
    layui.use(['admin', 'carousel', 'echarts'], function () {
        var $ = layui.$
            , admin = layui.admin
            , carousel = layui.carousel
            , echarts = layui.echarts;

        var echartsApp = [], options = [
            //今日流量趋势
            {
                title: {
                    text: '今日报警趋势',
                    x: 'center',
                    textStyle: {
                        fontSize: 14
                    }
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: ['', '']
                },
                xAxis: [{
                    type: 'category',
                    boundaryGap: false,
                    data: [
                        '06:00', '06:30',
                        '07:00', '07:30',
                        '08:00', '08:30',
                        '09:00', '09:30',
                        '10:00', '10:30',
                        '11:00', '11:30',
                        '12:00', '12:30',
                        '13:00', '13:30',
                        '14:00', '14:30',
                        '15:00', '15:30',
                        '16:00', '16:30',
                        '17:00', '17:30',
                        '18:00', '18:30',
                        '19:00', '19:30',
                        '20:00', '20:30',
                        '21:00', '21:30',
                        '22:00', '22:30',
                        '23:00', '23:30']
                }],
                yAxis: [{
                    type: 'value'
                }],
                series: [{
                    name: '报警数量',
                    type: 'line',
                    smooth: true,
                    itemStyle: {normal: {areaStyle: {type: 'default'}}},
                    data: [10, 20, 30, 40, 50, 60, 50, 40, 30, 20, 30, 40, 50, 40, 50, 60, 70, 30, 20, 50, 40, 45, 30, 40, 50, 60, 70, 80, 90, 80, 70, 60, 40, 30, 20, 10]
                },]
            },

            //访客浏览器分布
            {
                title: {
                    text: '访客浏览器分布',
                    x: 'center',
                    textStyle: {
                        fontSize: 14
                    }
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    x: 'left',
                    data: ['Chrome', 'Firefox', 'IE', 'Safari', 'Opera']
                },
                series: [{
                    name: '访问来源',
                    type: 'pie',
                    radius: '55%',
                    center: ['50%', '50%'],
                    data: [
                        {value: 9052, name: 'Chrome'},
                        {value: 1610, name: 'Firefox'},
                        {value: 3200, name: 'IE'},
                        {value: 535, name: 'Safari'},
                        {value: 1700, name: 'Opera'}
                    ]
                }]
            },

            //新增的用户量
            {
                title: {
                    text: '最近一周新增的用户量',
                    x: 'center',
                    textStyle: {
                        fontSize: 14
                    }
                },
                tooltip: { //提示框
                    trigger: 'axis',
                    formatter: "{b}<br>新增用户：{c}"
                },
                xAxis: [{ //X轴
                    type: 'category',
                    data: ['11-07', '11-08', '11-09', '11-10', '11-11', '11-12', '11-13']
                }],
                yAxis: [{  //Y轴
                    type: 'value'
                }],
                series: [{ //内容
                    type: 'line',
                    data: [200, 300, 400, 610, 150, 270, 380],
                }]
            }
        ]
            , elemDataView = $('#LAY-index-dataview').children('div')
            , renderDataView = function (index) {
            echartsApp[index] = echarts.init(elemDataView[index], layui.echartsTheme);
            echartsApp[index].setOption(options[index]);
            //window.onresize = echartsApp[index].resize;
            admin.resize(function () {
                echartsApp[index].resize();
            });
        };


        //没找到DOM，终止执行
        if (!elemDataView[0]) return;


        renderDataView(0);

        //监听数据概览轮播
        var carouselIndex = 0;
        carousel.on('change(LAY-index-dataview)', function (obj) {
            renderDataView(carouselIndex = obj.index);
        });

        //监听侧边伸缩
        layui.admin.on('side', function () {
            setTimeout(function () {
                renderDataView(carouselIndex);
            }, 300);
        });

        //监听路由
        layui.admin.on('hash(tab)', function () {
            layui.router().path.join('') || renderDataView(carouselIndex);
        });
    });

    //最新订单
    layui.use('table', function () {
        var $ = layui.$
            , table = layui.table;

        //今日热搜
        table.render({
            elem: '#LAY-index-topSearch'
            , url: layui.setter.base + 'json/console/top-search.js' //模拟接口
            , page: true
            , cols: [[
                {type: 'numbers', fixed: 'left'}
                , {
                    field: 'keywords',
                    title: '关键词',
                    minWidth: 300,
                    templet: '<div><a href="https://www.baidu.com/s?wd={{ d.keywords }}" target="_blank" class="layui-table-link">{{ d.keywords }}</div>'
                }
                , {field: 'frequency', title: '搜索次数', minWidth: 120, sort: true}
                , {field: 'userNums', title: '用户数', sort: true}
            ]]
            , skin: 'line'
        });

        //今日热贴
        table.render({
            elem: '#LAY-index-topCard'
            , url: layui.setter.base + 'json/console/top-card.js' //模拟接口
            , page: true
            , cellMinWidth: 120
            , cols: [[
                {type: 'numbers', fixed: 'left'}
                , {
                    field: 'title',
                    title: '标题',
                    minWidth: 300,
                    templet: '<div><a href="{{ d.href }}" target="_blank" class="layui-table-link">{{ d.title }}</div>'
                }
                , {field: 'username', title: '发帖者'}
                , {field: 'channel', title: '类别'}
                , {field: 'crt', title: '点击率', sort: true}
            ]]
            , skin: 'line'
        });
    });

    exports('console', {})
});