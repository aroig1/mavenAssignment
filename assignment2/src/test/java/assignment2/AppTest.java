package assignment2;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;

import org.junit.Test;


/**
 * Unit test for simple App.
 */
public class AppTest 
{

    @Test
    public void outputPDF() {
        App.assignment2("PDF", "sample.csv");
        File filePDF = new File("assignment2.pdf");
        assertTrue(filePDF.isFile());
        assertTrue(filePDF.length() > 0);
    }

    @Test
    public void outputXLS() {
        App.assignment2("XLS", "sample.csv");
        File fileXLS = new File("assignment2.xls");
        assertTrue(fileXLS.isFile());
        assertTrue(fileXLS.length() > 0);
    }

    @Test
    public void invalidOutputType() {
        assertThrows(RuntimeException.class, () -> {
            App.assignment2("txt", "sample.csv");
        });
    }

    @Test
    public void fileNotFound() {
        assertThrows(RuntimeException.class, () -> {
            App.assignment2("PDF", "fakefile.csv");
        });
    }

    @Test
    public void wrongInputFileType() {
        assertThrows(RuntimeException.class, () -> {
            App.assignment2("PDF", "src/test/resources/sample.txt");
        });
    }
}
