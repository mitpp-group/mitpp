import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        //Point of entry. Result of execution is represented in report.xlsx file
        //new TasksExecutor().execute(8, 1_000_0);
        //lab 2 task 2 entry point. Output in transform_report.xlsx
       new ListTransformer(100).execute();

    }
}
