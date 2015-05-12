/*
# LengthMotifSearch - Tool to find motifs in clusters's sets of an experimental data set of RBP obtained by CLIP-seq protocol.
#
# Created by Edson Leon Araujo and Msc. Carlos Andres Sierra on April 2015.
# Copyright (c) 2015 Edson Leon Araujo and Msc. Carlos Andres Sierra. Research Group LACSER. Bios-UAN division. Universidad Antonio Narino. All rights reserved.
#
# This file is part of LengthMotifSearch.
#
# CLengthMotifSearch is free software: you can redistribute it and/or modify it under the terms of the 
# GNU General Public License as published by the Free Software Foundation, version 2.
*/

package proyecto;

import java.util.Random;

import proyecto.Individuo;
import proyecto.Poblacion;
import proyecto.SeleccionPadres;

//package proyecto;


/**
 * @author Eng. (C) Edson David Leon - MSc. Carlos Andrés Sierra
 */
public class Proyecto {
	static private int n=40; //TAMAÑO DE LA POBLACION
	static private int m=n/2; //CANTIDAD DE OPERACIONES GENETICAs   
	static private Individuo[] poblaciontemporal=new Individuo[n]; //SE GENERA LA POBLACION INICIAL
	static private int posiciontemporal=0;
        static Random rd=new Random();
        static int generaciones=5;
        
        static String padre1;
        static String padre2;
        static String file="C:\\Clusters_filtered.txt";
        static private Poblacion poblacion=new Poblacion(n,file); //SE GENERA LA POBLACION INICIAL
        static SeleccionPadres selecciondepadres=new SeleccionPadres(poblacion);
    static int contador=0;
	
    /**
     * 
     * @param args
     */
    public static void main(String[] args) 
    {   
        //file = args[0];
    	poblacion.setFile(file);
    	poblacion.Imprimirpoblacion();
    	for(int i=0;i<generaciones;i++)
        {
        	System.out.println(i + 1);
    		Iteraciones();
        	poblacion.Imprimirpoblacion();
        }
       
        
        
    }
    
    /**
     * METODO QUE REALIZA LAS ITERACIONES DE MUTACIONES O CRUCES Y REEMPLAZA
     */
    private static void Iteraciones() 
    {
    	int i=0;
    	
    		//////////////////
    		//////////////////SE DEFINEN LOS PADRES Y CREAMOS LOS DOS HIJOS IGUALES A LOS PADRES

    		for(int k=0;k<m;k++) // CICLO DE LAS OPERACIONES GENETICAS
    		{
                    posiciontemporal=0;
                    for(i=0;i<poblacion.getn();i=i+2) // CICLO POR POBLACION
                    {
                        selecciondepadres.calcularPadres();
                        padre1=selecciondepadres.getPadre1();
                        //System.out.println(padre1.getcromosoma());
                        padre2=selecciondepadres.getPadre2();
                        //System.out.println(padre2.getcromosoma());
	    		if(rd.nextDouble()<0.4)
	    		{
	    			Mutacion(padre1,padre2);
	    		}
	    		else
	    		{
	    			Cruce(padre1,padre2);
	    		}
                    }
    		}
 
    	poblacion.setPoblacion(poblaciontemporal);
        selecciondepadres.setPoblacion(poblaciontemporal);
       // poblacion.Imprimirpoblacion();  
	}
	
    /**
     * METODO QUE DETERMINA LA MUTACION ENTRE DOS PADRES
     * @param padre1 INDIVIDUO QUE REPRESENTA AL PRIMER PADRE
     * @param padre2 INDIVIDUO QUE REPRESENTA AL SEGUNDO PADRE
     */
    static void Mutacion(String padre1,String padre2) 
    {
    		int pos1=rd.nextInt(padre1.length());
    		int pos2=rd.nextInt(padre2.length());
    		String hijo1;
    		String hijo2;
    		
    		//////////////////
    		//////////////////SE DETERMINA LA BASE QUE SE CAMBIARA DISTINTA LA LA QUE YA TIENE
    		String base="";
    		do //CICLO QUE SE REPETIRA SIEMPRE QUE LA BASE SEA LA MISMA A LA ENCONTRADA EN LA POS ALEATORIA
    		{
	    		int random=rd.nextInt(4); //SELECCIONA U8N NUM ALEATORIO DE 1 A 4
	            switch(random) // DE FORMA ALEATORIA INGRESA LAS BASES NUCLEOTICAS
	            {
	                case 0:
	                	base="C";break;
	                case 1:
	                	base="G";break;
	                case 2:
	                	
	                	base="A";break;
	                case 3:
	                	base="U";break;
	            }   
	            
    		}while(base.equals(padre1.substring(pos1,pos1+1))); //CICLO QUE SE REPETIRA SIEMPRE QUE LA BASE SEA LA MISMA A LA ENCONTRADA EN LA POS ALEATORIA
    		
    		String base2="";
    		do //CICLO QUE SE REPETIRA SIEMPRE QUE LA BASE SEA LA MISMA A LA ENCONTRADA EN LA POS ALEATORIA
    		{
	    		int random=rd.nextInt(4); 
	            switch(random) // DE FORMA ALEATORIA INGRESA LAS BASES NUCLEOTICAS
	            {
	                case 0:
	                	base2="C";break;
	                case 1:
	                	base2="G";break;
	                case 2:
	                	
	                	base2="A";break;
	                case 3:
	                	base2="U";break;
	            }   
	            
    		}while(base2.equals(padre2.substring(pos2,pos2+1)));
               		
    		//////////////////
    		//////////////////CREA LOS NUEVOS HIJOS CONCATENANDO DOS SUBCADENAS DEL PADRE Y AGREGANDO LA NUEVA BASE
    		hijo1=padre1.substring(0, pos1) + base + (padre1.substring(pos1+1));
    		hijo2=padre1.substring(0, pos1) + base + (padre1.substring(pos1+1));
    		
            Reemplazo(padre1,padre2,hijo1,hijo2);    //REEMPLAZAMOS POR RULETA        
    }
    
    
    /**
     * METODO QUE DETERMINA EL CRUCE ENTRE DOS PADRES
     * @param padre1 INDIVIDUO QUE REPRESENTA AL PRIMER PADRE
     * @param padre2 INDIVIDUO QUE REPRESENTA AL SEGUNDO PADRE
     */
	static void Cruce(String padre1,String padre2) //OPERACIONES DE CRUZE
    {
		String hijo1;
		String hijo2;
    	
		//////////////////
		//////////////////DOS POSICIONES ALEATORIAS CON TAMANO MAXIMO EL TAMANO DEL INDIVIDUO MAS PEQUEÑO
		int tam=0;
				if(padre1.length()<padre2.length())
				{
					tam=padre1.length();
				}
				else
				{
					tam=padre2.length();
				}
				
		int pos=rd.nextInt(tam); // SE GENERA LA POSICION ALEATORIA ENTRE 0 Y EL TAMANO DEL PADRE MAS PEQUENO
		hijo1=padre1.substring(0, pos)+padre2.substring(pos); //CRUCE ENTRE LOS PADRES
		hijo2=padre2.substring(0, pos)+padre1.substring(pos); //CRUCE ENTRE LOS PADRES
		
        Reemplazo(padre1,padre2,hijo1,hijo2); //REEMPLAZAMOS POR RULETA
    }
	
	/**
	 * METODO QUE REALIZA EL REEMPLAZO EN LA POBLACION TEMPORAL, ESCOJIENDO ENTRE LOS DOS PADRES Y SUS RESPECTIVOS HIJOS PRODUCIDOS
	 * EN EL CRUCE O LA MUTACION
	 * @param padre1	INDIVIDUO QUE REPRESENTA EL PADRE 1
	 * @param padre2	INDIVIDUO QUE REPRESENTA EL PADRE 2
	 * @param hijo1		INDIVIDUO QUE REPRESENTA EL HIJO 1
	 * @param hijo2		INDIVIDUO QUE REPRESENTA EL HIJO 2
	 */
    static void Reemplazo(String p1,String p2, String h1, String h2) //METODO DE REEMPLAZAR POR RULETA
    {
        Individuo padre1=new Individuo(p1,file);
        Individuo padre2=new Individuo(p2,file);  
        Individuo hijo1=new Individuo(h1,file);
        Individuo hijo2=new Individuo(h2,file);
        Individuo mejorpadre;
        Individuo peorpadre;
        Individuo mejorhijo;
        Individuo peorhijo;  
	        
        /// DETERMINAR LOS MEJORES Y PEORES INDIVIDUOS ACORDE AL PESO DE CADA UNO
        if(padre1.getPeso()<padre2.getPeso())
        {
            peorpadre=new Individuo(padre1.getcromosoma(), file);
            mejorpadre=new Individuo(padre2.getcromosoma(), file);
        }
        else
        {
            peorpadre=new Individuo(padre2.getcromosoma(), file);
            mejorpadre=new Individuo(padre1.getcromosoma(), file); 
        }
        
        if(hijo1.getPeso()<hijo2.getPeso())
        {
            peorhijo=new Individuo(hijo1.getcromosoma(), file);
            mejorhijo=new Individuo(hijo2.getcromosoma(), file);
        }
        else
        {
            peorhijo=new Individuo(hijo2.getcromosoma(), file);
            mejorhijo=new Individuo(hijo1.getcromosoma(), file); 
        }
        
        ///////////////////////////////////////////////////
        /////  DETERMINAR EL MEJOR INDIVIDUO
        
        double sumamejorespesos=mejorpadre.getPeso()+mejorhijo.getPeso();
        double sumapeorespesos=peorpadre.getPeso()+peorhijo.getPeso();
        double aleatorio=rd.nextDouble();
        
        ////REALIZA RULETA SOBRE LOS MEJORES PADRES E HIJOS
        if(sumamejorespesos>0)
        {
            if ((aleatorio<mejorpadre.getPeso()/sumamejorespesos))
            {
            	//poblaciontemporal.setIndividuoPoblacion(mejorpadre,posiciontemporal);
            	poblaciontemporal[posiciontemporal]=mejorpadre;
            	posiciontemporal=posiciontemporal+1;
            }
            else
            {
            	//poblaciontemporal.setIndividuoPoblacion(mejorhijo,posiciontemporal);
            	poblaciontemporal[posiciontemporal]=mejorhijo;
            	posiciontemporal=posiciontemporal+1;
            }
        }
        else
        {
        	//poblaciontemporal.setIndividuoPoblacion(mejorpadre,posiciontemporal);
        	poblaciontemporal[posiciontemporal]=mejorpadre;
        	posiciontemporal=posiciontemporal+1;
        }
        ////REALIZA RULETA SOBRE LOS PEORES PADRES E HIJOS
        if(sumapeorespesos>0)
        {
            if ((aleatorio<peorpadre.getPeso()/sumapeorespesos))
            {
            	//poblaciontemporal.setIndividuoPoblacion(peorpadre,posiciontemporal);
            	poblaciontemporal[posiciontemporal]=peorpadre;
            	posiciontemporal=posiciontemporal+1;
            }
            else
            {
            	//poblaciontemporal.setIndividuoPoblacion(peorhijo,posiciontemporal);
            	poblaciontemporal[posiciontemporal]=peorhijo;
            	posiciontemporal=posiciontemporal+1;
            } 
        }
        else
        {
        	//poblaciontemporal.setIndividuoPoblacion(peorpadre,posiciontemporal);
        	poblaciontemporal[posiciontemporal]=peorpadre;
        	posiciontemporal=posiciontemporal+1;
        }
    }
  
}