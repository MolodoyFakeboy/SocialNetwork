package UI;

import Modules.ApplicationContext;
import Controller.*;
import UI.impl.*;


import java.util.ArrayList;

public class MenuBuilder {

    private final Menu rootMenu;
    private IRoomController roomController;
    private IServiceController serviceController;
    private IGuestController guestController;
    private ApplicationContext context;

    public MenuBuilder(IRoomController roomController, IServiceController serviceController, IGuestController guestController, ApplicationContext context) {
        rootMenu = new Menu("Root Menu", new ArrayList<>());
        this.roomController = roomController;
        this.serviceController = serviceController;
        this.guestController = guestController;
        this.context = context;
    }


    public void buildMenu() {
        Menu allRoomMenu = new Menu("allRoomMenu", new ArrayList<>());
        Menu allServiceMenu = new Menu("allServiceMenu", new ArrayList<>());
        Menu allGuestMenu = new Menu("allGuestMenu", new ArrayList<>());

        MenuItem exit = new MenuItem("Выход");
        exit.setAction(new ExitActionImpl(context));
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


        MenuItem getNumberGuest = new MenuItem("Получить колличество гостей");
        getNumberGuest.setAction(new getNumberGuestImpl(guestController));
        getNumberGuest.setNextMenu(allGuestMenu);
        allGuestMenu.getMenuItems().add(getNumberGuest);

        MenuItem sortUsingServicePrice = new MenuItem("Отсортировать использованный гостем услуги по цене");
        sortUsingServicePrice.setAction(new sortUsingServicePriceImpl(guestController));
        sortUsingServicePrice.setNextMenu(allGuestMenu);
        allGuestMenu.getMenuItems().add(sortUsingServicePrice);

        MenuItem sortUsingServiceTime = new MenuItem("Отсортировать использованный гостем услуги по времени");
        sortUsingServiceTime.setAction(new sortUsingServiceTimeImpl(guestController));
        sortUsingServiceTime.setNextMenu(allGuestMenu);
        allGuestMenu.getMenuItems().add(sortUsingServiceTime);

        MenuItem changeRoomStatus = new MenuItem("Поменять статус у комнаты");
        changeRoomStatus.setAction( new changeRoomStatusImpl(roomController));
        changeRoomStatus.setNextMenu(allRoomMenu);
        allRoomMenu.getMenuItems().add(changeRoomStatus);

        MenuItem changeRoomPrice = new MenuItem("Поменять цену у комнаты");
        changeRoomPrice.setAction( new changeRoomPriceImpl(roomController));
        changeRoomPrice.setNextMenu(allRoomMenu);
        allRoomMenu.getMenuItems().add(changeRoomPrice);

        MenuItem sortRoomPrice = new MenuItem("Отсортировать комнаты по цене");
        sortRoomPrice.setAction(new sortRoomforPriceActionImpl(roomController));
        sortRoomPrice.setNextMenu(allRoomMenu);
        allRoomMenu.getMenuItems().add(sortRoomPrice);

        MenuItem sortRoomBed = new MenuItem("Отсортировать комнаты по кроватям");
        sortRoomBed.setAction(new sortRoomforBedActionImpl(roomController));
        sortRoomBed.setNextMenu(allRoomMenu);
        allRoomMenu.getMenuItems().add(sortRoomBed);

        MenuItem sortRoomStart = new MenuItem("Отсортировать комнаты по звездам");
        sortRoomStart.setAction(new sortRoomforStarsActionImpl(roomController));
        sortRoomStart.setNextMenu(allRoomMenu);
        allRoomMenu.getMenuItems().add(sortRoomStart);


        MenuItem sortFreeRoomforPrice = new MenuItem("Отсортировать свободные комнаты по цене");
        sortFreeRoomforPrice.setAction(new sortFreeRoomforPriceActionImpl(roomController));
        sortFreeRoomforPrice.setNextMenu(allRoomMenu);
        allRoomMenu.getMenuItems().add(sortFreeRoomforPrice);

        MenuItem sortFreeRoomBed = new MenuItem("Отсортировать свободные комнаты по кроватям");
        sortFreeRoomBed.setAction(new sortFreeRoomBedActionImpl(roomController));
        sortFreeRoomBed.setNextMenu(allRoomMenu);
        allRoomMenu.getMenuItems().add(sortFreeRoomBed);

        MenuItem sortFreeRoomStars = new MenuItem("Отсортировать свободные комнаты по звездам");
        sortFreeRoomStars.setAction(new sortFreeRoomStarsActionImpl(roomController));
        sortFreeRoomStars.setNextMenu(allRoomMenu);
        allRoomMenu.getMenuItems().add(sortFreeRoomStars);

        MenuItem getAmountFreeRoom = new MenuItem("Получить колличество свободных комнат");
        getAmountFreeRoom.setAction(new getAmountFreeRoomActionImpl(roomController));
        getAmountFreeRoom.setNextMenu(allRoomMenu);
        allRoomMenu.getMenuItems().add(getAmountFreeRoom);

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
