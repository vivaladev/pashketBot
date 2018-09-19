package com.itship.pashketbot.bot;

import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class PashketBot extends TelegramLongPollingBot{

    private String botName;
    private String botToken;

    public PashketBot(@Value("${telegram.credentional.bot.name}") String botName,
                      @Value("${telegram.credentional.bot.token}") String botToken,
                      DefaultBotOptions defaultBotOptions) {
        super(defaultBotOptions);
        this.botName = botName;
        this.botToken = botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
