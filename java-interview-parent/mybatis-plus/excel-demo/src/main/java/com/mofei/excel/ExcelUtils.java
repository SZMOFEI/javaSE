package com.mofei.excel;

import com.mofei.excel.entity.Device;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mofei
 * @date 2020/11/24 16:15
 */
public class ExcelUtils {
    private HSSFSheet sheet;

    public ExcelUtils(String filePath, String sheetName) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            HSSFWorkbook sheets = new HSSFWorkbook(fileInputStream);
            sheet = sheets.getSheet(sheetName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 首先读取excel数据
     * 然后处理数据
     * 写excel 数据
     *
     * @param args
     */
    //测试方法
    public static void main(String[] args) {
        String filePath = "C:\\Users\\86156\\Desktop\\查询设备.xls";
        String activityPath = "C:\\Users\\86156\\Desktop\\处理数据sn\\acivity.xls";
        String destPath = "D:\\final.xls";
//        String activiPath = "C:\\Users\\86156\\Desktop\\sn_json.xls";
        List<Device> devices = readExcel2003(new File(filePath), 1);
        List<Device> activity = readExcel2003(new File(activityPath), 2);
        List<String> collect = activity.parallelStream().map(Device::getSn).collect(Collectors.toList());
        for (int i = 0; i < devices.size(); i++) {
            Device device = devices.get(i);
            String sn = device.getSn();
            if (collect.contains(sn)) {
                device.setActivity("YES");
            } else {
                device.setActivity("NO");
            }
        }

        //导出excel
        writeExcel(devices, activityPath);
        //在线设备
//        List<String> onlineDevice = XXX.getOnlineDevice();
        System.out.println();
    }

    public static void writeExcel(List<Device> result, String path) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet1");
        for (int i = 0; i < result.size(); i++) {
            HSSFRow row = sheet.createRow(i);
            Device device = result.get(i);
            if (device != null) {
                for (int j = 0; j < 5; j++) {
                    HSSFCell cell = row.createCell(j);
                    if (j == 0) {
                        cell.setCellValue(device.getSn());
                    }
                    if (j == 1) {
                        cell.setCellValue(device.getCsn());
                    }
                    if (j == 2) {
                        cell.setCellValue(device.getGroup());
                    }
                    if (j == 3) {
                        cell.setCellValue(device.getOnline());
                    }
                    if (j == 4) {
                        cell.setCellValue(device.getActivity());
                    }
                }
            }
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            wb.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        File file = new File(path);//Excel文件生成后存储的位置。
        OutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(content);
            os.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeExcel(ArrayList<ArrayList<Object>> result, String path) {
        if (result == null) {
            return;
        }
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet1");
        for (int i = 0; i < result.size(); i++) {
            HSSFRow row = sheet.createRow(i);
            if (result.get(i) != null) {
                for (int j = 0; j < result.get(i).size(); j++) {
                    HSSFCell cell = row.createCell(j);
                    cell.setCellValue(result.get(i).get(j).toString());
                }
            }
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            wb.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        File file = new File(path);//Excel文件生成后存储的位置。
        OutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(content);
            os.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static List<Device> getDevices(ArrayList<ArrayList<Object>> arrayLists) {
        List<Device> result = new ArrayList<>();
        for (int i = 0; i < arrayLists.size(); i++) {
            if (i == 0) {
                continue;
            }
            ArrayList<Object> list = arrayLists.get(i);
            Device device = new Device();
            for (int i1 = 0; i1 < list.size(); i1++) {
                Object o = list.get(i1);
                if (i1 == 0) {
                    continue;
                }
                if (i1 == 1) {
                    device.setSn(o.toString());
                }
                if (i1 == 2) {
                    device.setCsn(o.toString());
                }
                if (i1 == 3) {
                    device.setGroup(o.toString());
                }
                if (i1 == 4) {
                    System.out.print(o);
                }
                if (i1 == 5) {
                    System.out.print(o);
                } else {
                    System.out.print(o);
                }
            }
            device.setOnline("offline");
            result.add(device);
        }
        return result;
    }

    public String getCellByCaseName(String caseName, int currentColumn, int targetColumn) {
        String operateSteps = "";
        //获取行数
        int rows = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < rows; i++) {
            HSSFRow row = sheet.getRow(i);
            String cell = row.getCell(currentColumn).toString();
            if (cell.equals(caseName)) {
                operateSteps = row.getCell(targetColumn).toString();
                break;
            }
        }
        return operateSteps;
    }

    //默认单元格内容为数字时格式
    private static DecimalFormat df = new DecimalFormat("0");
    // 默认单元格格式化日期字符串
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // 格式化数字
    private static DecimalFormat nf = new DecimalFormat("0.00");

    public static List<Device> readExcel2003(File file, int count) {
        try {
            List<Device> result = new ArrayList<>();
            HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file));
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row;
            HSSFCell cell;
            Device value;
            //遍历所有的表格
            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
            int firstRowNum = sheet.getFirstRowNum();
            for (int i = sheet.getFirstRowNum(), rowCount = 0; rowCount < sheet.getPhysicalNumberOfRows(); i++) {
                Device device = new Device();
                Device device1 = new Device();
                row = sheet.getRow(i);

                if (row == null) {
                    //当读取行为空时
                    if (i != sheet.getPhysicalNumberOfRows()) {//判断是否是最后一行
                        result.add(device);
                    }
                    continue;
                } else {
                    rowCount++;
                }
                if (i == 0) {
                    continue;
                }
                for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
                    String sn;
                    cell = row.getCell(j);
                    if (cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                        continue;
                    }
                    if (j == 1) {
                        sn = cell.getStringCellValue();
                        device.setSn(sn);
                    } if (j == 2) {
                        sn = cell.getStringCellValue();
                        device.setCsn(sn);
                    }
                    if (count == 2) {
                        if (j == 0) {
                            sn = cell.getStringCellValue();
                            device.setSn(sn);
                        }
                    }
                }//end for j
                result.add(device);
            }//end for i

            return result;
        } catch (Exception e) {
            return null;
        }
    }


    private String getExcelDateByIndex(int row, int column) {
        HSSFRow row1 = sheet.getRow(row);
        String cell = row1.getCell(column).toString();
        return cell;
    }


}
