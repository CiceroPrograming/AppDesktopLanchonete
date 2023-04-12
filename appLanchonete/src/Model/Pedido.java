package Model;

public class Pedido {
    public int codigo;
    public String nomeCliente;
    public String cpf;
    public String sexo;
    public String nomeProduto;
    public double valorUnitario;
    public int quantidade;
    public double valorTotal;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getnomeCliente() {
        return nomeCliente;
    }

    public void setnomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getnomeProduto() {
        return nomeProduto;
    }

    public void setnomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getvalorUnitario() {
        return valorUnitario;
    }

    public void setvalorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getvalorTotal() {
        return this.valorTotal;
    }

    public void setvalorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    /*
     * public Pedido(int codigo, String nomeProduto, double valorUnitario) {
     * this.codigo = codigo;
     * this.nomeProduto = nomeProduto;
     * this.valorUnitario = valorUnitario;
     * }
     */

}
