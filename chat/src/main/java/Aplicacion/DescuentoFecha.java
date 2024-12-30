package Aplicacion;

import java.time.LocalDate;

import Controlador.Controlador;

public class DescuentoFecha implements Descuento {

	@Override
	public double getDescuento(double precio) {
        LocalDate fechaReg = Controlador.INSTANCE.getUsuarioActual().getFechaRegistro();
        LocalDate fechaActual = LocalDate.now();

        if (fechaActual.getMonth() == fechaReg.getMonth() && fechaActual.getDayOfMonth() == fechaReg.getDayOfMonth()) {
            return precio * 0.5;
        }
        return precio;
    }

}
