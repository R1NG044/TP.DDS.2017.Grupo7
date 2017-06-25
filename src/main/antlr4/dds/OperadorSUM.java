package dds;

public class OperadorSUM implements IOperador {
	@Override
	public double calcularOperacion(IExpresion expresion1, IExpresion expresion2){
		
		if(expresion1 == null || expresion2 == null){
			throw new RuntimeException();
		}
		
		return (expresion1.calcularResultado() + expresion2.calcularResultado());
	}
	
	@Override
	public String getSimbolo(){
		return "+";
	}
}
