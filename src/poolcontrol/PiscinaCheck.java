package poolcontrol;

/** Verificação rápida da regra de lotação: java -cp out poolcontrol.PiscinaCheck */
public class PiscinaCheck {

    public static void main(String[] args) {
        Piscina p = new Piscina(2);
        Morador ana = new Morador("Ana", "101");
        Morador bia = new Morador("Bia", "102");
        Morador caio = new Morador("Caio", "103");

        check(p.entrar(ana), "ana entra");
        check(!p.entrar(new Morador(" ana ", "101")), "mesma pessoa não entra 2x (sem distinguir caixa/espaços)");
        check(p.entrar(bia), "bia entra");
        check(p.cheia(), "piscina cheia com 2/2");
        check(!p.entrar(caio), "caio barrado com piscina lotada");
        check(p.sair(ana), "ana sai");
        check(!p.sair(ana), "ana não sai 2x");
        check(p.entrar(caio), "caio entra após vaga liberada");
        check(p.getOcupacao() == 2, "ocupação final 2");
        System.out.println("OK");
    }

    private static void check(boolean cond, String msg) {
        if (!cond) {
            throw new AssertionError("FALHOU: " + msg);
        }
    }
}
