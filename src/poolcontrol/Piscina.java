package poolcontrol;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Piscina {

    private final int capacidade;
    private final Map<String, Morador> presentes = new LinkedHashMap<>();

    public Piscina(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("Capacidade deve ser maior que zero");
        }
        this.capacidade = capacidade;
    }

    private static String chave(Morador m) {
        return (m.apartamento() + "|" + m.nome()).toLowerCase();
    }

    public boolean cheia() {
        return presentes.size() >= capacidade;
    }

    public boolean contem(Morador m) {
        return presentes.containsKey(chave(m));
    }

    /** Falha (false) se a piscina estiver lotada ou o morador já estiver dentro. */
    public boolean entrar(Morador m) {
        if (cheia() || contem(m)) {
            return false;
        }
        presentes.put(chave(m), m);
        return true;
    }

    public boolean sair(Morador m) {
        return presentes.remove(chave(m)) != null;
    }

    public int getOcupacao() {
        return presentes.size();
    }

    public Collection<Morador> getPresentes() {
        return presentes.values();
    }

    public String status() {
        int vagas = capacidade - presentes.size();
        String base = "Lotação: " + presentes.size() + "/" + capacidade;
        return cheia()
                ? base + " - PISCINA LOTADA, entrada bloqueada"
                : base + " - " + vagas + " vaga(s)";
    }
}
