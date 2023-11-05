import java.io.InputStream;
import java.util.concurrent.atomic.AtomicBoolean;

public class Listener_Client implements Runnable{

    private InputStream in;
    private byte[] buffer = new byte[1024];
    private AtomicBoolean fine;

    public Listener_Client(InputStream in, AtomicBoolean fine){
        this.in = in;
        this.fine = fine;
    }
    

    @Override
    public void run() {
        try{
            while(true){
                int read = in.read(buffer);
                if(read<=0 || read>buffer.length){
                    Thread.sleep(100);
                    continue;
                }
                String input = new String(buffer, 0, read);
                System.out.println(input);
                if(input.contains("GAME OVER!") || input.contains("YOU WON!")){
                    fine.set(true);
                    break;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
