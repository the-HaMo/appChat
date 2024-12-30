package Aplicacion;

public class DescuentoNumMensajes implements Descuento{

	@Override
	public double getDescuento(double precio) {
		// TODO Auto-generated method stub
		return precio*0.9;
	}

}
