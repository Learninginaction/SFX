import processing.core.*;
//import processing.sound.*;
import java.util.*;
public final class FunTime extends PApplet {
    List<Entitiy> craptodraw = new ArrayList<>();
    List<Dot> dots = new ArrayList<>();
    Center center;
    PImage dotTexture;
    PImage centerTexture;
    long lastTimeCalled = -1;
    public static void main (String args[]){
        PApplet.main(new String[]{"FunTime"});
    }
    public void settings() { fullScreen(); }

    public void setup() {
        center = new Center();
        craptodraw.add(center);
        imageMode(CENTER);
        dotTexture = loadImage("Dot.jpg");
        centerTexture = loadImage("Center2.png");
        noCursor();
    }
    private void addDot(){
        Dot a = new Dot();
        craptodraw.add(a);
        dots.add(a);
    }
    public void draw(){
        long delta = lastTimeCalled == -1 ? 0 : System.currentTimeMillis() - lastTimeCalled;
        lastTimeCalled = System.currentTimeMillis();
        background(0);
        Iterator<Dot> itr = dots.iterator();
            while (itr.hasNext()) {
                Dot a = itr.next();
                a.Falling(delta);
                if (a.timeLeft <= 0) {
                    itr.remove();
                    craptodraw.remove(a);
                }
            }

        if (random(1) <= .4) addDot();
        for (Entitiy e : craptodraw)
            e.draw();

    }
    public void keyPressed() {
        if (key == 'e')
            exit();
    }
    private final class Center extends Entitiy{
        public void Center() {
            x = 50;
            y = 50;
        }
        public void draw() {
            noStroke();
            fill(0);
            imageMode(CENTER);
            image(centerTexture, x, y);

        }
    }
    private final class Dot extends Entitiy {
        private long timeLeft = 2000;
        public Dot() {
             x = random(width);
             y = random(height);
        }
        public void Falling(long delta){
            x += random(7);
            y += random(7);
            timeLeft -= delta;
        }
        public void draw() {
            noStroke();
            fill(0);
            image(dotTexture, x, y);
        }
    }
    private abstract class Entitiy{
        float x, y;

        abstract void draw();
    }

}

