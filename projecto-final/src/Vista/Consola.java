package Vista;

//Importar el paquete de scanner para entradas
import java.util.Scanner;

public class Consola {
	//Variable sc para llamar rapidamente al scanner
	private Scanner sc;

	//Constuctor
	public Consola(){
			sc = new Scanner(System.in);
	}

	//Metodo para mostrar un mensaje en consola, se debe enviar un String
	public void printMsg(String pMen){
			System.out.println(pMen);
	}
	
	//Metodo para leer String
	public String readString(String pStr){
			System.out.println(pStr);
			String str = sc.nextLine();
			return str;
	}

	//Metodo para leer Double
	public double readDouble(String pStr){
			System.out.println(pStr);
			double d = sc.nextDouble();
			sc.nextLine();
			return d;
	}

	//Metodo para leer enteros
    public int readInt(String pStr){
        System.out.println(pStr);
        int num = sc.nextInt();
        sc.nextLine();
        return num;
    }
}
