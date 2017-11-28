package ar.edu.utn.frba.dds.tp.antlr.dds;

public class OperadorSUM implements IOperador {
	@Override
	public double calcularOperacion(IExpresion expresion1, IExpresion expresion2,String empresa,Integer periodo) throws Exception {
		
		if(expresion1 == null || expresion2 == null){
			throw new RuntimeException();
		}
		
		return (expresion1.calcularResultado(empresa, periodo) + expresion2.calcularResultado(empresa, periodo));
	}
	
	@Override
	public String getSimbolo(){
		return "+";
	}
}
