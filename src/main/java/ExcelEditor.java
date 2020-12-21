import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

class ExcelEditor {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private final FileOutputStream fos;
    private int currentRowIndex;
    private int maxNumberOfThreads;

    ExcelEditor(int maxNumberOfThreads) throws FileNotFoundException {
        this.workbook = new XSSFWorkbook();
        this.sheet = workbook.createSheet("Time (ms) per Number of Threads ");
        this.fos = new FileOutputStream("src/main/resources/report.xlsx");
        this.currentRowIndex = 1;
        this.maxNumberOfThreads = maxNumberOfThreads;
        Row row = sheet.createRow(0);
        for(int i = 0; i < maxNumberOfThreads; i++){
            row.createCell(i + 1).setCellValue(i + 1);
        }
    }

    void addRow(String taskType, List<Double> results){
        Row row = sheet.createRow(currentRowIndex);
        row.createCell(0).setCellValue(taskType);
        sheet.autoSizeColumn(0);
        int index = 1;
        for(Double value : results){
            row.createCell(index).setCellValue(value);
            index++;
        }
        currentRowIndex++;
    }

    void drawCharts(){
        XSSFDrawing drawing = sheet.createDrawingPatriarch();
        XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 4, 7, 26);

        XSSFChart chart = drawing.createChart(anchor);
        chart.setTitleText("Execution time per number of threads");
        chart.setTitleOverlay(false);

        XDDFChartLegend legend = chart.getOrAddLegend();
        legend.setPosition(LegendPosition.TOP_RIGHT);

        XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
        bottomAxis.setTitle("Number of threads");
        XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
        leftAxis.setTitle("Execution time");

        XDDFDataSource<String> numOfThreads = XDDFDataSourcesFactory.fromStringCellRange(sheet,
                new CellRangeAddress(0, 0, 1, maxNumberOfThreads));

        XDDFNumericalDataSource<Double> cpu = XDDFDataSourcesFactory.fromNumericCellRange(sheet,
                new CellRangeAddress(1, 1, 1, maxNumberOfThreads));

        XDDFNumericalDataSource<Double> memory = XDDFDataSourcesFactory.fromNumericCellRange(sheet,
                new CellRangeAddress(2, 2, 1, maxNumberOfThreads));

        XDDFNumericalDataSource<Double> io = XDDFDataSourcesFactory.fromNumericCellRange(sheet,
                new CellRangeAddress(3, 3, 1, maxNumberOfThreads));

        XDDFLineChartData data = (XDDFLineChartData) chart.createData(ChartTypes.LINE, bottomAxis, leftAxis);

        XDDFLineChartData.Series series1 = (XDDFLineChartData.Series) data.addSeries(numOfThreads, cpu);
        series1.setTitle("CPU Bound Task", null);
        series1.setSmooth(false);
        series1.setMarkerStyle(MarkerStyle.SQUARE);

        XDDFLineChartData.Series series2 = (XDDFLineChartData.Series) data.addSeries(numOfThreads, memory);
        series2.setTitle("Memory Bound Task", null);
        series2.setSmooth(true);
        series2.setMarkerStyle(MarkerStyle.SQUARE);

        XDDFLineChartData.Series series3 = (XDDFLineChartData.Series) data.addSeries(numOfThreads, io);
        series3.setTitle("IO Bound Task", null);
        series3.setSmooth(true);
        series3.setMarkerStyle(MarkerStyle.SQUARE);
        chart.plot(data);
    }

    void saveToFile() throws IOException {
        workbook.write(fos);
        workbook.close();
    }
}
