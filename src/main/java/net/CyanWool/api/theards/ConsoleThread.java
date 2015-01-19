package net.CyanWool.api.theards;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import net.CyanWool.CyanServer;

public class ConsoleThread extends Thread {

    private CyanServer server;
    private boolean running;

    public ConsoleThread(CyanServer server) {
        this.server = server;
        this.running = true;
        setName("CyanConsole");
    }

    public void shutdown() {
        this.running = false;
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));// Console...
        while (running) {
            // Maybe...
            try {
                String line = reader.readLine();
                server.getCommandManager().dispatchCommand(server.getConsoleCommandSender(), line);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
