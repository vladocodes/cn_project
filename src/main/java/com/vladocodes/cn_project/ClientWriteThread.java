package com.vladocodes.cn_project;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientWriteThread extends Thread {
    private final String username;
    private PrintWriter toServer;

    ClientWriteThread(Socket socket, String username) {
        this.username = username;

        try {
            this.toServer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException ex) {
            System.out.println("Error getting output stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        // Then send input to server line by line, until exit
        try (Scanner sc = new Scanner(System.in)) {
            String userInput;
            System.out.println("Opcije: igraj [1] i posmatraj [2]");
            System.out.print("[?] Izaberite opciju:");

            do {
                userInput = sc.nextLine();

                if (userInput.equals("1")) {
                    System.out.print("[?] Unesite ime igraca:");
                    userInput = "igraj-" + sc.nextLine();

                } else if (userInput.equals("2")) {
                    System.out.print("[?] Unesite ID igre:");
                    userInput = "gledaj-" + sc.nextLine();

                }

                toServer.println(userInput);
            } while (!userInput.equals("exit"));
        }
    }
}
