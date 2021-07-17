package player;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

class addMusicToQueue implements Runnable {

    private final Music music;

    public addMusicToQueue(Music music, Queue<Music> queue) {
        queue.add(music);
        this.music = music;
    }

    @Override
    public void run() {
        System.out.printf("%s foi colocada na fila\n", music.name);
    }
}

class removeMusicFromQueue implements Runnable {

    private final Music music;

    public removeMusicFromQueue(Music music, Queue<Music> queue) {
        queue.remove(music);
        this.music = music;
    }

    @Override
    public void run() {
        System.out.printf("%s foi removida na fila\n", music.name);
    }
}

class playCurrentMusic implements Runnable {

    private final Music music;
    public playCurrentMusic(Music music) {
        this.music = music;

    }

    @Override
    public void run() {
        System.out.printf("%s está tocando\n", music.name);
        int musicDuration = music.duration;
        for (int i = 0; i <= musicDuration; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Musica Pausada");
                return;
            }
            //TESTE DE EXECUÇÃO DA MUSICA NO COMENTARIO ABAIXO
            System.out.println(i + "seg");
        }
    }

}

/*class pauseCurrentMusic implements Runnable {

    private Thread currentMusic;

    public pauseCurrentMusic(Thread currentMusic) {
        this.currentMusic = currentMusic;
    }

    @Override
    public void run() {
        while (true) {
            try {
                currentMusic.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}*/
/*class pauseCurrentMusic implements Runnable{

}*/
/*class pauseCurrentMusic implements Runnable{

}*/

public class Player {
    public static void main(String[] args) throws InterruptedException {

        ReentrantLock lock = new ReentrantLock();
        Scanner in = new Scanner(System.in);

        LinkedList<Music> musicQueue = new LinkedList<>(); // LISTA DE REPRODUÇÃO
        String command = "";
        int currentMusicId = 0; // INDICE DA MUSICA DA LISTA QUE ESTÁ SENDO TOCADA
        boolean musicPlaying = false; // INDICA SE A MUSICA ATUAL ESTA SENDO TOCADA OU NÃO
        boolean musicPaused = false; // INDICA SE A MUSICA FOI PAUSADA
        Thread currentMusic = null; // LINHA DE EXECUÇÃO DA MUSICA ATUAL
        Thread currentMusicPaused = null;

        while (!command.equals("CLOSE")) {
            command = in.nextLine();


            if (!musicQueue.isEmpty()) {
                currentMusic = new Thread(new playCurrentMusic(musicQueue.get(currentMusicId)));
            }

            //PLAY
            if (command.equals("PLAY")) {
                if (!musicPaused) {
                    currentMusic.start();
                    musicPlaying = true;
                    musicPaused = false;
                }
            }

            //PAUSE
            if (command.equals("PAUSE")) {

                currentMusic.interrupt();
                musicPaused = true;
                musicPlaying = false;
                System.out.println("/// Musica pausada ///");
            }

            //NEXT
            if (command.equals("NEXT")) {
                currentMusicId++;

            }
            //PREVIOUS

            //ADICIONAR MUSICA À LISTA DE REPRODUÇÃO
            if (command.equals("ADD")) {
                System.out.print("Nome: ");
                String name = in.nextLine();

                System.out.print("Cantor: ");
                String artist = in.nextLine();

                System.out.print("Album: ");
                String album = in.nextLine();

                System.out.print("Duração: ");
                int duration = in.nextInt();

                Music music = new Music(name, artist, album, duration);
                Thread t = new Thread(new addMusicToQueue(music, musicQueue));
                t.start();
            }

            //REMOVER MUSICA DA LISTA DE REPRODUÇÃO
            if (command.equals("REMOVE")) {
                //System.out.println("Digite o nome da música que deseja remover da fila");
                for (int i = 0; i < musicQueue.size(); i++) {
                    System.out.printf("%s %d\n", musicQueue.get(i).name, i);
                }
                System.out.println("Digite o númeoro correspondente a musica que deseja remover: ");
                Music music = musicQueue.get(in.nextInt());
                Thread t = new Thread(new removeMusicFromQueue(music, musicQueue));
                t.start();
            }

            //VISUALIZAR A LISTA DE REPRODUÇÃO
            if (command.equals("CHECK")) {
                for (int i = 0; i < musicQueue.size(); i++) {
                    System.out.printf("%s %d\n", musicQueue.get(i).name, i);
                }
            }

        }
        for (int i = 0; i < musicQueue.size(); i++) {
            System.out.printf("%s %d\n", musicQueue.get(i).name, i);
        }
        in.close();
    }
}

