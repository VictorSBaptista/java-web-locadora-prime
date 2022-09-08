package br.com.foursys.locadora.backingbean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

import br.com.foursys.locadora.bean.Locacao;
import br.com.foursys.locadora.controller.LocacaoController;

/**
 * Classe respons�vel por controlar os componentes do front-end Filme
 * @author vicbaptista
 * @since 27/04/2021
 * @version 1.0
 */

@ManagedBean(name = "faturamentoBacking")
@ViewScoped
public class FaturamentoBacking implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private LineChartModel lineModel;
	private ArrayList<Locacao> listaLocacao;
	private ArrayList<Locacao> listaLocacaoAtual;
	private String anoAtual;
	private double faturamentoTotal;
	
	public FaturamentoBacking() {
		carregarListaTodasLocacao();
		anoAtual = carregaDataAtual();
		carregaLocacaoAtual();
		createLineModals();
		faturamentoTotal = getFatTotal();
	}

	/*
	 * Métodos getters e setters
	 */
	
	public LineChartModel getLineModel() {
		return lineModel;
	}

	public double getFaturamentoTotal() {
		return faturamentoTotal;
	}

	public void setFaturamentoTotal(double faturamentoTotal) {
		this.faturamentoTotal = faturamentoTotal;
	}

	public ArrayList<Locacao> getListaLocacaoAtual() {
		return listaLocacaoAtual;
	}

	public void setListaLocacaoAtual(ArrayList<Locacao> listaLocacaoAtual) {
		this.listaLocacaoAtual = listaLocacaoAtual;
	}

	public ArrayList<Locacao> getListaLocacao() {
		return listaLocacao;
	}

	public void setListaLocacao(ArrayList<Locacao> listaLocacao) {
		this.listaLocacao = listaLocacao;
	}

	public String getAnoAtual() {
		return anoAtual;
	}

	public void setAnoAtual(String anoAtual) {
		this.anoAtual = anoAtual;
	}

	public void setLineModel(LineChartModel lineModel) {
		this.lineModel = lineModel;
	}
	
	/*
	 * 
	 */
	private LineChartModel initCategoryModel() {
		LineChartModel model = new LineChartModel();
		
		ChartSeries ano = new ChartSeries();
		ano.setLabel(anoAtual);
		ano.set("Janeiro", getFaturamentoJaneiro());
		ano.set("Fevereiro", getFaturamentoFevereiro());
		ano.set("Março", getFaturamentoMarco());
		ano.set("Abril", getFaturamentoAbril());
		ano.set("Maio", getFaturamentoMaio());
		ano.set("Junho", getFaturamentoJunho());
		ano.set("Julho", getFaturamentoJulho());
		ano.set("Agosto", getFaturamentoAgosto());
		ano.set("Setembro", getFaturamentoSetembro());
		ano.set("Otubro", getFaturamentoOutubro());
		ano.set("Novembro", getFaturamentoNovembro());
		ano.set("Dezembro", getFaturamentoDezembro());
		
		ChartSeries ano1 = new ChartSeries();
		ano1.setLabel("Ano Passado");
		ano1.set("Janeiro", 20);
		ano1.set("Fevereiro", 10);
		ano1.set("Março", 70);
		ano1.set("Abril", 110);
		ano1.set("Maio", 90);
		ano1.set("Junho", 10);
		ano1.set("Julho", 30);
		ano1.set("Agosto", 180);
		ano1.set("Setembro", 150);
		ano1.set("Otubro", 130);
		ano1.set("Novembro", 190);
		ano1.set("Dezembro", 280);
		
		ChartSeries ano2 = new ChartSeries();
		ano2.setLabel("Ano Retrasado");
		ano2.set("Janeiro", 230);
		ano2.set("Fevereiro", 120);
		ano2.set("Março", 110);
		ano2.set("Abril", 60);
		ano2.set("Maio", 20);
		ano2.set("Junho", 80);
		ano2.set("Julho", 250);
		ano2.set("Agosto", 170);
		ano2.set("Setembro", 50);
		ano2.set("Otubro", 10);
		ano2.set("Novembro", 90);
		ano2.set("Dezembro", 190);
		
		model.addSeries(ano);
		model.addSeries(ano1);
		model.addSeries(ano2);
		
		return model;
	}
	
	private void createLineModals() {
		lineModel = initCategoryModel();
		lineModel.setTitle(anoAtual);
		lineModel.setLegendPosition("e");
		lineModel.setShowPointLabels(true);
		lineModel.getAxes().put(AxisType.X, new CategoryAxis("Meses"));
		Axis yAxis = lineModel.getAxis(AxisType.Y);
		yAxis.setLabel("Faturamento Mensal");
		yAxis.setMin(0);
		yAxis.setMax(500);
	}
	
	/*
	 * Método para carregar os dados da locação
	 */
	public void carregarListaTodasLocacao() {
		listaLocacao = new ArrayList<Locacao>();
		ArrayList<Locacao> listaLoc = new ArrayList<Locacao>();
		try {
			listaLoc = new LocacaoController().buscarTodos();
			for (Locacao loc : listaLoc) {
				if (!listaLocacao.contains(loc)) {
					listaLocacao.add(loc);
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private String carregaDataAtual() {
		Date date = new Date();
		String dataAtual;
		String ano;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dataAtual = dateFormat.format(date);
		
		String aux[] = dataAtual.split("/");
		ano = aux[2];
		return ano;
	}
	
	private void carregaLocacaoAtual() {
		listaLocacaoAtual = new ArrayList<Locacao>();
		String anoLoc;
		for (Locacao loc : listaLocacao) {
			String aux[] = loc.getDataLocacao().split("/");
			anoLoc = aux[2];
			if (anoLoc.equals(anoAtual)) {
				listaLocacaoAtual.add(loc);
			}
		}
	}
	
	public double getFaturamentoJaneiro() {
		double faturamento = 0.0;
		
		for (Locacao loca : listaLocacaoAtual) {
			String aux[] = loca.getDataLocacao().split("/");
			if (aux[1].equals("01")) {
				faturamento += loca.getValor();
			}
		}
		
		return faturamento;
	}
	public double getFaturamentoFevereiro() {
		double faturamento = 0.0;
		
		for (Locacao loca : listaLocacaoAtual) {
			String aux[] = loca.getDataLocacao().split("/");
			if (aux[1].equals("02")) {
				faturamento += loca.getValor();
			}
		}
		
		return faturamento;
	}
	public double getFaturamentoMarco() {
		double faturamento = 0.0;
		
		for (Locacao loca : listaLocacaoAtual) {
			String aux[] = loca.getDataLocacao().split("/");
			if (aux[1].equals("03")) {
				faturamento += loca.getValor();
			}
		}
		
		return faturamento;
	}
	public double getFaturamentoAbril() {
		double faturamento = 0.0;
		
		for (Locacao loca : listaLocacaoAtual) {
			String aux[] = loca.getDataLocacao().split("/");
			if (aux[1].equals("04")) {
				faturamento += loca.getValor();
			}
		}
		
		return faturamento;
	}
	public double getFaturamentoMaio() {
		double faturamento = 0.0;
		
		for (Locacao loca : listaLocacaoAtual) {
			String aux[] = loca.getDataLocacao().split("/");
			if (aux[1].equals("05")) {
				faturamento += loca.getValor();
			}
		}
		
		return faturamento;
	}
	
	public double getFaturamentoJunho() {
		double faturamento = 0.0;
		
		for (Locacao loca : listaLocacaoAtual) {
			String aux[] = loca.getDataLocacao().split("/");
			if (aux[1].equals("06")) {
				faturamento += loca.getValor();
			}
		}
		
		return faturamento;
	}
	public double getFaturamentoJulho() {
		double faturamento = 0.0;
		
		for (Locacao loca : listaLocacaoAtual) {
			String aux[] = loca.getDataLocacao().split("/");
			if (aux[1].equals("07")) {
				faturamento += loca.getValor();
			}
		}
		
		return faturamento;
	}
	public double getFaturamentoAgosto() {
		double faturamento = 0.0;
		
		for (Locacao loca : listaLocacaoAtual) {
			String aux[] = loca.getDataLocacao().split("/");
			if (aux[1].equals("08")) {
				faturamento += loca.getValor();
			}
		}
		
		return faturamento;
	}
	public double getFaturamentoSetembro() {
		double faturamento = 0.0;
		
		for (Locacao loca : listaLocacaoAtual) {
			String aux[] = loca.getDataLocacao().split("/");
			if (aux[1].equals("09")) {
				faturamento += loca.getValor();
			}
		}
		
		return faturamento;
	}
	public double getFaturamentoOutubro() {
		double faturamento = 0.0;
		
		for (Locacao loca : listaLocacaoAtual) {
			String aux[] = loca.getDataLocacao().split("/");
			if (aux[1].equals("10")) {
				faturamento += loca.getValor();
			}
		}
		
		return faturamento;
	}
	public double getFaturamentoNovembro() {
		double faturamento = 0.0;
		
		for (Locacao loca : listaLocacaoAtual) {
			String aux[] = loca.getDataLocacao().split("/");
			if (aux[1].equals("11")) {
				faturamento += loca.getValor();
			}
		}
		
		return faturamento;
	}
	public double getFaturamentoDezembro() {
		double faturamento = 0.0;
		
		for (Locacao loca : listaLocacaoAtual) {
			String aux[] = loca.getDataLocacao().split("/");
			if (aux[1].equals("12")) {
				faturamento += loca.getValor();
			}
		}
		
		return faturamento;
	}
	public double getFatTotal() {
		double fatTotal = 0.0;
		fatTotal = getFaturamentoJaneiro() + getFaturamentoFevereiro() + getFaturamentoMarco() + getFaturamentoAbril() + getFaturamentoMaio() + getFaturamentoJunho() + getFaturamentoJulho() + getFaturamentoAgosto() + getFaturamentoSetembro() + getFaturamentoOutubro() + getFaturamentoNovembro() + getFaturamentoDezembro();
		
		return fatTotal;
	}
}
