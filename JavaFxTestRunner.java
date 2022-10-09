package wrapper.sk.machine;

import javafx.application.Platform;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class JavaFxTestRunner {

    public boolean run(Runnable runnable, long timeout, TimeUnit timeUnit) throws ExecutionException,
            InterruptedException, TimeoutException {
        var future = new CompletableFuture<Boolean>();
        runFuture(runnable, future);
        return future.get(timeout, timeUnit);
    }

    private void runFuture(Runnable runnable, CompletableFuture<Boolean> future) {
        Platform.runLater(() -> {
            try {
                runnable.run();
                future.complete(true);
            } catch (Exception exception) {
                future.completeExceptionally(exception);
            }
        });
    }

}
