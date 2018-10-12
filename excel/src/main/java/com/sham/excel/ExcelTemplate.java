package com.sham.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ExcelTemplate {
    private static ExcelTemplate et = new ExcelTemplate();
    private Workbook wb;
    private Sheet sheet;
    private Row curRow;
    private int curColIndex;
    private int curRowIndex;
    private int initHeadColIndex = -1;
    private int initHeadRowIndex = -1;
    private int lastRowIndex;
    private CellStyle headStyle;
    private int initColIndex;
    private int initRowIndex;
    private CellStyle defaultStyle;
    private float rowHeight;
    private Map<Integer, CellStyle> styles;

    public static ExcelTemplate getInstance() {
        return et;
    }

    public ExcelTemplate readTemplate(String path) {
        try {
            this.wb = new HSSFWorkbook(TemplateFileUtil.getTemplates(path));
            this.init();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this;
    }

    private void init() {
        this.sheet = this.wb.getSheetAt(0);
        this.lastRowIndex = this.sheet.getLastRowNum();
        this.initConfig();
        this.curRow = this.sheet.createRow(this.curRowIndex);
    }

    private void initConfig() {
        Iterator iterator = this.sheet.iterator();
        this.styles = new HashMap<>();
        while (iterator.hasNext()) {
            Row row = (Row) iterator.next();
            Iterator rit = row.iterator();
            while (rit.hasNext()) {
                Cell cell = (Cell) rit.next();
                String str = cell.getStringCellValue().trim();
                if (str.equals("{head}")) {
                    this.initHeadColIndex = cell.getColumnIndex();
                    this.initHeadRowIndex = row.getRowNum();
                    this.headStyle = cell.getCellStyle();
                }
                if (str.equals("{datas}")) {
                    this.initColIndex = cell.getColumnIndex();
                    this.initRowIndex = row.getRowNum();
                    this.curColIndex = this.initColIndex;
                    this.curRowIndex = this.initRowIndex;
                    this.defaultStyle = cell.getCellStyle();
                    this.rowHeight = row.getHeightInPoints();
                }
                if (str.equals("{defaultStyles}")) {
                    this.defaultStyle = cell.getCellStyle();
                }

                if (str.equals("{styles}")) {
                    this.styles.put(cell.getColumnIndex(), cell.getCellStyle());
                }
            }
        }
    }


    public void writeOs(OutputStream os) {
        try {
            this.wb.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFile(String filePath) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            this.wb.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setData(List<Integer> sort, List<Map<Integer, Object>> dataList) {
        Map<Integer, Object> map = null;
        for (Iterator<Map<Integer, Object>> iterator = dataList.iterator(); iterator.hasNext(); ) {
            map = iterator.next();
        }
    }

    public void setHeader(List<ExeclHeader> header) {
        if (this.initHeadColIndex >= 0) {
            Row row = this.sheet.getRow(this.initHeadRowIndex);
            Integer col = this.initHeadColIndex;
            for (Iterator<ExeclHeader> iterator = header.iterator(); iterator.hasNext(); col++) {
                ExeclHeader execlHeader = iterator.next();
                Cell cell = row.getCell(col);
                if (cell == null) {
                    cell = row.createCell(col);
                }
                cell.setCellValue(execlHeader.getTitle());
                cell.setCellStyle(this.headStyle);
                if (execlHeader.getWidth() > 0) {
                    this.sheet.setColumnWidth(col, execlHeader.getWidth());
                }
            }
        }
    }

    private void setCellStyle(Cell c) {
        if (this.styles.containsKey(this.curColIndex)) {
            c.setCellStyle((CellStyle) this.styles.get(this.curColIndex));
        } else {
            c.setCellStyle(this.defaultStyle);
        }

    }

    public void createNewRow() {
        this.curRow = this.sheet.createRow(this.curRowIndex);
        this.curRow.setHeightInPoints(this.rowHeight);
        ++this.curRowIndex;
        this.curColIndex = this.initColIndex;
    }

    public void createCell(String value) {
        Cell c = this.curRow.createCell(this.curColIndex);
        this.setCellStyle(c);

        c.setCellValue(value);


        ++this.curColIndex;
    }

    public void replaceData(Map<String, String> map) {
        Iterator sot = sheet.iterator();
        while (sot.hasNext()) {
            Row row = (Row) sot.next();
            Iterator rot = row.iterator();
            while (rot.hasNext()) {
                Cell cell = (Cell) rot.next();
                String str = cell.getStringCellValue().trim();
                if (str.startsWith("#") && map.containsKey(str.substring(1))) {
                    cell.setCellValue(map.get(str.substring(1)));
                }
            }
        }
    }
}
