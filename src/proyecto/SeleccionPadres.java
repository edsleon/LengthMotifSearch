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