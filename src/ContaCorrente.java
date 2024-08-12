import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContaCorrente extends Conta {
	protected List<Investimento> Investimentos;

	public ContaCorrente(Cliente cliente) {
		super(cliente);
		this.Investimentos = new ArrayList<>();
	}

	void Investir(String tipoInvestimento, double valorInicial, LocalDate dataInicio, LocalDate dataVencimento,
			double taxaJuros) {

		if (valorInicial <= this.saldo) {
			Investimento novoInvestimento = new Investimento(tipoInvestimento, valorInicial, dataInicio, dataVencimento,
					taxaJuros);
			Investimentos.add(novoInvestimento);
			this.saldo = this.saldo - valorInicial;
		}

	}

	@Override
	public void imprimirExtrato() {
		System.out.println("=== Extrato Conta Corrente ===");
		super.imprimirInfosComuns();
	}

}
