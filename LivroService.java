import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LivroService {

    private List<Livro> acervo = new ArrayList<>();

    // CADASTRAR
    public void cadastrar(Livro novoLivro) throws Exception {

        if (novoLivro == null)
            throw new Exception("Objeto Nulo");

        if (novoLivro.getTitulo() == null || novoLivro.getTitulo().isEmpty())
            throw new Exception("Título inválido!!!");

        novoLivro.setTitulo(novoLivro.getTitulo().trim().toUpperCase());

        if (novoLivro.getAutor() == null || novoLivro.getAutor().isEmpty())
            throw new Exception("Autor inválido!!!");

        novoLivro.setAutor(novoLivro.getAutor().trim().toUpperCase());

        if (novoLivro.getAnoPublicacao() <= 0
                || novoLivro.getAnoPublicacao() > LocalDate.now().getYear())
            throw new Exception("Ano de publicação inválido");

        if (novoLivro.getNumeroPaginas() <= 0)
            throw new Exception("Número de páginas inválido");

        for (Livro livro : acervo) {
            if (livro.getTitulo().equals(novoLivro.getTitulo())
                    && livro.getAutor().equals(novoLivro.getAutor())
                    && livro.getAnoPublicacao() == novoLivro.getAnoPublicacao())
                throw new Exception("Já existe livro cadastrado com este Título, Autor e Ano de publicação");
        }
        acervo.add(novoLivro);
    }

    // LISTAR
    public List<Livro> listar() {
        return acervo;
    }

    // PESQUISAR POR TÍTULO
    public List<Livro> pesquisar(String titulo) {
        List<Livro> livrosEncontrados = new ArrayList<>();
        titulo = titulo.toUpperCase();

        for (Livro livro : acervo) {
            if (livro.getTitulo().contains(titulo))
                livrosEncontrados.add(livro);
        }
        return livrosEncontrados;
    }

    // PESQUISAR POR AUTOR
    public List<Livro> pesquisarPorAutor(String autor) {
        List<Livro> livrosEncontrados = new ArrayList<>();
        autor = autor.toUpperCase();

        for (Livro livro : acervo) {
            if (livro.getAutor().contains(autor))
                livrosEncontrados.add(livro);
        }
        return livrosEncontrados;
    }

    // PESQUISAR POR ANO DE PUBLICAÇÃO
    public List<Livro> pesquisarPorAno(int ano) {
        List<Livro> livrosEncontrados = new ArrayList<>();

        for (Livro livro : acervo) {
            if (livro.getAnoPublicacao() == ano)
                livrosEncontrados.add(livro);
        }
        return livrosEncontrados;
    }

    // REMOVER
    public void remover(int indice) throws Exception {
        if (acervo.isEmpty())
            throw new Exception("Nenhum livro cadastrado para remover");

        if (indice < 1 || indice > acervo.size())
            throw new Exception("Número inválido! Selecione um livro da lista");

        acervo.remove(indice - 1);
    }

    // EDITAR
    public void editar(int indice, Livro dadosAtualizados) throws Exception {
        if (acervo.isEmpty())
            throw new Exception("Nenhum livro cadastrado para editar");

        if (indice < 1 || indice > acervo.size())
            throw new Exception("Número inválido! Selecione um livro da lista");

        if (dadosAtualizados == null)
            throw new Exception("Objeto Nulo");

        if (dadosAtualizados.getTitulo() == null || dadosAtualizados.getTitulo().isEmpty())
            throw new Exception("Título inválido!!!");

        if (dadosAtualizados.getAutor() == null || dadosAtualizados.getAutor().isEmpty())
            throw new Exception("Autor inválido!!!");

        if (dadosAtualizados.getAnoPublicacao() <= 0
                || dadosAtualizados.getAnoPublicacao() > LocalDate.now().getYear())
            throw new Exception("Ano de publicação inválido");

        if (dadosAtualizados.getNumeroPaginas() <= 0)
            throw new Exception("Número de páginas inválido");

        dadosAtualizados.setTitulo(dadosAtualizados.getTitulo().trim().toUpperCase());
        dadosAtualizados.setAutor(dadosAtualizados.getAutor().trim().toUpperCase());

        Livro livroExistente = acervo.get(indice - 1);
        livroExistente.setTitulo(dadosAtualizados.getTitulo());
        livroExistente.setAutor(dadosAtualizados.getAutor());
        livroExistente.setAnoPublicacao(dadosAtualizados.getAnoPublicacao());
        livroExistente.setNumeroPaginas(dadosAtualizados.getNumeroPaginas());
    }

    // MELHORIA LIVRE - ORDENAR POR ANO
    public List<Livro> ordenarPorAno() {
        List<Livro> ordenados = new ArrayList<>(acervo);
        ordenados.sort((a, b) -> Integer.compare(a.getAnoPublicacao(), b.getAnoPublicacao()));
        return ordenados;
    }
}
