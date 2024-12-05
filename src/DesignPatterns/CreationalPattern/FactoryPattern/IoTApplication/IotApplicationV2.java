package DesignPatterns.CreationalPattern.FactoryPattern.IoTApplication;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

class SensorLogger {
    private static AtomicReference<SensorLogger> instance = new AtomicReference<>();
    private Logger logger;

    private SensorLogger() {
        logger = Logger.getLogger(SensorLogger.class.getName());
        logger.setLevel(Level.INFO);
    }

    public static SensorLogger getInstance() {
        if (instance == null) {
            instance.compareAndSet(null,  new SensorLogger());
        }
        return instance.get();
    }

    public void log(String message) {
        logger.info(message);
    }

    public void log(String message,Level level) {
        logger.log(level, message);
    }
}

enum SensorType {
    HUMIDITY,
    TEMPERATURE,
    PRESSURE,
}

interface Sensor {
    void read();
}

abstract class AbstractSensor implements Sensor {
    protected String sensorName;
    protected SensorType sensorType;
    protected UUID sensorUUID;

    public AbstractSensor(String sensorName, SensorType sensorType, UUID sensorUUID) {
        this.sensorName = sensorName;
        this.sensorType = sensorType;
        this.sensorUUID = sensorUUID;
    }

    public void read(){
        SensorLogger sensorLogger = SensorLogger.getInstance();
        sensorLogger.log("UUID: " + sensorUUID);
        sensorLogger.log("SensorName: " + sensorName);
      }
}

class HumiditySensor extends AbstractSensor implements Sensor {
    public HumiditySensor(String sensorName) {
        super(sensorName, SensorType.HUMIDITY, UUID.randomUUID());
    }
}
class TemperatureSensor extends AbstractSensor implements Sensor {
    public TemperatureSensor(String sensorName) {
        super(sensorName, SensorType.TEMPERATURE, UUID.randomUUID());
    }
}

class PressureSensor extends AbstractSensor implements Sensor {
    public PressureSensor(String sensorName) {
        super(sensorName, SensorType.PRESSURE, UUID.randomUUID());
    }
}

class SensorFactory {
    public static Sensor getSensor(String sensorName,SensorType sensorType) {
        switch (sensorType) {
            case HUMIDITY:
                return new HumiditySensor(sensorName);
            case TEMPERATURE:
                return new TemperatureSensor(sensorName);
            case PRESSURE:
                return new PressureSensor(sensorName);
            default:
                throw new IllegalArgumentException("Invalid sensor type");
        }
    }
}

class SensorSystem {
    List<Sensor> sensors;
    Map<Sensor,Integer> sensorIntervals;
    Map<Sensor,Timer> sensorTimers;
    public SensorSystem() {
        this.sensors = new ArrayList<>();
        this.sensorIntervals = new HashMap<>();
        this.sensorTimers = new HashMap<>();
    }

    public void addSensor(Sensor sensor, int interval) {
    this.sensors.add(sensor);
    this.sensorIntervals.put(sensor, interval);
    }


    public void startAllSensors() {
    for (Sensor sensor : this.sensors) {
        int interval = this.sensorIntervals.get(sensor);
        this.runSensor(sensor, interval);
    }
    }

    public void runSensor(Sensor sensor, int interval) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                sensor.read();
            }
        };
        timer.schedule(task,0, interval);
        this.sensorTimers.put(sensor, timer);
    }


    public void removeSensor(Sensor sensor) {
        Timer timer = this.sensorTimers.get(sensor);
        if(timer == null) {
            throw new IllegalArgumentException("No sensor timer for " + sensor);
        }
        timer.cancel();
        this.sensors.remove(sensor);
        this.sensorIntervals.remove(sensor);
        this.sensorTimers.remove(sensor);
    }

    public void updateSensor(Sensor sensor,int interval) {
        this.removeSensor(sensor);
        this.addSensor(sensor, interval);
        this.runSensor(sensor, interval);
    }


}

public class IotApplicationV2 {

    public static void main(String[] args) {
        Sensor HumiditySensor = SensorFactory.getSensor("HUMIDITY", SensorType.HUMIDITY);
        Sensor TemperatureSensor = SensorFactory.getSensor("TEMPERATURE", SensorType.TEMPERATURE);
        Sensor PressureSensor = SensorFactory.getSensor("PRESSURE", SensorType.PRESSURE);
        // initialize sensors
        SensorSystem sensorSystem = new SensorSystem();
        sensorSystem.addSensor(HumiditySensor, 3000);
        sensorSystem.addSensor(TemperatureSensor, 5000);
        sensorSystem.addSensor(PressureSensor, 8000);
        // run sensor operations
        sensorSystem.startAllSensors();
        // update sensor operations
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                SensorLogger.getInstance().log("Updating Humidity Sensor");
                sensorSystem.updateSensor(HumiditySensor,6000);
                SensorLogger.getInstance().log("Updated Humidity sensor");
            }
        },20000);
    }
}
