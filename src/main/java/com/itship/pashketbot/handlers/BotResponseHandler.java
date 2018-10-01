package com.itship.pashketbot.handlers;

import com.itship.pashketbot.utils.LocalizedMessage;
import com.itship.pashketbot.utils.MessageProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class BotResponseHandler extends AbstractHandler implements MessageHandler {

    protected BotResponseHandler(MessageProvider provider) {
        super(provider);
    }

    @Override
    public boolean execute(Message message, AbsSender sender) throws TelegramApiException {
        final String textMessage = message.getText();

        if (StringUtils.startsWithIgnoreCase(textMessage, "/hi")) {
            sendMessage(sender, message.getChatId(), LocalizedMessage.DEFAULT_MESSAGE);
            return true;
        }
        return false;
    }

}
