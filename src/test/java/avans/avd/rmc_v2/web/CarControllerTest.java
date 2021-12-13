package avans.avd.rmc_v2.web;

import avans.avd.rmc_v2.enums.CarType;
import avans.avd.rmc_v2.enums.UserRole;
import avans.avd.rmc_v2.model.Car;
import avans.avd.rmc_v2.model.User;
import avans.avd.rmc_v2.service.CarService;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Get all Cars endpoint test
    @Test
    public void testGetCarList() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/v1/cars/")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(4)))
        .andDo(print());
    }

    // Create new Car endpoint test
    @Test
    public void testCreateCar() throws Exception {
        User user = new User(6L, "Merel","van Laren","Poolseweg","48D","6755JQ","Breda","Nederland","+31664855136","NL12INGB078695722","merelvanlaren@gmail.com", UserRole.OWNER, LocalDateTime.now());
        Car car = new Car(6L, "Suzuki", "Swift", "2010", "420-BI-1", 4.3, 18480, 0, CarType.ICE, user, LocalDateTime.now());

        // Formatting DateTime Object
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")));

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        mapper.registerModule(javaTimeModule);

        //Object to Json
        ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = writer.writeValueAsString(car);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/cars/")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andDo(print());
    }

    // Update existing Car endpoint test
    @Test
    public void testUpdateCar() throws Exception {
        User user = new User(1L, "John","Doe","Veemarktstraat","12","4811ZJ","Breda","Nederland","+31618588947","NL12INGB047583365","john.doe@gmail.com", UserRole.OWNER, LocalDateTime.now());
        Car car = new Car(1L, "Suzuki", "Swift", "2010", "123-AB-4", 4.3, 18480, 0, CarType.ICE, user, LocalDateTime.now());

        // Formatting DateTime Object
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")));

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        mapper.registerModule(javaTimeModule);

        //Object to Json
        ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = writer.writeValueAsString(car);

        mockMvc.perform(
            MockMvcRequestBuilders.put("/api/v1/cars/1")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.brand").value("Suzuki"))
        .andDo(print());
    }

    // Delete Car with {id} endpoint test
    @Test
    public void testDeleteCar() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/v1/cars/5")
        )
        .andExpect(status().isOk())
        .andDo(print());

    }

}
