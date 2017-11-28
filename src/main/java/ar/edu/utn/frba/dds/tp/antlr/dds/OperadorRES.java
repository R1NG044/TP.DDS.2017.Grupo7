package ar.edu.utn.frba.dds.tp.antlr.dds;

public class OperadorRES implements IOperador {
	@Override
	public double calcularOperacion(IExpresion expresion1, IExpresion expresion2,String empresa,Integer periodo) throws Exception {

		if (expresion1 == null || expresion2 == null) {
			throw new RuntimeException();
		} 
		else if (expresion2.getOperador() instanceof OperadorRES || expresion2.getOperador() instanceof OperadorSUM) {
			ExpresionCompuesta aux = new ExpresionCompuesta(expresion1, expresion2.getOperando1(), new OperadorRES());
			return expresion2.getOperador().calcularOperacion(aux, (expresion2.getOperando2()), empresa, periodo);
//			double aux;
//			aux = (expresion2.getOperando1().calcularResultado(empresa, periodo))*(-2);
//			
//			return (expresion1.calcularResultado(empresa, periodo) + aux + expresion2.calcularResultado(empresa, periodo) );
		}
		else{
			return (expresion1.calcularResultado(empresa, periodo) - expresion2.calcularResultado(empresa, periodo) );
		}
	}

	@Override
	public String getSimbolo() {
		return "-";
	}

}
