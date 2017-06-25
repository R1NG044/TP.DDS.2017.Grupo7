package ar.edu.utn.frba.dds.tp.antlr.dds;

public class OperadorRES implements IOperador {
	@Override
	public double calcularOperacion(IExpresion expresion1, IExpresion expresion2) {

		if (expresion1 == null || expresion2 == null) {
			throw new RuntimeException();
		} 
		else if (expresion2.getOperador() instanceof OperadorRES || expresion2.getOperador() instanceof OperadorSUM) {
			double aux;
			aux = (expresion2.getOperando1().calcularResultado())*(-2);
			
			return (expresion1.calcularResultado() + aux + expresion2.calcularResultado() );
		}
		else{
			return (expresion1.calcularResultado() - expresion2.calcularResultado() );
		}
	}

	@Override
	public String getSimbolo() {
		return "-";
	}

}
