			
/****/
$(document).ready(function(){
	var whei=$(window).width()
	$("html").css({fontSize:whei/20})
	$(window).resize(function(){
		var whei=$(window).width()
	 $("html").css({fontSize:whei/20})
});
	});


 $(window).load(function(){$(".loading").fadeOut()})  
$(function () {
    echarts_1()
    echarts_11()
    echarts_111()
    echarts_1111()

    echarts_21()
    echarts_22()
    echarts_23()
    echarts_24()

    echarts_31()
    echarts_32()
    echarts_33()
    echarts_34()

    echarts_2()
    echarts_3()
    pe04()

    pe01()
    pe02()
    pe03()
    function echarts_4() {
      var myChart = echarts.init(document.getElementById('echarts4'));
      var data = [{
        "name": "邮件总量",
        "value": 20000
    }, {
        "name": "公文交换",
        "value": 18170
    }, {
        "name": "内部流转",
        "value": 19870
    } ]
   
   
    option = {
   
        color: ['#A0CE3A', '#31C5C0', '#1E9BD1', '#0F347B', '#585247', '#7F6AAD', '#009D85', "rgba(250,250,250,0.3)"],

        grid: {
            bottom: 0,
            left: 0,
            right: '0'
        },
        series: [
            // 主要展示层的
            {
                radius: ['30%', '50%'],
                center: ['50%', '50%'],
                type: 'pie',
                label: {
                    normal: {
                        show: true,
                          formatter: ['{b|{b}}','{c|{c}次}', '{d|同比：{d}%}'].join('\n'),
                         rich: {
                       c: {
                           color: 'rgb(241,246,104)',
                           fontSize: 20,
                           fontWeight:'bold',
                           lineHeight: 25
                       },
                       b: {
                           color: 'rgb(98,137,169)',
                           fontSize: 15,
                           height: 40
                       },
                   },
                       
                        position: 'outside'
                    },
                    emphasis: {
                        show: true
                    }
                },
                labelLine: {
                    normal: {
                        show: true,
                        length: 15,
                        length2: 50
                    },
                    emphasis: {
                        show: true
                    }
                },
                name: "民警训练总量",
                data: data,
   
            },
            // 边框的设置
            {
                radius: ['30%', '35%'],
                center: ['50%', '50%'],
                type: 'pie',
                label: {
                    normal: {
                        show: false
                    },
                    emphasis: {
                        show: false
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    },
                    emphasis: {
                        show: false
                    }
                },
                animation: false,
                tooltip: {
                    show: false
                },
                data: [{
                    value: 1,
                    itemStyle: {
                        color: "rgba(250,250,250,0.3)",
                    },
                }],
            }, {
                name: '外边框',
                type: 'pie',
                clockWise: false, //顺时加载
                hoverAnimation: false, //鼠标移入变大
                center: ['50%', '50%'],
                radius: ['60%', '61%'],
                label: {
                    normal: {
                        show: false
                    }
                },
                data: [{
                    value: 9,
                    name: '',
                    itemStyle: {
                        normal: {
                            borderWidth: 2,
                            borderColor: '#0c2c69'
                        }
                    }
                }]
            },
        ]
    };
     
             myChart.setOption(option);
             window.addEventListener("resize",function(){
                 myChart.resize();
             });
     
       
         }

    function echarts_21() {
        $.ajax({
            url: 'gender/ratio',
            method: 'GET',

            success: function(response) {
                var data = response;

                var chart = echarts.init(document.getElementById('echarts21'));

                option = {
                    title: {
                        text: '男女比例',
                        left: 'center',
                        textStyle: {
                            color: 'rgba(255, 255, 255, 0.8)', // 设置标签文本的字体颜色
                            fontSize: 15 // 标签文本的字体大小
                        }
                    },
                    tooltip: {
                        trigger: 'item'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        textStyle: {
                            color: 'rgba(255, 255, 255, 0.9)', // 设置标签文本的字体颜色
                            fontSize: 9 // 标签文本的字体大小
                        }
                    },
                    series: [
                        {
                            type: 'pie',
                            radius: '46%',
                            data: data,
                            label: {
                                show: true,
                                formatter: '{d}%', // 标签内容格式，{b} 表示名称，{d} 表示百分比
                                textStyle: {
                                    color: 'rgba(255, 255, 255, 0.8)', // 设置标签文本的字体颜色
                                    fontSize: 9 // 标签文本的字体大小
                                }
                            },
                            emphasis: {
                                itemStyle: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)',
                                }
                            }
                        }
                    ]
                };

                chart.setOption(option);
                window.addEventListener("resize",function(){
                    chart.resize();
                });
            },
            error: function(error) {
                console.log("Error fetching data: ", error);
            }
        });


    }

    function echarts_22() {
        $.ajax({
            url: 'gender/sales',
            method: 'GET',

            success: function(response) {
                var data = response;

                var chart = echarts.init(document.getElementById('echarts22'));

                option = {
                    title: {
                        text: '男女销量',
                        left: 'center',
                        textStyle: {
                            color: 'rgba(255, 255, 255, 0.8)', // 设置标签文本的字体颜色
                            fontSize: 15 // 标签文本的字体大小
                        }
                    },
                    tooltip: {
                        trigger: 'item'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        textStyle: {
                            color: 'rgba(255, 255, 255, 0.9)', // 设置标签文本的字体颜色
                            fontSize: 9 // 标签文本的字体大小
                        }
                    },
                    series: [
                        {
                            type: 'pie',
                            radius: '46%',
                            data: data,
                            label: {
                                show: true,
                                formatter: '{d}%', // 标签内容格式，{b} 表示名称，{d} 表示百分比
                                textStyle: {
                                    color: 'rgba(255, 255, 255, 0.8)', // 设置标签文本的字体颜色
                                    fontSize: 9 // 标签文本的字体大小
                                }
                            },
                            emphasis: {
                                itemStyle: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)',
                                }
                            }
                        }
                    ]
                };

                chart.setOption(option);
                window.addEventListener("resize",function(){
                    chart.resize();
                });
            },
            error: function(error) {
                console.log("Error fetching data: ", error);
            }
        });


    }

    function echarts_23() {
        $.ajax({
            url: 'gender/boy_cat1',
            method: 'GET',

            success: function(response) {
                var data = response;

                var chart = echarts.init(document.getElementById('echarts23'));

                option = {
                    title: {
                        text: '男孩销售一级类目',
                        top: 'bottom', // 标题距离容器底部的位置
                        left: 'center', // 标题居中对齐
                        textStyle: {
                            color: 'rgba(255, 255, 255, 0.8)', // 设置标签文本的字体颜色
                            fontSize: 15 // 标签文本的字体大小
                        }
                    },
                    tooltip: {
                        trigger: 'item'
                    },
                    // legend: {
                    //     orient: 'vertical',
                    //     left: 'left',
                    //     textStyle: {
                    //         color: 'rgba(255, 255, 255, 0.9)', // 设置标签文本的字体颜色
                    //         fontSize: 9 // 标签文本的字体大小
                    //     }
                    // },
                    series: [
                        {
                            type: 'pie',
                            radius: '60%',
                            data: data,
                            label: {
                                show: true,
                                formatter: '{d}%', // 标签内容格式，{b} 表示名称，{d} 表示百分比
                                textStyle: {
                                    color: 'rgba(255, 255, 255, 0.8)', // 设置标签文本的字体颜色
                                    fontSize: 9 // 标签文本的字体大小
                                }
                            },
                            emphasis: {
                                itemStyle: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)',
                                }
                            }
                        }
                    ]
                };

                chart.setOption(option);
                window.addEventListener("resize",function(){
                    chart.resize();
                });
            },
            error: function(error) {
                console.log("Error fetching data: ", error);
            }
        });


    }

    function echarts_24() {
        $.ajax({
            url: 'gender/girl_cat1',
            method: 'GET',

            success: function(response) {
                var data = response;

                var chart = echarts.init(document.getElementById('echarts24'));

                option = {
                    title: {
                        text: '女孩销售一级类目',
                        top: 'bottom', // 标题距离容器底部的位置
                        left: 'center', // 标题居中对齐
                        textStyle: {
                            color: 'rgba(255, 255, 255, 0.8)', // 设置标签文本的字体颜色
                            fontSize: 15 // 标签文本的字体大小
                        }
                    },
                    tooltip: {
                        trigger: 'item'
                    },
                    // legend: {
                    //     orient: 'vertical',
                    //     left: 'left',
                    //     textStyle: {
                    //         color: 'rgba(255, 255, 255, 0.9)', // 设置标签文本的字体颜色
                    //         fontSize: 9 // 标签文本的字体大小
                    //     }
                    // },
                    series: [
                        {
                            type: 'pie',
                            radius: '60%',
                            data: data,
                            label: {
                                show: true,
                                formatter: '{d}%', // 标签内容格式，{b} 表示名称，{d} 表示百分比
                                textStyle: {
                                    color: 'rgba(255, 255, 255, 0.8)', // 设置标签文本的字体颜色
                                    fontSize: 9 // 标签文本的字体大小
                                }
                            },
                            emphasis: {
                                itemStyle: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)',
                                }
                            }
                        }
                    ]
                };

                chart.setOption(option);
                window.addEventListener("resize",function(){
                    chart.resize();
                });
            },
            error: function(error) {
                console.log("Error fetching data: ", error);
            }
        });


    }

    function echarts_1() {
    $.ajax({
        url: 'major/analysis',
        method: 'GET',

        success: function(response) {
            var labels = response.major;
            var data = response.cnt;

            var chart = echarts.init(document.getElementById('echarts1'));
            option = {
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {type: 'shadow'},

                },
                legend: {
                    x: 'center',
                    y: '0',
                    icon: 'circle',
                    itemGap:8,
                    textStyle: {color: 'rgba(255,255,255,.5)'},
                    itemWidth: 10,
                    itemHeight: 10,
                },
                grid: {
                    left: '0',
                    top: '30',
                    right: '15',
                    bottom: '10',
                    containLabel: true
                },
                xAxis: {
                    type: 'category',
                    data: labels,
                    axisLine: {show:false},

                    axisLabel: {
                        textStyle: {
                            color:'rgba(255,255,255,.6)',
                            fontSize:9
                        }
                    },
                },

                yAxis: {
                    show: true,
                    type: 'value',
                    splitNumber:2,
                    axisLine: { show: false },
                    axisTick: {show: false},
                    splitLine: {
                        show: true,
                        lineStyle: {
                            color: 'rgba(255,255,255,0.05)'
                        }
                    },
                    axisLabel:  {
                        textStyle: {
                            color:'rgba(255,255,255,.6)',
                            fontSize:9
                        },
                    },
                },
                series: [ {
                    name: '专业人数',
                    type: 'bar',
                    barWidth: '40%',
                    itemStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                offset: 0,
                                color: '#8bd46e'
                            }, {
                                offset: 1,
                                color: '#03b48e'
                            }]),
                            barBorderRadius: 11,
                        }

                    },
                    data: data

                }

                ]
            };
            chart.setOption(option);
            window.addEventListener("resize",function(){
                chart.resize();
            });
        },
        error: function(error) {
            console.log("Error fetching data: ", error);
        }
    });

	
    }

    function echarts_11() {
        $.ajax({
            url: 'major/analysis',
            method: 'GET',

            success: function(response) {
                var labels = response.major;
                var data = response.salary;

                var chart = echarts.init(document.getElementById('echarts11'));
                option = {
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {type: 'shadow'},

                    },
                    legend: {
                        x: 'center',
                        y: '0',
                        icon: 'circle',
                        itemGap:8,
                        textStyle: {color: 'rgba(255,255,255,.5)'},
                        itemWidth: 10,
                        itemHeight: 10,
                    },
                    grid: {
                        left: '0',
                        top: '30',
                        right: '15',
                        bottom: '10',
                        containLabel: true
                    },
                    xAxis: {
                        type: 'category',
                        data: labels,
                        axisLine: {show:false},

                        axisLabel: {
                            textStyle: {
                                color:'rgba(255,255,255,.6)',
                                fontSize:9
                            }
                        },
                    },

                    yAxis: {
                        show: true,
                        type: 'value',
                        splitNumber:2,
                        axisLine: { show: false },
                        axisTick: {show: false},
                        splitLine: {
                            show: true,
                            lineStyle: {
                                color: 'rgba(255,255,255,0.05)'
                            }
                        },
                        axisLabel:  {
                            textStyle: {
                                color:'rgba(255,255,255,.6)',
                                fontSize:9
                            },
                        },
                    },
                    series: [ {
                        name: '期望薪资',
                        type: 'bar',
                        barWidth: '40%',
                        itemStyle: {
                            normal: {
                                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                    offset: 0,
                                    color: '#8bd46e'
                                }, {
                                    offset: 1,
                                    color: '#03b48e'
                                }]),
                                barBorderRadius: 11,
                            }

                        },
                        data: data

                    }

                    ]
                };
                chart.setOption(option);
                window.addEventListener("resize",function(){
                    chart.resize();
                });
            },
            error: function(error) {
                console.log("Error fetching data: ", error);
            }
        });


    }
    function echarts_111() {
        $.ajax({
            url: 'major/analysis',
            method: 'GET',

            success: function(response) {
                var labels = response.major;
                var data = response.satis;

                var chart = echarts.init(document.getElementById('echarts111'));
                option = {
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {type: 'shadow'},

                    },
                    legend: {
                        x: 'center',
                        y: '0',
                        icon: 'circle',
                        itemGap:8,
                        textStyle: {color: 'rgba(255,255,255,.5)'},
                        itemWidth: 10,
                        itemHeight: 10,
                    },
                    grid: {
                        left: '0',
                        top: '30',
                        right: '15',
                        bottom: '10',
                        containLabel: true
                    },
                    xAxis: {
                        type: 'category',
                        data: labels,
                        axisLine: {show:false},

                        axisLabel: {
                            textStyle: {
                                color:'rgba(255,255,255,.6)',
                                fontSize:9
                            }
                        },
                    },

                    yAxis: {
                        show: true,
                        type: 'value',
                        splitNumber:2,
                        axisLine: { show: false },
                        axisTick: {show: false},
                        splitLine: {
                            show: true,
                            lineStyle: {
                                color: 'rgba(255,255,255,0.05)'
                            }
                        },
                        axisLabel:  {
                            textStyle: {
                                color:'rgba(255,255,255,.6)',
                                fontSize:9
                            },
                        },
                    },
                    series: [ {
                        name: '就业满意度',
                        type: 'bar',
                        barWidth: '40%',
                        itemStyle: {
                            normal: {
                                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                    offset: 0,
                                    color: '#8bd46e'
                                }, {
                                    offset: 1,
                                    color: '#03b48e'
                                }]),
                                barBorderRadius: 11,
                            }

                        },
                        data: data

                    }

                    ]
                };
                chart.setOption(option);
                window.addEventListener("resize",function(){
                    chart.resize();
                });
            },
            error: function(error) {
                console.log("Error fetching data: ", error);
            }
        });


    }
    function echarts_1111() {
        $.ajax({
            url: 'major/analysis',
            method: 'GET',

            success: function(response) {
                var labels = response.labels;
                var data = response.data;

                var chart = echarts.init(document.getElementById('echarts1111'));
                option = {
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {type: 'shadow'},

                    },
                    legend: {
                        x: 'center',
                        y: '0',
                        icon: 'circle',
                        itemGap:8,
                        textStyle: {color: 'rgba(255,255,255,.5)'},
                        itemWidth: 10,
                        itemHeight: 10,
                    },
                    grid: {
                        left: '0',
                        top: '30',
                        right: '15',
                        bottom: '10',
                        containLabel: true
                    },
                    xAxis: {
                        type: 'category',
                        data: labels,
                        axisLine: {show:false},

                        axisLabel: {
                            textStyle: {
                                color:'rgba(255,255,255,.6)',
                                fontSize:9
                            }
                        },
                    },

                    yAxis: {
                        show: true,
                        type: 'value',
                        splitNumber:2,
                        axisLine: { show: false },
                        axisTick: {show: false},
                        splitLine: {
                            show: true,
                            lineStyle: {
                                color: 'rgba(255,255,255,0.05)'
                            }
                        },
                        axisLabel:  {
                            textStyle: {
                                color:'rgba(255,255,255,.6)',
                                fontSize:9
                            },
                        },
                    },
                    series: [ {
                        name: '期望薪资',
                        type: 'bar',
                        barWidth: '40%',
                        itemStyle: {
                            normal: {
                                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                    offset: 0,
                                    color: '#8bd46e'
                                }, {
                                    offset: 1,
                                    color: '#03b48e'
                                }]),
                                barBorderRadius: 11,
                            }

                        },
                        data: data

                    }

                    ]
                };
                chart.setOption(option);
                window.addEventListener("resize",function(){
                    chart.resize();
                });
            },
            error: function(error) {
                console.log("Error fetching data: ", error);
            }
        });


    }

    function echarts_2() {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('echarts2'));

       option = {
	    tooltip: {
        trigger: 'axis',
        axisPointer: {
            lineStyle: {
                color: '#dddc6b'
            }
        }
    },
    grid: {
        left: '0',
		top: '30',
        right: '20',
        bottom: '-10',
        containLabel: true
    },legend: {
        data: ['字段1', '字段2'],
        right: 'center',
        top:0,
        textStyle: {
            color: "#fff"
        },
        itemWidth: 12,
        itemHeight: 10,
        // itemGap: 35
    },

    xAxis: [{
        type: 'category',
        boundaryGap: false,
axisLabel:  {
                textStyle: {
 					color: "rgba(255,255,255,.6)",
					fontSize:14,
                },
            },
        axisLine: {
			lineStyle: { 
				color: 'rgba(255,255,255,.1)'
			}

        },

   data: ['8:00', '9:00', '10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00', '21:00', '22:00']

    }, {

        axisPointer: {show: false},
        axisLine: {  show: false},
        position: 'bottom',
        offset: 20,

       

    }],

    yAxis: [{
        type: 'value',
        axisTick: {show: false},
       // splitNumber: 6,
        axisLine: {
            lineStyle: {
                color: 'rgba(255,255,255,.1)'
            }
        },
       axisLabel:  {
                textStyle: {
 					color: "rgba(255,255,255,.6)",
					fontSize:14,
                },
            },

        splitLine: {
            lineStyle: {
                 color: 'rgba(255,255,255,.1)'
            }
        }
    }],
    series: [
		{
        name: '字段1',
        type: 'line',
        smooth: true,
        symbol: 'circle',
        symbolSize: 5,
        showSymbol: false,
        lineStyle: {
			
            normal: {
				color: 'rgba(228, 228, 126, 1)',
                width: 2
            }
        },
        areaStyle: {
            normal: {
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                    offset: 0,
                    color: 'rgba(228, 228, 126, .8)'
                }, {
                    offset: 0.8,
                    color: 'rgba(228, 228, 126, 0.1)'
                }], false),
                shadowColor: 'rgba(0, 0, 0, 0.1)',
            }
        },
			itemStyle: {
			normal: {
				color: '#dddc6b',
				borderColor: 'rgba(221, 220, 107, .1)',
				borderWidth: 12
			}
		},
        data: [600, 200, 600, 200, 400, 200, 400, 300, 400, 300, 400, 300, 200, 400, 200 ]
    }, {
        name: '字段2',
        type: 'line',
        smooth: true,
        symbol: 'circle',
        symbolSize: 5,
        showSymbol: false,
        lineStyle: {
			
            normal: {
				color: 'rgba(255, 128, 128, 1)',
                width: 2
            }
        },
        areaStyle: {
            normal: {
                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                    offset: 0,
                    color: 'rgba(255, 128, 128,.8)'
                }, {
                    offset: 0.8,
                    color: 'rgba(255, 128, 128, .1)'
                }], false),
                shadowColor: 'rgba(0, 0, 0, 0.1)',
            }
        },
			itemStyle: {
			normal: {
				color: '#dddc6b',
				borderColor: 'rgba(221, 220, 107, .1)',
				borderWidth: 12
			}
		},
        data: [300, 100, 300, 400, 300, 400, 300, 600, 200, 400, 200, 400, 100, 300, 400]

    }, 

		 ]

};

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        window.addEventListener("resize",function(){
            myChart.resize();
        });
    }

    function echarts_3() {

        $.ajax({
            url: 'data/data-servlet',
            method: 'GET',
            success: function(response) {
                var labels = response.labels;
                var data = response.data;

                var chart = echarts.init(document.getElementById('echarts3'));

                var option = {
                    title: {
                        text: 'Sample Chart'
                    },
                    tooltip: {},
                    xAxis: {
                        data: labels
                    },
                    yAxis: {},
                    series: [{
                        name: 'Data',
                        type: 'bar',
                        data: data
                    }],
                    grid: {
                        // left: '0',
                        // top: '15',
                        // right: '10',
                        bottom: '0',
                        containLabel: true
                    }
                };

                chart.setOption(option);
                window.addEventListener("resize",function(){
                    chart.resize();
                });
            },
            error: function(error) {
                console.log("Error fetching data: ", error);
            }
        });



        // 基于准备好的dom，初始化echarts实例
    }


    function echarts_31() {
        $.ajax({
            url: 'cate/total_subcat',
            method: 'GET',

            success: function(response) {
                var labels = response.labels;
                var data = response.data;

                var chart = echarts.init(document.getElementById('echarts31'));
                option = {
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {type: 'shadow'},

                    },
                    legend: {
                        x: 'center',
                        y: '0',
                        icon: 'circle',
                        itemGap:8,
                        textStyle: {color: 'rgba(255,255,255,.5)'},
                        itemWidth: 10,
                        itemHeight: 10,
                    },
                    grid: {
                        left: '0',
                        top: '5',
                        right: '15',
                        bottom: '10',
                        containLabel: true
                    },
                    yAxis: {
                        type: 'category',
                        data: labels,
                        axisLine: {show:false},

                        axisLabel: {
                            textStyle: {
                                color:'rgba(255,255,255,.6)',
                                fontSize:9
                            }
                        },
                    },

                    xAxis: {
                        show: true,
                        type: 'value',
                        splitNumber:4,
                        axisLine: { show: false },
                        axisTick: {show: false},
                        splitLine: {
                            show: true,
                            lineStyle: {
                                color: 'rgba(255,255,255,0.05)'
                            }
                        },
                        axisLabel:  {
                            textStyle: {
                                color:'rgba(255,255,255,.6)',
                                fontSize:9
                            },
                        },
                    },
                    series: [ {
                        type: 'bar',
                        barWidth: '40%',
                        itemStyle: {
                            normal: {
                                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                    offset: 0,
                                    color: '#8bd46e'
                                }, {
                                    offset: 1,
                                    color: '#03b48e'
                                }]),
                                barBorderRadius: 11,
                            }

                        },
                        data: data

                    }

                    ]
                };
                chart.setOption(option);
                window.addEventListener("resize",function(){
                    chart.resize();
                });
            },
            error: function(error) {
                console.log("Error fetching data: ", error);
            }
        });


    }

    function echarts_32() {
        $.ajax({
            url: 'cate/total_sale',
            method: 'GET',

            success: function(response) {
                var labels = response.labels;
                var data = response.data;

                var chart = echarts.init(document.getElementById('echarts32'));
                option = {
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {type: 'shadow'},

                    },
                    legend: {
                        x: 'center',
                        y: '0',
                        icon: 'circle',
                        itemGap:8,
                        textStyle: {color: 'rgba(255,255,255,.5)'},
                        itemWidth: 10,
                        itemHeight: 10,
                    },
                    grid: {
                        left: '0',
                        top: '5',
                        right: '15',
                        bottom: '10',
                        containLabel: true
                    },
                    yAxis: {
                        type: 'category',
                        data: labels,
                        axisLine: {show:false},

                        axisLabel: {
                            textStyle: {
                                color:'rgba(255,255,255,.6)',
                                fontSize:9
                            }
                        },
                    },

                    xAxis: {
                        show: true,
                        type: 'value',
                        splitNumber:4,
                        axisLine: { show: false },
                        axisTick: {show: false},
                        splitLine: {
                            show: true,
                            lineStyle: {
                                color: 'rgba(255,255,255,0.05)'
                            }
                        },
                        axisLabel:  {
                            textStyle: {
                                color:'rgba(255,255,255,.6)',
                                fontSize:9
                            },
                        },
                    },
                    series: [ {
                        type: 'bar',
                        barWidth: '40%',
                        itemStyle: {
                            normal: {
                                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                    offset: 0,
                                    color: '#8bd46e'
                                }, {
                                    offset: 1,
                                    color: '#03b48e'
                                }]),
                                barBorderRadius: 11,
                            }

                        },
                        data: data

                    }

                    ]
                };
                chart.setOption(option);
                window.addEventListener("resize",function(){
                    chart.resize();
                });
            },
            error: function(error) {
                console.log("Error fetching data: ", error);
            }
        });


    }

    function echarts_33() {
        $.ajax({
            url: 'cate/subcat_avg_sale',
            method: 'GET',

            success: function(response) {
                var labels = response.labels;
                var data = response.data;

                var chart = echarts.init(document.getElementById('echarts33'));
                option = {
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {type: 'shadow'},

                    },
                    legend: {
                        x: 'center',
                        y: '0',
                        icon: 'circle',
                        itemGap:8,
                        textStyle: {color: 'rgba(255,255,255,.5)'},
                        itemWidth: 10,
                        itemHeight: 10,
                    },
                    grid: {
                        left: '0',
                        top: '5',
                        right: '15',
                        bottom: '10',
                        containLabel: true
                    },
                    xAxis: {
                        type: 'category',
                        data: labels,
                        axisLine: {show:false},

                        axisLabel: {
                            textStyle: {
                                color:'rgba(255,255,255,.6)',
                                fontSize:9
                            }
                        },
                    },

                    yAxis: {
                        show: true,
                        type: 'value',
                        splitNumber:4,
                        axisLine: { show: false },
                        axisTick: {show: false},
                        splitLine: {
                            show: true,
                            lineStyle: {
                                color: 'rgba(255,255,255,0.05)'
                            }
                        },
                        axisLabel:  {
                            textStyle: {
                                color:'rgba(255,255,255,.6)',
                                fontSize:9
                            },
                        },
                    },
                    series: [ {
                        type: 'bar',
                        barWidth: '25%',
                        itemStyle: {
                            normal: {
                                color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                    offset: 0,
                                    color: '#8bd46e'
                                }, {
                                    offset: 1,
                                    color: '#03b48e'
                                }]),
                                barBorderRadius: 11,
                            }

                        },
                        data: data

                    }

                    ]
                };
                chart.setOption(option);
                window.addEventListener("resize",function(){
                    chart.resize();
                });
            },
            error: function(error) {
                console.log("Error fetching data: ", error);
            }
        });


    }

    function echarts_34() {
        $.ajax({
            url: 'cate/hot_subcat',
            method: 'GET',

            success: function(response) {
                var labels = response.labels;
                var data1 = response.sale;
                var data2 = response.user;

                var chart = echarts.init(document.getElementById('echarts34'));
                option = {
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {type: 'shadow'},

                    },
                    legend: {
                        x: 'center',
                        y: '0',
                        icon: 'circle',
                        itemGap:8,
                        textStyle: {color: 'rgba(255,255,255,.5)'},
                        itemWidth: 10,
                        itemHeight: 10,
                    },
                    grid: {
                        left: '0',
                        top: '5',
                        right: '15',
                        bottom: '10',
                        containLabel: true
                    },
                    xAxis: {
                        name: '二级类别',
                        type: 'category',
                        data: labels,
                        axisLine: {show:false},

                        axisLabel: {
                            textStyle: {
                                color:'rgba(255,255,255,.6)',
                                fontSize:9
                            }
                        },
                    },

                    yAxis: {
                        show: true,
                        type: 'value',
                        splitNumber:4,
                        axisLine: { show: false },
                        axisTick: {show: false},
                        splitLine: {
                            show: true,
                            lineStyle: {
                                color: 'rgba(255,255,255,0.05)'
                            }
                        },
                        axisLabel:  {
                            textStyle: {
                                color:'rgba(255,255,255,.6)',
                                fontSize:9
                            },
                        },
                    },
                    series: [
                        {
                            name: '销量',
                            type: 'bar',
                            barWidth: '18%',
                            itemStyle: {
                                normal: {
                                    color: '#ff4500', // 红色
                                    barBorderRadius: 11,
                                }
                            },
                            data: data1 // 替换为实际的销量数据数组
                        },
                        {
                            name: '用户数',
                            type: 'bar',
                            barWidth: '18%',
                            itemStyle: {
                                normal: {
                                    color: '#ff7f50', // 橙色
                                    barBorderRadius: 11,
                                }
                            },
                            data: data2 // 替换为实际的用户数数据数组
                        }
                    ]
                };
                chart.setOption(option);
                window.addEventListener("resize",function(){
                    chart.resize();
                });
            },
            error: function(error) {
                console.log("Error fetching data: ", error);
            }
        });


    }

    function pe01() {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('pe01'));
        var txt=80
        option = {
            title: {
              text: txt+'%',
              x: 'center',
             y: 'center',
              textStyle: {
                fontWeight: 'normal',
                color: '#fff',
                fontSize: '18'
              }
            },
            color:'#49bcf7',
         
            series: [{
              name: 'Line 1',
              type: 'pie',
              clockWise: true,
              radius: ['65%', '80%'],
              itemStyle: {
                normal: {
                  label: {
                    show: false
                  },
                  labelLine: {
                    show: false
                  }
                }
              },
              hoverAnimation: false,
              data: [{
                value: txt,
                name: '已使用',
                itemStyle: {
                  normal: {
                    color:'#eaff00',
                    label: {
                      show: false
                    },
                    labelLine: {
                      show: false
                    }
                  }
                }
              }, {
                name: '未使用',
                value: 100-txt
              }]
            }]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        window.addEventListener("resize",function(){
            myChart.resize();
        });
    }

    function pe02() {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('pe02'));
        var txt=73
        option = {
            title: {
              text: txt+'%',
              x: 'center',
             y: 'center',
              textStyle: {
                fontWeight: 'normal',
                color: '#fff',
                fontSize: '18'
              }
            },
            color:'#49bcf7',
         
            series: [{
              name: 'Line 1',
              type: 'pie',
              clockWise: true,
              radius: ['65%', '80%'],
              itemStyle: {
                normal: {
                  label: {
                    show: false
                  },
                  labelLine: {
                    show: false
                  }
                }
              },
              hoverAnimation: false,
              data: [{
                value: txt,
                name: '已使用',
                itemStyle: {
                  normal: {
                    color:'#ea4d4d',
                    label: {
                      show: false
                    },
                    labelLine: {
                      show: false
                    }
                  }
                }
              }, {
                name: '未使用',
                value: 100-txt
              }]
            }]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        window.addEventListener("resize",function(){
            myChart.resize();
        });
    }
    function pe03() {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('pe03'));
        var txt=30
        option = {
            title: {
              text: txt+'%',
              x: 'center',
             y: 'center',
              textStyle: {
                fontWeight: 'normal',
                color: '#fff',
                fontSize: '18'
              }
            },
            color:'#49bcf7',
         
            series: [{
              name: 'Line 1',
              type: 'pie',
              clockWise: true,
              radius: ['65%', '80%'],
              itemStyle: {
                normal: {
                  label: {
                    show: false
                  },
                  labelLine: {
                    show: false
                  }
                }
              },
              hoverAnimation: false,
              data: [{
                value: txt,
                name: '已使用',
                itemStyle: {
                  normal: {
                    color:'#395ee6',
                    label: {
                      show: false
                    },
                    labelLine: {
                      show: false
                    }
                  }
                }
              }, {
                name: '未使用',
                value: 100-txt
              }
            ]
            }]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        window.addEventListener("resize",function(){
            myChart.resize();
        });
    }

    function pe04() {
      // 基于准备好的dom，初始化echarts实例
      var myChart = echarts.init(document.getElementById('lastecharts'));
      option = {
    
        tooltip: {
            trigger: 'axis'
        },
        radar: [{
            indicator: [{
                text: '盈利能力',
                max: 100
            }, {
                text: '发展水平',
                max: 100
            }, {
                text: '融资能力',
                max: 100
            }, {
                text: '技术能力',
                max: 100
            }, {
                text: '企业规模',
                max: 100
            }],
            textStyle: {
                color: 'red'
            },
            center: ['50%', '50%'],
            radius: '70%',
            startAngle: 90,
            splitNumber: 4,
            shape: 'circle',
        
            name: {
                padding:-5,
                formatter: '{value}',
                textStyle: {
                  fontSize:14,
                    color: 'rgba(255,255,255,.6)'
                }
            },
            splitArea: {
                areaStyle: {
                    color: 'rgba(255,255,255,.05)'
                }
            },
            axisLine: {
                lineStyle: {
                    color: 'rgba(255,255,255,.05)'
                }
            },
            splitLine: {
                lineStyle: {
                    color: 'rgba(255,255,255,.05)'
                }
            }
        }, ],
        series: [{
            name: '雷达图',
            type: 'radar',
            tooltip: {
                trigger: 'item'
            },
            data: [{
                name: '园区平均值',
                value: [90, 80, 20, 10, 30],
                lineStyle: {
                    normal: { 
                        color:'#03b48e',
                        width:2,
                    }
                },
                areaStyle: {
                    normal: {
                        color: '#03b48e',
                        opacity:.4
                    }
                },
                symbolSize: 0,
              
            }, {
                name: '当前园区',
                value: [30, 20, 75, 80, 70],
                symbolSize: 0,
                lineStyle: {
                    normal: { 
                        color:'#3893e5',
                        width:2,
                    }
                },
                     areaStyle: {
                    normal: {
                        color: 'rgba(19, 173, 255, 0.5)'
                    }
                }
            }]
        }, ]
    };

      // 使用刚指定的配置项和数据显示图表。
      myChart.setOption(option);
      window.addEventListener("resize",function(){
          myChart.resize();
      });
  }
})



		
		
		


		



















