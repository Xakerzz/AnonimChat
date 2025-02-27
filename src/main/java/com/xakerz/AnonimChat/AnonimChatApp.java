package com.xakerz.AnonimChat;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AnonimChatApp extends TelegramLongPollingBot {
    private static final Map<Long, Client> clients = new HashMap<>();
    private static ArrayList<String> chatStringsArray = new ArrayList<>();

    @Override
    public void onUpdateReceived(Update update) {
        Client client;
        Message message = update.getMessage();
        String inPut;
        String outPut;

        if (update.hasMessage() && update.getMessage().hasText()) {
            if (clients.containsKey(message.getChatId())) {
                client = clients.get(message.getChatId());
                String name = message.getFrom().getFirstName();
                String nickName = "@" + message.getFrom().getUserName();


                if (!name.equals(client.getName())) {
                    client.setName(name);
                } else if (!nickName.equals(client.getNickName())) {
                    client.setNickName(nickName);
                }


            } else {
                String name = update.getMessage().getFrom().getFirstName();
                String nickName = update.getMessage().getFrom().getUserName();
                Long idClient = update.getMessage().getFrom().getId();

                client = new Client(name, nickName, idClient, false);
                clients.put(client.getIdClient(), client);
            }
            if (message.getText().equals("/start")) {
                if (!chatStringsArray.isEmpty()) {
                    for (String s : chatStringsArray) {
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setText(s);
                        sendMessage.setChatId(client.getIdClient());
                        try {
                            execute(sendMessage);
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
            inPut = message.getText();
            outPut ="ID " + client.getId() + ": " + inPut;
            if (message.getText().equals("/start")) {

                    outPut = outPut.replace("/start", "Всем привет\uD83D\uDC4B");

            }
                for (Long clientId : clients.keySet()) {
                    if (client.getIdClient() == clientId) {
                        continue;
                    }

                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setText(outPut);
                    sendMessage.setChatId(clientId);
                    chatStringsArray.add(outPut);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

    }

    @Override
    public String getBotUsername() {
        return BotConfig.getBOT_NAME();
    }

    @Override
    public String getBotToken() {
        return BotConfig.getBOT_TOKEN();
    }
}
