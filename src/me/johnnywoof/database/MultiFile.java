package me.johnnywoof.database;

import java.io.*;
import java.util.UUID;

public class MultiFile implements Database {

    @Override
    public UUID getUUID(String name) {

        if (name.length() > 16) {

            throw new IllegalArgumentException("Invalid name!");

        }

        File f = new File("accounts/" + name + ".txt");

        File dir = new File("accounts");

        if (!dir.exists()) {

            dir.mkdir();

        }

        if (f.exists()) {

            try {

                BufferedReader br = new BufferedReader(new FileReader(f));

                br.readLine();

                String l = br.readLine();

                br.close();

                return UUID.fromString(l);

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

        return null;

    }

    @Override
    public String getIP(String name) {

        if (name.length() > 16) {

            throw new IllegalArgumentException("Invalid name!");

        }

        File f = new File("accounts/" + name + ".txt");

        File dir = new File("accounts");

        if (!dir.exists()) {

            dir.mkdir();

        }

        if (f.exists()) {

            try {

                BufferedReader br = new BufferedReader(new FileReader(f));

                String l = br.readLine();

                br.close();

                return l;

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

        return null;

    }

    @Override
    public void updatePlayer(String name, String ip, UUID uuid) {

        if (name.length() > 16) {

            throw new IllegalArgumentException("Invalid name!");

        }

        File f = new File("accounts/" + name + ".txt");

        File dir = new File("accounts");

        if (!dir.exists()) {

            dir.mkdir();

        }

        if (f.exists()) {

            f.delete();

        }

        try {

            f.createNewFile();

            PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(f, true)));

            w.println(ip);
            w.println(uuid.toString());

            w.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    @Override
    public void resetCache() {
    }

    @Override
    public void close() {
    }

    @Override
    public void init(String host, int port, String databasename, String username, String password) {
    }

    @Override
    public DatabaseType getType() {
        return DatabaseType.MultiFile;
    }

}
