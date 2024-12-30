package Aplicacion;

public class DescuentoFecha implements Descuento {

	@Override
	public double getDescuento(double precio) {
		// TODO Auto-generated method stub
		return precio * 0.8;
	}

}
