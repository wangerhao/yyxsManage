(function($){
	
	if(typeof $.highchartsutil == "undefined"){
		$.highchartsutil = {};
	}
	
	/** 
	 *  饼状图
	 *  @param labelId		-->标签id
	 *  @param titleText	-->饼状图主标题
	 *  @param seriesData	-->饼状百分比数据
	 */
	$.highchartsutil.pie = function(labelId,titleText,seriesData){
		$(labelId).highcharts({
	        title: {
	            text: titleText
	        },
	        tooltip: {
	    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'	//鼠标移到数据块时显示的数据
	        },
	        plotOptions: {
	            pie: {
	                allowPointSelect: false,		//数据块取出（true:取出、false:禁用）
	                cursor: 'pointer',
	                dataLabels: {
	                    enabled: true,				//饼状图外边缘数据显示
	                    color: '#000000',			//饼状图外边缘数据字体颜色
	                    connectorColor: '#000000',	//饼状图外边缘数据线颜色
	                    format: '<b>{point.name}</b>: {point.y}<br/><b>比例</b>: {point.percentage:.1f} %'		//饼状图外边缘数据格式
	                }
	            }
	        },
	        series: [{
	            type: 'pie',
	            name: 'the proportion',
	            data: seriesData
	        }]
	    });
	}
	
	/** 
	 *  并列柱状图
	 *  @param labelId				-->标签id
	 *  @param titleText			-->柱状图主标题
	 *  @param categoriesArrary		-->柱状图底部目录数据
	 *  @param seriesData			-->柱状图柱状数据
	 *  @param currentDateArray		-->柱状图当前日期
	 */
	$.highchartsutil.singleRowBar = function(labelId, titleText, categoriesArrary, seriesData, currentDateArray){
		 $(labelId).highcharts({
	        chart: {
	            type: 'column'
	        },
	        title: {			//统计图主标题
	            text: '<b>' + titleText + '</b>'	
	        },
	        xAxis: {
	            categories: categoriesArrary,
	            labels: {							
	                rotation: -40,					//X轴数据斜度40设置
	                style: {
	                    fontFamily: 'Verdana, sans-serif'
	                }
                }
	        },
	        yAxis: {
	            title: {			//数值目录
	                text: ''
	            },
	            stackLabels: {			//每个柱形显示的总额
	                enabled: true,
	                style: {
	                    fontWeight: 'bold',
	                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
	                }
	            }
	        },
	        tooltip: {		//鼠标移到柱子上显示的内容
	            formatter: function() {
	        		var tool = $.trim($("#beginMouth").val()).length <= 0 ? currentDateArray[0] + '.' : '';
	                return '<b>' + tool + this.x + '</b><br/>' + this.series.name + ': ' + this.y + '<br/> Total: ' + this.point.stackTotal;
	            }
	        },
	        plotOptions: {
	            column: {
	        		cursor: 'pointer',
	                stacking: 'normal'				//柱子并列显示，不加为分列显示
	            }
	        },
	        series:seriesData
	    });
	}
	
	/** 
	 *  分列柱状图
	 *  @param labelId				-->标签id
	 *  @param titleText			-->柱状图主标题
	 *  @param categoriesArrary		-->柱状图底部目录数据
	 *  @param seriesData			-->柱状图柱状数据
	 *  @param currentDateArray		-->柱状图当前日期
	 */
	$.highchartsutil.respectivelyBar = function(labelId, titleText, categoriesArrary, seriesData, currentDateArray){
	    var chart = $(labelId).highcharts({
	        chart: {
	            type: 'column'
	        },
	        title: {			//统计图主标题
	            text: '<b>' + titleText + '</b>'	
	        },
	        xAxis: {
	       		categories: categoriesArrary,
                labels: {
                    rotation: -40,		//X轴数据斜度40设置
                    style: {
                        fontFamily: 'Verdana, sans-serif'
                    }
                }
	        },
	        yAxis: {
	            min: 0,
	            title: {		//数值目录
	                text: ''
	            }
	        },
	        tooltip: {		//鼠标移到柱子上显示的内容
	            formatter: function() {
	        		var tool = $.trim($("#beginMouth").val()).length <= 0 ? currentDateArray[0] + '.' : '';
	        		return '<b>' + tool + this.x +'</b><br/>' + this.series.name + ': ' + this.y;
	            }
	        },
	        plotOptions: {
	            column: {
	        		cursor: 'pointer',
	        		//stacking: 'normal',				//柱子分列显示，不加为分列显示
	                dataLabels: {
	                    enabled: true,				//显示数值（true:如果是并列展示，数据是显示在柱子里面的。如果不是分列展示，数据是显示在柱子的最上面的   false:隐藏数值）
	                    color: Highcharts.getOptions().colors[11],
	                }
	            }
	        },
	        series: seriesData
	    });
	}
})(jQuery);