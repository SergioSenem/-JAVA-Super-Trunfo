package supertrunfo;

public class Bot {
    
    int numCartas;
    boolean vez;
    CartaDeus cartas[] = new CartaDeus[32];
    
    public char escolherCaracteristica()
    {
        CartaDeus carta = new CartaDeus();
        carta = this.cartas[0];
        int attMax = 0;
        char att = 'X';
        if(carta.adoradores > attMax){
            attMax = carta.adoradores;
            att = 'A';
        }
        if(carta.poder > attMax){
            attMax = carta.poder;
            att = 'P';
        }
        if(carta.forca > attMax){
            attMax = carta.forca;
            att = 'F';
        }
        if(carta.inteligencia > attMax){
            attMax = carta.inteligencia;
            att = 'I';
        }
        if(carta.velocidade > attMax){
            attMax = carta.velocidade;
            att = 'V';
        }
        
        return att;
        
    }
    
    public void perderCarta(){
        int i;
        for(i=0;i<this.numCartas - 1;i++){
            this.cartas[i] = this.cartas[i+1];
            this.cartas[i+1] = null;
        }
        this.numCartas--;
    }
    
    public void ganharCarta(CartaDeus carta){
        this.cartas[this.numCartas] = carta;
        this.numCartas++;
        int i;
        CartaDeus primeira = new CartaDeus();
        primeira = this.cartas[0];
        for(i=0;i<this.numCartas-1;i++){
            this.cartas[i] = this.cartas[i+1];
        }
        this.cartas[this.numCartas-1] = primeira;
    }
    
    public void mostraNumCartas(){
        System.out.println("\nNúmero de cartas na mão (Computador): " + this.numCartas + "\n");
    }
    
}

