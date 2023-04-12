package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Pedido;

public class PedidoRepository {

    public void inserePedido(Pedido pedido) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();

        try {

            String sql = "INSERT INTO pedido (CODIGO, NOMECLIENTE, CPF, SEXO, NOMEPRODUTO, VALORUNITARIO, QUANTIDADE, VALORTOTAL)"
                    + "VALUES (NEXTVAL('CODIGO_SEQ'), ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstm;
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, pedido.getnomeCliente());
            pstm.setString(2, pedido.getCpf());
            pstm.setString(3, pedido.getSexo());
            pstm.setString(4, pedido.getnomeProduto());
            pstm.setDouble(5, pedido.getvalorUnitario());
            pstm.setInt(6, pedido.getQuantidade());
            pstm.setDouble(7, pedido.getvalorTotal());

            pstm.execute();

        } catch (Exception e) {
            System.out.println("Não conseguiu inserir o pedido");
        } finally {
            conexao.desconectar(conn);
        }

    }

    public ArrayList<Pedido> consultar() throws SQLException {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        ArrayList<Pedido> pedidos = new ArrayList<>();

        String sql = "SELECT codigo, nomeproduto, quantidade, valorunitario FROM pedido";

        Statement stm = conn.createStatement();
        ResultSet resultado = stm.executeQuery(sql);

        try {
            while (resultado.next()) {
                Pedido pedido = new Pedido();
                pedido.setCodigo(resultado.getInt("codigo"));
                pedido.setnomeProduto(resultado.getString("nomeproduto"));
                pedido.setQuantidade(resultado.getInt("quantidade"));
                pedido.setvalorUnitario(resultado.getDouble("valorunitario"));
                pedidos.add(pedido);
            } 
        } catch (Exception e) {
            System.out.println("Não conseguiu consultar a tabela Pedido");
            
        } finally {
            conexao.desconectar(conn);
        }

        return pedidos;
    }

    public void removerPedido(int id) throws Exception {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();

        try{
            String sql = "DELETE FROM pedido WHERE codigo = ?";

            PreparedStatement stm;
            stm = conn.prepareStatement(sql);
            stm.setInt(1, id);
            stm.execute();
        } catch (Exception e) {
            System.out.println("NAO EXCLUIU");
        } finally {
            conexao.desconectar(conn);
        }
    }

    public void limparPedido() throws Exception {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();

        try{
            String sql = "DELETE FROM pedido";

            PreparedStatement stm;
            stm = conn.prepareStatement(sql);
            
            stm.execute();
        } catch (Exception e) {
            System.out.println("NAO EXCLUIU");
        } finally {
            conexao.desconectar(conn);
        }
        
    }
}