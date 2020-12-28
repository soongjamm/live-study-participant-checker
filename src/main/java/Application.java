import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException {

        Controller controller = new Controller();

        try {
            controller.run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
