import java.io.IOException;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        //Point of entry. Result of execution is represented in report.xlsx file
        new TasksExecutor().execute(8, 1_000_0);
    }

}
