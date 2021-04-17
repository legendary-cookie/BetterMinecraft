package cosmo.betterminecraft.Util;

import org.bukkit.Bukkit;

public class ReflectionUtil {
    private Class<?> getNMSClass(String name) throws ClassNotFoundException {
        return Class.forName("net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + "." + name);
    }
}
