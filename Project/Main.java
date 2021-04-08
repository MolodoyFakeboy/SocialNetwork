import Controller.GuestController;
import Controller.MenuController;
import Controller.RoomController;
import Controller.ServiceController;
import Dao.GuestDao;
import Dao.RoomDao;
import Dao.ServiceDao;
import Resources.Prop;
import Service.FunctionService;
import Service.GuestService;
import Service.RoomService;
import UI.MenuBuilder;
import UI.Navigator;
import com.google.gson.Gson;
import org.apache.log4j.PropertyConfigurator;


import java.io.*;


public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        FileInputStream fileInputStream = new FileInputStream("save.ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        RoomDao roomDao1 = (RoomDao) objectInputStream.readObject();
        GuestDao guestDao1 = (GuestDao) objectInputStream.readObject();
        ServiceDao serviceDao1 = (ServiceDao) objectInputStream.readObject();
        objectInputStream.close();

        Prop.load();
        String log4jConfPath = "src/Resources/log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);

        RoomService roomService = new RoomService(roomDao1);
        FunctionService functionService = new FunctionService(serviceDao1);
        GuestService guestService = new GuestService(guestDao1);
        RoomController roomController = new RoomController(roomService);
        ServiceController serviceController = new ServiceController(functionService);
        GuestController guestController = new GuestController(guestService);
        MenuBuilder menuBuilder = new MenuBuilder(roomController, serviceController, guestController);
        Navigator navigator = new Navigator(menuBuilder.getRootMenu());
        MenuController menuController = new MenuController(menuBuilder, navigator);
        menuController.run();

        Gson gson = new Gson();
        String json = gson.toJson(roomDao1);
        String json2 = gson.toJson(serviceDao1);
        String json3 = gson.toJson(guestDao1);
        gson.fromJson(json,RoomDao.class);

        FileOutputStream fileOutputStream = new FileOutputStream("save.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(roomDao1);
        objectOutputStream.writeObject(guestDao1);
        objectOutputStream.writeObject(serviceDao1);
        objectOutputStream.close();





    }

}
