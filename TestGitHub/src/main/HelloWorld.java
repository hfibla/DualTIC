package main;

import java.util.Scanner;

public class HelloWorld {
	
	private static int num1 = 0;
	private static int num2 = 0;
	private static int resultado = 0;
	private static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Programa para sumar");
		System.out.println("Introduce el primer número");
		num1 = teclado.nextInt();
		System.out.println("Introduce el segundo número");
		num2 = teclado.nextInt();
		resultado = num1 + num2;
		System.out.println("El resultado es " + resultado);
		System.out.println("Pero si supieras sumar no te haría falta usar esta app");

	}

}
