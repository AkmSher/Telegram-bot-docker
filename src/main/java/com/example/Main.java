package com.example;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
        try {
            // Инициализация API
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            
            // Регистрация нашего бота
            botsApi.registerBot(new SimpleEchoBot());
            
            System.out.println("Bot started successfully!");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
