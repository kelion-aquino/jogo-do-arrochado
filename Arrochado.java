package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Arrochado {

    public static void main(String[] args) {
	    arrochado();
    }

    private static void arrochado() {

        Scanner keyboard = new Scanner(System.in);

        int random = getRandomNumberUsingNextInt();

        List<Integer> tries = new ArrayList<Integer>();
        int maior = 0;
        int menor = 0;
        int amount = 0;
        int extremidade_menor = 0;
        int extremidade_maior = 100;
        int input = 0;
        int aux = 0;

        // estatisticas
        float media = 0F;

        System.out.println("Digite seu nome: ");
        String name = keyboard.nextLine();

        do {

            System.out.println("Digite seu palpite!\nDICA: O número está no intervalo de " + extremidade_menor + " ~ " + extremidade_maior + ":");
            input = keyboard.nextInt();

            if (input > extremidade_maior || input < extremidade_menor) {
                System.out.println("=>O numero digitado nao se encontra no range determinado de " + extremidade_menor + " ~ " + extremidade_maior + ". Tente novamente.");
            } else {

                if(amount == 0)
                    menor = input;

                // armazena o maior e menor valor
                if (input > maior)
                    maior = input;
                else  if (input < menor)
                    menor = input;

                if (input == random){
                    tries.add(input);
                    amount++;
                    break;
                }

                if (random > input)
                    extremidade_menor = input;
                else if (random < input)
                    extremidade_maior = input;

                tries.add(input);
                amount++;

                System.out.println("=>Valor errado, tente novamente!");
            }
        }while (input != random);

        System.out.println("=> Parabéns " + name + ",\nVocê acertou o número!");

        tries = ordenar(tries, aux);
        media = media(tries);

        System.out.println("\n==> O maior numero digitado foi " + maior + " e o menor " + menor);
        System.out.printf("\n==> A media dos numeros digitados foi: %.2f", media);

        estatisticas(tries, media);
    }

    private static int getRandomNumberUsingNextInt() {
        Random random = new Random();
        return random.nextInt(100 + 1);
    }

    private static List<Integer> ordenar (List<Integer> list, int aux) {

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (list.get(i) < list.get(j)) {
                    aux = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, aux);
                }
            }
        }

        System.out.printf("\n==> Seus números: ");
        for (int num : list) {
            System.out.printf("[" + num + "]");
        }

        return list;
    }

    private static float media (List<Integer> list) {

        float somatorio = 0;
        for (int num : list) {
            somatorio += num;
        }
        return somatorio /= list.size();
    }

    private static void estatisticas(List<Integer> list, float media) {

        float mediana = 0F;
        float variancia = 0F;
        float desvio = 0F;
        int aux = 0;

        // mediana
        aux = list.size();
        if (aux % 2 == 0) {
            aux = (int) aux/2;
            mediana = (list.get(aux - 1) + list.get(aux))/2;
        } else {
            aux += 1;
            aux = (int) aux/2;
            mediana = list.get(aux - 1);
        }

        // variancia
        for (int num : list) {
            variancia += ((num - media) * (num - media));
        }
        if (list.size() - 1 != 0)
            variancia /= (list.size() - 1);
        else
            variancia /= list.size();

        // desvio padrão
        desvio = (float) Math.sqrt(variancia);

        System.out.printf("\n==> A mediana dos numeros digitados foi: %.2f", mediana);
        System.out.printf("\n==> A variancia dos numeros digitados foi: %.2f", variancia);
        System.out.printf("\n==> O desvio padrao dos numeros digitados foi: %.2f", desvio);
    }
}
