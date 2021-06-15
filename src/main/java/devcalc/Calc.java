// 1- Pacote
package devcalc;

// 2- Bibliotecas

import java.util.Scanner;

// 3- Classe
public class Calc {
    // 3.1 - Abributos da classe

    static Scanner entrada = new Scanner(System.in); // entrada = input

    // 3.2 - Métodos e Funções //Método: só faz - Função: Mostra o que fez

    public static void main(String[] args){
        System.out.println(">>>> CALCULADORA <<<<");
        System.out.println("(1) Somar");
        System.out.println("(2) Subtrair");
        System.out.println("(3) Multiplicar");
        System.out.println("(4) Dividir");
        System.out.print("Escolha o Cálculo Desejado: ");

        int opcao = entrada.nextInt();
        int num1 = 0;
        int num2 = 0;

        // Pergunta os valores a serem utilizados no cáulculo

        if (opcao >= 1 && opcao <=4){
            System.out.print("Digite o 1º Número: ");
            num1 = entrada.nextInt();
            System.out.print("Digite o 2º Número: ");
            num2 = entrada.nextInt();
            System.out.print("O resultado é= ");
        }


        // Chama a função do cálculo desejado

        switch (opcao) {
            case 1:
                System.out.println(somarDoisNumeros(num1, num2));
                break;
            default:
                System.out.println("Opção Invalida!");
                break;

        }


    }

    public static int somarDoisNumeros(int num1, int num2){
        return num1 + num2;

    }
}