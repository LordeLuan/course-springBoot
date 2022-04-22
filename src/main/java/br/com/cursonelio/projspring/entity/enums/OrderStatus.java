package br.com.cursonelio.projspring.entity.enums;

public enum OrderStatus {

	WAITING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);
	
//	Código dos valores enumerado
	private int code;
	
	private OrderStatus(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

//	Metodo para recuperar o atributo da posição de acordo com o número passado ex: passado 1 retorna Waiting_Paiment
	public static OrderStatus valueOf (int code) {
//		Percorre todos os valores cadastrados no enum e compara com o código passado
		for(OrderStatus value: OrderStatus.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
//		Quando não encontrado um status do código irá retornar uma exceção
		throw new IllegalArgumentException("Invalid OrserStatus code");
		
	}
}
