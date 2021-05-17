package org.project.UI;


import java.util.List;


public class Navigator {

  private Menu currentMenu;
  private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Navigator.class);

  public Navigator(Menu currentMenu) {
    this.currentMenu = currentMenu;
  }

  public void printMenu() {
    for (int i = 0; i < currentMenu.getMenuItems().size(); i++) {
      System.out.println(i + " " + currentMenu.getMenuItems().get(i).getTitle());
    }

  }

  public void navigate(Integer index) throws Exception {
    List<MenuItem> items = currentMenu.getMenuItems();
    if (index <= items.size()) {
      if (items.get(index).getAction() == null) {
        currentMenu = items.get(index).getNextMenu();
        printMenu();
      } else {
        items.get(index).doAction();
      }
    } else {
      log.warn("Неверно указанный индекс");
    }
  }

}
