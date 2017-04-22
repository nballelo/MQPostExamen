package Part1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
/**
 * Created by Ignacio on 20/04/2017.
 */
public class EtakemonManagerImpl implements EtakemonManager {
    private static EtakemonManagerImpl instance=null;
    HashMap<String, Usuario> usuarios;
    final static Logger logger= Logger.getLogger(EtakemonManagerImpl.class);
    private EtakemonManagerImpl() {

        usuarios=new HashMap<String, Usuario>();
    }
    public static EtakemonManagerImpl getInstance(){
        if(instance==null){
            instance=new EtakemonManagerImpl();
            logger.info("Se ha creado la tabla");
        }
        return instance;
    }
    public List<Usuario> listarUsuarios(){
        List<Usuario>us=new ArrayList<Usuario>(usuarios.values());
        return us;
    }
    public boolean addUser(Usuario usuario){
        if(!usuarios.containsKey(usuario.getName())){
        usuarios.put(usuario.getName(),usuario);
        logger.info("Se ha añadido: "+usuario.getName()+" a la lista de usuarios");
        return true;
        }
        logger.error("No se ha podido añadir: "+usuario.getName()+" a la lista de usuarios");
        return false;
    }
    public boolean modifyUsuario(String name, Usuario usuario){
        if( usuarios.containsKey(name)){
            usuarios.remove(name);
            usuarios.put(name,usuario);
            logger.info("Se ha modificado: "+usuario.getName()+" de la lista de usuarios");
            return true;
        }
        logger.error("No se ha podido modificar: "+usuario.getName()+" de la lista de usuarios");
        return false;
    }
    public Usuario getUser(String name){
        return usuarios.get(name);
    }

    public List<Objeto> getObjectos(String name){
        return usuarios.get(name).getObjetos();
    }
    public boolean addObjecto(String name, Objeto objeto){
        List<Objeto>obj=new ArrayList<Objeto>();
        Usuario usuario;
        if( usuarios.containsKey(name)){
            obj=usuarios.get(name).getObjetos();
            if(obj==null)
                obj=new ArrayList<Objeto>();
            obj.add(objeto);
            usuario=usuarios.get(name);
            usuario.setObjetos(obj);
            modifyUsuario(usuario.getName(),usuario);
            logger.info("Se ha añadido: "+objeto.getName()+" de la lista de objetos de: "+name);
            return true;
        }
        logger.info("No se ha añadido: "+objeto.getName()+" de la lista de objetos de: "+name);
        return false;
    }

}
