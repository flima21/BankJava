/*

---------------------------------------------------------------------------
Desenvolvedor: Felipe Pedroso de Lima 
Curso: Análise e Desenvolvimento de Sistemas 
---------------------------------------------------------------------------
Representação de um sistema bancário feito em Java

*/
import java.util.Scanner  ;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;  
import java.util.ArrayList;
public class BankJava {

    public static void main(String[] args) {
        int senha_user = 0; // senha que o sistema vai receber
        int senha = 12;
        int tentativa = 0;
        int menu;

        String cpf_user; // CPF que o sistema vai receber
        String cpf = "12345678900";

        double saldo = 10000.00;

        Scanner ler = new Scanner(System.in);
        LocalDateTime dt_now = LocalDateTime.now();

        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
        String dt_fmt = dt_now.format(format);  

        System.out.println(dt_fmt);  

        ArrayList<String> extrato = new ArrayList();

        do {
            System.out.println("BEM VINDO AO BANCO JAVA\n");

            System.out.println("Digite seu CPF (Apenas números)");
            cpf_user = ler.next();

            if (cpf_user.equals(cpf)) {
                System.out.println("Digite sua senha");
                senha_user = ler.nextInt();

                if (senha_user == senha) {
                    System.out.println("Bem vindo!\n");

                } else {
                    System.out.println("Senha incorreta! Tente novamente\n\n");
                    tentativa++;
                }

                if (tentativa == 3) {
                    System.out.println("Senha bloqueada. Procure uma agência próxima");
                    break;
                }

            } else {
                System.out.println("CPF não encontrado! Digite novamente");
            }

        } while (senha_user != senha);
        /*
            Aqui o sistema entra em loop para manter o usuario "preso no caixa" ate digitar 0
            Quando digitar 0, a sessão encerra
        */
        while (true) {
            System.out.println(
                    "[1] Saldo\n"+
                    "[2] Depósito\n"+
                    "[3] Saque\n"+
                    "[4] Tirar extrato\n"+
                    "[5] Transferência\n"+
                    "[0] Sair");
            menu = ler.nextInt();

            switch (menu) {
                case 1:
                    System.out.println("O seu saldo é de R$" + saldo + "\n");
                    extrato.add(dt_fmt+" | Saldo         | "+saldo);
                    break;
                case 2:
                    System.out.println("Digite a quantidade que deseja depositar");
                    double deposito = ler.nextDouble();
                    saldo = saldo + deposito;
                    System.out.println("O seu saldo atualizado é de R$" + saldo);
                    extrato.add(dt_fmt+" | Depósito      | "+deposito);
                    break;
                case 3:
                    System.out.println("Digite a quantidade que deseja retirar");
                    double saldo_temp = ler.nextDouble();

                    if ((saldo - saldo_temp) >= 0) {
                        System.out.println("Processando...");
                        saldo -= saldo_temp;
                        System.out.println("Saldo atual R$" + saldo + "\n");
                        extrato.add(dt_fmt+" | Saque         | "+saldo_temp);
                    } else {
                        System.out.println("O valor desejado não é compatível com seu saldo em conta");
                    }

                    break;
                case 4: 
                    int size_arr = extrato.size();
                    System.out.println("_________________________________________________");
                    System.out.println("Data/hora          "+" | Ação          |"+" Valor");
                    
                    for (int i = 0; i < size_arr; i++) {
                        System.out.println("_________________________________________________");
                        System.out.println(extrato.get(i));
                    }
                    break;
                case 5: // Transeferencia
                    String agencia;
                    String conta;
                    double valor;

                    System.out.println("Informe agencia");
                    agencia = ler.next();

                    if (agencia.equals("03549")) {
                        System.out.println("Informe a conta");
                        conta = ler.next();

                        if (conta.equals("632300")) {
                            System.out.println("Informe o valor desejado para transferir");
                            valor = ler.nextDouble();

                            saldo += valor;
                            System.out.println("Saldo atualizado é R$" + saldo);
                            extrato.add(dt_fmt+" | Transferência | "+valor);

                        } else {
                            System.out.println("Conta inválida");
                        }
                    } else {
                        System.out.println("Agência inválida\n");
                    }
                    break; // End case 5
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida\n");
                    continue;
            }//Fim do switch

        }

    }
}
