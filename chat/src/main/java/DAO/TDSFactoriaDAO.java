package DAO;
/*

/** 
 * Factoria concreta DAO para el Servidor de Persistencia de la asignatura TDS.
 * 
 */

public final class TDSFactoriaDAO extends FactoriaDAO {
	
	public TDSFactoriaDAO() {	}
	
	//@Override
	@Override
	public TDSUsuarioDAO getUsuarioDAO() {
		// TODO Auto-generated method stub
		return new TDSUsuarioDAO();
	}

}
