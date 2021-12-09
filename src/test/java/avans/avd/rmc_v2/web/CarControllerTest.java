package avans.avd.rmc_v2.web;

import avans.avd.rmc_v2.controller.CarController;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class CarControllerTest {
    @Autowired
    private MockMvc mockMvc;

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

    @Test
    public void testCreateCar() throws Exception {
        User user = new User(6L, "Merel","van Laren","Poolseweg","48D","6755JQ","Breda","Nederland","+31664855136","NL12INGB078695722","merelvanlaren@gmail.com", UserRole.OWNER, LocalDateTime.now());
        Car car = new Car(5L, "Tesla", "Model", "3", "890-AH-1", 5.7, 33450, 0, CarType.BEV, user, LocalDateTime.now());

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")));

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        mapper.registerModule(javaTimeModule);

        ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = writer.writeValueAsString(car);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/cars/")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
//       todo vergelijk inhoud-aantal .andExpect()
        .andDo(print());
    }
}
