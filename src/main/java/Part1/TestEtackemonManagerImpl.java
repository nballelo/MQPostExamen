package Part1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Ignacio on 20/04/2017.
 */
public class TestEtackemonManagerImpl {
    EtakemonManagerImpl etakemonManager;
    List<Usuario>usuarios=new ArrayList<Usuario>();;
    @Before
    public void setUp(){
        Usuario usuario=new Usuario("Nombre","Pass");
        usuarios.add(usuario);
        etakemonManager.getInstance().addUser(usuario);

    }
    @Test
    public void setUsuarios(){
        Usuario usuario=new Usuario("Nombre","Pass");
        Assert.assertEquals(false,etakemonManager.getInstance().addUser(usuario));
        usuario=new Usuario("Masmi","Pass");
        Assert.assertEquals(true,etakemonManager.getInstance().addUser(usuario));

    }
    @Test
    public void getUsuario(){
        Usuario usuario=new Usuario("Nombre","Pass");
        Assert.assertEquals(usuario.getPass(),etakemonManager.getInstance().getUser(usuario.getName()).getPass());
        etakemonManager.getInstance().listarUsuarios().size();
    }
    @Test
    public void setObjeto(){
        Objeto objeto=new Objeto();
        objeto.setName("Lobo");
        objeto.setLvl(3);
        Assert.assertEquals(true,etakemonManager.getInstance().addObjecto("Nombre",objeto));
    }
    @After
    public  void  tearDown(){
    }
}
