package org.example.ReaderModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LaunchPoolReader {
    // Метод для завантаження інформації про пули з файлу
    public List<LaunchPoolInfo> loadLaunchPools(String filePath) {
        List<LaunchPoolInfo> pools = new ArrayList<>(); // Список для зберігання інформації про пули
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Читаємо файл рядок за рядком
            while ((line = br.readLine()) != null) {
                // Пропускаємо пусті рядки
                if (line.trim().isEmpty()) {
                    continue; // Якщо рядок пустий, переходимо до наступного
                }
                // Розбиваємо рядок по комах, враховуючи лапки
                String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                // Перевіряємо, що масив має потрібну кількість елементів
                if (parts.length == 5) {
                    String exchange = parts[0].trim(); // Обмін
                    String launchPool = parts[1].trim(); // Назва пулу
                    String poolsString = parts[2].trim().replace("\"", ""); // Убираємо зайві лапки
                    String period = parts[3].trim().replace("\"", ""); // Період
                    String status = parts[4].trim().replace("\"", ""); // Статус

                    // Додаємо новий об'єкт LaunchPoolInfo до списку
                    pools.add(new LaunchPoolInfo(exchange, launchPool, poolsString, period, status));
                } else {
                    // Виводимо повідомлення про неправильний формат рядка
                    System.err.println("Невірний формат рядка: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Виводимо стек трейс у разі виключення
        }
        return pools; // Повертаємо список пулів
    }
}
