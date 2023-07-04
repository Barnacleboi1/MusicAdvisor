package advisor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        boolean authorized = false;
        loop: while (true) {
            String input = scanner.nextLine();
            switch (input.toLowerCase()) {
                case "featured" -> {
                    if (authorized) {
                        System.out.println("""
                                ---FEATURED---
                                Mellow Morning
                                Wake Up and Smell the Coffee
                                Monday Motivation
                                Songs to Sing in the Shower""");
                    } else {
                        System.out.println("Please, provide access for application.\n");
                    }
                }
                case "new" -> {
                    if (authorized) {
                        System.out.println("""
                                ---NEW RELEASES---
                                Mountains [Sia, Diplo, Labrinth]
                                Runaway [Lil Peep]
                                The Greatest Show [Panic! At The Disco]
                                All Out Life [Slipknot]""");
                    } else {
                        System.out.println("Please, provide access for application.\n");
                    }
                }
                case "playlists mood" -> {
                    if (authorized) {
                        System.out.println("""
                                ---MOOD PLAYLISTS---
                                Walk Like A Badass \s
                                Rage Beats \s
                                Arab Mood Booster \s
                                Sunday Stroll""");
                    } else {
                        System.out.println("Please, provide access for application.\n");
                    }
                }
                case "categories" -> {
                    if (authorized) {
                        System.out.println("""
                                ---CATEGORIES---
                                Top Lists
                                Pop
                                Mood
                                Latin""");
                    } else {
                        System.out.println("Please, provide access for application.\n");
                    }
                }
                case "auth" -> {
                    System.out.println("https://accounts.spotify.com/authorize?client_id=a19ee7dbfda443b2a8150c9101bfd645&redirect_uri=http://localhost:8080&response_type=code\n");
                    System.out.println("---SUCCESS---");
                    authorized = true;
                }
                case "exit" -> {
                    System.out.println("---GOODBYE!---");
                    break loop;
                }
            }
        }
    }
}
