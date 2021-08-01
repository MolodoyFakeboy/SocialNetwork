package org.project.Controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.project.Configs.Config;
import org.project.Configs.WebConfig;
import org.project.Model.EnumStatus;
import org.project.Model.Room;
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

import java.nio.charset.Charset;
import java.sql.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextHierarchy({
        @ContextConfiguration(classes = Config.class),
        @ContextConfiguration(classes = WebConfig.class)
})
public class RoomControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @BeforeEach
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testGetRoom() throws Exception {
        int roomID = 5;
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/room/{index}", roomID)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testSortRoomforPrice() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/roomsPrice"))
                .andExpect(status().isOk());
    }

    @Test
    public void testSortRoomforBed() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/roomsBed"))
                .andExpect(status().isOk());
    }

    @Test
    public void testSortRoomforStars() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/roomsStars"))
                .andExpect(status().isOk());
    }

    @Test
    public void testSortFreeRoomforPrice() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/free/roomsPrice"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateRoom() throws Exception {
        Room room = new Room(101, 1, 2, 4, 3000);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/room")
                .contentType(APPLICATION_JSON_UTF8).content(asJsonString(room)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleatRoom() throws Exception {
        int roomID = 112;
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/room/{id}", roomID))
                .andExpect(status().isOk());
    }

    @Test
    public void testChangeRoomStatus() throws Exception {
        int roomID = 108;
        this.mockMvc.perform(MockMvcRequestBuilders.put("/roomStatus/{index}/{status}", roomID, EnumStatus.BOOK_ROOM))
                .andExpect(status().isOk());
    }

    @Test
    public void testChangeRoomPrice() throws Exception {
        int roomID = 108;
        double price = 2000.00;
        this.mockMvc.perform(MockMvcRequestBuilders.put("/roomPrice/{index}/{price}", roomID, price))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAmountFreeRoom() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/free/roomsAmount")).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testSortRoomIsFree() throws Exception {
        Date date = Date.valueOf("2021-06-01");
        this.mockMvc.perform(MockMvcRequestBuilders.get("/rooms/{date}", date)).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetLastThreeGuest() throws Exception {
        int roomID = 1;

        this.mockMvc.perform(MockMvcRequestBuilders.get("/roomsGuest/{roomIndex}", roomID)).andDo(print())
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new Gson().toJson(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}