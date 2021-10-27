/*
---------------------------------------------------------------------------
Desenvolvedor: Felipe Pedroso de Lima 
Curso: Análise e Desenvolvimento de Sistemas 
---------------------------------------------------------------------------
Representação de um sistema bancário feito em Java

[1] Saldo
[2] Deposito
[3] Saque
[4] Transferencia 
[5] Extrato 
[0] Sair

Estudar a possibilidade de trabalhar com VETOR DE OBJETOS ao inves de vetor de strings

Formato do extrato deve ser:

_______________________________________________________
DATA        |           AÇÃO            |  VALOR (R$) |
_______________________________________________________
10/10/2021  | transferencia             |  R$1000,00  |
_______________________________________________________
*/
package bankjava;
import java.util.Scanner  ;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
public class BankJava {

    public static void main(String[] args) {
        int senha_user = 0; // senha que o sistema vai receber
        int senha = 1234;
        int tentativa = 0;
        int menu;

        String cpf_user; // CPF que o sistema vai receber
        String cpf = "11111111111";

        double saldo = 10000.00;

        Scanner ler = new Scanner(System.in);
/*
        LocalDateTime dt_now = LocalDateTime.now();
        DateTimeFormatter dt_fmt = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String dt_today = formatterData.format(dt_fmt);
*/
        ArrayList<String> extrato = new ArrayList<>();

        do {
            System.out.println("BEM VINDO AO BANCO JAVA\n");

            System.out.println("Digite seu CPF (Apenas números)");
            cpf_user = ler.next();
            
            
            if (cpf_user.equals(cpf)) {
                System.out.println("Digite sua senha");
                senha_user = ler.nextInt();

                if (senha_user == senha) {
                    System.out.println("Bem vindo!");

                    while (true) {
                        System.out.println(
                                "[1] Saldo\n[2] Depósito\n[3] Saque\n[4] Tirar extrato\n[5] Transferência\n[0] Sair");
                        menu = ler.nextInt();

                        switch (menu) {
                        case 1:
                            System.out.println("O seu saldo é de R$" + saldo + "\n");
                            break;
                        case 2:
                            System.out.println("Digite a quantidade que deseja depositar");
                            saldo = saldo + ler.nextDouble();
                            System.out.println("O seu saldo atualizado é de R$" + saldo);
                            
                            // extrato.add();
                            break;
                        case 3:
                            System.out.println("Digite a quantidade que deseja retirar");
                            double saldo_temp = ler.nextDouble();

                            if ((saldo - saldo_temp) >= 0) {
                                System.out.println("Processando...");
                                saldo -= saldo_temp;
                                System.out.println("Saldo atual R$" + saldo + "\n");
                            } else {
                                System.out.println("O valor desejado não é compatível com seu saldo em conta");
                            }

                            break;
                        // case 4: TIRAR EXTRATO
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

                                } else {
                                    System.out.println("Conta inválida");
                                }
                            } else {
                                System.out.println("Agência inválida\n");
                            }
                            break;
                        case 0:
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Opção inválida\n");
                            continue;
                        }

                    }

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
    }
}
