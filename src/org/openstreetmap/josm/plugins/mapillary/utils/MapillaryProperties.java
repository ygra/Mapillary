// License: GPL. For details, see LICENSE file.
package org.openstreetmap.josm.plugins.mapillary.utils;

import java.awt.Color;

import org.openstreetmap.josm.data.preferences.BooleanProperty;
import org.openstreetmap.josm.data.preferences.ColorProperty;
import org.openstreetmap.josm.data.preferences.DoubleProperty;
import org.openstreetmap.josm.data.preferences.IntegerProperty;
import org.openstreetmap.josm.data.preferences.StringProperty;
import org.openstreetmap.josm.gui.layer.OsmDataLayer;
import org.openstreetmap.josm.plugins.mapillary.io.download.MapillaryDownloader;

public final class MapillaryProperties {
  public static final BooleanProperty DELETE_AFTER_UPLOAD = new BooleanProperty("mapillary.delete-after-upload", true);
  public static final BooleanProperty DEVELOPER = new BooleanProperty("mapillary.developer", false);
  public static final BooleanProperty DISPLAY_HOUR = new BooleanProperty("mapillary.display-hour", true);
  public static final BooleanProperty HOVER_ENABLED = new BooleanProperty("mapillary.hover-enabled", true);
  public static final BooleanProperty MOVE_TO_IMG = new BooleanProperty("mapillary.move-to-picture", true);
  public static final BooleanProperty TIME_FORMAT_24 = new BooleanProperty("mapillary.format-24", true);

  /**
   * @see OsmDataLayer#PROPERTY_BACKGROUND_COLOR
   */
  public static final ColorProperty BACKGROUND = new ColorProperty("background", Color.BLACK);
  /**
   * @see OsmDataLayer#PROPERTY_OUTSIDE_COLOR
   */
  public static final ColorProperty OUTSIDE_DOWNLOADED_AREA = new ColorProperty("outside downloaded area", Color.YELLOW);

  public static final DoubleProperty MAX_DOWNLOAD_AREA = new DoubleProperty("mapillary.max-download-area", 0.015);

  public static final IntegerProperty PICTURE_DRAG_BUTTON = new IntegerProperty("mapillary.picture-drag-button", 3);
  public static final IntegerProperty PICTURE_OPTION_BUTTON = new IntegerProperty("mapillary.picture-option-button", 2);
  public static final IntegerProperty PICTURE_ZOOM_BUTTON = new IntegerProperty("mapillary.picture-zoom-button", 1);
  public static final IntegerProperty SEQUENCE_MAX_JUMP_DISTANCE =
    new IntegerProperty("mapillary.sequence-max-jump-distance", 100);

  public static final StringProperty ACCESS_TOKEN = new StringProperty("mapillary.access-token", null);
  public static final StringProperty DOWNLOAD_MODE =
    new StringProperty("mapillary.download-mode", MapillaryDownloader.DOWNLOAD_MODE.getDefault().getPrefId());
  public static final StringProperty START_DIR =
    new StringProperty("mapillary.start-directory", System.getProperty("user.home"));
}