package org.example.Configurate;

import org.example.MyScheduler.MyScheduler;
import org.example.Bot.LaunchPoolBot;
import org.example.ReaderModel.LaunchPoolInfo;
import org.example.ReaderModel.LaunchPoolReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Читання даних з файлу
        LaunchPoolReader launchPoolReader = new LaunchPoolReader();
        List<LaunchPoolInfo> pools = launchPoolReader.loadLaunchPools("C:\\Users\\Админ\\IdeaProjects\\BybitBot\\src\\main\\resources\\launch_pools.txt");

        LaunchPoolBot bot = context.getBean(LaunchPoolBot.class);
        bot.setPools(pools);

        // Вивід інформації про пули в консоль та Telegram
        bot.printPools();

        // Запускаємо планувальник задач
        new MyScheduler(bot); // Якщо ви використовуєте планувальник в окремому класі
        System.out.println("Бот успішно запущено!");
    }
}
