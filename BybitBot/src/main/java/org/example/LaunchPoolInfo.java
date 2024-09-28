package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class LaunchPoolInfo {
    private String exchange;  // Добавлено поле для биржи
    private String launchPool;
    private String period;
    private String status;
    private Map<String, String> pools; // Добавлено поле для параметров пула

    public LaunchPoolInfo(String exchange, String launchPool, Map<String, String> pools, String period, String status) {
        this.exchange = exchange;
        this.launchPool = launchPool;
        this.pools = pools; // Инициализируем параметры пула
        this.period = period;
        this.status = status;
    }

    // Метод для проверки и обновления статуса
    public void checkAndUpdateStatus() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM HH:mm yyyy");
        String[] periodParts = this.period.split(" — ");
        String startDateStr = periodParts[0] + " " + LocalDateTime.now().getYear();  // Формат строки
        LocalDateTime startDate;
        try {
            startDate = LocalDateTime.parse(startDateStr, formatter);
        } catch (Exception e) {
            System.out.println("Ошибка при парсинге даты: " + e.getMessage());
            return;
        }
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(startDate) && !this.status.equals("Активний")) {
            this.status = "Активний";
            System.out.println("Статус пулу " + this.launchPool + " обновлен на Активний");
        } else if (now.isBefore(startDate) && !this.status.equals("Скоро почнеться")) {
            this.status = "Скоро почнеться";
            System.out.println("Статус пулу " + this.launchPool + " остается Скоро почнеться");
        }
        // Убираем вывод текущего статуса
    }

    // Метод для получения информации о пуле
    public String getPoolInfo() {
        return "Биржа: " + exchange + ", Пул: " + launchPool + ", Параметры: " + pools;
    }

    // Геттер для статуса
    public String getStatus() {
        return status;
    }
}
