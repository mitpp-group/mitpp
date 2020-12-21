import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class IOBoundTask extends Thread{
    private long timesToRead;

    IOBoundTask(long timesToRead) {
        this.timesToRead = timesToRead;
    }

    @Override
    public void run() {
        try {
            for(int i = 0; i < timesToRead; i++){
                //Open and read file with data
                File file = new File("src/main/resources/file.txt");
                BufferedReader br = new BufferedReader(new FileReader(file));
                br.readLine();
//                System.out.println(br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
