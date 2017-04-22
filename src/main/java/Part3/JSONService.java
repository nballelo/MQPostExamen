package Part3;

import Part1.EtakemonManagerImpl;
import Part1.Objeto;
import Part1.Usuario;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Ignacio on 20/04/2017.
 */
@Path("/part3")
public class JSONService {
    private MySQL mySQL=new MySQL();
    public JSONService(){
    }
    @GET
    @Path("/getUser/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUsuario(@PathParam("name")String name) throws Exception {

       return mySQL.getUser(name);
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getUsuarios() throws SQLException {

        return mySQL.listarUsuarios();
    }
    @GET
    @Path("/{name}/getObject")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Objeto> getObjetos(@PathParam("name")String name) throws Exception {
        List<Objeto>list=mySQL.getObjectos(name);
        return list;
    }

    @POST
    @Path("/setUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String setUser(Usuario usuario) throws Exception {
        return mySQL.addUser(usuario);
    }
    @POST
    @Path("/{name}/setObject")
    @Produces(MediaType.TEXT_PLAIN)
    public String setUser(@PathParam("name") String name,Objeto objeto){

        return mySQL.addObjecto(name,objeto);
    }
    @POST
    @Path("/modifyUser/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String modifyUser(@PathParam("name") String name,Usuario usuario) throws Exception {

        return mySQL.modifyUsuario(name,usuario);
    }


}
