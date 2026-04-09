import java.util.List;

LivroService service = new LivroService();

void main() {
    String menu = """
            ===== SysBiblio =====
            1 - Cadastrar Livro
            2 - Listar Livros
            3 - Pesquisar Livro
            4 - Remover Livro
            5 - Editar Livro
            6 - Ordenar Por Ano
            0 - Sair
            """;
    int opcao;
    do {
        IO.println(menu);
        opcao = Input.scanInt("Digite uma opção: ");
        try {
            switch (opcao) {
                case 1 -> cadastrar();
                case 2 -> listar();
                case 3 -> pesquisar();
                case 4 -> remover();
                case 5 -> editar();
                case 6 -> ordenarPorAno();
                case 0 -> IO.println("Até logo, volte sempre!!!");
                default -> IO.println("Opção Inválida");
            }
        } catch (Exception e) {
            IO.println("ERRO: " + e.getMessage());
        }
        IO.readln("Pressione Enter para continuar...");
    } while (opcao != 0);
}

// CADASTRAR
void cadastrar() throws Exception {
    String titulo = Input.scanString("Digite o título do livro: ");
    String autor = Input.scanString("Digite o autor do livro: ");
    int anoPublicacao = Input.scanInt("Digite o ano de publicação do livro: ");
    int numeroPaginas = Input.scanInt("Digite o número de páginas do livro: ");

    Livro novoLivro = new Livro(titulo, autor, anoPublicacao, numeroPaginas);
    service.cadastrar(novoLivro);
    IO.println("Cadastro do livro realizado com sucesso!");
}

// LISTAR
void listar() {
    List<Livro> livros = service.listar();
    imprimirLista(livros);
}

// PESQUISAR
void pesquisar() {
    String menuPesquisa = """
            === Pesquisar por ===
            1 - Título
            2 - Autor
            3 - Ano de publicação
            """;
    IO.println(menuPesquisa);
    int opcao = Input.scanInt("Digite uma opção: ");

    List<Livro> livros;
    try {
        switch (opcao) {
            case 1 -> {
                String pesquisa = Input.scanString("Digite parte do título: ");
                livros = service.pesquisar(pesquisa);
                imprimirLista(livros);
            }
            case 2 -> {
                String pesquisa = Input.scanString("Digite parte do nome do autor: ");
                livros = service.pesquisarPorAutor(pesquisa);
                imprimirLista(livros);
            }
            case 3 -> {
                int ano = Input.scanInt("Digite o ano de publicação: ");
                livros = service.pesquisarPorAno(ano);
                imprimirLista(livros);
            }
            default -> IO.println("Opção Inválida");
        }
    } catch (Exception e) {
        IO.println("ERRO: " + e.getMessage());
    }
}

// REMOVER
void remover() throws Exception {
    List<Livro> livros = service.listar();
    imprimirLista(livros);

    int numero = Input.scanInt("Digite o número do livro que deseja remover: ");
    service.remover(numero);
    IO.println("Exclusão do livro realizada com sucesso!");
}

// EDITAR
void editar() throws Exception {
    List<Livro> livros = service.listar();
    imprimirLista(livros);

    int numero = Input.scanInt("Digite o número do livro que deseja editar: ");

    String titulo = Input.scanString("Novo título: ");
    String autor = Input.scanString("Novo autor: ");
    int anoPublicacao = Input.scanInt("Novo ano de publicação: ");
    int numeroPaginas = Input.scanInt("Novo número de páginas: ");

    Livro dadosAtualizados = new Livro(titulo, autor, anoPublicacao, numeroPaginas);
    service.editar(numero, dadosAtualizados);
    IO.println("Livro editado com sucesso!!!");
}

// MELHORIA LIVRE - ORDENAR POR ANO

void ordenarPorAno() {
    List<Livro> livros = service.ordenarPorAno();
    imprimirLista(livros);
}

// IMPRIMIR LISTA
void imprimirLista(List<Livro> livros) {
    if (livros.isEmpty()) {
        IO.println("Livro não encontrado!");
        return;
    }

    int i = 1;
    for (Livro livro : livros) {
        IO.println(i++ + " - " + livro);
    }
}
