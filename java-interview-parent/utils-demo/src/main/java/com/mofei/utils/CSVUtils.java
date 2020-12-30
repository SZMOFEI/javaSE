package com.mofei.utils;

import org.jumpmind.symmetric.csv.CsvReader;

import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * @author mofei
 * @see Java-samples
 * @since 2020/11/24 11:12
 */
public class CSVUtils {
    public static void readCsvFile(String filePath) {
        try {
            ArrayList<String[]> csvList = new ArrayList<String[]>();
            CsvReader reader = new CsvReader(filePath, ',', Charset.forName("UTF-8"));
//          reader.readHeaders(); //跳过表头,不跳可以注释掉

            while (reader.readRecord()) {
                csvList.add(reader.getValues()); //按行读取，并把每一行的数据添加到list集合
            }
            reader.close();
            System.out.println("读取的行数：" + csvList.size());

            for (int row = 0; row < csvList.size(); row++) {
                System.out.println("-----------------");
                //打印每一行的数据
                System.out.print(csvList.get(row)[0] + ",");
//                System.out.print(csvList.get(row)[1] + ",");
//                System.out.print(csvList.get(row)[2] + ",");
//                System.out.print(csvList.get(row)[3] + ",");
//                System.out.print(csvList.get(row)[4] + ",");
//                System.out.print(csvList.get(row)[5] + ",");
//                System.out.print(csvList.get(row)[6] + ",");
//                System.out.print(csvList.get(row)[7] + ",");
//                System.out.print(csvList.get(row)[8] + ",");
//                System.out.print(csvList.get(row)[9] + ",");
//                System.out.print(csvList.get(row)[10] + ",");
//                System.out.print(csvList.get(row)[11] + ",");
//                System.out.print(csvList.get(row)[12] + ",");
                System.out.print(csvList.get(row)[13] + ",");
//                System.out.print(csvList.get(row)[14] + ",");
//                System.out.print(csvList.get(row)[15] + ",");
//                System.out.print(csvList.get(row)[16] + ",");
//                System.out.print(csvList.get(row)[17] + ",");
//                System.out.print(csvList.get(row)[18] + ",");
//                System.out.print(csvList.get(row)[19] + ",");
//                System.out.print(csvList.get(row)[20] + ",");
//                System.out.print(csvList.get(row)[21] + ",");
                System.out.print(csvList.get(row)[22] + ",");
//                System.out.print(csvList.get(row)[23] + ",");
//                System.out.print(csvList.get(row)[24] + ",");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String filePath = "C:\\Users\\mof\\Downloads\\deviceMetadata.csv";
        readCsvFile(filePath);
    }
}
