// License: GPL. For details, see LICENSE file.
package org.openstreetmap.josm.plugins.mapillary.gui.imageinfo;

import java.awt.event.ActionEvent;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import org.openstreetmap.josm.gui.Notification;
import org.openstreetmap.josm.tools.I18n;
import org.openstreetmap.josm.tools.ImageProvider;
import org.openstreetmap.josm.tools.ImageProvider.ImageSizes;
import org.openstreetmap.josm.tools.Logging;
import org.openstreetmap.josm.tools.OpenBrowser;

public class WebLinkAction extends AbstractAction {
    private static final long serialVersionUID = 2397830510179013823L;

    private URL url;

    public WebLinkAction(final String name, final URL url) {
        super(name, ImageProvider.get("link", ImageSizes.SMALLICON));
        setURL(url);
    }

    /**
     * Set the URL to be opened
     *
     * @param url the url to set
     */
    public final void setURL(URL url) {
        this.url = url;
        setEnabled(url != null);
    }

    /*
     * (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (url != null) {
                OpenBrowser.displayUrl(url.toURI());
            } else {
                throw new URISyntaxException("‹null›", "The URL is null");
            }
        } catch (URISyntaxException e1) {
            String msg = I18n.tr("Could not open the URL {0} in a browser", url == null ? "‹null›" : url);
            Logging.log(Logging.LEVEL_WARN, msg, e1);
            new Notification(msg).setIcon(JOptionPane.WARNING_MESSAGE).show();
        }
    }

}
