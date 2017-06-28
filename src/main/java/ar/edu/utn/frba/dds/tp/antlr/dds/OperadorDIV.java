package ar.edu.utn.frba.dds.tp.antlr.dds;

public class OperadorDIV implements IOperador {
	

	@Override
	public double calcularOperacion(IExpresion expresion1, IExpresion expresion2,String empresa,Integer periodo) {

		if (expresion1 == null || expresion2 == null) {
			throw new RuntimeException();
		} else if (expresion2 instanceof ExpresionCompuesta) {
			ExpresionCompuesta aux = new ExpresionCompuesta(expresion1, expresion2.getOperando1(), new OperadorDIV());
			return expresion2.getOperador().calcularOperacion(aux, (expresion2.getOperando2()), empresa, periodo);
		} else {
			return (expresion1.calcularResultado(empresa, periodo) / expresion2.calcularResultado(empresa, periodo));
		}
	}

	@Override
	public String getSimbolo() {
		return "/";
	}
}
