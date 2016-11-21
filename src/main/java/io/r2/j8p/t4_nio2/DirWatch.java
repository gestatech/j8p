package io.r2.j8p.t4_nio2;

import java.nio.file.*;
import java.util.concurrent.TimeUnit;

/**
 * Observe the changes in a directory by using the WatchService interface
 */
public class DirWatch {

    public void watch(Path dir) throws Exception {

        // create the watch service
        WatchService watchService = FileSystems.getDefault().newWatchService();

        dir.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);

        System.out.println("Watching dir: "+dir.toString());

        while (true) {
            // we have to poll the watcher for  changes - this could be on a different thread also...

            WatchKey key;

            key = watchService.poll(30, TimeUnit.SECONDS);
            // key = watchService.poll(); // just check it
            // key = watchService.take(); // wait indefinitely

            if (key == null) {
                // no change in 30 seconds, we are done
                System.out.println("No change in 30 seconds, exiting");
                break;
            }

            // pollEvents is a list of WatchEvents
            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();

                // the context is the path for which the event has happened
                // as WatchEvent may have other contexts too, we have to cast manually
                WatchEvent<Path> ev = (WatchEvent<Path>)event;

                System.out.println("Event: "+kind.toString()+" for "+ev.context().toString());
            }

            // this is important, it looks like, otherwise no further watch events will be sent to this key
            boolean valid = key.reset();
            // if reset returned invalid, the object does not exist anymore and can't be watched - now exit
            if (!valid) {
                System.out.println(key.watchable().toString()+" not valid, exiting");
                break;
            }
        }
    }

}
