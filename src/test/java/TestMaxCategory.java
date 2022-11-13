import com.google.gson.Gson;
import org.example.Main;
import org.example.MaxCategory;
import org.example.Product;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class TestMaxCategory {
    public class TestCalculationMax {
        final String textJsonOther = "{\"title\": \"кастрюля\", \"date\": \"2022.02.08\", \"sum\": 3000}";
        final String textJsonCetegory = "{\"title\": \"колбаса\", \"date\": \"2022.02.08\", \"sum\": 800}";
        MaxCategory maxCategory = new MaxCategory();
        Gson gsonTest = new Gson();

        @Mock
        private Main mainServer;

        @BeforeAll
        static void setUpAll() {
            System.out.println("Вызываюсь до выполнения всех тестов");
        }

        @AfterAll
        static void tearDownAll() {
            System.out.println("Вызываюсь после выполнения всех тестов");
        }


        @BeforeEach
        void setUp() {
            mainServer = Mockito.mock(Main.class);
        }


        @Test
        public void testLoadOtherFromTSV() throws IOException {
            Product productTest = gsonTest.fromJson(textJsonOther, Product.class);
            maxCategory.productList.add(productTest);
            Map actualResult = (maxCategory.loadFromTSV(new File("categories.tsv")));
            JSONObject expectedResult = new JSONObject();
            expectedResult.put("categories", "другое");
            expectedResult.put("sum", 3000);
            System.out.println(expectedResult.get("categories"));
            System.out.println(actualResult.get("categories"));
            Assertions.assertEquals(expectedResult.get("categories"), actualResult.get("categories"));
        }

        @Test
        public void testLoadCategoryFromTSV() throws IOException {
            Product productTestCategory = gsonTest.fromJson(textJsonCetegory, Product.class);
            maxCategory.productList.add(productTestCategory);
            Map actualResult = (MaxCategory.loadFromTSV(new File("categories.tsv")));
            JSONObject expectedResult = new JSONObject();
            expectedResult.put("categories", "еда");
            expectedResult.put("sum", 800);
            System.out.println(expectedResult.get("sum"));
            System.out.println(actualResult.get("sum"));
            Assertions.assertEquals(expectedResult.get("sum"), actualResult.get("sum"));
        }
    }
}
