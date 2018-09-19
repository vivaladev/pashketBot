package com.itship.pashketbot;

import com.itship.pashketbot.bot.PashketBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;

public class Application {
    public static void main(String[] args) {
        // Initializes dependencies necessary for the base bot - Guice
        ApiContextInitializer.init();

        // Create the TelegramBotsApi object to register your bots
        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            // Register your newly created AbilityBot
            botsApi.registerBot((LongPollingBot) new PashketBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
