package Part3;
import Part1.Objeto;
import Part1.*;
import com.mysql.jdbc.Connection;;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by Ignacio on 20/04/2017.
 */
public class MySQL {
    Connection connection;
    PreparedStatement stm;
    public List<Usuario> listarUsuarios() throws SQLException {
        int i=1;
        List<Usuario>usuarios=new ArrayList<>();
        Usuario user=new Usuario();
        StringBuffer query=new StringBuffer("SELECT name FROM usuario");
        try {
            connection= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/examen","root","root");
            PreparedStatement stm=connection.prepareStatement(query.toString());
            stm.execute();
            ResultSet resultSet=stm.getResultSet();
            while (resultSet.next()){
            user=new Usuario();
            user=getUser(resultSet.getString(1));
            usuarios.add(user);}
            return usuarios;
        }
        catch (SQLException sqle) {
            System.out.println("Error en la ejecucion MySQLRepository.select"
                    + sqle.getErrorCode() + " " + sqle.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarios;}
    public String addUser(Usuario usuario) throws Exception {
        StringBuffer query=new StringBuffer("INSERT INTO usuario(");
        Field[] atributos = usuario.getClass().getDeclaredFields();
        for (int i=0;i<atributos.length-1;i++) {

            Field a=atributos[i];
            query.append(a.getName());
            if (i<atributos.length-2)
                query.append(",");
        }
        query.append(") VALUES (");
        for (int i=0;i<atributos.length-1;i++) {
            if(i<atributos.length-2)
                query.append("?,");
            else
                query.append("?)");
            }
        try {
            connection= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/examen","root","root");
            PreparedStatement stm=connection.prepareStatement(query.toString());
            /*for (int i=0;i<atributos.length-1;i++){
                stm.setObject((i+1),atributos[i].get(usuario));
            }*/
            stm.setObject(1,usuario.getName());
            stm.setObject(2,usuario.getPass());
            stm.execute();
            return "El usuario: "+usuario.getName()+" se ha añadido correctamente";
            }
            catch (SQLException sqle) {
                System.out.println("Error en la ejecucion MySQLRepository.select"
                        + sqle.getErrorCode() + " " + sqle.getMessage());
                return "El usuario no se ha añadido correctamente";
            }

    }
    public String modifyUsuario (String name, Usuario ususuario)throws SQLException,Exception{
        StringBuffer query=new StringBuffer("UPDATE usuario SET ");
        Field[] atributos = ususuario.getClass().getDeclaredFields();
        for (int i=0;i<atributos.length-1;i++) {
            Field a=atributos[i];
            query.append(a.getName()).append("=?");
            if (i<atributos.length-2)
                query.append(",");
        }
        query.append(" WHERE name=?");
        /*for (int i=0;i<atributos.length;i++){
            stm.setObject((i+1),atributos[i].get(ususuario).toString());
        }*/
        try {
            connection= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/examen","root","root");
            PreparedStatement stm=connection.prepareStatement(query.toString());
            stm.setObject(1,ususuario.getName());
            stm.setObject(2,ususuario.getPass());
            stm.setObject(3,ususuario.getName());
            stm.execute();
            return "El usuario: "+name+" se ha modificado correctamente";
        }
        catch (SQLException sqle) {
            System.out.println("Error en la ejecucion MySQLRepository.select"
                    + sqle.getErrorCode() + " " + sqle.getMessage());
            return "No se ha modificado correctamente";
        }
    }
    public Usuario getUser(String name) throws SQLException,Exception{
        Usuario user;
        StringBuffer query=new StringBuffer("SELECT * FROM usuario WHERE name=?");
        connection= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/examen","root","root");
        PreparedStatement stm=connection.prepareStatement(query.toString());
        stm.setObject(1,name);
        stm.execute();
        ResultSet resultSet=stm.getResultSet();
        resultSet.next();
        user=new Usuario(resultSet.getString(1),resultSet.getString(2));
        user.setObjetos(getObjectos(name));
        return user;
    }

    public List<Objeto> getObjectos(String name)throws SQLException,Exception{
        List<Objeto> list=new ArrayList<Objeto>();
        Objeto objeto=new Objeto();
        StringBuffer query=new StringBuffer("SELECT name,lvl FROM objetos WHERE nameusuario=?");
        connection= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/examen","root","root");
        PreparedStatement stm=connection.prepareStatement(query.toString());
        stm.setObject(1,name);
        stm.execute();
        ResultSet resultSet=stm.getResultSet();
        while (resultSet.next())
        {
            objeto=new Objeto(resultSet.getString(1),resultSet.getInt(2));
            list.add(objeto);
        }

        return list;}
    public String addObjecto(String name, Objeto objeto){
        StringBuffer query=new StringBuffer("INSERT INTO objetos(");
        Field[] atributos = objeto.getClass().getDeclaredFields();
        for (int i=0;i<atributos.length;i++) {

            Field a=atributos[i];
            query.append(a.getName());
                query.append(",");
        }
        query.append("nameusuario) VALUES (");
        for (int i=0;i<atributos.length;i++) {
            if(i<atributos.length-1)
                query.append("?,");
            else
                query.append("?,?)");
        }try {
            connection= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/examen","root","root");
            PreparedStatement stm=connection.prepareStatement(query.toString());
            /*for (int i=0;i<atributos.length-1;i++){
                stm.setObject((i+1),atributos[i].get(ususuario));
            }*/
            stm.setObject(1,objeto.getName());
            stm.setObject(2,objeto.getLvl());
            stm.setObject(3,name);
            stm.execute();
            return "El objeto:"+objeto.getName()+" se ha añadido correctamente";
        }
        catch (SQLException sqle) {
            System.out.println("Error en la ejecucion MySQLRepository.select"
                    + sqle.getErrorCode() + " " + sqle.getMessage());
            return "El objeto no se ha añadido correctamente";
        }
    }

}
