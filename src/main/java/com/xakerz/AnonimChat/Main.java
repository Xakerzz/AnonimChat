package com.xakerz.AnonimChat;


import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
       try {
           TelegramBotsApi bot = new TelegramBotsApi(DefaultBotSession.class);
           bot.registerBot(new AnonimChatApp());
       } catch (TelegramApiException e){
           System.out.println(e.getMessage());
       }
    }
}
