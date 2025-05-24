/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pia;

/**
 *
 * Gustavo Arreola Almaguer 2074164
 */
public class Casillas 
{
    
    private int posicionF; 
    private int posicionC; 
    private boolean mina; 
    private int nMinasAlrededor; 
    private boolean casillaAbierta;

    public Casillas(int posicionF, int posicionC) 
    {
        this.posicionF = posicionF;
        this.posicionC = posicionC;
        
    }

    
    public int getPosicionF() 
    {
        return posicionF;
    }
    
    public void setPosicionF(int posicionF)
    {
        this.posicionF = posicionF;
    }

    public int getPosicionC()
    {
        return posicionC;
    }
    
    public void setPosicionC(int posicionC)
    {
        this.posicionC = posicionC;
    }

    public boolean isMina() 
    {
        return mina;
    }

    public void setMina(boolean mina) 
    {
        this.mina = mina;
    }    

    public int getnMinasAlrededor() 
    {
        return nMinasAlrededor;
    }

    public void setnMinasAlrededor(int nMinasAlrededor) 
    {
        this.nMinasAlrededor = nMinasAlrededor;
    }
    public void incrementarMinasAlrededor()
    {
        this.nMinasAlrededor++;
    }
    
    public boolean isCasillaAbierta() 
    {
        return casillaAbierta;
    }

   
    public void setCasillaAbierta(boolean casillaAbierta) 
    {
        this.casillaAbierta = casillaAbierta;
    }
}
