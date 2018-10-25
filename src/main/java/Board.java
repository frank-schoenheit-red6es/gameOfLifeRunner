/*
 * Created on 25.10.18
 */

public interface Board {
    void init(final int size);
    void setAlive(final int x, final int y, final boolean alive);
    boolean isAlive(final int x, final int y);
    void step();
}
