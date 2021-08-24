package com.system.project.model.user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    private int id;
    private String name;
    private String password;


    public User() {
    }


    public User(String name, String password) {
        this.name = name;
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            this.password = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }
            this.password = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
