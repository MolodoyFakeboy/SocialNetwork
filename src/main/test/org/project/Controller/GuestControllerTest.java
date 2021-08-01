package org.project.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.project.Configs.Config;
import org.project.Configs.WebConfig;
import org.project.Model.Guest;
import org.project.Model.Room;
import org.project.Model.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.sql.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = Config.class),
        @ContextConfiguration(classes = WebConfig.class)
})
public class GuestControllerTest  {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testGetGuest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/guest/{index}",1)).andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    public void testAddNewGuest() throws Exception{
        Guest guest = new Guest("Zahar","Zahar","111-111-111");
        Date date = Date.valueOf("2021-07-30");
        guest.setLocalDate(date);
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/guest")
                        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(guest)))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).
                andExpect(jsonPath("$.name").value("Zahar"))
                .andDo(print());
    }

    @Test
    public void testBookRoom()throws Exception {
        Room room = new Room(101, 1, 2, 4, 2000);
        room.setRoomId(108);
        this.mockMvc.perform(MockMvcRequestBuilders.put("/guestBR/{guestID}",4362)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(room))).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testUseService() throws Exception {
        Service service = new Service("Кофе в номер",300,"принесем кофе прямо вам в номер");
        service.setIdService(1);
        int guestID = 4362;
        this.mockMvc.perform(MockMvcRequestBuilders.put("/service_guest/{guestIndex}",guestID)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(service))).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testGetaBill() throws Exception{
        int roomID = 108;
        this.mockMvc.perform(MockMvcRequestBuilders.get("/guestBill/{roomIndex}",roomID))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testLeaveHotel() throws Exception {
        Room room = new Room(101, 1, 2, 4, 2000);
        room.setRoomId(108);
        int guestID = 4362;
        this.mockMvc.perform(MockMvcRequestBuilders.put("/leave_guest/{guestIndex}",guestID)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(room))).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testGetNumberGuest() throws Exception{
        this.mockMvc.perform(MockMvcRequestBuilders.get("/guests")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testSortUsingServicePrice() throws Exception{
        int guestID = 1;

        this.mockMvc.perform(MockMvcRequestBuilders.get("/guestSPrice/{guestIndex}", guestID))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testSortUsingServiceTime() throws Exception{
        int guestID = 1;

        this.mockMvc.perform(MockMvcRequestBuilders.get("/guestSTime/{guestIndex}", guestID))
                .andDo(print()).andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            String json = ow.writeValueAsString(obj);
            return json;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}