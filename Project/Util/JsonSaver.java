package Util;

import Annotations.Singleton;
import Dao.*;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import org.apache.log4j.Logger;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Singleton
public class JsonSaver {

    private final Gson gson;
    private Gson gsonRoomDao;
    private Gson gsonGuestDao;
    final Logger log = org.apache.log4j.Logger.getLogger(JsonSaver.class);

    public JsonSaver() {
        gsonRoomDao = GsonExclusionStrategy.createGsonFromBuilder( new GsonExclusionStrategy(null) );
        gsonGuestDao = GsonExclusionStrategy.createGsonFromBuilder( new GsonExclusionStrategy(null) );
        gson = new Gson();
    }

    public void searilization(RoomDao roomDao, GuestDao guestDao, ServiceDao serviceDao) throws IOException {
        String jsonRoom = gsonRoomDao.toJson(roomDao);
        String jsonService = gson.toJson(serviceDao);
        String jsonGuest = gsonGuestDao.toJson(guestDao);

        FileWriter fileWriterRoom = new FileWriter("roomDao.json");
        FileWriter fileWriterGuest = new FileWriter("guestDao.json");
        FileWriter fileWriterService = new FileWriter("serviceDao.json");
        try {
            fileWriterRoom.write(jsonRoom);
            fileWriterService.write(jsonService);
            fileWriterGuest.write(jsonGuest);
        } catch (IOException e) {
            log.error("Невозможность прочитать json");
        } finally {
            fileWriterRoom.flush();
            fileWriterRoom.close();
            fileWriterGuest.flush();
            fileWriterGuest.close();
            fileWriterService.flush();
            fileWriterService.close();
        }
    }


    public  RoomDao deserializationRoom() {
        RoomDao roomDao = null;
        try {
            FileReader fileReaderRoom = new FileReader("Project/roomDao.json");
            roomDao = gson.fromJson(fileReaderRoom, RoomDao.class);
            fileReaderRoom.close();
            log.info("Все прошло успешно");
        } catch (JsonSyntaxException | JsonIOException | IOException e) {
            log.error("Невозможность прочитать json");
        }
        return  roomDao;
    }

    public  GuestDao deserializationGuest() {
        GuestDao guestDao = null;
        try {
            FileReader fileReaderGuest = new FileReader("Project/guestDao.json");
            guestDao = gson.fromJson(fileReaderGuest, GuestDao.class);
            fileReaderGuest.close();
        } catch (JsonSyntaxException | JsonIOException | IOException e) {
            log.error("Невозможность прочитать json");
        }
        return guestDao;
    }

    public  ServiceDao deserializationService() {
        ServiceDao serviceDao = null;
        try {
            FileReader fileReaderService = new FileReader("Project/serviceDao.json");
            serviceDao = gson.fromJson(fileReaderService, ServiceDao.class);
            fileReaderService.close();
        } catch (JsonSyntaxException | JsonIOException | IOException e) {
            log.error("Невозможность прочитать json");
        }
        return serviceDao;
    }

}
