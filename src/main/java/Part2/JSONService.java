package Part2;
import Part1.*;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Ignacio on 20/04/2017.
 */
@Path("/json")
public class JSONService {
   private EtakemonManager service = EtakemonManagerImpl.getInstance();

    public JSONService(){


    }
    @GET
    @Path("/getUser/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUsuario(@PathParam("name")String name){
       return service.getUser(name);
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getUsuarios(){

        return service.listarUsuarios();
    }
    @GET
    @Path("/{name}/getObject")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Objeto> getObjetos(@PathParam("name")String name){
        List<Objeto>list=service.getObjectos(name);
        return list;
    }

    @POST
    @Path("/setUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String setUser(Usuario usuario){
       service.addUser(usuario);
        return "Se ha añadido correctamente";
    }
    @POST
    @Path("/{name}/setObject")
    @Produces(MediaType.TEXT_PLAIN)
    public String setUser(@PathParam("name") String name,Objeto objeto){
        System.out.println("asdsadas");
        service.addObjecto(name,objeto);
        return "Se ha añadido correctamente";
    }
    @POST
    @Path("/modifyUser/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String modifyUser(@PathParam("name") String name,Usuario usuario){
        service.modifyUsuario(name,usuario);
        return "Se ha modificado correcamente";
    }

}
