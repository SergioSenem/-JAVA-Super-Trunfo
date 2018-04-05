package supertrunfo;
import java.util.Scanner;

public class Jogador {
    
    int numCartas;
    boolean vez;
    CartaDeus cartas[] = new CartaDeus[32];
    
    public char escolherCaracteristica(Pilha pilha)
    {
        CartaDeus carta = new CartaDeus(); 
        carta = this.cartas[0];
        Scanner sc = new Scanner(System.in);
        char carac;
        System.out.println("Nome: " + carta.nome + "\n");
        System.out.println("Escolher Caracteristica\n");
        System.out.println("[P] - Poder: " + carta.poder + " (" + pilha.maxPoder +")");
        System.out.println("[F] - Forca: " + carta.forca + " (" + pilha.maxForca +")");
        System.out.println("[I] - Inteligencia: " + carta.inteligencia + " (" + pilha.maxInteligencia +")");
        System.out.println("[V] - Velocidade: " + carta.velocidade + " (" + pilha.maxVelocidade +")");
        System.out.println("[A] - Adoradores: " + carta.adoradores + " (" + pilha.maxAdoradores +")");
        carac = sc.next().charAt(0);
        return carac;
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
    }    
    public void mostraNumCartas(){
        System.out.println("\nNúmero de cartas na mão (Jogador): " + this.numCartas + "\n");
    }
    
}
