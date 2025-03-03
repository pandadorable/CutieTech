package dev.pandadorable.cutietech;

import net.minecraft.client.Minecraft;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ClientUtils {
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static void runLater(Runnable task, long delay, TimeUnit unit) {
        scheduler.schedule(() -> {
            Minecraft.getInstance().execute(task);
        }, delay, unit);
    }
}
