/**
 * Created by Alex on 24.08.2016.
 */


public interface Const {
    String NAME = "Game of Life";

    int LOCATION_X = 100;
    int LOCATION_Y = 100;
    int DIAMETER = 15;
    int FIELD_SIZE_X = 40;
    int FIELD_SIZE_Y = 40;
    int BTN_PANEL_HEIGHT = 65;
    int OFFSET = 10;
    int SIZE_X = FIELD_SIZE_X * DIAMETER + DIAMETER + OFFSET;
    int SIZE_Y = FIELD_SIZE_Y * DIAMETER + DIAMETER + OFFSET + BTN_PANEL_HEIGHT;
    int BTN_HEIGHT = BTN_PANEL_HEIGHT-35;
    int BTN_WIDTH = BTN_PANEL_HEIGHT-35;
}
