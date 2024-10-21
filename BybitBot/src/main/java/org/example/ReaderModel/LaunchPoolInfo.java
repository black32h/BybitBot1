package org.example.ReaderModel;

public class LaunchPoolInfo {
    private String exchange;
    private String launchPool;
    private String pools;
    private String period;
    private String status;

    // Конструктор
    public LaunchPoolInfo(String exchange, String launchPool, String pools, String period, String status) {
        this.exchange = exchange;
        this.launchPool = launchPool;
        this.pools = pools;
        this.period = period;
        this.status = status;
    }

    // Геттери
    public String getExchange() {
        return exchange;
    }

    public String getLaunchPool() {
        return launchPool;
    }

    public String getPools() {
        return pools;
    }

    public String getPeriod() {
        return period;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,\"%s\",\"%s\",%s", exchange, launchPool, pools, period, status);
    }

    // Метод для отримання початкової дати і часу
    public String getStartDateTime() {
        if (period == null || period.isEmpty()) {
            return "";
        }
        String[] parts = period.split(" — ");
        return parts.length > 0 ? parts[0] : "";
    }
}
