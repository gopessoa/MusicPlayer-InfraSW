package player;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

class addMusicToQueue implements Runnable{

    private final Music music;
    public addMusicToQueue(Music music, Queue<Music> queue){
        queue.add(music);
        this.music = music;
    }

    @Override
    public void run() {
        System.out.printf("%s foi colocada na fila\n", music.name);
    }
}
class removeMusicFromQueue implements Runnable{

    private final Music music;
    public removeMusicFromQueue(Music music, Queue<Music> queue){
        queue.remove(music);
        this.music = music;
    }

    @Override
    public void run() {
        System.out.printf("%s foi removida na fila\n", music.name);
    }
}

public class Player {
    public static void main(String[] args) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        Queue<Music> musicQueue = new LinkedList<>();
        //playlist contendo musicas que podem ser adicionas á fila de reprodução
        //Music [] playlist = new Music[5];
        //Queue<Thread> threads = new LinkedList<>();
        String command = "";

        while(!command.equals("CLOSE")){
            command = in.nextLine();

            if (command.equals("ADD")){
                //System.out.println("Digite o nome da música que deseja adicionar à fila")
                System.out.print("Nome: "); String name = in.nextLine();

                System.out.print("Cantor: "); String artist = in.nextLine();

                System.out.print("Album: "); String album = in.nextLine();

                System.out.print("Duração: "); int duration = in.nextInt();

                Music music = new Music( name, artist, album, duration);
                Thread t = new Thread(new addMusicToQueue(music, musicQueue));
                t.start();
            }
            if (command.equals("REMOVE")){
                //System.out.println("Digite o nome da música que deseja remover da fila");
                for (int i=0;i < musicQueue.size(); i++){
                    System.out.printf("%s %d\n", ((LinkedList<Music>) musicQueue).get(i).name, i);
                }
                System.out.println("Digite o númeoro correspindente a musica que deseja remover: ");
                Music music = ((LinkedList<Music>) musicQueue).get(in.nextInt());
                Thread t = new Thread(new removeMusicFromQueue(music, musicQueue));
                t.start();
            }
            if (command.equals("CHECK")){
                for (int i=0;i < musicQueue.size(); i++){
                    System.out.printf("%s %d\n", ((LinkedList<Music>) musicQueue).get(i).name, i);
                }
            }
        }
        for (int i=0;i < musicQueue.size(); i++){
            System.out.printf("%s %d\n", ((LinkedList<Music>) musicQueue).get(i).name, i);
        }
        in.close();
    }
}

