package com.xakerz.AnonimChat;

import java.util.HashMap;
import java.util.Map;

public class Client {
    private String name;
    private String nickName;
    private long idClient;
    private final int id;
    static private int idCounter = 0;

    public int getId() {
        return id;
    }

    public Client() {
        idCounter++;
        this.id = idCounter;
    }

    public Client(String name, String nickName, long idClient, boolean questionToAdmin) {
        this.name = name;
        this.nickName = "@" + nickName;
        this.idClient = idClient;


       idCounter++;
        this.id = idCounter;
    }





    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }


}
