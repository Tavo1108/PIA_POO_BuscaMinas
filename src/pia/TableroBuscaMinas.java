/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pia;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author 52811
 */
public class TableroBuscaMinas 
{ 
    Casillas[][] casillasT;
    
    int nFilas;
    int nColumnas;
    int nMinas;
    
    
    int numCasillasAbiertas;
    boolean generacionMinas;
    
    Consumer<List<Casillas>> partidaPerdida;
    Consumer<List<Casillas>> eventoPartidaGanada;
    
    Consumer<Casillas> casillaAbierta;

    public TableroBuscaMinas(int nFilas, int nColumnas, int nMinas) {
        this.nFilas = nFilas;
        this.nColumnas = nColumnas;
        this.nMinas = nMinas;
        this.inicializacionCasillas();
        this.generacionMinas = false;
        
       
    }
    
    public void inicializacionCasillas()
    {
        casillasT = new Casillas[this.nFilas][this.nColumnas];
        
        for(int i = 0 ; i < casillasT.length ; i++)
        {
            for(int j = 0 ; j < casillasT[i].length ; j++)
            {
                casillasT[i][j] = new Casillas(i, j);
            }
        }
        
       
    }
    
    private void generadorMinas(int posFilaI, int posColumnaI)
    {
        int minas = 0;
        while(minas!=nMinas)
        {
            int filaMina = (int)(Math.random()*casillasT.length);
            int columnaMina = (int)(Math.random()*casillasT[0].length);
            do
            {
                filaMina = (int)(Math.random()*casillasT.length);
                columnaMina = (int)(Math.random()*casillasT[0].length);
            }while((filaMina == posFilaI && columnaMina == posColumnaI));
            
            casillasT[filaMina][columnaMina].setMina(true);
            minas++;
        }
        actualizarNumeroMinasAlrededor();
        this.generacionMinas = true;
    }
    
    public List<Casillas> obtenerTodasLasCasillas() 
    {
        List<Casillas> todas = new LinkedList<>();
        for (int i = 0; i < casillasT.length; i++) 
        {
            for (int j = 0; j < casillasT[i].length; j++) 
            {
                todas.add(casillasT[i][j]);
            }
        }
        return todas;
    }
    
    public void imprimirTablero()
    {
        for(int i = 0 ; i < casillasT.length ; i++)
        {
            for(int j = 0 ; j < casillasT[i].length ; j++)
            {
                if(casillasT[i][j].isMina())
                {
                    System.out.print("*");
                }
                else
                    System.out.print("0");
            }
            System.out.println("");
        }
    }
    
    private void imprimirPistas()
    {
        for(int i = 0 ; i < casillasT.length ; i++)
        {
            for(int j = 0 ; j < casillasT[i].length ; j++)
            {
               System.out.print(casillasT[i][j].getnMinasAlrededor());
            }
            System.out.println();
        }
        
    }
    
    private void actualizarNumeroMinasAlrededor()
    {
        for(int i = 0 ; i < casillasT.length ; i++)
        {
            for(int j = 0 ; j < casillasT[i].length ; j++)
            {
                if(casillasT[i][j].isMina())
                {
                    List<Casillas> casillasAlrededor = obtenerCasillasSinMinas(i, j);
                    casillasAlrededor.forEach((c)->c.incrementarMinasAlrededor());
                }
                
            }
            
        }
    }
    
    private List<Casillas> obtenerCasillasSinMinas(int posicionF, int posicionC)
    {
        List<Casillas> listaCasillas = new LinkedList<>();
        for(int i = 0 ; i < 8 ; i++)
        {
            int filaMina = posicionF;
            int columnaMina = posicionC;
            
            switch(i)
            {
                case 0:
                    filaMina--;
                    break;
                    
                case 1:
                    filaMina--;
                    columnaMina++;
                    break;
                case 2: 
                    columnaMina++;
                    break;
                case 3:
                    columnaMina++;
                    filaMina++;
                    break;
                case 4:
                    filaMina++;
                    break;
                case 5:
                    filaMina++;
                    columnaMina--;
                    break;
                case 6:
                    columnaMina--;
                    break;
                case 7:
                    filaMina--;
                    columnaMina--;
                    break;  
            }
            if(filaMina >= 0 && filaMina < this.casillasT.length && columnaMina >= 0 && columnaMina < this.casillasT[0].length)
            {
                listaCasillas.add(this.casillasT[filaMina][columnaMina]);
            }
        }
        return listaCasillas;
    }
    List<Casillas> obtenerCasillasConMinas()
    {
        List<Casillas> casillasMinas = new LinkedList<>();
        for(int i = 0 ; i < casillasT.length ; i++)
        {    
            for(int j = 0 ; j < casillasT[i].length ; j++)
            {
                if(casillasT[i][j].isMina())
                {
                    casillasMinas.add(casillasT[i][j]);
                }
                
            }
            
        }
        return casillasMinas;
    }
    public void seleccionarCasilla(int posicionF, int posicionC)
    {
        
        if(!this.generacionMinas)
        {
            this.generadorMinas(posicionF, posicionC);
        }
        casillaAbierta.accept(this.casillasT[posicionF][posicionC]);
        if(this.casillasT[posicionF][posicionC].isMina())
        {
            partidaPerdida.accept(obtenerCasillasConMinas());
            
        }
        else if(this.casillasT[posicionF][posicionC].getnMinasAlrededor() == 0)
        {
            marcarCasillaAbierta(posicionF, posicionC);
            List<Casillas> casillasAlrededor = obtenerCasillasSinMinas(posicionF, posicionC);
            for(Casillas casilla : casillasAlrededor)
            {
                if(!casilla.isCasillaAbierta())
                {
                    seleccionarCasilla(casilla.getPosicionF(), casilla.getPosicionC());           
                }
            }
        } 
        else
        {
            marcarCasillaAbierta(posicionF, posicionC);
        }
        if(partidaGanada())
        {
            eventoPartidaGanada.accept(obtenerCasillasConMinas());
        }
    }
    void marcarCasillaAbierta(int posicionF, int posicionC)
    {
        if(!this.casillasT[posicionF][posicionC].isCasillaAbierta())
        {
            numCasillasAbiertas++;
            this.casillasT[posicionF][posicionC].setCasillaAbierta(true);
        }
    }
    boolean partidaGanada()
    {
        return numCasillasAbiertas >= (nFilas*nColumnas)-nMinas;
    }
    
   
    public static void main(String[] args)
    {
        TableroBuscaMinas tablero = new TableroBuscaMinas(16,20, 15);
        tablero.imprimirTablero();
        System.out.print("---\n");
        tablero.imprimirPistas();
    }
    
    public void setPartidaPerdida(Consumer<List<Casillas>> partidaPerdida)
    {
        this.partidaPerdida = partidaPerdida;
    }

    
    public void setCasillaAbierta(Consumer<Casillas> casillaAbierta) {
        this.casillaAbierta = casillaAbierta;
    }
    
    public void setEventoPartidaGanada(Consumer<List<Casillas>> eventoPartidaGanada)
    {
        this.eventoPartidaGanada = eventoPartidaGanada;
    }
    
    
}
