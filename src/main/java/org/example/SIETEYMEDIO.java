package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
public class SIETEYMEDIO {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("¡Bienvenido al juego de Siete y Medio!");
        double saldo = 100.0;
        while (true) {
            System.out.println("\nComenzando una nueva partida...");

            Stack<String> baraja = new Stack<>();
            inicializarBaraja(baraja);

            double apuesta = 0.0;

            System.out.println("Tu saldo actual es: $" + saldo);
            System.out.print("¿Cuánto deseas apostar? (0 para salir): $");
            apuesta = scanner.nextDouble();

            if (apuesta <= 0 || apuesta > saldo) {
                System.out.println("Gracias por jugar. Tu saldo final es: $" + saldo);
                break;
            }

            List<String> mano = new ArrayList<>();
            double total = 0.0;
            boolean jugadorContinua = true;

            while (jugadorContinua) {
                String carta = sacarCarta(baraja, random);
                mano.add(carta);
                double valorCarta = valorCarta(carta);
                total += valorCarta;

                System.out.println("Has recibido un/a " + carta + " - Valor total de tu mano: " + total);

                if (total >= 7.5 || total <= 0.0) {
                    jugadorContinua = false;
                } else {
                    System.out.print("¿Quieres otra carta? (s/n): ");
                    String decision = scanner.next().toLowerCase();
                    if (!decision.equals("s")) {
                        jugadorContinua = false;
                    }
                }
            }

            while (total < 5.5) {
                String carta = sacarCarta(baraja, random);
                mano.add(carta);
                double valorCarta = valorCarta(carta);
                total += valorCarta;
            }

            System.out.println("\nResultado de la partida:");
            for (String carta : mano) {
                System.out.println(" - " + carta);
            }

            if (total > 7.5) {
                System.out.println("Has perdido $" + apuesta);
                saldo = saldo - apuesta;
            } else {
                System.out.println("Has ganado $" + apuesta);
                saldo = saldo + apuesta;
            }
        }

        scanner.close();
    }

    private static void inicializarBaraja(Stack<String> baraja) {
        String[] cartas = {"1", "2", "3", "4", "5", "6", "7", "0.5", "0.5", "0.5", "0.5", "0.5", "0.5", "0.5", "0.5", "0.5", "As", "As", "As", "As"};
        String[] palos = {"Oros", "Copas", "Espadas", "Bastos"};

        for (String carta : cartas) {
            for (String palo : palos) {
                baraja.add(carta + " de " + palo);
            }
        }
    }

    private static String sacarCarta(Stack<String> baraja, Random random) {
        int indice = random.nextInt(baraja.size());
        //String carta = baraja.get(indice);
        //baraja.remove(indice);
        //return carta;
        return baraja.remove(indice);
    }

    private static double valorCarta(String carta) {
        if (carta.startsWith("As")) {
            return 1.0;
        } else if (carta.startsWith("0.5")) {
            return 0.5;
        } else {
            int valor = Integer.parseInt(carta.split(" ")[0]);
            return valor <= 7 ? valor : 0.5;
        }
    }
}