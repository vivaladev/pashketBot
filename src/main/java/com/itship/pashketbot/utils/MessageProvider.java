package com.itship.pashketbot.utils;

import com.itship.pashketbot.user.CurrentUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class MessageProvider {

    public String getMessage(LocalizedMessage message) {
        //TODO make localized messages regards of user's language settings
        /*final String langCode = CurrentUser.getCurrentUser().getLang();
        if (langCode != null && StringUtils.containsIgnoreCase(langCode, "ru")) {
            return message.getRusMes();
        }*/
        return message.getEngMes();
    }
}
