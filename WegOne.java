import java.util.Scanner;

public class WegOne {
    static final int MAX_ORIENTACOES = 100;

    static String[] titulos = new String[MAX_ORIENTACOES];
    static String[] tipos = new String[MAX_ORIENTACOES];
    static String[] conteudosPT = new String[MAX_ORIENTACOES];
    static String[] conteudosEN = new String[MAX_ORIENTACOES];
    static String[] conteudosDE = new String[MAX_ORIENTACOES];

    static int totalOrientacoes = 10;
    static String idiomaSelecionado = "PT";

    public static void main(String[] args) {
        inicializarOrientacoes();
        escolherIdioma();

        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            exibirMenu();
            System.out.print("> ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpa o buffer

            switch (opcao) {
                case 1:
                    cadastrarOrientacao(sc);
                    break;
                case 2:
                    pesquisarOrientacao(sc);
                    break;
                case 3:
                    editarOrientacao(sc);
                    break;
                case 4:
                    excluirOrientacao(sc);
                    break;
                case 5:
                    System.out.println(getTexto("Saindo...", "Exiting...", "Beende..."));
                    break;
                default:
                    System.out.println(getTexto("Opção inválida.", "Invalid option.", "Ungültige Option."));
            }

        } while (opcao != 5);

        sc.close();
    }

    static void inicializarOrientacoes() {
        for (int i = 0; i < 10; i++) {
            titulos[i] = "Orientação " + (i + 1);
            tipos[i] = "Manual de Operação";
            conteudosPT[i] = "Conteúdo em português da orientação " + (i + 1);
            conteudosEN[i] = "English content for guidance " + (i + 1);
            conteudosDE[i] = "Deutscher Inhalt der Anleitung " + (i + 1);
        }
    }

    static void escolherIdioma() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escolha o idioma | Choose the language | Sprache wählen:");
        System.out.println("1 - Português\n2 - English\n3 - Deutsch");
        int escolha = sc.nextInt();

        switch (escolha) {
            case 2:
                idiomaSelecionado = "EN";
                break;
            case 3:
                idiomaSelecionado = "DE";
                break;
            default:
                idiomaSelecionado = "PT";
                break;
        }
    }

    static void exibirMenu() {
        System.out.println("\n==== WegOne ====");
        switch (idiomaSelecionado) {
            case "EN":
                System.out.println("1 - Register Guidance");
                System.out.println("2 - Search Guidance");
                System.out.println("3 - Edit Guidance");
                System.out.println("4 - Delete Guidance");
                System.out.println("5 - Exit");
                break;
            case "DE":
                System.out.println("1 - Anleitung registrieren");
                System.out.println("2 - Anleitung suchen");
                System.out.println("3 - Anleitung bearbeiten");
                System.out.println("4 - Anleitung löschen");
                System.out.println("5 - Beenden");
                break;
            default:
                System.out.println("1 - Cadastrar Orientação");
                System.out.println("2 - Pesquisar Orientação");
                System.out.println("3 - Editar Orientação");
                System.out.println("4 - Excluir Orientação");
                System.out.println("5 - Sair");
        }
    }

    static void cadastrarOrientacao(Scanner sc) {
        if (totalOrientacoes >= MAX_ORIENTACOES) {
            System.out.println(getTexto("Limite de orientações atingido.", "Guidance limit reached.", "Orientierungslimit erreicht."));
            return;
        }

        System.out.println(getTexto("Título da orientação:", "Guidance title:", "Titel der Anleitung:"));
        String titulo = sc.nextLine();

        System.out.println(getTexto("Tipo (1-Manual, 2-Segurança, 3-Manutenção, 4-Testes, 5-Conduta):",
                                    "Type (1-Manual, 2-Safety, 3-Maintenance, 4-Tests, 5-Conduct):",
                                    "Typ (1-Handbuch, 2-Sicherheit, 3-Wartung, 4-Tests, 5-Verhalten):"));
        int tipo = sc.nextInt();
        sc.nextLine();

        String tipoTexto;
        switch (tipo) {
            case 2: tipoTexto = "Procedimento de Segurança"; break;
            case 3: tipoTexto = "Manutenção e Reparos"; break;
            case 4: tipoTexto = "Testes e Diagnóstico"; break;
            case 5: tipoTexto = "Manual de Conduta e Operações Setoriais"; break;
            default: tipoTexto = "Manual de Operação"; break;
        }

        System.out.println("Conteúdo PT:");
        String pt = sc.nextLine();
        System.out.println("Content EN:");
        String en = sc.nextLine();
        System.out.println("Inhalt DE:");
        String de = sc.nextLine();

        titulos[totalOrientacoes] = titulo;
        tipos[totalOrientacoes] = tipoTexto;
        conteudosPT[totalOrientacoes] = pt;
        conteudosEN[totalOrientacoes] = en;
        conteudosDE[totalOrientacoes] = de;

        totalOrientacoes++;

        System.out.println(getTexto("Orientação cadastrada com sucesso.", "Guidance successfully registered.", "Anleitung erfolgreich registriert."));
    }

    static void pesquisarOrientacao(Scanner sc) {
        System.out.println(getTexto("Digite o título ou código:", "Enter the title or code:", "Geben Sie den Titel oder Code ein:"));
        String busca = sc.nextLine();
        boolean encontrou = false;

        for (int i = 0; i < totalOrientacoes; i++) {
            if (titulos[i] != null && (titulos[i].equalsIgnoreCase(busca) || String.valueOf(i).equals(busca))) {
                System.out.println("[" + i + "] " + titulos[i] + " - " + tipos[i]);
                switch (idiomaSelecionado) {
                    case "EN":
                        System.out.println(conteudosEN[i]);
                        break;
                    case "DE":
                        System.out.println(conteudosDE[i]);
                        break;
                    default:
                        System.out.println(conteudosPT[i]);
                        break;
                }
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println(getTexto("Orientação não encontrada.", "Guidance not found.", "Anleitung nicht gefunden."));
        }
    }

    static void editarOrientacao(Scanner sc) {
        System.out.println(getTexto("Digite o código da orientação para editar:", "Enter guidance code to edit:", "Geben Sie den Anleitungscode zum Bearbeiten ein:"));
        int codigo = sc.nextInt();
        sc.nextLine();

        if (codigo < 0 || codigo >= totalOrientacoes || titulos[codigo] == null) {
            System.out.println(getTexto("Código inválido.", "Invalid code.", "Ungültiger Code."));
            return;
        }

        System.out.println(getTexto("Novo título:", "New title:", "Neuer Titel:"));
        titulos[codigo] = sc.nextLine();
        System.out.println("Novo conteúdo PT:");
        conteudosPT[codigo] = sc.nextLine();
        System.out.println("New content EN:");
        conteudosEN[codigo] = sc.nextLine();
        System.out.println("Neuer Inhalt DE:");
        conteudosDE[codigo] = sc.nextLine();

        System.out.println(getTexto("Orientação atualizada com sucesso.", "Guidance successfully updated.", "Anleitung erfolgreich aktualisiert."));
    }

    static void excluirOrientacao(Scanner sc) {
        System.out.println(getTexto("Digite o código da orientação para excluir:", "Enter guidance code to delete:", "Geben Sie den Anleitungscode zum Löschen ein:"));
        int codigo = sc.nextInt();
        sc.nextLine();

        if (codigo < 0 || codigo >= totalOrientacoes || titulos[codigo] == null) {
            System.out.println(getTexto("Código inválido.", "Invalid code.", "Ungültiger Code."));
            return;
        }

        for (int i = codigo; i < totalOrientacoes - 1; i++) {
            titulos[i] = titulos[i + 1];
            tipos[i] = tipos[i + 1];
            conteudosPT[i] = conteudosPT[i + 1];
            conteudosEN[i] = conteudosEN[i + 1];
            conteudosDE[i] = conteudosDE[i + 1];
        }

        totalOrientacoes--;
        titulos[totalOrientacoes] = null;

        System.out.println(getTexto("Orientação excluída com sucesso.", "Guidance deleted successfully.", "Anleitung erfolgreich gelöscht."));
    }

    static String getTexto(String pt, String en, String de) {
        switch (idiomaSelecionado) {
            case "EN": return en;
            case "DE": return de;
            default: return pt;
        }
    }
}
