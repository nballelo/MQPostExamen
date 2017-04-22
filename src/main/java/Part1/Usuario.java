package Part1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ignacio on 20/04/2017.
 */
public class Usuario {
    String name;
    String pass;
    List<Objeto>objetos;


    public Usuario(){
   }

    public Usuario(String name,String pass){
        this.name=name;
        this.pass=pass;
        this.objetos=new ArrayList<Objeto>();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public List<Objeto> getObjetos() {
        return objetos;
    }

    public void setObjetos(List<Objeto> objetos) {
        this.objetos = objetos;
    }
}
