package lectures.chapter_04.code;
public abstract class GraphicObject {
    public int x, y;

    public void moveTo(int newX, int newY) {
        x += newX;
        y += newY;
    }

    public abstract void draw();
    
    public abstract void resize();
}
