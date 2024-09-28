package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Создаем список LaunchPoolInfo
        List<LaunchPoolInfo> launchPools = new ArrayList<>();

        // Добавляем примеры данных
        launchPools.add(createLaunchPool("Bybit", "DBR", createPoolsMap("MNT", "800%", "USDT", "800%", "DBR", "800%"), "22.10 08:00 — 29.10 08:00 UTC", "Скоро почнеться"));
        launchPools.add(createLaunchPool("Bitget", "PUFFER", createPoolsMap("PUFFER", "0%"), "16.10 12:00 — 26.10 12:00 UTC", "Активний"));
        launchPools.add(createLaunchPool("Gate", "AIX", createPoolsMap("USDT", "0%", "GT", "0%"), "16.10 05:00 — 23.10 05:00 UTC", "Активний"));
        launchPools.add(createLaunchPool("Gate", "CYRUS", createPoolsMap("USDT", "0%", "GT", "0%"), "25.10 06:00 — 29.10 06:00 UTC", "Скоро почнеться"));
        launchPools.add(createLaunchPool("Bybit", "PUFFER", createPoolsMap("PUFFER", "800%", "MNT", "352.7%", "USDT", "186.67%"), "14.10 12:00 — 23.10 12:00 UTC", "Активний"));
        launchPools.add(createLaunchPool("Gate", "PUFFER", createPoolsMap("PUFFER", "5073.87%", "GT", "25.76%"), "14.10 12:00 — 2.10 12:00 UTC", "Активний"));
        launchPools.add(createLaunchPool("Bitget", "PUFFER", createPoolsMap("USDT", "984.49%"), "14.10 12:00 — 21.10 12:00 UTC", "Активний"));
        launchPools.add(createLaunchPool("Kucoin", "PUFFER", createPoolsMap("PUFFER", "", "USDT", "", "KCS", ""), "14.10 12:00 — 21.10 12:00 UTC", "Активний"));
        launchPools.add(createLaunchPool("Kucoin", "DEEP", createPoolsMap("USDT", "", "KCS", ""), "15.10 10:00 — 29.10 10:00 UTC", "Активний"));
        launchPools.add(createLaunchPool("Gate", "GEEK", createPoolsMap("GT", "0.89%", "GEEK", "1421.95%"), "21.10 10:00 — 26.10 10:00 UTC", "Активний"));
        launchPools.add(createLaunchPool("Bitget", "CARV", createPoolsMap("CARV", "240.95%", "USDT", "87.36%"), "20.10 08:00 — 30.10 08:00 UTC", "Активний"));
        launchPools.add(createLaunchPool("Bitget", "OGLG", createPoolsMap("BTC", "1.32%"), "19.10 12:00 — 29.10 12:00 UTC", "Активний"));
        launchPools.add(createLaunchPool("Bitget", "CATS", createPoolsMap("CATS", "141.95%"), "18.10 10:00 — 28.10 10:00 UTC", "Активний"));
        launchPools.add(createLaunchPool("Bybit", "CATS", createPoolsMap("CATS", "1158.81%", "MNT", "66.15%", "USDT", "41.42%"), "24.10 10:00 — 28.10 10:00 UTC", "Скоро почнеться"));
        launchPools.add(createLaunchPool("Gate", "CATS", createPoolsMap("CATS", "1012.63%", "GT", "44.92%"), "18.10 10:00 — 25.10 10:00 UTC", "Активний"));

        // Бесконечный цикл для проверки статуса пулов
        while (true) {
            System.out.println("Запланированное задание выполняется...");
            for (LaunchPoolInfo pool : launchPools) {
                pool.checkAndUpdateStatus(); // Проверяем и обновляем статус

                // Выводим только те пулы, которые имеют статус "Активний" или "Скоро почнеться"
                if (pool.getStatus().equals("Активний") || pool.getStatus().equals("Скоро почнеться")) {
                    System.out.println(pool.getPoolInfo()); // Выводим полную информацию о пуле
                }
            }

            // Задержка перед следующей проверкой  60 секунд
            try {
                Thread.sleep(60000); // Ожидаем 60 секунд
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break; // Для выхода из цикла
            }
        }
    }

    // Метод для создания карты пулов
    private static Map<String, String> createPoolsMap(String... args) {
        Map<String, String> pools = new HashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            pools.put(args[i], args[i + 1]);
        }
        return pools;
    }

    // Метод для создания объекта LaunchPoolInfo
    private static LaunchPoolInfo createLaunchPool( String exchange, String launchPool, Map<String, String> pools, String period, String status) {
        return new LaunchPoolInfo(exchange, launchPool, pools, period, status);
    }
}
