package ar.edu.utn.frba.dds.tp.antlr.dds;

public class ExpresionCompuesta implements IExpresion{

	private IExpresion Operando1, Operando2;
	private IOperador Operador;
	
	public ExpresionCompuesta(){
		
	}
	
	public ExpresionCompuesta(IExpresion op1, IExpresion op2, IOperador operador)  {
		this.Operando1 = op1;
		this.Operando2 = op2;
		this.Operador = operador;
	}
	
	public IExpresion getOperando1() {
		return Operando1;
	}

	public void setOperando1(IExpresion operando1) {
		Operando1 = operando1;
	}

	public IExpresion getOperando2() {
		return Operando2;
	}

	public void setOperando2(IExpresion operando2) {
		Operando2 = operando2;
	}

	public IOperador getOperador() {
		return Operador;
	}

	public void setOperador(IOperador operador) {
		Operador = operador;
	}

	
	
	public double calcularResultado(String empresa, Integer periodo){
		
		if(this.Operador == null){
			throw new RuntimeException("Operador no definido");
		}
		return this.Operador.calcularOperacion(this.Operando1, this.Operando2, empresa, periodo);
	}

//	public double calcularResultado(Empresa empresa, Integer periodo) {
//		
//		Indicador indicador = null;
//		if(this.Operador == null){
//			throw new RuntimeException("Operador no definido");
//		} 
//		else {
//		return this.Operador.calcularOperacion(this.Operando1, this.Operando2);
//		
//	}
	
}
