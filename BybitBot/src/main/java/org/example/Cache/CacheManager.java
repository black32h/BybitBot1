package org.example.Cache;

import org.example.ReaderModel.LaunchPoolInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CacheManager {
    private static final String CACHE_FILE = "Cash.txt"; // Константа для імені файлу кешу
    private static final String ARCHIVE_FILE = "ArchivedPools.txt"; // Константа для імені файлу архіву

    // Метод для збереження пулів у кеш
    public void savePools(List<LaunchPoolInfo> pools) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CACHE_FILE))) {
            for (LaunchPoolInfo pool : pools) {
                writer.write(pool.toString()); // Записуємо рядок пулу у файл
                writer.newLine(); // Перехід на новий рядок
            }
        } catch (IOException e) {
            e.printStackTrace(); // Виводимо помилку, якщо не вдалося зберегти
        }
    }

    // Метод для завантаження пулів з кешу
    public List<LaunchPoolInfo> loadPools() {
        List<LaunchPoolInfo> pools = new ArrayList<>(); // Список для збереження пулів
        try (BufferedReader reader = new BufferedReader(new FileReader(CACHE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                LaunchPoolInfo pool = parseLaunchPoolInfo(line); // Парсимо рядок у об'єкт LaunchPoolInfo
                pools.add(pool); // Додаємо пул до списку
            }
        } catch (IOException e) {
            e.printStackTrace(); // Виводимо помилку, якщо не вдалося завантажити
        }
        return pools; // Повертаємо список пулів
    }

    // Метод для очищення кешу
    public void clearCache() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CACHE_FILE))) {
            writer.write(""); // Очищаємо файл, записуючи пустий рядок
        } catch (IOException e) {
            e.printStackTrace(); // Виводимо помилку, якщо не вдалося очистити кеш
        }
    }

    // Метод для архівування пулів
    public void archivePools() {
        try {
            // Читаємо існуючий кеш і архівуємо
            List<LaunchPoolInfo> pools = loadPools(); // Завантажуємо пулів з кешу
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVE_FILE, true))) {
                for (LaunchPoolInfo pool : pools) {
                    writer.write(pool.toString()); // Записуємо пул у файл архіву
                    writer.newLine(); // Перехід на новий рядок
                }
            }
            clearCache(); // Очищаємо кеш після архівування
        } catch (IOException e) {
            e.printStackTrace(); // Виводимо помилку, якщо не вдалося архівувати
        }
    }

    // Метод для видалення неактивних пулів
    public void removeInactivePools() {
        List<LaunchPoolInfo> pools = loadPools(); // Завантажуємо пулів з кешу
        List<LaunchPoolInfo> activePools = new ArrayList<>(); // Список для активних пулів

        // Фільтруємо неактивні пули (приклад: статус не рівний "Активний")
        for (LaunchPoolInfo pool : pools) {
            if (pool.getStatus().equalsIgnoreCase("Активний")) {
                activePools.add(pool); // Додаємо активний пул до списку
            }
        }

        // Зберігаємо тільки активні пули назад у кеш
        savePools(activePools); // Зберігаємо активні пули у кеш
    }

    // Метод для парсингу рядка у об'єкт LaunchPoolInfo
    private LaunchPoolInfo parseLaunchPoolInfo(String line) {
        line = line.trim(); // Обрізаємо пробіли

        String[] parts = line.split("\\s+"); // Розбиваємо рядок на частини по пробілам

        if (parts.length != 7) { // Перевіряємо, що масив має потрібну кількість елементів
            throw new IllegalArgumentException("Неверний формат строки: " + line); // Викидаємо помилку, якщо формат невірний
        }

        // Отримуємо дані з частин рядка
        String name = parts[0].split("=")[0]; // Назва пулу
        String percentage = parts[0].split("=")[1]; // Процент
        String startDateTime = parts[1] + " " + parts[2]; // Дата і час початку
        String endDateTime = parts[4] + " " + parts[5]; // Дата і час закінчення
        String timezone = parts[6]; // Часовий пояс

        return new LaunchPoolInfo(name, percentage, startDateTime, endDateTime, timezone); // Повертаємо новий об'єкт LaunchPoolInfo
    }
}
