import java.sql.Connection;

public class TesteConexao {
    public static void main(String[] args) {
        Connection conn = Conexao.getConexao();
        System.out.println("Conexão realizada com sucesso!"+conn);
    }
}
