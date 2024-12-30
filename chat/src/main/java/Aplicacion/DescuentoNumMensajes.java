package Aplicacion;

import Controlador.Controlador;

public class DescuentoNumMensajes implements Descuento{

	@Override
	public double getDescuento(double precio) {
		// TODO Auto-generated method stub
		if (Controlador.INSTANCE.getNumMensajesEnviados() >= 30) {
			return precio * 0.8;
		}
		return precio;
	}

}
