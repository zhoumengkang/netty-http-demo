package api.mengkang.net;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author zhoumengkang
 */
public class Config {

    private static Properties PROPERTIES = new Properties();
    private static final String CONFIG_NAME = "/api.properties";
    private static Config instance;

    private Config() {
        try {
            PROPERTIES.load(new InputStreamReader(getClass().getResourceAsStream(CONFIG_NAME), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private synchronized static Config getInstance() {
        if (null == instance) {
            instance = new Config();
        }
        return instance;
    }

    public static int getInt(String str) {
        try {
            if (null == instance) {
                getInstance();
            }
            return Integer.parseInt(PROPERTIES.getProperty(str));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static long getLong(String str) {
        try {
            if (null == instance) {
                getInstance();
            }
            return Long.parseLong(PROPERTIES.getProperty(str));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static double getDouble(String str) {
        try {
            if (null == instance) {
                getInstance();
            }
            return Double.parseDouble(PROPERTIES.getProperty(str));

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getString(String str) {
        try {
            if (null == instance) {
                getInstance();
            }
            return PROPERTIES.getProperty(str);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static boolean getBoolean(String str) {
        try {
            if (null == instance) {
                getInstance();
            }
            return Boolean.parseBoolean(PROPERTIES.getProperty(str));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
