import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConsultarOrientacoes {
    public static void main(String[] args) {
        try {
            Connection conexao = Conexao.getConexao(); 

            Statement stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM orientacoes");

            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String tipo = rs.getString("tipo");
                String conteudo_pt = rs.getString("conteudo_pt");

                System.out.println("ID: " + id);
                System.out.println("Titulo: " + titulo);
                System.out.println("Tipo: " + tipo);
                System.out.println("Conteudo (PT): " + conteudo_pt);
                System.out.println("------------------------------");
            }

            conexao.close();
        } catch (Exception e) {
            e.printStackTrace();  // Mostra erro se ocorrer
        }
    }
}
