package com.example.homework;

import android.provider.Settings;
import java.sql.DriverManager;

import  java.sql.Connection;
public class clsConnectionPG {
    Connection connection=null;
    private final String host = "dbtestproduct.cahbgoobadac.us-east-2.rds.amazonaws.com";
    private final String database = "postgres";
    private final int port = 5432;
    private final String user = "postgres";
    private final String pass = "test1234";
    private String url = "jdbc:postgresql://%s:%d/%s";
    private boolean status;

    public Connection Database() {

            this.url = String.format(this.url, this.host, this.port, this.database);
            connect();
            //this.disconnect();
            System.out.println("connection status:" + status);

        return null;
    }


    public void connect() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("org.postgresql.Driver");
                    connection = DriverManager.getConnection(url, user, pass);
                    status = true;
                    System.out.println("connected:" + status);
                } catch (Exception e) {
                    status = false;
                    System.out.print(e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
            this.status = false;
        }
    }



    public Connection getUser(String username, String password) {
        return connection;
    }
}
