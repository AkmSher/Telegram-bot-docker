package com.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SimpleEchoBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        // Имя пользователя бота можно также вынести в ENV, но для примера оставим хардкод или ENV
        return System.getenv().getOrDefault("BOT_USERNAME", "MyDockerTestBot");
    }

    @Override
    public String getBotToken() {
        // Токен берется строго из переменных окружения для безопасности
        return System.getenv("BOT_TOKEN");
    }

    @Override
    public void onUpdateReceived(Update update) {
        // Проверяем, что пришло сообщение и в нем есть текст
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            System.out.println("Received message: " + messageText + " from: " + chatId);

            sendMessage(chatId, "Привет из Москвы! Вы написали: " + messageText);
        }
    }

    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);

        try {
            execute(message); // Отправка сообщения
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
