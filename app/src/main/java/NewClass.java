import java.util.*;
import java.util.Scanner;

public class NewClass {

    public static void main(String[] args) {

        int n1, n2;
        
        Scanner leitorScanner = new Scanner(System.in); 
        
        System.out.println("Digite um número: ");
        n1 = leitorScanner.nextInt();
        
        System.out.println("Digite um número: ");
        n2 = leitorScanner.nextInt();
        
        if(n1 == n2){
          System.out.println("Sao iguais!");
        } else{
          System.out.println("Nao sao iguais!");
        }

    }
}