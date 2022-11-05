
package py.com.svma.model.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import py.com.svma.bean.dto.ProductoDTO;
/**
*
* Esta clase almacena las consultas que vamos a usar (los query INSERT, UPDATE, DELETE y SELECT)
* Esta clase implementa la interface ProductoDao es una implementación con la
* tecnología JDBC podría haber otro tipo de implementaciones con tecnologías
* como EclipseLink, Hibernate, JPA; TopLink, etc. La interface es independiente
* de la tecnología, pero en su implementación se puede aplicar JDBC.
* Definimos la clase ProductoDaoJDBC e implementamos la interface ProductoDAO
* Al utilizar la implementación de una interface estamos obligados a agregar una
* implementación de cada uno de los métodos definidos en la interface
* Es en este punto donde estamos usando patrones de diseño y utilizando el API
* JDBC para nuestra capa de datos.
* La variable userConn almacena la dirección de la BD que le proporcionemos
*
* @author Gloria Barreto y Fátima Barreto
*/

public class ProductoDaoJDBC implements ProductoDAO{
private Connection userConn;
private final String SQL_INSERT = "INSERT INTO producto (id_producto, descripcion, cod_unidad, stockActual, stockMinimo, precio) VALUES(?,?,?,?,?,?)";
private final String SQL_UPDATE = "UPDATE producto SET stockActual=? WHERE id_producto=?";
private final String SQL_DELETE = "DELETE FROM producto WHERE id_producto = ?";
private final String SQL_SELECT = "SELECT id_producto, descripcion, cod_unidad, stockActual, stockMinimo, precio FROM producto";
/**
* Constructor vacio
*/
public ProductoDaoJDBC() {
}
/**
* Constructor ProductoDaoJDBC recibe una conexion, este constructor
*Es útil para manejo de transacciones ya que nos asegura una única conexion
* durante el tiempo de vida de este objeto y una vez que asignamos una
* conexion no la vamos a cerrar durante el tiempo de vida del objeto y cada
* vez que utilicemos una operación CRUD vamos a utilizar la misma conexion
* Esto es para que al final se pueda hacer un commit o rollback de toda la
* transacción
*
* @param conn
*/
public ProductoDaoJDBC(Connection conn) {
this.userConn = conn;
}
/**
* El método insert recibe como argumento un objeto DTO el cual viene de
* otra capa, y se extraen sus valores para crear un nuevo registro, por eso
* este método es del tipo Data Transfer Object (DTO)
* El Overrride aparece porque estamos sobrescribiendo un método definido en la
* interface
*/
@Override
public int insert(ProductoDTO producto) throws SQLException {
//Declaración de las variables
Connection conn = null;
PreparedStatement stmt = null;
int rows = 0; //para saber el número de registros que han sido modificados
try {
conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
stmt = conn.prepareStatement(SQL_INSERT);
int index = 1;
stmt.setInt(index++, producto.getId_producto()); // ver esta parte
stmt.setString(index++, producto.getDescripcion()); // ver esta parte
stmt.setInt(index++, producto.getCod_unidad()); // ver esta parte
stmt.setInt(index++, producto.getStockActual());
stmt.setInt(index++, producto.getStockMinimo());
stmt.setInt(index++, producto.getPrecio());
        
System.out.println("Ejecutando query:" + SQL_INSERT);
rows = stmt.executeUpdate();
System.out.println("Registros afectados:" + rows);
} finally {
Conexion.close(stmt);
if (this.userConn == null) {
Conexion.close(conn);
}
}
return rows;
}
/**
* El método update recibe un objeto productoDTO el cual encapsula la
* información en un solo objeto y evitamos pasar los 3 parámetros de manera
* aislada, Después extraemos la información del objeto y actualizamos el
* registro seleccionado
*En un DTO transferimos todo el objeto y no solo parte del objeto
* Este objeto ProductoDTO tiene los datos que necesitamos para manejar
* el metodo update
*/
@Override
public int update(ProductoDTO producto)
throws SQLException {
Connection conn = null;
PreparedStatement stmt = null;
int rows = 0;
try {
	conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
System.out.println("Ejecutando query:" + SQL_UPDATE);
stmt = conn.prepareStatement(SQL_UPDATE);
int index = 1;//definimos nuestro parámetro con índice 1
stmt.setInt(index++, producto.getStockActual()); // ver esta parte
stmt.setInt(index, producto.getId_producto());
rows = stmt.executeUpdate();
System.out.println("Registros actualizados:" + rows);
} finally {
Conexion.close(stmt);
if (this.userConn == null) {
Conexion.close(conn);
}
}
return rows;
}
/**
* Recibimos un objeto ProductoDTO no necesariamente debe venir lleno, sino
* solo nos importa el atributo id_producto
*/
@Override
public int delete(ProductoDTO producto) throws SQLException {
Connection conn = null;
PreparedStatement stmt = null;
int rows = 0;
try {
conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
System.out.println("Ejecutando query:" + SQL_DELETE);
stmt = conn.prepareStatement(SQL_DELETE);
stmt.setInt(1, producto.getId_producto());
rows = stmt.executeUpdate();
System.out.println("Registros eliminados:" + rows);
} finally {
Conexion.close(stmt);
if (this.userConn == null) {
Conexion.close(conn);
}
}
return rows;
}
/**
* Por último definimos el método select el cual regresa una lista de objetos
* de tipo DTO
* En este método utilizamos el objeto ProductoDTO para llenar una lista y
* regresarla
*
*/

@Override
public List<ProductoDTO> select() throws SQLException {
Connection conn = null;
PreparedStatement stmt = null;
ResultSet rs = null;
ProductoDTO productoDTO = null;
List<ProductoDTO> productos = new ArrayList<ProductoDTO>();
try {
conn = (this.userConn != null) ? this.userConn : Conexion.getConnection();
stmt = conn.prepareStatement(SQL_SELECT);
rs = stmt.executeQuery();
while (rs.next()) {
//Por cada registro se recuperan los valores
//de las columnas y se crea un objeto DTO
//En este punto hay que pasar los valores en el mismo orden que se le paso al select
int idProductoTemp = rs.getInt(1);
String descripcionTemp = rs.getString(2);
int codUnidadTemp = rs.getInt(3);
int stockActualTemp = rs.getInt(4);
int stockMinimoTemp = rs.getInt(5);
int precioTemp = rs.getInt(6);

//Llenamos el DTO y lo agregamos a la lista
productoDTO = new ProductoDTO();
productoDTO.setId_producto(idProductoTemp);
productoDTO.setDescripcion(descripcionTemp);
productoDTO.setCod_unidad(codUnidadTemp);
productoDTO.setStockActual(stockActualTemp);
productoDTO.setStockMinimo(stockMinimoTemp);
productoDTO.setPrecio(precioTemp);

productos.add(productoDTO);//agregamos a lista DTO
}
} finally {
Conexion.close(rs);
Conexion.close(stmt);
if (this.userConn == null) {
Conexion.close(conn);
}
}
return productos;
}
}

