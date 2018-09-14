package com.itship.pashketbot;

import com.itship.pashketbot.botProcessing.PashketBot;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContext;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.exceptions.TelegramApiException;

@SpringBootApplication
public class PashketbotApplication {

    private static String PROXY_HOST = "..." /* proxy host */;
    private static Integer PROXY_PORT = 3128 /* proxy port */;
    private static String PROXY_USER = "..." /* proxy user */;
    private static String PROXY_PASSWORD = "..." /* proxy password */;

	public static void main(String[] args) {


        // Initialize Api Context
        ApiContextInitializer.init();

        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Register our bot
        try {
            botsApi.registerBot(new PashketBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        //Add this line to initialize bots context
        SpringApplication.run(PashketbotApplication.class, args);

	}

	private void setProxyVsFkinRKN(){

    }
}
