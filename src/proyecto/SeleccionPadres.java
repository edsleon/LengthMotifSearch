package proyecto;

import java.util.Random;


/**
 *
 * @author Edson
 */
public class SeleccionPadres 
{
    Poblacion poblacion;
    int longitud;
    //Individuo padre1=new Individuo("", "");
    //Individuo padre2=new Individuo("", "");
    String padre1;
    String padre2;
    static Random rd=new Random();
    
    public SeleccionPadres(Poblacion po)
    {
        this.poblacion=po;
        this.longitud=po.getn();
    }
    
    public void setPoblacion(Individuo[] po)
    {
        this.poblacion.setPoblacion(po);
        this.longitud=poblacion.getn();
        
    }
    
    public String getPadre1()
    {
        return padre1;
    }
    
    public String getPadre2()
    {
        return padre2;
    }
    
    public void calcularPadres()
    {
        
        padre1=poblacion.getIndividuoPoblacion(rd.nextInt(longitud)).getcromosoma();
        do
        {
            padre2=poblacion.getIndividuoPoblacion(rd.nextInt(longitud)).getcromosoma();
        }
        while(padre1.equals(padre2));
    }
          
}