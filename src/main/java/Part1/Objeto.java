package Part1;

/**
 * Created by Ignacio on 20/04/2017.
 */
public class Objeto {
    String name;
    int lvl;

    public Objeto(){

    }
    public Objeto(String name,int lvl) {
        this.name=name;
        this.lvl=lvl;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }
}
