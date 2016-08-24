/**
 * Created by Alex on 24.08.2016.
 */
public interface Const {
    String NAME = "Game of Life";

    int LOCATION_X = 100;
    int LOCATION_Y = 100;
    int RADIUS = 15;
    int FIELD_SIZE_X = 50;
    int FIELD_SIZE_Y = 50;
    int BTN_PANEL_HEIGHT = 65;
    int SIZE_X = FIELD_SIZE_X * RADIUS + 10;
    int SIZE_Y = FIELD_SIZE_Y * RADIUS + 10 + BTN_PANEL_HEIGHT;
}
