package exportTest;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**
 * 海量数据导出
 * @author wb-cb236432
 * @create 2018-07-04 17:02
 **/
public class ExportData {
    public void exportBigDataExcel(ValueDataDto valueDataDto, String path)
        throws IOException {
        // 最重要的就是使用SXSSFWorkbook，表示流的方式进行操作
        // 在内存中保持100行，超过100行将被刷新到磁盘
        SXSSFWorkbook wb = new SXSSFWorkbook(100);
        Sheet sh = wb.createSheet(); // 建立新的sheet对象
        Row row = sh.createRow(0);   // 创建第一行对象
        // -----------定义表头-----------
        Cell cel0 = row.createCell(0);
        cel0.setCellValue("1");
        Cell cel2 = row.createCell(1);
        cel2.setCellValue("2");
        Cell cel3 = row.createCell(2);
        cel3.setCellValue("3");
        Cell cel4 = row.createCell(3);
      /*  // ---------------------------
        List<valuedatabean> list = new ArrayList<valuedatabean>();
        // 数据库中存储的数据行
        int page_size = 10000;
        // 求数据库中待导出数据的行数
        int list_count = this.daoUtils.queryListCount(this.valueDataDao
            .queryExportSQL(valueDataDto).get("count_sql"));
        // 根据行数求数据提取次数
        int export_times = list_count % page_size > 0 ? list_count / page_size
            + 1 : list_count / page_size;
        // 按次数将数据写入文件
        for (int j = 0; j < export_times; j++) {
            list = this.valueDataDao.queryPageList(this.valueDataDao
                    .queryExportSQL(valueDataDto).get("list_sql"), j + 1,
                page_size);
            int len = list.size() < page_size ? list.size() : page_size;
            for (int i = 0; i < len; i++) {
                Row row_value = sh.createRow(j * page_size + i + 1);
                Cell cel0_value = row_value.createCell(0);
                cel0_value.setCellValue(list.get(i).getaa());
                Cell cel2_value = row_value.createCell(1);
                cel2_value.setCellValue(list.get(i).getaa());
                Cell cel3_value = row_value.createCell(2);
                cel3_value.setCellValue(list.get(i).getaa_person());
            }
            list.clear(); // 每次存储len行，用完了将内容清空，以便内存可重复利用
        }
        FileOutputStream fileOut = new FileOutputStream(path);
        wb.write(fileOut);
        fileOut.close();
        wb.dispose();*/
    }

}
