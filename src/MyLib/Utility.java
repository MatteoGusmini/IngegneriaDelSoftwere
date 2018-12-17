package MyLib;

import java.text.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;



public class Utility 
{
	
	final static String MSGERRORE="Il valore con cui evochi questa funzione non è accettabile";
	final static String ERROREFORMATO="Il valore inserito non è del tipo richiesto";
	
	static Scanner sc =new Scanner(System.in);

	public static String leggiStringa(String msg)
	{
		String nome;
		do{
			System.out.println(msg);
			nome = sc.next();
		}while(nome.length()==0);
		return nome;
	}
	
	public static String leggiData(String msg)
	{
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Boolean valido=true;
		String nome;
		do{
			System.out.println(msg);
			nome = sc.next();
			
			try {
				format.parse(nome);
			} catch (ParseException e) {
				System.out.println(nome + " non è nel formato richiesto "
				+ ((SimpleDateFormat) format).toPattern());
				valido=false;
				}
		}while(!valido);
		return nome;
	}	
		
		
		
		
	
	public static String leggiCodicefiscale(String msg)
	{
		String nome;
		do{
			System.out.println(msg);
			nome = sc.next();
		}while(nome.length()==0 && nome.length()<17);
		return nome;
	}
	
	public static String leggiLinea(String msg)
	{
		String nome;
		System.out.println(msg);
		do{
			nome = sc.nextLine();
		}while(nome.length()==0);
		return nome;
	}
	public static int leggiIntero(int min, int max, String msg)
	{
		int numero=min-1;
		boolean finito=false;
		do{
		System.out.println(msg);
		
		try
		{
		numero = sc.nextInt();
		finito = true;
		}
		catch (InputMismatchException e)
		{
		System.out.println(ERROREFORMATO);
		String daButtare = sc.next();
		}
		if(numero<min||numero>max)
			finito=false;
		
		}while(!finito);
		return numero;
	}
	
	
	public static int leggiVoto(String msg)
	{
		int numero=0;
		boolean finito=false;
		do{
		System.out.println(msg);
		
		try
		{
		numero = sc.nextInt();
		finito = true;
		}
		catch (InputMismatchException e)
		{
		System.out.println(ERROREFORMATO);
		String daButtare = sc.next();
		}
		if(numero<18||numero>110||(numero>30&&numero<67))
			finito=false;
		
		}while(!finito);
		return numero;
	}
	
	
	
	public static boolean assegnaBoolean(int n)
	{	
		boolean var;
		
		if (n==1)var=true;
		else var=false;
		return var;
	
	}
		
	
	public static int casuale(int nMax)
	{
		nMax++;
		Random estrattore=new Random();
		int numero;
		numero=estrattore.nextInt(nMax);
		numero=Math.abs(numero);
		return numero;
	}
	
	
	public static boolean confrontaCar(char [] nome,char [] corso)
	{
		boolean trovato=false;
		boolean errore=false;
		int j;
		int k;
		for(int i=0;i<(corso.length-nome.length+1);i++)
		{
			errore=false;
			k=0;
			for(j=i ;j<(nome.length+i);j++)
			{
				 		
					if(nome[k]!=corso[j])
						errore=true;
				k++;
				
			}
			if(errore==false)
				trovato=true;
			
		}
		
		return trovato;
	}
	
	
	
}
