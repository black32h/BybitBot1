package org.example.Bot;

import org.example.ReaderModel.LaunchPoolInfo;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
public class LaunchPoolBot extends TelegramLongPollingBot {

    private List<LaunchPoolInfo> pools;

    @Override
    public void onUpdateReceived(Update update) {
        // Логіка обробки оновлень
    }

    @Override
    public String getBotUsername() {
        return "Cryptouser_73bot"; //  ім'я  бота
    }

    @Override
    public String getBotToken() {
        return "7645000805:AAHq2WHmEOGTncILPMZ9ThRg1k8-pVBySnY"; //  токен  бота
    }

    public void setPools(List<LaunchPoolInfo> pools) {
        this.pools = pools;
    }

    public void printPools() {
        for (LaunchPoolInfo pool : pools) {
            System.out.println(pool.toString());
            sendMessageToChat(pool.toString()); // Відправка повідомлення в чат
        }
    }

    private void sendMessageToChat(String message) {
        Long chatId = 985667869L; //  ID чату

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(message);

        try {
            execute(sendMessage); // Використовуэться метод execute для відправки повідомлення
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void notifyActivePools() {
        List<LaunchPoolInfo> activePools = getActivePools();
        if (activePools.isEmpty()) {
            sendMessageToChat("На даний момент немає активних пулів.");
        } else {
            for (LaunchPoolInfo pool : activePools) {
                sendMessageToChat(pool.toString());
            }
        }
    }

    private List<LaunchPoolInfo> getActivePools() {
        return pools.stream()
                .filter(pool -> pool.getStatus().equalsIgnoreCase("Активний")) // Перевірка статусу
                .toList();
    }

    private List<LaunchPoolInfo> getUpcomingPools() {
        return pools.stream()
                .filter(pool -> /* Логіка для перевірки, чи скоро почнеться пул */ false)
                .toList();
    }
}
