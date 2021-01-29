package ee.bcs.valiit.tasks;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.Request;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SolutionBankingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createAccount() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Account account = new Account();
        account.setOwnerId(1000001);
        mockMvc.perform(MockMvcRequestBuilders.post("/bank/account").contentType("application/json").content(mapper.writeValueAsString(account)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createCustomer() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Customer customer = new Customer();
        customer.setFirstName("Madis");
        customer.setLastName("Mätas");
        customer.setIsikukood("37605310278");
        customer.setAadress("Mets");
        customer.setTelefon("55668877");
        customer.setEmail("Madois@");
        mockMvc.perform(MockMvcRequestBuilders.post("/bank/customer").contentType("application/json").content(mapper.writeValueAsString(customer)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void dep() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Transaction transaction = new Transaction();
        transaction.setAmount(new BigDecimal("157"));
        transaction.setComment("Sissemakse oma kontole");
        mockMvc.perform(MockMvcRequestBuilders.post("/bank/dep/771002").contentType("application/json").content(mapper.writeValueAsString(transaction)))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void with() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Transaction transaction = new Transaction();
        transaction.setAmount(new BigDecimal("1"));
        transaction.setComment("Raha väljavõtt oma kontolt");
        mockMvc.perform(MockMvcRequestBuilders.post("/bank/with/771002").contentType("application/json").content(mapper.writeValueAsString(transaction)))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void tran() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Transaction transaction = new Transaction();
        transaction.setAmount(new BigDecimal("10"));
        transaction.setComment("Ülekanne elektri eest");
        mockMvc.perform(MockMvcRequestBuilders.post("/bank/tran/771002/771001/").contentType("application/json").content(mapper.writeValueAsString(transaction)))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void show() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bank/customer/1000000").contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void showCusts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bank/customer/").contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}