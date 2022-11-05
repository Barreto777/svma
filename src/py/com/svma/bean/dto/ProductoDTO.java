
package py.com.svma.bean.dto;

/**
*La clase tiene una clase vacía que funciona como un constructor y otra con un argumento
* Este argumento debe coincidir con el atributo que se encuentra en la clase Producto
* por eso proporcionamos id_producto
* @author Gloria y Fátima
*/
public class ProductoDTO {
public ProductoDTO() {
}
public ProductoDTO(int id_producto) {
this.id_producto = id_producto;
}
/**Definir los atributos de la clase Producto
* Estos atributos deben corresponder a cada una de las columnas
* de nuestra tabla producto
*/
private int id_producto;
private String descripcion;
private int cod_unidad;
private int stockActual;
private int stockMinimo;
private int precio;
/**
* Agregamos los métodos get y set para cada uno de los atributos
* Por ultimo sobrescribimos el método toString() para imprimir el estado
* de este objeto concatenando el valor de cada atributo del objeto
     * @return 
*/

public int getId_producto() {
return id_producto;
}
public void setId_producto(int idProducto) {
id_producto= idProducto;
}

public String getDescripcion() { // pongo en  mayuscula
return descripcion;
}
public void setDescripcion(String Descripcion) {
this.descripcion= Descripcion;
}

public int getCod_unidad() {
return cod_unidad;
}
public void setCod_unidad(int cod_unidad) {
this.cod_unidad=cod_unidad;
}

public int getStockActual() {
return stockActual;
}
public void setStockActual(int stockActual) {
this.stockActual=stockActual;
}


public int getStockMinimo() {
return stockMinimo;
}
public void setStockMinimo(int stockMinimo) {
this.stockMinimo=stockMinimo;
}


public int getPrecio() {
return precio;
}
public void setPrecio(int precio) {
this.precio=precio;
}

@Override
public String toString() {
return "Producto{" + "id_producto=" + id_producto + ", descripcion=" + descripcion + "cod_unidad=" + cod_unidad+
"stockActual=" + stockActual +"stockMinimo=" + stockMinimo + "precio=" + precio +'}';
}
}
