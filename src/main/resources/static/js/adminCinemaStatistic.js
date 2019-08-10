$(document).ready(function() {


    getScheduleRate();
    
    getBoxOffice();

    getAudiencePrice();

    getPlacingRate();

    getPopularMovie();

    function getScheduleRate() {

        getRequest(
            '/statistics/scheduleRate',
            function (res) {
                var data = res.content||[];
                var tableData = data.map(function (item) {
                   return {
                       value: item.time,
                       name: item.name
                   };
                });
                var nameList = data.map(function (item) {
                    return item.name;
                });
                var option = {
                    title : {
                        text: '今日排片率',
                        subtext: new Date().toLocaleDateString(),
                        x:'center'
                    },
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        x : 'center',
                        y : 'bottom',
                        data:nameList
                    },
                    toolbox: {
                        show : true,
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: false},
                            magicType : {
                                show: true,
                                type: ['pie', 'funnel']
                            },
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    calculable : true,
                    series : [
                        {
                            name:'面积模式',
                            type:'pie',
                            radius : [30, 110],
                            center : ['50%', '50%'],
                            roseType : 'radius',
                            data:tableData
                        }
                    ]
                };
                var scheduleRateChart = echarts.init($("#schedule-rate-container")[0]);
                scheduleRateChart.setOption(option);
            },
            function (error) {
                alert(JSON.stringify(error));
            }
        );
    }

    function getBoxOffice() {
        //此处改为展示前五

        getRequest(
            '/statistics/boxOffice/total',
            function (res) {
                var data = res.content || [];
                var tableData=[];
                var nameList=[];
                var len = data.length<5?data.length:5;
                for (var i = 0; i < len; i++) {
                    tableData.push(data[i].boxOffice);
                    nameList.push(data[i].name);
                }

                var option = {
                    title : {
                        text: '所有电影票房',
                        subtext: '截止至'+new Date().toLocaleDateString(),
                        x:'center'
                    },
                    xAxis: {
                        type: 'category',
                        data: nameList
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: tableData,
                        type: 'bar'
                        // barWidth:30
                    }]
                };

                var scheduleRateChart = echarts.init($("#box-office-container")[0]);
                scheduleRateChart.setOption(option);
            },
            function (error) {
                alert(JSON.stringify(error));
            });
    }

    function getAudiencePrice() {
        getRequest(
            '/statistics/audience/price',
            function (res) {
                var data = res.content || [];
                var tableData = data.map(function (item) {
                    return item.price;
                });
                var nameList = data.map(function (item) {
                    return formatDate(new Date(item.date));
                });
                var option = {
                    title : {
                        text: '每日客单价',
                        x:'center'
                    },
                    xAxis: {
                        type: 'category',
                        data: nameList
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [{
                        data: tableData,
                        type: 'line'
                    }]
                };
                var scheduleRateChart = echarts.init($("#audience-price-container")[0]);
                scheduleRateChart.setOption(option);
            },
            function (error) {
                alert(JSON.stringify(error));
            });
    }

    function getPlacingRate() {
        getRequest(
            // '/statistics/placingRate?date='+'2019/04/21',
            '/statistics/placingRate?date='+formatDate(new Date()).replace(/-/g,'/'),
            function (res) {
                var data = res.content || [];
                var tableData = data.map(
                    function (item) {
                        return item.rate;
                    }
                );
                var nameList = data.map(function (item) {
                    return item.name;
                });
                var option = {
                    title: {
                        text: '上座率',
                        subtext: new Date().toLocaleDateString(),
                        x:'center'
                    },
                    xAxis: {
                        type: 'category',
                        data: nameList
                    },
                    yAxis: {
                        type: 'value',
                        axisLabel: {
                            show: true,
                            interval: 'auto',
                            formatter: function (data) {
                                        return data*100+'%';
                                    }
                        },
                        show: true
                    },
                    series: [{
                        data: tableData,
                        type: 'bar'
                    }]
                    // tooltip:{
                    //     show:true,
                    //     trigger: 'yAxis',
                    //     formatter: function (datas) {
                    //         return ''+datas*100+'%';
                    //     }
                    //
                    // }
                };
                var placingRateChart = echarts.init($("#place-rate-container")[0]);
                placingRateChart.setOption(option);

            },
        function (error) {
            alert(JSON.stringify(error));
            }
        );
    }

    function getPopularMovie() {
        getRequest(
            '/statistics/popular/movie?days=30&movieNum=5',
            function (res) {
                var data = res.content || [];
                var tableData = data.map(
                    function (item) {
                        return item.boxOffice;
                });
                var nameList = data.map(
                    function (item) {
                        return item.name;
                });

                var option = {
                    title:  {
                        text: '一个月内最受欢迎电影',
                        subtext: '截止至'+new Date().toLocaleDateString(),
                        x:'center'
                    },
                    xAxis: {
                        type: 'category',
                        data: nameList
                    },

                    yAxis:{
                        type: 'value'
                        // data: tableData
                    },
                    series: [{
                        data: tableData,
                        type: 'bar'
                    }]
                };

                var popularMovieChart = echarts.init($('#popular-movie-container')[0]);
                popularMovieChart.setOption(option);
            }

        )
    }
});