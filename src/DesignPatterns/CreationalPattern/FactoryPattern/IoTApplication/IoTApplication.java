import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// Singleton Pattern: Logger
class SingletonLogger {
    private static SingletonLogger instance;
    private Logger logger;

    private SingletonLogger() {
        logger = Logger.getLogger(SingletonLogger.class.getName());
        logger.setLevel(Level.INFO);
    }

    public static SingletonLogger getInstance() {
        if (instance == null) {
            instance = new SingletonLogger();
        }
        return instance;
    }

    public void log(String message) {
        logger.info(message);
    }
}

// Factory Pattern: Sensor Factory
class SensorFactory {
    public static Sensor createSensor(String sensorType) {
        switch (sensorType) {
            case "Temperature":
                return new TemperatureSensor();
            case "Humidity":
                return new HumiditySensor();
            case "Pressure":
                return new PressureSensor();
            default:
                throw new IllegalArgumentException("Unknown sensor type: " + sensorType);
        }
    }
}

// Abstract Sensor Class
abstract class Sensor {
    public abstract String readData();
}

// Concrete Sensors
class TemperatureSensor extends Sensor {
    @Override
    public String readData() {
        return "Temperature: 25°C";
    }
}

class HumiditySensor extends Sensor {
    @Override
    public String readData() {
        return "Humidity: 60%";
    }
}

class PressureSensor extends Sensor {
    @Override
    public String readData() {
        return "Pressure: 1013 hPa";
    }
}

// Observer Pattern: Sensor System
class SensorSystem {
    private List<Sensor> sensors;
    private Map<Sensor, Integer> sensorIntervals;
    private Map<Sensor, Timer> sensorTimers;
    private SingletonLogger logger;

    public SensorSystem() {
        sensors = new ArrayList<>();
        sensorIntervals = new HashMap<>();
        sensorTimers = new HashMap<>();
        logger = SingletonLogger.getInstance();
    }

    public void addSensor(Sensor sensor, int interval) {
        sensors.add(sensor);
        sensorIntervals.put(sensor, interval);
        logger.log("Added " + sensor.getClass().getSimpleName() + " with interval: " + interval + " ms");
    }

    public void notifySensor(Sensor sensor) {
        String data = sensor.readData();
        logger.log("Sensor Data: " + data);
    }

    public Map<Sensor, Integer> getSensorIntervals() {
        return sensorIntervals;
    }

    public void updateSensorInterval(Sensor sensor, int newInterval) {
        if (sensorIntervals.containsKey(sensor)) {
            sensorIntervals.put(sensor, newInterval);
            logger.log("Updated interval for " + sensor.getClass().getSimpleName() + " to: " + newInterval + " ms");
            if (sensorTimers.containsKey(sensor)) {
                sensorTimers.get(sensor).cancel();
                startSensorTimer(sensor, newInterval);
            }
        } else {
            logger.log("Sensor not found: " + sensor.getClass().getSimpleName());
        }
    }

    public void startSensorTimer(Sensor sensor, int interval) {
        Timer timer = new Timer();
        sensorTimers.put(sensor, timer);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                notifySensor(sensor);
            }
        }, 0, interval);
    }

    public void cancelSensorTimer(String sensorName) {
        for (Sensor sensor : sensors) {
            if (sensor.getClass().getSimpleName().equals(sensorName)) {
                if (sensorTimers.containsKey(sensor)) {
                    sensorTimers.get(sensor).cancel();
                    sensorTimers.remove(sensor);
                    logger.log("Cancelled timer for " + sensorName);
                } else {
                    logger.log("No active timer found for " + sensorName);
                }
                return;
            }
        }
        logger.log("Sensor not found: " + sensorName);
    }
}


// Main Application
public class IoTApplication {
    private SensorSystem sensorSystem;
    private SingletonLogger logger;

    public IoTApplication() {
        sensorSystem = new SensorSystem();
        logger = SingletonLogger.getInstance();
    }

    public void initializeSensors() {
        String[] sensorTypes = {"Humidity"};
        Random random = new Random();
        for (String sensorType : sensorTypes) {
            Sensor sensor = SensorFactory.createSensor(sensorType);
            int interval = 3000 + random.nextInt(7000); // Random interval between 3 to 10 seconds
            sensorSystem.addSensor(sensor, interval);
        }
        logger.log("All sensors initialized.");
    }

    public void runRoutine() {
        logger.log("Starting routine operations...");
        for (Map.Entry<Sensor, Integer> entry : sensorSystem.getSensorIntervals().entrySet()) {
            Sensor sensor = entry.getKey();
            int interval = entry.getValue();
            sensorSystem.startSensorTimer(sensor, interval);
        }
    }

    public static void main(String[] args) {
        IoTApplication app = new IoTApplication();
        app.initializeSensors();
        app.runRoutine();

        // Update Humidity Sensor interval to 15 seconds
//        Sensor humiditySensor = SensorFactory.createSensor("Humidity");
//        app.sensorSystem.updateSensorInterval(humiditySensor, 15000);

        // Cancel Humidity Sensor after 20 seconds
        Timer cancelTimer = new Timer();
        cancelTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                app.sensorSystem.cancelSensorTimer("Humidity");
            }
        }, 20000); // Cancel after 20 seconds
    }
}
