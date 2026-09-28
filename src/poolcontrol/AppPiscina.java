package poolcontrol;

import java.util.Scanner;

public class AppPiscina {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Piscina piscina = new Piscina(lerCapacidade(in));

        System.out.println("\n" + piscina.status());
        System.out.print(MENU);
        while (in.hasNextLine()) {
            switch (in.nextLine().trim()) {
                case "1" -> entrar(in, piscina);
                case "2" -> sair(in, piscina);
                case "3" -> listar(piscina);
                case "0" -> {
                    System.out.println("Encerrado.");
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }
            System.out.println("\n" + piscina.status());
            System.out.print(MENU);
        }
    }

    private static final String MENU = "1-Entrar  2-Sair  3-Quem está na piscina  0-Encerrar\n> ";

    private static int lerCapacidade(Scanner in) {
        while (true) {
            System.out.print("Capacidade máxima da piscina: ");
            try {
                int c = Integer.parseInt(in.nextLine().trim());
                if (c > 0) {
                    return c;
                }
            } catch (NumberFormatException e) {
                // cai na mensagem abaixo
            }
            System.out.println("Informe um número inteiro maior que zero.");
        }
    }

    private static Morador lerMorador(Scanner in) {
        System.out.print("Nome: ");
        String nome = in.nextLine();
        System.out.print("Apartamento: ");
        String apto = in.nextLine();
        try {
            return new Morador(nome, apto);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static void entrar(Scanner in, Piscina piscina) {
        Morador m = lerMorador(in);
        if (m == null) {
            return;
        }
        if (piscina.contem(m)) {
            System.out.println(m + " já está na piscina.");
        } else if (piscina.entrar(m)) {
            System.out.println("Entrada liberada: " + m);
        } else {
            System.out.println("Entrada negada: piscina lotada.");
        }
    }

    private static void sair(Scanner in, Piscina piscina) {
        Morador m = lerMorador(in);
        if (m == null) {
            return;
        }
        if (piscina.sair(m)) {
            System.out.println("Saída registrada: " + m);
        } else {
            System.out.println(m + " não está na piscina.");
        }
    }

    private static void listar(Piscina piscina) {
        if (piscina.getPresentes().isEmpty()) {
            System.out.println("Piscina vazia.");
        }
        piscina.getPresentes().forEach(m -> System.out.println("  - " + m));
    }
}
