/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

/**
 *
 * @author Edson
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class FuncionDesempeno 
{
	
	private String individuo;
	private double peso;
    private File archivo = null;
    private FileReader fr = null;
    private BufferedReader br = null;
    private String linea;
	
	/**
	 * CONSTRUCTOR QUE CALCULA LA FUNCION DE DESEMPENO INCIAL
	 * @param m RECIBE COMO PARAMETRO EL CROMOSOMA
	 * @param file RECIBE EL ARCHIVO PLANO CON LOS DATASETS
	 */
	FuncionDesempeno(String m, String file)
	{
		this.individuo=m;
		this.peso=0;
		CalcularObjetivos(file);
	}
	
	/**
	 * METODO DETERMINA UN NUEVO INDIVIDUO A LA FUNCION DE DESEMPENO
	 * @param m RECIBE COMO PARAMETRO EL CROMOSOMA
	 * @param file RECIBE EL ARCHIVO PLANO CON LOS DATASETS
	 */
	void setIndividuo(String m, String file)
	{
		this.individuo=m;
		this.peso=0;
		CalcularObjetivos(file);
	}
	
	/**
	 * METODO QUE CALCULA LOS OBJETIVOS
	 * @param file RECIBE EL ARCHIVO PLANO CON LOS DATASETS
	 */
	void CalcularObjetivos(String file)
	{
        int pos=0; // AUX PARA DETERMINAR LA POS DE UNA CONCURRENCIA
        double cont=0;// CONTADOR DE LAS CONCURRENCIAS
        int auxbandera=0;// CONTADOR DE LAS CONCURRENCIAS
        int mutacion=0; // CONTADOR DE LAS MUTACIONES
        this.peso=0;
        int reads = 0, longitudes = 0;
        
        
 ////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
        try //LEER UN ARCHIVO DE TEXTO
        {
            archivo = new File (file);
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);
            linea = br.readLine();
            
            
            
            while((this.linea=br.readLine())!=null)
            {
	            ////////////////////////////////////////////////////////////////////////////////
            	String[] read = linea.split(" ");
            	reads++;
            	
            	if(read.length > 12)
            	{
            		
		            for(int j=0;j<read[8].length();j++) // CICLO PARA ENCONTRAR CONCURRENCIAS Y SUMAR MUTACIONES
		            {
			            pos=read[8].indexOf(this.individuo,pos+j); //FUNCION QUE BUSCA CONCURRENCIAS ENTRE CADENAS
			            if(pos==-1) //SI NO ENCUENTRA EN TODA LA CADENA SALE DEL CICLO
			            {
			                break;
			            }
			            else // SI ENCUENTRA UNA CONCURENCIA SUMA, TOMA LA MUTACION Y LA SUMA, Y CONTINUA EL CICLO
			            {
			               cont=cont+1;
			               auxbandera=1;//SI ENCUENTRA UNA CONCURRENCIA
			            }
		            }
		            if(auxbandera==1)//SI ENCUENTRA UNA CONCURRENCIA
		            {
		               mutacion=mutacion+( Integer.parseInt(read[9])); 
		               longitudes = longitudes + read[8].length();
		            }
		            auxbandera=0;
	                ////////////////////////////////////////////////////////////////////////////////               
            
            	}
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }     finally
        {
            try{                    
            if( null != fr )
                {   
               fr.close();     
                }                  
            }catch (Exception e2){ 
            e2.printStackTrace();
        }
        }
        
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
        if(cont==0)
        {
            this.peso=cont;   
        }
        else
        {
        	this.peso=((mutacion/longitudes) * 0.5) + ((cont / reads) / 0.5);   
        }
	}
	double getPeso()
	{
		return peso;
	}

}