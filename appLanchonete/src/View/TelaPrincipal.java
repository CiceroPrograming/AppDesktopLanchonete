package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Model.Pedido;
import Repository.Conexao;
import Repository.PedidoRepository;

public class TelaPrincipal {
    List<Pedido> pedidos = new ArrayList<>();
    JFrame frame = new JFrame();
    JPanel painel = new JPanel();
    JTable tabela = new JTable();
    JLabel dadosCliente = new JLabel();
    JLabel nome = new JLabel();
    JTextArea tNome = new JTextArea();
    JLabel cpf = new JLabel();
    JTextArea tCpf = new JTextArea();
    JLabel sexo = new JLabel();
    ButtonGroup grupoGeneros = new ButtonGroup();
    JRadioButton masculino = new JRadioButton();
    JRadioButton feminino = new JRadioButton();
    JLabel formaPagamento = new JLabel();
    JComboBox comboFormaPagamento = new JComboBox<>();
    JLabel dadosProduto = new JLabel();
    JLabel produto = new JLabel();
    JTextArea tProduto = new JTextArea();
    JLabel preco = new JLabel();
    JTextArea tPreco = new JTextArea();
    JLabel quantidade = new JLabel();
    JTextArea tQuantidade = new JTextArea();
    JLabel totalPedido = new JLabel();
    JTextArea tTotalPedido = new JTextArea();
    JLabel carrinhoDeCompras = new JLabel();
    JButton fazerPedido = new JButton();
    JButton finalizarPedido = new JButton();
    JButton removerItem = new JButton();
    PedidoRepository repository = new PedidoRepository();
    DefaultTableModel model = new DefaultTableModel();
    Pedido pedido = new Pedido();

    public void criaTelaCadastro() {
        frame.setSize(960, 650);
        frame.setVisible(true);
        painel.setLayout(null);
        painel.setSize(950, 650);
        frame.add(painel);

        JLabel lTitulo = new JLabel();
        lTitulo.setText("PEDIDOS");
        lTitulo.setBounds(450, 5, 450, 40);
        painel.add(lTitulo);
        dadosCliente.setText("Dados do cliente:");
        dadosCliente.setBounds(30, 30, 100, 60);
        painel.add(dadosCliente);

        carrinhoDeCompras.setText("Carrinho de compras:");
        carrinhoDeCompras.setBounds(450, 30, 140, 70);
        painel.add(carrinhoDeCompras);
        tabela.setBounds(450, 80, 450, 300);
        painel.add(tabela);

        nome.setText("Nome:");
        nome.setBounds(30, 70, 50, 60);
        painel.add(nome);
        tNome.setBounds(75, 92, 150, 20);
        painel.add(tNome);

        cpf.setText("CPF:");
        cpf.setBounds(30, 135, 50, 60);
        painel.add(cpf);
        tCpf.setBounds(70, 156, 150, 20);
        painel.add(tCpf);

        sexo.setText("Sexo:");
        sexo.setBounds(250, 70, 50, 60);
        painel.add(sexo);

        masculino.setText("Masculino");
        masculino.setBounds(285, 92, 120, 20);
        grupoGeneros.add(masculino);
        feminino.setBounds(285, 112, 120, 20);
        feminino.setText("Feminino");
        grupoGeneros.add(feminino);
        painel.add(masculino);
        painel.add(feminino);

        dadosProduto.setText("Dados do produto:");
        dadosProduto.setBounds(30, 290, 140, 70);
        painel.add(dadosProduto);

        quantidade.setText("Quantidade:");
        quantidade.setBounds(250, 470, 70, 70);
        painel.add(quantidade);
        tQuantidade.setBounds(325, 495, 120, 20);
        painel.add(tQuantidade);

        totalPedido.setText("Total pedido: R$");
        totalPedido.setBounds(600, 470, 140, 70);
        painel.add(totalPedido);
        tTotalPedido.setBounds(700, 495, 205, 20);
        tTotalPedido.setEditable(false);
        painel.add(tTotalPedido);

        fazerPedido.setText("Fazer pedido");
        fazerPedido.setBounds(200, 580, 150, 20);
        painel.add(fazerPedido);
        fazerPedido.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Pedido pedido = new Pedido();
                    pedido.setnomeCliente(tNome.getText());
                    pedido.setCpf(tCpf.getText());
                    if (feminino.isSelected()) {
                        pedido.setSexo("Feminino");
                    } else if (masculino.isSelected()) {
                        pedido.setSexo("Masculino");
                    }
                    pedido.setnomeProduto(tProduto.getText());
                    pedido.setvalorUnitario(Integer.parseInt(tPreco.getText()));
                    pedido.setQuantidade(Integer.parseInt(tQuantidade.getText()));

                    repository.inserePedido(pedido);

                    tProduto.setText(null);
                    tPreco.setText(null);
                    tQuantidade.setText(null);

                } catch (Exception e1) {
                    e1.printStackTrace();
                } finally {
                    new consultaAction().actionPerformed(e);
                }

            }
        });

        finalizarPedido.setText("Finalizar pedido");
        finalizarPedido.setBounds(400, 580, 150, 20);
        finalizarPedido.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) tabela.getModel();
                model.setRowCount(0);
                TableColumnModel columnModel = tabela.getColumnModel();
                columnModel.removeColumn(null);

                try {
                    repository.limparPedido();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            
            
        });
        painel.add(finalizarPedido);

        removerItem.setText("Remover pedido");
        removerItem.setBounds(600, 580, 150, 20);
        removerItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int linhaSelecionada = tabela.getSelectedRow();
                try {
                    repository.removerPedido((int) tabela.getValueAt(linhaSelecionada, 0));
                } catch (Exception e1) {
                }
                new consultaAction().actionPerformed(e);
            }
        });

        painel.add(removerItem);

        formaPagamento.setText("Forma de pagamento:");
        formaPagamento.setBounds(30, 200, 130, 70);
        painel.add(formaPagamento);
        comboFormaPagamento.setBounds(170, 225, 150, 20);
        painel.add(comboFormaPagamento);

        preco.setText("Preço: R$");
        preco.setBounds(30, 470, 70, 70);
        painel.add(preco);
        tPreco.setBounds(90, 495, 120, 20);
        painel.add(tPreco);

        produto.setText("Produto:");
        produto.setBounds(30, 380, 70, 70);
        painel.add(produto);
        tProduto.setBounds(85, 407, 120, 20);
        painel.add(tProduto);

        comboFormaPagamento.addItem("Débito");
        comboFormaPagamento.addItem("Crédito");
        comboFormaPagamento.addItem("PIX");

    }

    public class consultaAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<Pedido> pedidos = new ArrayList<>();

            while (tabela.getModel().getRowCount() > 0) {
                ((DefaultTableModel) tabela.getModel()).setRowCount(0);
                ((DefaultTableModel) tabela.getModel()).setColumnCount(0);
            }

            try {
                pedidos = repository.consultar();
            } catch (SQLException e1) {

                e1.printStackTrace();
            }

            model.addColumn("Código");
            model.addColumn("Nome");
            model.addColumn("Qtd");
            model.addColumn("Valor");

            for (int i = 0; i < pedidos.size(); i++) {
                pedido = pedidos.get(i);
                model.addRow(new Object[] { pedido.getCodigo(), pedido.getnomeProduto(), pedido.getQuantidade(), pedido.getvalorUnitario() });
            }

            tabela.setModel(model);
    }
 }
}

// listaProdutos.setBounds(90, 405, 150, 20);

// Trás o valor do produto automático no campo "Preço: R$"

// listaProdutos.addActionListener(new ActionListener() {

/*
 * @Override
 * public void actionPerformed(ActionEvent e) {
 * Pedido pedidoSelecionado = (Pedido) listaProdutos.getSelectedItem();
 * double preco = pedidoSelecionado.getvalorUnitario();
 * tPreco.setText("" + preco);
 * }
 * });
 */

// painel.add(listaProdutos);

/*
 * Lista de produtos num ArrayList
 * Pedido pedido1 = new Pedido(1, "Refri", 5.00);
 * Pedido pedido2 = new Pedido(2, "Batata", 8.00);
 * Pedido pedido3 = new Pedido(3, "Hamburguer", 14.00);
 * Pedido pedido4 = new Pedido(4, "Sorvete", 7.50);
 * listaProdutos.addItem(pedido1);
 * listaProdutos.addItem(pedido2);
 * listaProdutos.addItem(pedido3);
 * listaProdutos.addItem(pedido4);
 */
