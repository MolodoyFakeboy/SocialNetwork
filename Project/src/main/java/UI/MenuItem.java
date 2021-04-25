package src.main.java.UI;

public class MenuItem {

  private String title;
  private IAction action;
  private Menu nextMenu;

  public MenuItem(String title) {
    this.title = title;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public IAction getAction() {
    return action;
  }

  public void setAction(IAction action) {
    this.action = action;
  }

  public Menu getNextMenu() {
    return nextMenu;
  }

  public void setNextMenu(Menu nextMenu) {
    this.nextMenu = nextMenu;
  }

  public void doAction() throws Exception {
    action.execute();
  }
}
