// License: GPL. For details, see LICENSE file.
package org.openstreetmap.josm.plugins.mapillary.io.export;

import static org.openstreetmap.josm.tools.I18n.tr;

import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.openstreetmap.josm.data.osm.INode;
import org.openstreetmap.josm.gui.PleaseWaitRunnable;
import org.openstreetmap.josm.gui.progress.swing.PleaseWaitProgressMonitor;
import org.openstreetmap.josm.plugins.mapillary.utils.MapillaryImageUtils;
import org.openstreetmap.josm.tools.Logging;

/**
 * Export main thread. Exportation works by creating a
 * {@link MapillaryExportWriterThread} and several
 * {@link MapillaryExportDownloadThread}. The second ones download every single
 * image that is going to be exported and stores them in an
 * {@link ArrayBlockingQueue}. Then it is picked by the first one and written on
 * the selected folder. Each image will be named by its key.
 *
 * @param <T> The type of node being exported
 * @author nokutu
 * @see MapillaryExportWriterThread
 * @see MapillaryExportDownloadThread
 */
public class MapillaryExportManager<T extends INode> extends PleaseWaitRunnable {

    private final ArrayBlockingQueue<BufferedImage> queue = new ArrayBlockingQueue<>(10);
    private final ArrayBlockingQueue<INode> queueImages = new ArrayBlockingQueue<>(10);

    private final int amount;
    private final Set<T> images;
    private final String path;

    private Thread writer;
    private ThreadPoolExecutor ex;

    /**
     * Main constructor.
     *
     * @param images Set of {@link INode} objects to be exported.
     * @param path Export path.
     */
    public MapillaryExportManager(Collection<T> images, String path) {
        super(tr("Downloading…"), new PleaseWaitProgressMonitor(tr("Exporting Mapillary Images…")), true);
        this.images = images == null ? new HashSet<>() : new HashSet<>(images);
        this.path = path;
        this.amount = this.images.size();
    }

    @Override
    protected void cancel() {
        this.writer.interrupt();
        this.ex.shutdown();
    }

    @Override
    protected void realRun() {
        // Starts a writer thread in order to write the pictures on the disk.
        this.writer = new MapillaryExportWriterThread(this.path, this.queue, this.queueImages, this.amount,
            this.getProgressMonitor());
        this.writer.start();
        if (this.path == null) {
            try {
                this.writer.join();
            } catch (InterruptedException e) {
                Logging.error(e);
                Thread.currentThread().interrupt();
            }
            return;
        }
        ArrayBlockingQueue<Runnable> executionQueue = new ArrayBlockingQueue<>(10);
        this.ex = new ThreadPoolExecutor(20, 35, 25, TimeUnit.SECONDS, executionQueue);
        for (INode image : this.images) {
            if (MapillaryImageUtils.isImage(image)) {
                synchronized (this) {
                    while (this.ex.getQueue().remainingCapacity() == 0) {
                        try {
                            this.wait(1000);
                        } catch (InterruptedException e) {
                            Logging.error(e);
                            Thread.currentThread().interrupt();
                        }
                    }
                }
                try {
                    this.ex.execute(new MapillaryExportDownloadThread(image, this.queue, this.queueImages));
                } catch (RejectedExecutionException e) {
                    Logging.error(e);
                }
            }
        }
        try {
            this.writer.join();
        } catch (InterruptedException e) {
            Logging.error(e);
            Thread.currentThread().interrupt();
        }
    }

    @Override
    protected void finish() {
        this.cancel();
    }
}
