package supertrunfo;
import java.util.Scanner;

public class Jogador {
    
    int numCartas;
    boolean vez;
    CartaDeus cartas[] = new CartaDeus[32];
    boolean venc;
    
    public char escolherCarac()
    {
        CartaDeus carta = new CartaDeus(); 
        carta = this.cartas[0];
        Scanner sc = new Scanner(System.in);
        char carac;
        System.out.println("Nome: " + carta.nome + "\n");
        System.out.println("Escolher Caracteristica\n");
        System.out.println("[P] - Poder: " + carta.poder);
        System.out.println("[F] - Forca: " + carta.forca);
        System.out.println("[I] - Inteligencia: " + carta.inteligencia);
        System.out.println("[V] - Velocidade: " + carta.velocidade);
        System.out.println("[A] - Adoradores: " + carta.adoradores);
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
        int i;
        CartaDeus primeira = new CartaDeus();
        primeira = this.cartas[0];
        for(i=0;i<this.numCartas-1;i++){
            this.cartas[i] = this.cartas[i+1];
        }
        this.cartas[this.numCartas-1] = primeira;
    }    
    public void mostraNumCartas(){
        System.out.println("\nNúmero de cartas na mão (Jogador): " + this.numCartas + "\n");
    }
    
}
