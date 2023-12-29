package domain;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cliente {

	private StringProperty produto   = new SimpleStringProperty("");
	private DoubleProperty vUnitario = new SimpleDoubleProperty (0);
	private IntegerProperty qtdade   = new SimpleIntegerProperty (0);
	private DoubleProperty sub       = new SimpleDoubleProperty (0);
	
	public double calculaSubtotal() {
		return getVUnitario() * getQtdade();
	}

	public final StringProperty produtoProperty() {
		return this.produto;
	}
	

	public final String getProduto() {
		return this.produtoProperty().get();
	}
	

	public final void setProduto(final String produto) {
		this.produtoProperty().set(produto);
	}
	

	public final DoubleProperty vUnitarioProperty() {
		return this.vUnitario;
	}
	

	public final double getVUnitario() {
		return this.vUnitarioProperty().get();
	}
	

	public final void setVUnitario(final double vUnitario) {
		this.vUnitarioProperty().set(vUnitario);
	}
	

	public final IntegerProperty qtdadeProperty() {
		return this.qtdade;
	}
	

	public final int getQtdade() {
		return this.qtdadeProperty().get();
	}
	

	public final void setQtdade(final int qtdade) {
		this.qtdadeProperty().set(qtdade);
	}

	public final DoubleProperty subProperty() {
		return new SimpleDoubleProperty(calculaSubtotal());
	}
	
}
