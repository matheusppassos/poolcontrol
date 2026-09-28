package poolcontrol;

public record Morador(String nome, String apartamento) {

    public Morador {
        if (nome == null || nome.isBlank() || apartamento == null || apartamento.isBlank()) {
            throw new IllegalArgumentException("Nome e apartamento são obrigatórios");
        }
        nome = nome.trim();
        apartamento = apartamento.trim().toUpperCase();
    }

    @Override
    public String toString() {
        return nome + " (apto " + apartamento + ")";
    }
}
