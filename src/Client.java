import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;


public class Client {


    public static void main(String[] args) throws Exception {


        try{
            Socket socket = new Socket("127.0.0.1",3000); 
            
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            byte[] buffer = new byte[1024];

            System.out.println(new String(buffer, 0, in.read(buffer)));
            Thread.sleep(100);
            System.out.println(new String(buffer, 0, in.read(buffer)));

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String name = br.readLine();

            out.write(name.getBytes()); //send name
            out.flush();

            System.out.println(new String(buffer, 0, in.read(buffer)));
            System.out.println(new String(buffer, 0, in.read(buffer)));

            AtomicBoolean fine = new AtomicBoolean(false);

            Thread listener = new Thread(new Listener_Client(in, fine));
            listener.start();
            
            while(!fine.get()){
                String input = br.readLine();
                out.write(input.getBytes());
                out.flush();
                Thread.sleep(100);
            }
            
            listener.join();
            socket.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}


