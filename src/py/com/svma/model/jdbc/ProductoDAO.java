
package py.com.svma.model.jdbc;
import java.util.List;
import py.com.svma.bean.dto.ProductoDTO;
import java.sql.SQLException;
/**
*Esta interfaz contiene los métodos abstractos con las
* operaciones básicas sobre la tabla de Producto
* CRUD (Create, Read, Update y Delete)
* Se debe crear una clase concreta para implementar el
* código asociado a cada método.
* @ Gloria y Fátima Barreto
*/
public interface ProductoDAO {
	
public int insert(ProductoDTO producto)
throws SQLException;
public int update(ProductoDTO producto)
throws SQLException;
public int delete(ProductoDTO producto)
throws SQLException;

public List<ProductoDTO> select() throws SQLException;
}

