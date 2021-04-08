package UI;

import Controller.GuestController;
import Controller.RoomController;
import Controller.ServiceController;
import UI.impl.*;


import java.util.ArrayList;

public class MenuBuilder {

    private final Menu rootMenu;
    private final RoomController roomController;
    private final ServiceController serviceController;
    private final GuestController guestController;

    public MenuBuilder(RoomController roomController, ServiceController serviceController, GuestController guestController) {
        rootMenu = new Menu("Root Menu", new ArrayList<>());
        this.roomController = roomController;
        this.serviceController = serviceController;
        this.guestController = guestController;
    }


    public void buildMenu() {
        Menu allRoomMenu = new Menu("allRoomMenu", new ArrayList<>());
        Menu allServiceMenu = new Menu("allServiceMenu", new ArrayList<>());
        Menu allGuestMenu = new Menu("allGuestMenu", new ArrayList<>());

        MenuItem exit = new MenuItem("Выход");
        exit.setAction(new ExitActionImpl());
        exit.setNextMenu(null);
        rootMenu.getMenuItems().add(exit);
        allRoomMenu.getMenuItems().add(exit);
        allServiceMenu.getMenuItems().add(exit);
        allGuestMenu.getMenuItems().add(exit);

        MenuItem addRoom = new MenuItem("Добавить комнату");
        addRoom.setAction(new AddRoomActionImpl(roomController));
        addRoom.setNextMenu(allRoomMenu);
        allRoomMenu.getMenuItems().add(addRoom);

        MenuItem addService = new MenuItem("Добавить сервис");
        addService.setAction(new AddServiceActionImpl(serviceController));
        addService.setNextMenu(allServiceMenu);
        allServiceMenu.getMenuItems().add(addService);

        MenuItem bookRoom = new MenuItem("Забронировать комнату");
        bookRoom.setAction(new BookRoomActionImpl(guestController, roomController));
        bookRoom.setNextMenu(allGuestMenu);
        allGuestMenu.getMenuItems().add(bookRoom);

        MenuItem useService = new MenuItem("Воспользоватся сервисом");
        useService.setAction(new UseServiceActionImpl(guestController, serviceController));
        useService.setNextMenu(allGuestMenu);
        allGuestMenu.getMenuItems().add(useService);


        MenuItem leaveHotel = new MenuItem("Выехать из отеля");
        leaveHotel.setAction(new LeaveHotelActionImpl(guestController, roomController));
        leaveHotel.setNextMenu(allGuestMenu);
        allGuestMenu.getMenuItems().add(leaveHotel);


        MenuItem getaBillForRoom = new MenuItem("Получить чек за комнату");
        getaBillForRoom.setAction(new GetBillActionImpl(guestController, roomController));
        getaBillForRoom.setNextMenu(allGuestMenu);
        allGuestMenu.getMenuItems().add(getaBillForRoom);

        MenuItem sortGuest = new MenuItem("Отсортировать гостей");
        sortGuest.setAction(new SortGuestActionImpl(guestController));
        sortGuest.setNextMenu(allGuestMenu);
        allGuestMenu.getMenuItems().add(sortGuest);

        MenuItem changeRoomCondition = new MenuItem("Изменить информацию о комнате");
        changeRoomCondition.setAction(new ChangeRoomConditionImpl(roomController));
        changeRoomCondition.setNextMenu(allRoomMenu);
        allRoomMenu.getMenuItems().add(changeRoomCondition);

        MenuItem sortRoom = new MenuItem("Отсортировать комнаты");
        sortRoom.setAction(new SortRoomActionImpl(roomController));
        sortRoom.setNextMenu(allRoomMenu);
        allRoomMenu.getMenuItems().add(sortRoom);

        MenuItem sortFreeRoom = new MenuItem("Отсортировать свободные комнаты");
        sortFreeRoom.setAction(new sortFreeRoomActionImpl(roomController));
        sortFreeRoom.setNextMenu(allRoomMenu);
        allRoomMenu.getMenuItems().add(sortFreeRoom);

        MenuItem getInfoAboutRoom = new MenuItem("Получить информацию по комнате");
        getInfoAboutRoom.setAction(new GetInfoRoom(roomController));
        getInfoAboutRoom.setNextMenu(allRoomMenu);
        allRoomMenu.getMenuItems().add(getInfoAboutRoom);

        MenuItem cheackRoomFree = new MenuItem("Узнать свободна ли комната на дату");
        cheackRoomFree.setAction(new CheackRoomIsFreeImpl(roomController));
        cheackRoomFree.setNextMenu(allRoomMenu);
        allRoomMenu.getMenuItems().add(cheackRoomFree);

        MenuItem getThreeLastGuest = new MenuItem("Получить последних гостей в комнате");
        getThreeLastGuest.setAction(new getThreeLastGuestImpl(roomController));
        getThreeLastGuest.setNextMenu(allRoomMenu);
        allRoomMenu.getMenuItems().add(getThreeLastGuest);


        MenuItem managingServices = new MenuItem("Сортировать услуги по цене");
        managingServices.setAction(new managinServiceActionImpl(serviceController));
        managingServices.setNextMenu(allServiceMenu);
        allServiceMenu.getMenuItems().add(managingServices);

        MenuItem changePriceService = new MenuItem("Поменять цену у сервиса");
        changePriceService.setAction(new ChangePriceServiceImpl(serviceController));
        changePriceService.setNextMenu(allServiceMenu);
        allServiceMenu.getMenuItems().add(changePriceService);

        MenuItem backToMainMenu = new MenuItem("Вернуться назад");
        backToMainMenu.setAction(null);
        backToMainMenu.setNextMenu(rootMenu);
        allRoomMenu.getMenuItems().add(backToMainMenu);
        allGuestMenu.getMenuItems().add(backToMainMenu);
        allServiceMenu.getMenuItems().add(backToMainMenu);

        MenuItem roomMenu = new MenuItem("Управление комнатами");
        roomMenu.setAction(null);
        roomMenu.setNextMenu(allRoomMenu);
        rootMenu.getMenuItems().add(roomMenu);

        MenuItem guestMenu = new MenuItem("Управление гостями");
        guestMenu.setAction(null);
        guestMenu.setNextMenu(allGuestMenu);
        rootMenu.getMenuItems().add(guestMenu);

        MenuItem serviceMenu = new MenuItem("Управление сервисами");
        serviceMenu.setAction(null);
        serviceMenu.setNextMenu(allServiceMenu);
        rootMenu.getMenuItems().add(serviceMenu);

    }

    public Menu getRootMenu() {
        return rootMenu;
    }
}
