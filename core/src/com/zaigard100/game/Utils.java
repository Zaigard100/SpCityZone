package com.zaigard100.game;

import com.badlogic.gdx.graphics.Color;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

public class Utils {

    ArrayList<Zone> zones;

    public Utils(){
        zones = new ArrayList<>();
    }

    public static HSSFWorkbook readWorkbook(String file) {
        try {
            POIFSFileSystem fs = new POIFSFileSystem(Files.newInputStream(Paths.get(file)));
            return new HSSFWorkbook(fs);
        }
        catch (Exception e) {
            System.out.println(e.fillInStackTrace());
            return null;
        }
    }
    public static HSSFSheet getSheet(HSSFWorkbook book,int number){
        return book.getSheetAt(number);
    }

    public void load_zones(String file, boolean isCity){

        HSSFWorkbook book = readWorkbook(file);
        HSSFSheet sheet = getSheet(book,0);
        Iterator rowIter = sheet.rowIterator();
        while (rowIter.hasNext()) {
            HSSFRow row = (HSSFRow) rowIter.next();
            zones.add(new Zone(
                    row.getCell(0).getRichStringCellValue().getString(),
                    row.getCell(1).getRichStringCellValue().getString(),
                    isCity,
                    row.getCell(3).getRichStringCellValue().getString(),
                    (int) row.getCell(5).getNumericCellValue(),
                    (int) row.getCell(6).getNumericCellValue(),
                    (int) row.getCell(7).getNumericCellValue(),
                    (int) row.getCell(8).getNumericCellValue(),
                    (int) row.getCell(9).getNumericCellValue(),
                    Color.GREEN
            ));
            row.getCell(0);
        }
    }

    public ArrayList<Zone> getZones() {
        return zones;
    }

    public void setZones(ArrayList<Zone> zones) {
        this.zones = zones;
    }
}