package com.zaigard100.game;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class Utils {

    ArrayList<Zone> zones;

    public Utils(){
        zones = new ArrayList<>();
    }

    public static XSSFWorkbook readWorkbook(String file) {
        try {
            return new XSSFWorkbook(new File(file));
        }
        catch (Exception e) {
            System.out.println(e.fillInStackTrace());
            return null;
        }
    }
    public static XSSFSheet getSheet(XSSFWorkbook book,int number){
        return book.getSheetAt(number);
    }

    public void load_zones(String file, boolean isCity){

        XSSFWorkbook book = readWorkbook(file);
        XSSFSheet sheet = getSheet(book,0);
        Iterator rowIter = sheet.rowIterator();
        while (rowIter.hasNext()) {
            XSSFRow row = (XSSFRow) rowIter.next();
            try {
                //поменять колонки при изменении формата храненеия данных эксель
                zones.add(new Zone(
                        row.getCell(0).getRichStringCellValue().getString(),
                        row.getCell(1).getRichStringCellValue().getString(),
                        isCity,
                        row.getCell(2).getRichStringCellValue().getString(),
                        (int) row.getCell(4).getNumericCellValue(),
                        (int) row.getCell(5).getNumericCellValue(),
                        (int) row.getCell(6).getNumericCellValue(),
                        (int) row.getCell(7).getNumericCellValue(),
                        (int) row.getCell(8).getNumericCellValue()
                ));
            }catch (IllegalStateException e){
                System.out.print("IllegalStatement: ");
                System.out.println(row.getCell(0).getRichStringCellValue().getString());//какие-то города пропущены по этому да
            }catch (NullPointerException e){
                System.out.print("NullPointer: ");
                System.out.println(row.getCell(0).getRichStringCellValue().getString());
            }
        }
    }

    public ArrayList<Zone> getZones() {
        return zones;
    }

    public void setZones(ArrayList<Zone> zones) {
        this.zones = zones;
    }
}