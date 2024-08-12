import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Investimento {

	private static final String ATIVO = "Ativo";
	private static final String ENCERRADO = "Encerrado";
	private static int ID = 1;

	private int idInvestimento;
	private String tipoInvestimento;
	private double valorInicial;
	private LocalDate dataInicio;
	private LocalDate dataVencimento;
	private double taxaJuros;
	private double saldoAtual;
	private String status;

	public Investimento(String tipoInvestimento, double valorInicial, LocalDate dataInicio, LocalDate dataVencimento,
			double taxaJuros) {
		this.idInvestimento = ID++;
		this.tipoInvestimento = tipoInvestimento;
		this.valorInicial = valorInicial;
		this.dataInicio = dataInicio;
		this.dataVencimento = dataVencimento;
		this.taxaJuros = taxaJuros;
		this.saldoAtual = valorInicial;
		this.status = ATIVO;

	}

	protected void exibirDetalhes() {
		atualizarSaldo();
		System.out.println("### Investimento " + this.idInvestimento + " ###");
		System.out.println("Tipo: " + this.tipoInvestimento);
		System.out.println("Valor inicial: " + this.valorInicial);
		System.out.println("Data Inicial: " + this.dataInicio);
		System.out.println("Data Vencimento: " + this.dataVencimento);
		System.out.println("Taxa de juros: " + this.taxaJuros);
		System.out.println("Saldo Atual: " + this.saldoAtual);
		System.out.println("Status: " + this.status);
	}

	private double calcularRendimento() {

		if (LocalDate.now().isBefore(this.dataVencimento)) {
			long dias = ChronoUnit.DAYS.between(this.dataInicio, LocalDate.now());
			double anos = dias / 365.0;
			double rendimento = this.valorInicial * (this.taxaJuros / 100) * anos;
			return rendimento;
		}
		long dias = ChronoUnit.DAYS.between(this.dataInicio, this.dataVencimento);
		double anos = dias / 365.0;
		double rendimento = this.valorInicial * (this.taxaJuros / 100) * anos;
		return rendimento;

	}

	protected boolean resgatarInvestimento(Conta conta) {
		if (validarResgate()) {
			conta.depositar(this.saldoAtual);
			this.status = ENCERRADO;
		}
		return false;
	}

	private boolean validarResgate() {
		if (LocalDate.now().isEqual(this.dataVencimento) || LocalDate.now().isAfter(this.dataVencimento)) {
			return true;
		}
		return false;
	}

	private boolean atualizarSaldo() {
		if (this.status.equals(ATIVO))
			this.saldoAtual = calcularRendimento() + this.saldoAtual;
		return false;
	}

}
