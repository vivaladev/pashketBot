package com.itship.pashketbot.handlers;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface MessageHandler {

    boolean execute(final Message message, final AbsSender sender) throws TelegramApiException;

}
