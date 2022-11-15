package rc.sudokugenius;

import rc.mvc.FrontLoader;
import rc.sudokugenius.controllers.MainController;

public class Index extends FrontLoader {

    public static void main(String[] args) {
        startApp(new MainController());
    }
}
