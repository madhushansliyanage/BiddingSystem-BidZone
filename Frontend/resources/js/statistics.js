$(document).ready(function() {
    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(createCharts);

    function createCharts() {
        drawCharts('ageRangePerCategoryStatisticsChart', 'Age Range: ');
        drawCharts('categoryPerAgeRangeStatisticsChart', 'Category: ');
        drawCharts('genderPerCategoryStatisticsChart', 'Gender: ');
        drawCharts('categoryPerGenderStatisticsChart', 'Category: ');
        drawCharts('combinationPerCategoryStatisticsChart', 'Combination: ');
        drawCharts('categoryPerCombinationStatisticsChart', 'Category: ');
    }

    function drawCharts(chartType, prefix) {
        let charts = document.getElementsByClassName(chartType);
        for (let i = 0; i < charts.length; i++) {
            let chartData = $(charts[i]).next();
            let options = {'title':prefix + chartData.attr('name'), 'is3D': true, 'width':348, 'height':400};
            let array = convertDataToArray(chartData.val());
            let chart = new google.visualization.PieChart(charts[i]);
            let data = google.visualization.arrayToDataTable(array);
            chart.draw(data, options);
        }
    }

    function convertDataToArray(data) {
        let array = [];
        data = data.replace('{', '').replace('}', '');
        let splitData = data.split(', ');
        array.push(['Attribute', 'Count'])
        for (let i = 0; i < splitData.length; i++) {
            let object = splitData[i].split('=');
            array.push([object[0].toString(), Number(object[1])])
        }
        return array;
    }
});

