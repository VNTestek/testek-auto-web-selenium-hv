package java_core.lession09_ioStream;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaStreamAutoTest {

    public static void main(String[] args) {
        JavaStreamAutoTest javaStreamAutoTest = new JavaStreamAutoTest();
        var result = javaStreamAutoTest.getDataTest("RISE_Login_002_Correct");

        System.out.println("Debug");
    }

    private JSONObject readContentFromJsonFile(String filePath) {
        try {
            File file = new File(filePath);
            String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            return new JSONObject(content);
        } catch (Exception e) {
            System.out.println("Error reading : " + e.getMessage());
        }
        return null;
    }

    Object[][] getDataTest(String tcName) {
        // Read content from data file
      //  String dataFile = System.getProperty("user.dir") + File.separator + "src/test/java/lession16/data/clientData.json";
       String dataFile = "/Users/vincent/Work/workspace/source/testek/selenium-basic/src/test/java/lession16/data/loginData.json";
        JSONObject jsonData = readContentFromJsonFile(dataFile);

        // Convert to a map and parse to data list
        Map dataMap = jsonData.toMap();
        List jsonList = (List<HashMap>) dataMap.get(tcName);

        // Add data to Objects
        Object[][] result = new Object[jsonList.size()][1];
        for (int i = 0; i < jsonList.size(); i++) {
            result[i][0] = jsonList.get(i);
        }
        return result;
    }

    Map<Integer, List<String>> getDataFromXLS() {
        Map<Integer, List<String>> data = new HashMap<>();
        String filePath = "";
        try {
            FileInputStream file = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);

            int rowIndex = 0;
            for (Row row : sheet) {
                data.put(rowIndex, new ArrayList<>());
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            data.get(new Integer(rowIndex)).add(cell.getRichStringCellValue().getString());
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                data.get(rowIndex).add(cell.getDateCellValue() + "");
                            } else {
                                data.get(rowIndex).add(cell.getNumericCellValue() + "");
                            }
                            break;
                        case BOOLEAN:
                            data.get(rowIndex).add(cell.getBooleanCellValue() + "");
                            break;
                        case FORMULA:
                            break;
                        default:
                            data.get(new Integer(rowIndex)).add(" ");
                    }
                }
                rowIndex++;
            }

//            Sheet sheet2 = workbook.createSheet("Result");
//            Row header = sheet2.createRow(0);
//            Cell cell = header.createCell(1);
//            cell.setCellValue("STT");
//            FileOutputStream outputStream = new FileOutputStream(filePath);
//            workbook.write(outputStream);
//            workbook.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}
