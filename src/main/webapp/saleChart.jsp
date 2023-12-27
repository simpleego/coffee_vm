<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자판기 판매현황 차트</title>
<style type="text/css">
 #chart{
 	height: 1200px;
 }
</style>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>  
<script>
	google.charts.load('current', {packages:['corechart']});
    google.charts.setOnLoadCallback(columnChart1);
    
    function columnChart1(){
    	
    	const dataTable = google.visualization.arrayToDataTable([
    		['상품종류','총판매수량','총판매금액'],
    		['밀크커피', ${pList[2][0]*100}, ${pList[2][1]}],
    		['프림커피', ${pList[1][0]*100}, ${pList[1][1]}],
    		['설탕커피', ${pList[3][0]*100}, ${pList[3][1]}],
    		['블랙커피', ${pList[0][0]*100}, ${pList[0][1]}] 
    	]);
    	
    	const options = {
    			title:'자판기 상품종류별 판매 현황',
    			vAxis:{title:'판매금액'},
    			hAxis:{
    				title:'상품종류', 
    				titleTextStyle:{color:'red'}
    		},
    	
    	seriesType:'bars',
    	series:{1:{type:'line'}}
    	};
    	
    	
    	const chart = new google.visualization.ComboChart(
    			document.getElementById('chart') );
    	
    	chart.draw(dataTable,options)
    	
	}
</script>


</head>
<body>
	<button type="button" id="btn">차트보기</button>
	<div id="chart"> 
	</div>
</body>
</html>