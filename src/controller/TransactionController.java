package br.com.foursys.fourcamp.fourstore.controller;

import br.com.foursys.fourcamp.fourstore.enums.PaymentMethod;
import br.com.foursys.fourcamp.fourstore.model.Product;
import br.com.foursys.fourcamp.fourstore.model.Transaction;
import br.com.foursys.fourcamp.fourstore.service.TransactionService;

@SuppressWarnings("ALL")
public class TransactionController {

	// cria objeto de transaction service
	TransactionService transactionService = new TransactionService();

	public String sell(String sku, Integer qtt, String CPF, Integer paymentMethod, String paymentData) {
		String result = "";
		
		//muda CPF para n�o informado caso usu�rio digite 0
		if(CPF.equals("0")) {
			CPF = "Não informado.";
		}
		
		
		PaymentMethod updatedPayment = updatePayment(paymentMethod);
		//se pagamento for dinheiro
		if(paymentMethod == 1) {
			paymentData = "Pagamento feito em dinheiro.";
		}
		
		//cria produto (sku e quantidade)
		Product product = new Product(sku, qtt);
		
		//cria objeto com os dados digitados pelo usu�rio
		Transaction transaction = new Transaction(updatedPayment, paymentData, 0.0, CPF, product);

		//chama m�todo da venda do objeto criado
		if(transactionService.sell(transaction)) {
			result = "Compra efetuada com sucesso.";
			System.out.println(transaction);
			System.out.println("");
			System.out.println("");
		}else {
			result = "Falha na compra, estoque insuficiente ou produto inexistente.";
		}
		
		return result;
	}
	
	
	public PaymentMethod updatePayment(Integer paymentMethod) {
		
		PaymentMethod updatedPayment = null;
		
		switch(paymentMethod) {	
		case 1:
			updatedPayment = PaymentMethod.MONEY;
			break;
		case 2:
			updatedPayment = PaymentMethod.DEBITCARD;
			break;
		case 3:
			updatedPayment = PaymentMethod.CREDITCARD;
			break;
		case 4:
			updatedPayment = PaymentMethod.PIX;
			break;
		}
		return updatedPayment;
		
	}

	public String paymentMoney() {
		String payment;
		payment = "Pagamento em dinheiro.";
		return payment;
	}
	
	public String paymentDebitCard(String nCartao, String vencCartao, String nomeCartao, Integer codCartao) {
		String payment;
		payment = "\nCartão de débito\n"
				+ "Número do cartão: "+nCartao+"\n"
				+ "Proprietário do cartão: "+nomeCartao+"\n"
				+ "Vencimento do cartão: "+vencCartao+"\n"
				+ "Código de segurança: "+codCartao+"\n";
		return payment;
	}
	
	public String paymentCreditCard(String nCartao, String vencCartao, String nomeCartao, Integer codCartao) {
		String payment;
		payment = "\nCartão de crédito\n"
				+ "Número do cartão: "+nCartao+"\n"
				+ "Proprietário do cartão: "+nomeCartao+"\n"
				+ "Vencimento do cartão: "+vencCartao+"\n"
				+ "Código de segurança: "+codCartao+"\n";
		return payment;
	}
	
	public String paymentPix(String pix) {
		String payment;
		payment = "========Pagamento PIX========/n"
				+ "Chave PIX: "+pix+"/n";
		return payment;
	}
	
	public String returnSellHistory() {

		return transactionService.returnSellHistory();
		}
	
}
