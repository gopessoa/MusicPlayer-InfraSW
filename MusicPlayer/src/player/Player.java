package player;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

class addMusicToQueue implements Runnable{

    private final String music;
    public addMusicToQueue(String music, Queue<String> queue){
        queue.add(music);
        this.music = music;
    }

    @Override
    public void run() {
        System.out.printf("%s foi colocada na fila\n", music);
    }
}
class removeMusicFromQueue implements Runnable{

    private final String music;
    public removeMusicFromQueue(String music, Queue<String> queue){
        queue.remove(music);
        this.music = music;
    }

    @Override
    public void run() {
        System.out.printf("%s foi removida na fila\n", music);
    }
}

public class Player {
    public static void main(String[] args) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        Queue<String> musicQueue = new LinkedList<>();
        //Queue<Thread> threads = new LinkedList<>();
        String command = "";

        while(!command.equals("CLOSE")){
            command = in.next();

            if (command.equals("ADD")){
                //System.out.println("Digite o nome da música que deseja adicionar à fila");
                String music = in.next();
                Thread t = new Thread(new addMusicToQueue(music, musicQueue));
                t.start();
            }
            if (command.equals("REMOVE")){
                //System.out.println("Digite o nome da música que deseja remover da fila");
                String music = in.next();
                Thread t = new Thread(new removeMusicFromQueue(music, musicQueue));
                t.start();
            }
            if (command.equals("CHECK")){
                System.out.println(musicQueue);
            }
        }
        System.out.println(musicQueue);
        in.close();
    }
}

