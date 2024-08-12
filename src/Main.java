import java.time.LocalDate;

public class Main {

	public static void main(String[] args) {
		Cliente filipe = new Cliente();
		filipe.setNome("filipe");

		ContaCorrente cc = new ContaCorrente(filipe);
		Conta poupanca = new ContaPoupanca(filipe);

		cc.depositar(10000);
		cc.transferir(100, poupanca);

		cc.imprimirExtrato();
		poupanca.imprimirExtrato();

		cc.Investir("CDB", 500, LocalDate.of(2024, 01, 01), LocalDate.of(2024, 07, 31), 12); // Realiza um investimento de 500 no dia 01/01/2024 com fim em 31/07/2024, com taxa de juros anual de 12%
		cc.imprimirExtrato(); // O saldo deverá ser 9400

		for (Investimento investimento : cc.Investimentos) {
			investimento.exibirDetalhes();
			investimento.resgatarInvestimento(cc); // Atualiza o saldo da conta com o investimento realizado,caso este possa ser resgatado no momento.

		}
		cc.imprimirExtrato(); // O saldo deverá ser 9400 + o saldo do investimento.

	}

}
