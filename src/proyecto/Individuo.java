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

/**
 *
 * @author Eng. (C) Edson David Leon - MSc. Carlos Andrés Sierra
 */
public class Individuo 
{
    private double desempeno=0; //PESO ACORDE A LA FUNCION FITNESS
    private String cromosoma; //CADENA DE CARACTERES QUE CONFORMA AL CROMOSOMA
    private FuncionDesempeno fitness;
    //private String file;
    
    public Individuo(String cromosoma, String f) //CONSTRUCTOR
    {
    	//this.file=f;
        this.cromosoma=cromosoma;
        this.desempeno=0;
        //this.longitud=this.cromosoma.length(); //EL TAMAÑO SE CALCULA CON EL TAMAÑO DEL STRING cromosoma
        this.fitness=new FuncionDesempeno(this.cromosoma, f); //SE GENERA UNA CLASE QUE DEFINE LA FUNCION OBJETIVA Y LA CALCULA AL cromosoma
        this.desempeno=this.fitness.getPeso(); //ASIGANEMOS EL PESO ACORDE A LA FUNCION OBJETIVO
        
    }
  
    /**
     * METODO QUE OBTIENE EL TAMANO DEL INDIVIDUO
     * @return RETORNA EL TAMANO DEL CROMOSOMA EN UN ENTERO
     */
    public int getTamano() 
    {
        return cromosoma.length();
    }    
    
    
    /**
     * METODO GET QUE RETORNA EL VALOR DE DESEMPENO QUE DETERMINAR EL PESO
     * @return DEVUELVE EL PESO O MEDIDA DE DESEMPENO
     */
    public double getPeso() //OBTENER PESO
    {
        return desempeno;
    }
    
    /**
     * METODO GET QUE RETORNA EL VALOR DE LA CADENA DEL CROMOSOMA
     * @return DEVUEVLE EL CROMOSOMA
     */
    public String getcromosoma() //OBTENER cromosoma
    {
        return cromosoma;
    }
    
    /**
     * METODO SET QUE MODIFICA EL VALOR DEL CROMOSOMA EN EL INDIVIDUO
     * @param cr CROMOSOMA NUEVO A MODIFICAR
     */
    void setcromosoma(String cr,String file) 
    {
        this.cromosoma=cr;
        this.fitness=new FuncionDesempeno(this.cromosoma, file); //SE GENERA UNA CLASE QUE DEFINE LA FUNCION OBJETIVA Y LA CALCULA AL CROMOSOMA
        this.desempeno=this.fitness.getPeso(); //ASIGANEMOS EL PESO ACORDE A LA FUNCION OBJETIVO
    }
}