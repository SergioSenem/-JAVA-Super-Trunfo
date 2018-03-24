package supertrunfo;

public class Regra {
    
    CartaDeus[] pilha = new CartaDeus[32];
    CartaDeus[] cartAcum = new CartaDeus[32];
    int numCartAcum;
    
    public void compararCarta(char esc, Jogador j, Bot b)
    {
        int att1 = 0, att2 = 0;
        switch (esc){
            case 'P':
                att1 = j.cartas[0].poder;
                att2 = b.cartas[0].poder;
                break;
            case 'F':
                att1 = j.cartas[0].forca;
                att2 = b.cartas[0].forca;
                break;
            case 'I':
                att1 = j.cartas[0].inteligencia;
                att2 = b.cartas[0].inteligencia;
                break;
            case 'V':
                att1 = j.cartas[0].velocidade;
                att2 = b.cartas[0].velocidade;
                break;
            case 'A':
                att1 = j.cartas[0].adoradores;
                att2 = b.cartas[0].adoradores;
                break;
            default:
                System.out.println("Erro ao comparar cartas");
        }
        if(att1 == 0){System.out.println("Deu merda");}
        if(att2 == 0){System.out.println("Deu merda");}
        if(att1 > att2){
            j.vez = true;
            b.vez = false;
            j.ganharCarta(b.cartas[0]);
            b.perderCarta();
            if(this.numCartAcum != 0){
                int i;
                for(i=0;i<this.numCartAcum;i++){
                    j.ganharCarta(this.cartAcum[i]);
                }
                this.numCartAcum = 0;
            }
            System.out.println("Jogador ganhou a rodada!");
        }
        else{
            if(att2 > att1){
                j.vez = false;
                b.vez = true;
                b.ganharCarta(j.cartas[0]);
                j.perderCarta();
                if(this.numCartAcum != 0){
                    int i;
                    for(i=0;i<this.numCartAcum;i++){
                        b.ganharCarta(this.cartAcum[i]);
                    }
                    this.numCartAcum = 0;
                }
                System.out.println("Computador ganhou a rodada!");
            }
            else{
                this.numCartAcum += 2;
                this.cartAcum[this.numCartAcum - 2] = j.cartas[0];
                this.cartAcum[this.numCartAcum - 1] = b.cartas[0];
                j.perderCarta();
                b.perderCarta();
                System.out.println("A rodada terminou em empate!");
            }
        }
    }
    
    public CartaDeus[] embaralhar()
    {
        CartaDeus pilhaNova[] = new CartaDeus[32];
        int i;
        int contNum = 0;
        int nums[] = new int[32];
        for(i=0;i<32;i++){
            int index = -1;
            int j;
            boolean jaExiste = false;
            while(index == -1 || jaExiste){
                jaExiste = false;
                index = (int)(long)Math.round(Math.random()*31);
                for(j=0;j<contNum;j++){
                    if(nums[j] == index){
                        jaExiste = true;
                    }
                }
            }
        
            
            nums[contNum] = index;
            pilhaNova[contNum] = this.pilha[index];
            contNum++;
        }
        
        return pilhaNova;
    }
    
    
    public void distribuir(Jogador j1, Bot j2){
        int i;
        for(i = 0;i<16;i++){
            j1.cartas[i] = this.pilha[i];
            j1.numCartas = 16;
            j2.cartas[i] = this.pilha[i+16];
            j2.numCartas = 16;
        }
    }
    
    public boolean verificarCartas(Jogador j, Bot b)
    {
        boolean acabou;
        if(j.numCartas != 0 && b.numCartas == 0){
            System.out.println("Jogador ganhou!");
            j.venc = true;
            acabou = true;
        }
        else{
            if(j.numCartas == 0 && b.numCartas != 0){
                System.out.println("Bot ganhou!");
                b.venc = true;
                acabou = true;
            }
            else{
                if(j.numCartas == 0 && b.numCartas == 0){
                    System.out.println("Empatou!");
                    acabou = true;
                }
                else{
                    acabou = false;
                }
            }
        }
        return acabou;
    }
    
    public CartaDeus getCarta(String codigo, String nome, int poder, int forca, 
                         int inteligencia, int velocidade, int adoradores){
        CartaDeus carta = new CartaDeus();
        carta.codigo = codigo;
        carta.nome = nome;
        carta.poder = poder;
        carta.forca = forca;
        carta.inteligencia = inteligencia;
        carta.velocidade = velocidade;
        carta.adoradores = adoradores;
        
        return carta;
    }
    
    public void jogar(Jogador j, Bot b){
        CartaDeus cartaJ = new CartaDeus(); 
        cartaJ = j.cartas[0];
        CartaDeus cartaB = new CartaDeus();
        cartaB = b.cartas[0];
        System.out.println("Nome(VC): " + cartaJ.nome + "\n");
        System.out.println("Escolher Caracteristica\n");
        System.out.println("[P] - Poder: " + cartaJ.poder);
        System.out.println("[F] - Forca: " + cartaJ.forca);
        System.out.println("[I] - Inteligencia: " + cartaJ.inteligencia);
        System.out.println("[V] - Velocidade: " + cartaJ.velocidade);
        System.out.println("[A] - Adoradores: " + cartaJ.adoradores + "\n");
        
        System.out.println("Nome(BOT): " + cartaB.nome + "\n");
        System.out.println("Escolher Caracteristica\n");
        System.out.println("[P] - Poder: " + cartaB.poder);
        System.out.println("[F] - Forca: " + cartaB.forca);
        System.out.println("[I] - Inteligencia: " + cartaB.inteligencia);
        System.out.println("[V] - Velocidade: " + cartaB.velocidade);
        System.out.println("[A] - Adoradores: " + cartaB.adoradores + "\n");
        if(j.vez && !b.vez){
            char car = j.escolherCarac();
            this.compararCarta(car, j, b);
        }
        else{
            if(!j.vez && b.vez){
                char car = b.escolherCarac();
                this.compararCarta(car, j, b);
            }
            else{
                System.out.println("Erro ao verificar vez de jogadores!");
            }
        }
    }
    
}
