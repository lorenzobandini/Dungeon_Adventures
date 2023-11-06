import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;


public class Server {
    
    private static boolean potionboolean = false;
    private static boolean exit = false;
    private static int kills = 0;

    public static void main(String[] args) throws Exception {

        try (ServerSocket serverSocket = new ServerSocket(3000)) {
            Socket socket = serverSocket.accept();
            byte[] buffer = new byte[1024];
            System.out.println("Client " + socket.getInetAddress() +" connected");

            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            out.write("============================================================\n".getBytes());
            out.flush();
            out.write(("Welcome to the dungeon!\nChoose a name for your character:\n").getBytes());
            out.flush();

            Player player = new Player(new String(buffer, 0, in.read(buffer)));

            out.write((player.getName() + " let's start this adventure!\n").getBytes()); 
            out.flush();
            out.write("============================================================\n".getBytes());
            out.flush();
            
            while (!exit) {

                if(kills==15){
                    exit = true;
                    continue;
                }

                out.write(("You have " + player.getHealth() + "/"+player.getMaxHealth() + " health and " + String.format("%.2f",player.getPotions()) + " potions.\nWhat do you want to do?\n[1] Fight monster\n").getBytes());
                out.flush();
                if(potionboolean){
                    out.write("[2] Use potion\n[3] Exit\n".getBytes());
                    out.flush();
                }else{
                    out.write("[2] Exit\n".getBytes());
                    out.flush();
                }


                String input = new String(buffer, 0, in.read(buffer));

                out.write("================================================\n".getBytes());
                out.flush();

                if (input.equals("1")) {
                    Monster monster = new Monster();
                    out.write(("ROUND " + (kills+1) + "\n").getBytes());
                    out.flush();
                    out.write(("You encountered a monster with " + monster.getHealth() + " health!\n").getBytes());
                    out.flush();
                    combat(player, monster, in, out, buffer);
                    if(!(player.getPotions()==0.00)){
                        potionboolean = true;
                    }
                } else if (input.equals("2")) {
                    if(potionboolean){
                        if(player.getHealth()==player.getMaxHealth()){
                            out.write("You have already full health.\n".getBytes());
                            out.flush();
                            continue;
                        }
                        out.write(("You drank a bit of a potion and gained " + (int)Math.floor(player.usePotion()*50) +" health.\n").getBytes());
                        out.flush();
                        potionboolean = false;
                    }else{
                        exit = true;
                    }
                } else if (input.equals("3") && potionboolean) {
                    exit = true;
                } else {
                    out.write("Invalid input\n".getBytes());
                    out.flush();
                }

                out.write("================================================\n".getBytes());
                out.flush();
            }
            out.write(("You killed " + kills + " monsters!\n").getBytes());
            out.flush();
            if(kills<15){
                out.write("GAME OVER!\n".getBytes());
            }else{
                out.write(("CONGRATULATION "+player.getName().toUpperCase()+", YOU WON!\n").getBytes());
            }
            out.flush();
            out.write("================================================\n".getBytes());
            out.flush();
            out.write("Press `q` to exit.\n".getBytes());
            out.flush();
            while(!(new String(buffer,0,in.read(buffer)).equals("q"))){
                out.write("Press `q` to exit.\n".getBytes());
                out.flush();
            }
            out.write("Goodbye!\n".getBytes());
            out.flush();
            socket.close();
            serverSocket.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    private static void combat(Player player, Monster monster,InputStream in, OutputStream out, byte[] buffer) throws IOException {
        int turn = 1;
        while (player.getHealth() > 0 && monster.getHealth() > 0) {
            out.write(("ROUND " + (kills+1) + " - TURN " + turn + "\n").getBytes());
            out.write(("You have " + player.getHealth()+"/"+ player.getMaxHealth() + " health and " + String.format("%.2f",player.getPotions()) +" potions\nThe monster has " + monster.getHealth() +"/"+monster.getMaxHealth() +" health.\n").getBytes());
            out.flush();
            out.write(("You can deal at maximum "+ player.getMaxHealth() +" damage to the monster.\n").getBytes());
            out.write(("What do you want to do?\n[1] Attack\n[2] Use potion\n").getBytes());
            out.flush();
            String input = new String(buffer, 0, in.read(buffer));

            if(input.equals("1")){
                out.write(("You attack the monster and deal " + player.attack(monster) + " damage to it.\n").getBytes());
                out.flush();
            }else if(input.equals("2")){
                if(player.getPotions()==0.00){
                    out.write("You don't have any potions left.\n".getBytes());
                    out.flush();
                    continue;
                }else if(player.getHealth()==player.getMaxHealth()){
                    out.write("You have already full health.\n".getBytes());
                    out.flush();
                    continue;
                }else{
                    out.write(("You drank a bit of a potion and gained " + (int)Math.floor(player.usePotion()*50) +".\n").getBytes());
                    out.flush();
                }
            }else{
                out.write("Invalid input\n".getBytes());
                out.flush();
                continue;
            }

            if (monster.getHealth() > 0) {
                out.write(("The monster has " + monster.getHealth() +"/"+ monster.getMaxHealth() + " health left.\n").getBytes());
                out.flush();
                out.write(("The monster attacks you for " + monster.attack(player) + " damage.\n").getBytes());
                out.flush();
            }
            turn++;
            out.write("------------------------------------------------\n".getBytes());
        }
        if (player.getHealth() > 0) {
            out.write("The monster is dead.\n".getBytes());
            out.flush();
            out.write(("You won the fight!\n").getBytes());
            out.flush();
            int drop = monster.dropPotions();
            if(drop==0){
                out.write("The monster didn't drop any potions.\n".getBytes());
                out.flush();
            }else{
                player.addPotions(drop);
                out.write(("The monster dropped " + drop + " potions and now you have " + String.format("%.2f",player.getPotions()) + " potions.\n").getBytes());
                out.flush();
            }
            kills++;
        } else {
            out.write(("You lost the fight!\n").getBytes());
            out.flush();
            exit = true;
        }
    }
}
