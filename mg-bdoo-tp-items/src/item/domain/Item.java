package item.domain;

import transicion.domain.Transicion;
import usuario.domain.Usuario;
import estado.domain.Estado;

public class Item {

	
	public void guardarHistorico(){}
	public void cambiarEstado(Transicion unaTransicion){}
	public void cambiarResponsable(Usuario unUsuario){}
	public Boolean estadoEliminado(){
		return false;
	} 
	public void asignarEstado(Estado unEstado){}
}
