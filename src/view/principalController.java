package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import util.Mensagens;
import domain.Cliente;

public class principalController {

    @FXML private TextField txtProd;
    @FXML private TextField txtVUnitario;
    @FXML private TextField txtQt;

    @FXML private TableView<Cliente> tbl;
    @FXML private TableColumn<Cliente, String> colProd;
    @FXML private TableColumn<Cliente, Number> colVUnit;
    @FXML private TableColumn<Cliente, Number> colQt;
    @FXML private TableColumn<Cliente, Number> colSub;

    @FXML private Label lblSDesc;
    @FXML private Label lblDesc;
    @FXML private Label lblVFinal;
    
    
    @FXML
    public void initialize() {
    	colProd.setCellValueFactory(cellData -> cellData.getValue().produtoProperty());
    	colQt.setCellValueFactory(cellData -> cellData.getValue().qtdadeProperty());
    	colVUnit.setCellValueFactory(cellData -> cellData.getValue().vUnitarioProperty());
    	colSub.setCellValueFactory(cellData -> cellData.getValue().subProperty());
    	lerArquivo();
    }
        
    @FXML
    void btAd() {
    	try {
    		String produto = txtProd.getText().replaceAll(",", "");
    		if (produto.isEmpty()) {
    			Mensagens.msgErro("ERRO", "Campo produto vazio \n");
    			txtProd.setAccessibleText("");
    			txtProd.requestFocus();
    		}
    		
    		if (produto.contains(",")) {
    			Mensagens.msgErro("ERRO", "Campo contendo vírgula \n");
    			txtProd.setAccessibleText("");
    			txtProd.requestFocus();
    		}
    		
    		String vUnitario = txtVUnitario.getText().replaceAll(",", ".");
    		if(Double.parseDouble(vUnitario) <= 0) {
    			Mensagens.msgErro("ERRO", "Valor inválido \n");
    			txtProd.setAccessibleText("");
    			txtProd.requestFocus();
    		}
       		if (vUnitario.equals(null)) {
    			Mensagens.msgErro("ERRO", "Campo produto vazio \n");
    			txtProd.setAccessibleText("");
    			txtProd.requestFocus();
    		}
    		if (vUnitario.equals(toString())) {
    			Mensagens.msgErro("ERRO", "Valor inválido \n");
    			txtProd.setAccessibleText("");
    			txtProd.requestFocus();
    		}
    		    		
    		String qtdade = txtQt.getText().replaceAll(",", "");
    		if(Integer.parseInt(qtdade) <= 0) {
    			Mensagens.msgErro("ERRO", "Valor inválido \n");
    			txtProd.setAccessibleText("");
    			txtProd.requestFocus();
    		}
    		
    		if (qtdade.equals(null)) {
    			Mensagens.msgErro("ERRO", "Campo produto vazio \n");
    			txtProd.setAccessibleText("");
    			txtProd.requestFocus();
    		}

    		
       		String linha = produto + "," + vUnitario + "," + qtdade + "\n";
    		FileWriter fw = new FileWriter("dados.txt", true);
    		BufferedWriter bw = new BufferedWriter(fw);
    		bw.append(linha);
    		bw.close();
    		lerArquivo();
    		//lblSDesc.setText("Valor sem desconto R$: " + getcalculaTotal());
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    
    public void lerArquivo() {
    	ArrayList<Cliente> lista = new ArrayList<Cliente>();
    	try {
			String linha = "";
			FileReader fr = new FileReader("dados.txt");
			BufferedReader br = new BufferedReader(fr);
			while((linha = br.readLine())!=null) {
				String[] vetor = linha.split(",");
				Cliente c = new Cliente();
				c.setProduto(vetor[0]);
				c.setVUnitario(Double.parseDouble(vetor[1]));
				c.setQtdade(Integer.parseInt(vetor[2]));
				lista.add(c);
			}
			br.close();
			tbl.setItems(FXCollections.observableArrayList(lista));
		} catch (Exception e) {
			e.printStackTrace();
		}
   }
    
}
