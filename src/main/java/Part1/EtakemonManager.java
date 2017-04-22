package Part1;

import java.util.List;

/**
 * Created by Ignacio on 20/04/2017.
 */
public interface EtakemonManager {
    List<Usuario> listarUsuarios();
    boolean addUser(Usuario ususuario);
    boolean modifyUsuario(String name, Usuario ususuario);
    Usuario getUser(String name);
    List<Objeto> getObjectos(String name);
    boolean addObjecto(String name, Objeto objeto);

}
