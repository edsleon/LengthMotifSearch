package proyecto;
//package proyecto;
/**
 *
 * @author Edson
 */
public class Poblacion 
{
    private int n; //TAMAÑO DE LA POBLACION
    private Individuo[] poblacion; // VECTOR QUE CONTIENE TODOS LOS INDIVIDUOS
    private int tammax=35;
    private int tammin=5;
    private String file;
    
    
    public Poblacion(int n, String f) //CONSTRUCTOR
    {
        this.n=n; //TAMANO PARA LA POBLACION RECIBIDO COMO PARAMETRO POR EL CONTRUCTOR
        this.file=f;
        poblacion=new Individuo[this.n]; // CONTRUYE EL VECTOR CON EL TAMAÑO RECIBIDO
        GenerarPoblacion(); //LLAMADA DE METODO PARA CONSTRUIR LA POBLACION
    }
    
    /**
     * METODO GET QUE RETORNA EL TAMANO DE LA POBLACION
     * @return DEVUELVE EL TAMANO DE LA POBLACION
     */
    public int getn() 
    {
        return n;
    }
  
    /**
     * METODO SET QUE MODIFICA UN INDIVIDUO EN LA POBLACION
     * @param in INDIVIDUO A REEMPLAZAR EN LA POBLACION
     * @param pos POSICION EN LA POBLACION DONDE SE REEMPLAZARA EL OBJETIVO
     */
    void setIndividuoPoblacion(String in,int pos) 
    {
        this.poblacion[pos].setcromosoma(in,file);
    } 
    
    
    /**
     * METODO GET QUE RETORNA UN INDIVIDUO ESPECIFICO DE LA POBLACION
     * @param pos POSICION DEL INDIVIDUO EN LA POBLACION QUE RETORNARA
     * @return DEVUELVE UN INDIVIDUO DE LA POBLACION 
     */
    Individuo getIndividuoPoblacion(int pos) 
    {
        return poblacion[pos];
    } 
    
    /**
     * METODO QUE MODIFICA UN INDIVIDUO DE LA POBLACION
     * @param in INDIVIDUO 	QUE SE REEMPLAZARA EN LA POBLACION
     */
    public void setPoblacion(Individuo[] in) 
    {
        this.poblacion=in;
    }
    
    /**
     * METODO QUE DEVUELVE LA POBLACION
     * @return DEVUELVE LA POBLACION
     */
    Individuo[] getpoblacion()
    {
        return poblacion;
    }
    
    
    public void setFile(String file)
    {
    	this.file = file;
    }
    public String getFile()
    {
    	return file;
    }
    
    
    /**
     * IMPRIME POR CONSOLA LA POBLACION ACTUAL
     */
    public void Imprimirpoblacion() 
    {
        Individuo mejorind=new Individuo("", file);
        for(int i=0;i<n;i++)
        {
        	System.out.println(poblacion[i].getcromosoma()+"\t"+poblacion[i].getPeso());
            if(poblacion[i].getPeso()!=0)
            {
               if(mejorind.getPeso()<poblacion[i].getPeso())
               {
                   mejorind=poblacion[i];
               }
               
            }
        }
        //TODO UTILIZAR BUFFERED WRITER
        System.out.println("El mejor individuo es "+mejorind.getcromosoma()+" con peso "+mejorind.getPeso()+" y tamaño "+mejorind.getTamano()+"\n\n\n\n");
    }
  
    
    /**
     * GENERA LA POBLACION INICIAL EN EL CONSTRUCTOR DE FORMA ALEATORIA
     */
    void GenerarPoblacion() 
    {
        for(int i=0;i<n;i++) 
        {
	        String ind="";
	        int tamano=(int)(Math.random()*(tammax-tammin)+tammin);
	        for(int j=0;j<tamano;j++) 
	        {
	            int random=(int)((Math.random()*4)+1); 
	            switch(random) 
	            {
	                case 1:
	                    ind=ind.concat("C");break;
	                case 2:
	                    ind=ind.concat("G");break;
	                case 3:
	                    ind=ind.concat("A");break;
	                case 4:
	                    ind=ind.concat("U");break;
	            }
	        }
	        poblacion[i]=new Individuo(ind, file); 
        }
    }
}