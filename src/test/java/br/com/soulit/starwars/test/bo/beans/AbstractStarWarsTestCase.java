package br.com.soulit.starwars.test.bo.beans;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans-test.xml")
public abstract class AbstractStarWarsTestCase {
    
    protected String getBaseText() throws FileNotFoundException {
        final File file = new File("src/test/resources/sample-script.txt");
        final Scanner scanner = new Scanner(file);
        try {
            final StringBuilder sb = new StringBuilder();
            if (scanner.hasNext()) {
                while (scanner.hasNext()) {
                    sb.append(scanner.nextLine()).append("\n");
                }
            }
            final String text = sb.toString();
            return text;
        }
        catch (final Exception e) {
            throw e;
        }
        finally {
            scanner.close();
        }
    }
}
