package business.control;

public class ControllerFacadeFactory {
    public static ControllerFacade getFacade() {
        return ControllerFacade.getInstance();
    }
}
