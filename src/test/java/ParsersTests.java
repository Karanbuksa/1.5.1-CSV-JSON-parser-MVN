import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ParsersTests {
    List<Employee> expectedEmployees = new ArrayList<>();

    /**
     * ѕыталс€ примен€ть аннотации @BeforeAll и @BeforeEach дл€ заполнени€ листа сотрудников объектами,
     * но в итоге в тесты приходили пустые листы
     **/
@BeforeEach
public void start(){
    expectedEmployees.add(new Employee(1, "John", "Smith", "USA", 25));
    expectedEmployees.add(new Employee(2, "Ivan", "Petrov", "RU", 23));
    expectedEmployees.add(new Employee(3, "David", "Tenant", "UK", 45));
    expectedEmployees.add(new Employee(4, "Math", "Smith", "UK", 38));
}

    @Test
    public void parseXML_test() throws ParserConfigurationException, IOException, SAXException {
        String data = "src/test/resources/data.xml";

        List<Employee> employees = Main.parseXML(data);

        assertThat(expectedEmployees.toString(), equalTo(employees.toString()));

    }

    @Test
    public void parseCSV_test() {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String data = "src/test/resources/data.csv";
        List<Employee> expectedEmployees = new ArrayList<>();
        expectedEmployees.add(new Employee(1, "John", "Smith", "USA", 25));
        expectedEmployees.add(new Employee(2, "Ivan", "Petrov", "RU", 23));
        expectedEmployees.add(new Employee(3, "David", "Tenant", "UK", 45));
        expectedEmployees.add(new Employee(4, "Math", "Smith", "UK", 38));

        List<Employee> employees = Main.parseCSV(columnMapping, data);

        assertThat(employees.toString(), equalTo(expectedEmployees.toString()));


    }

    @Test
    public void readString_test() {
        String data = "src/test/resources/data.json";
        List<Employee> expectedEmployees = new ArrayList<>();
        expectedEmployees.add(new Employee(1, "John", "Smith", "USA", 25));
        expectedEmployees.add(new Employee(2, "Ivan", "Petrov", "RU", 23));
        expectedEmployees.add(new Employee(3, "David", "Tenant", "UK", 45));
        expectedEmployees.add(new Employee(4, "Math", "Smith", "UK", 38));

        List<Employee> employees = Main.jsonToList(Main.readString(data));

        assertThat(employees.toString(), equalTo(expectedEmployees.toString()));

    }
}
