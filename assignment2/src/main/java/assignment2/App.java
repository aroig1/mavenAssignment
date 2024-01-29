package assignment2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.File;

// PDF dependencies
import com.itextpdf.kernel.pdf.PdfDocument; 
import com.itextpdf.kernel.pdf.PdfWriter; 
import com.itextpdf.layout.Document; 
import com.itextpdf.layout.element.Table;  

// XLS dependencies
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class App {

    public static void createPdfTable(BufferedReader br) {

        try {
            
            String line = br.readLine();
            String[] row = line.split(";");

            PdfDocument pdfDoc = new PdfDocument(new PdfWriter("assignment2.pdf"));
            Document doc = new Document(pdfDoc);
            Table table = new Table(row.length);

            for (String x : row) {
                table.addCell(x);
            }

            while ((line = br.readLine()) != null) {
                row = line.split(";");
                for (String x : row) {
                    table.addCell(x);
                }
            }

            doc.add(table);
            doc.close();

        }
        catch (Exception e) {
            System.out.println("File Exception occured");
        }
    }

    public static void createXlsTable(BufferedReader br) {

        try {
            String line = br.readLine();
            String[] row = line.split(";");

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("assignment2");

            XSSFRow tempRow = sheet.createRow(0);

            int j = 0;
            for (String x : row) {
                tempRow.createCell(j++).setCellValue(x);
            }
            
            int i = 1;
            while ((line = br.readLine()) != null) {
                j = 0;
                row = line.split(";");
                tempRow = sheet.createRow(i++);
                for (String x : row) {
                    tempRow.createCell(j++).setCellValue(x);
                }
            }

            FileOutputStream out = new FileOutputStream(new File("assignment2.xls"));
            workbook.write(out);
            out.close();
            workbook.close();
        }
        catch (Exception e) {
            System.out.println("File Exception occured");
        }
    }

    public static void main( String[] args ) {

        try (BufferedReader br = new BufferedReader(new FileReader(args[1]))) { 
            
            // If PDF is input
            if (args[0].equals("PDF")) {
                createPdfTable(br);
            }
            
            // if XLS is input
            else if (args[0].equals("XLS")) {
                createXlsTable(br);
            }
        }
        catch (FileNotFoundException excpt) {
            System.out.println("File not found error");
        }
        catch (IOException excpt) {
            System.out.println("IOException encountered");
        }

    }
}
