import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class GestaoDeTrafego {

    public static void quickSort(String[] ruas, int inicio, int fim) {
        if (inicio < fim) {
            int pivoIndex = partition(ruas, inicio, fim);
            quickSort(ruas, inicio, pivoIndex - 1);
            quickSort(ruas, pivoIndex + 1, fim);
        }
    }

    private static int partition(String[] ruas, int inicio, int fim) {
        String pivo = ruas[fim];
        int i = inicio - 1;
        for (int j = inicio; j < fim; j++) {
            if (ruas[j].compareTo(pivo) <= 0) {
                i++;
                String temp = ruas[i];
                ruas[i] = ruas[j];
                ruas[j] = temp;
            }
        }
        String temp = ruas[i + 1];
        ruas[i + 1] = ruas[fim];
        ruas[fim] = temp;

        return i + 1;
    }

    public static void main(String[] args) {
        // captura do tempo
        LocalDateTime inicio = LocalDateTime.now();
        Scanner scanner = new Scanner(System.in);

        // Ruas
        String[] ruas = {"Rua C", "Rua A", "Rua B"}; // Ordem inicial
        String[] sinais = {"VERDE", "VERMELHO", "AMARELO"}; // Estado dos sinais
        boolean[] interditadas = {false, false, false}; // Status de interdição

        quickSort(ruas, 0, ruas.length - 1);

        // Eu rezo a deus e por tudo que é mais sagrado que esse menu funcione, amen.
        boolean executando = true;
        while (executando) {
            System.out.println("\n======= GESTÃO DE TRÁFEGO =======");
            for (int i = 0; i < ruas.length; i++) {
                System.out.printf("%d. %s - Sinal: %s - %s\n", 
                    i + 1, 
                    ruas[i], 
                    sinais[i], 
                    interditadas[i] ? "INTERDITADA" : "TRÂNSITO OK");
            }
            System.out.println("4. Atualizar status de uma rua");
            System.out.println("5. Gerar relatório e sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            switch (opcao) {
                case 1, 2, 3:
                    int index = opcao - 1;
                    boolean submenu = true;
                    while (submenu) {
                        System.out.println("\n-- Informações da " + ruas[index] + " --");
                        System.out.println("Sinal: " + sinais[index]);
                        System.out.println("Status: " + (interditadas[index] ? "INTERDITADA" : "TRÂNSITO OK"));
                        System.out.println("1. Atualizar informações");
                        System.out.println("2. Voltar ao menu principal");
                        System.out.print("Escolha uma opção: ");
                        int subOpcao = scanner.nextInt();
                        scanner.nextLine();

                        switch (subOpcao) {
                            case 1:
                                System.out.println("Atualizar sinal (VERDE/AMARELO/VERMELHO): ");
                                sinais[index] = scanner.next().toUpperCase();
                                System.out.println("Está interditada? (true/false): ");
                                interditadas[index] = scanner.nextBoolean();
                                break;
                            case 2:
                                submenu = false;
                                break;
                            default:
                                System.out.println("Opção inválida. Tente novamente.");
                        }
                    }
                    break;

                case 4:
                    System.out.println("\nEscolha a rua para atualizar (1-3): ");
                    int ruaIndex = scanner.nextInt() - 1;
                    if (ruaIndex >= 0 && ruaIndex < ruas.length) {
                        System.out.println("Atualizar sinal (VERDE/AMARELO/VERMELHO): ");
                        sinais[ruaIndex] = scanner.next().toUpperCase();
                        System.out.println("Está interditada? (true/false): ");
                        interditadas[ruaIndex] = scanner.nextBoolean();
                    } else {
                        System.out.println("Opção inválida.");
                    }
                    break;

                case 5:
                    executando = false;
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }

        //5 relatório
        LocalDateTime fim = LocalDateTime.now();
        System.out.println("\n======= RELATÓRIO FINAL =======");
        System.out.println("Tempo de execução: " + java.time.Duration.between(inicio, fim).toSeconds() + " segundos");
        System.out.println("Status das ruas:");
        for (int i = 0; i < ruas.length; i++) {
            System.out.printf("%s - Sinal: %s - %s\n", 
                ruas[i], 
                sinais[i], 
                interditadas[i] ? "INTERDITADA" : "TRÂNSITO OK");
        }
        System.out.println("================================");

        scanner.close();
    }
}
