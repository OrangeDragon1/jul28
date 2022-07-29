package udemy.jul28;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class App {
    private static ArrayList<Album> albums = new ArrayList<Album>();

    public static void main(String[] args) {
        Album album = new Album("Blackpink In Your Area", "BLACKPINK");
        album.addSong("Boombayah", 4.1);
        album.addSong("Whistle", 3.5);
        album.addSong("Playing with Fire", 3.2);
        album.addSong("Stay", 3.8);
        album.addSong("As If It's Your Last", 3.6);
        album.addSong("Ddu-Du Ddu-Du", 3.5);
        album.addSong("Forever Young", 3.9);
        album.addSong("Really", 3.3);
        album.addSong("See U Later", 3.4);
        albums.add(album);

        album = new Album("The Album", "BLACKPINK"); // new album has been created
        album.addSong("How You Like That", 3.0);
        album.addSong("Ice Cream", 2.9);
        album.addSong("Pretty Savage", 3.4);
        album.addSong("Bet You Wanna", 2.6);
        album.addSong("Lovesick Girls", 3.2);
        album.addSong("Crazy Over You", 2.6);
        album.addSong("Love to Hate Me", 2.8);
        album.addSong("You Never Know", 3.8);
        albums.add(album);

        LinkedList<Song> playList = new LinkedList<>();
        albums.get(0).addToPlayList("Boombayah", playList);
        albums.get(0).addToPlayList("Stay", playList);
        albums.get(0).addToPlayList("ForeverYoung", playList); // string missing a space
        albums.get(0).addToPlayList(5, playList);
        albums.get(0).addToPlayList(11, playList);
        albums.get(1).addToPlayList(2, playList);
        albums.get(1).addToPlayList(16, playList);

        play(playList);
    }

    private static void play(LinkedList<Song> playList) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        boolean forward = true;
        ListIterator<Song> listIterator = playList.listIterator();
        if (playList.size() == 0) {
            System.out.println("No songs in playlist");
            return;
        } else {
            System.out.println("Now playing " + listIterator.next().toString());
            printMenu();
        }

        while (!quit) {
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 0:
                    System.out.println("Playlist complete");
                    quit = true;
                    break;
                case 1:
                    if (!forward) {
                        if (listIterator.hasNext()) {
                            listIterator.next();
                        }
                        forward = true;
                    }
                    if (listIterator.hasNext()) {
                        System.out.println("Now playing " + listIterator.next().toString());
                    } else {
                        System.out.println("We have reached the end of the playlist");
                        forward = false;
                    }
                    break;
                case 2:
                    if (forward) {
                        if (listIterator.hasPrevious()) {
                            listIterator.previous();
                        }
                        forward = false;
                    }
                    if (listIterator.hasPrevious()) {
                        System.out.println("Now playing " + listIterator.previous().toString());
                    } else {
                        System.out.println("We have reached the start of the playlist");
                        forward = true;
                    }
                    break;
                case 3:
                    if (forward) {
                        if (listIterator.hasPrevious()) {
                            System.out.println("Now replaying " + listIterator.previous().toString());
                            forward = false;
                        } else {
                            System.out.println("We are at the start of the list");
                        }
                    } else {
                        if (listIterator.hasNext()) {
                            System.out.println("Now replaying " + listIterator.next().toString());
                            forward = true;
                        } else {
                            System.out.println("We have reached the end of the list");
                        }
                    }
                    break;
                case 4:
                    printList(playList);
                    break;
                case 5:
                    printMenu();
                    break;
                case 6:
                    if(playList.size() > 0) {
                        listIterator.remove();
                        if (listIterator.hasNext()) {
                            System.out.println("Now playing " + listIterator.next());
                        } else if (listIterator.hasPrevious()) {
                            System.out.println("Now playing " + listIterator.previous());
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("Avaiable actions:");
        System.out.println("0 - quit");
        System.out.println("1 - play next");
        System.out.println("2 - play previous");
        System.out.println("3 - replay current");
        System.out.println("4 - list songs in the playlist");
        System.out.println("5 - print available actions");
        System.out.println("6 - delete current song from playlist");
    }

    private static void printList(LinkedList<Song> playList) {
        Iterator<Song> iterator = playList.iterator(); // using iterator instead of listiterator because we are not going foward and backward
        System.out.println("==============================");
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("==============================");
    }
}
