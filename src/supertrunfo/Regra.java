package supertrunfo;

public class Regra {
    
    Pilha pilha = new Pilha();
    CartaDeus[] cartAcum = new CartaDeus[32];
    String codSuperTrunfo;
    char catSup;
    int numCartAcum;
    
    public void compararCarta(char esc, Jogador j, Bot b)
    {
        float attJ, attB;
        
        CartaDeus cartaJ;
        cartaJ = j.cartas[0];
        CartaDeus cartaB;
        cartaB = b.cartas[0];
        
        if((attJ = cartaJ.getAtributo(esc)) == -1){
            System.out.println("Erro na atribuição de valor para atributo (Jogador)");
        }

        if((attB = cartaB.getAtributo(esc)) == -1){
            System.out.println("Erro na atribuição de valor para atributo (Bot)");
        }
        
        int maxAtt;
        
        switch (esc){
            case 'P':
                maxAtt = this.pilha.maxPoder;
                break;
            case 'F':
                maxAtt = this.pilha.maxForca;
                break;
            case 'I':
                maxAtt = this.pilha.maxInteligencia;
                break;
            case 'V':
                maxAtt = this.pilha.maxVelocidade;
                break;
            case 'A':
                maxAtt = this.pilha.maxAdoradores;
                break;
            default:
                maxAtt = -1;
        }
        
        attJ = attJ/maxAtt;
        attB = attB/maxAtt;
        
        if(attJ<0){
            System.out.println("Erro ao definir valor máximo de atributos");
        }
        


        if(attJ > attB){
            this.jogGanhaRodada(j, b);
        }
        if(attJ < attB){
            this.botGanhaRodada(j, b);
        }
        if(attJ == attB){
            this.empataRodada(j, b);
        }
    }
    
    public boolean verificarCartas(Jogador j, Bot b)
    {
        boolean acabou = false;
        if(b.numCartas == 0){
            System.out.println("Jogador ganhou o jogo!");
            acabou = true;
        }
        if(j.numCartas == 0){
            System.out.println("Computador ganhou o jogo!");
            acabou = true;
        }
        return acabou;
    }
    
    public CartaDeus criarCarta(String codigo, String nome, int poder, int forca, 
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
        char car = 'X';
        if(cartaJ.codigo.equals(this.codSuperTrunfo) && cartaB.codigo.charAt(0) != this.catSup){
            this.mostraCartas(cartaJ, cartaB, car);
            System.out.println("SUPER TRUNFO!");
            this.jogGanhaRodada(j, b);
        }
        else{
            if(cartaB.codigo.equals(this.codSuperTrunfo) && cartaJ.codigo.charAt(0) != this.catSup){
                this.mostraCartas(cartaJ, cartaB, car);
                System.out.println("SUPER TRUNFO!");
                this.botGanhaRodada(j, b);
            }
            else{
                if(j.vez && !b.vez){
                    car = j.escolherCaracteristica(this.pilha);
                    this.mostraCartas(cartaJ, cartaB, car);
                    this.compararCarta(car, j, b);
                }
                else{
                    if(!j.vez && b.vez){
                        car = b.escolherCaracteristica();
                        this.mostraCartas(cartaJ, cartaB, car);
                        this.compararCarta(car, j, b);
                    }
                    else{
                        System.out.println("Erro ao verificar vez de jogadores!");
                    }
                }
            }
        }
        j.mostraNumCartas();
        b.mostraNumCartas();
    }
    
    public void jogGanhaRodada(Jogador j, Bot b){
        this.mandaPilha(j, b);
        j.vez = true;
        b.vez = false;
        int i;
        for(i=0;i<this.numCartAcum;i++){
            j.ganharCarta(this.cartAcum[i]);
        }
        this.numCartAcum = 0;
        System.out.println("Jogador ganhou a rodada!\n");
    }
    
    public void botGanhaRodada(Jogador j, Bot b){
        this.mandaPilha(j, b);
        j.vez = false;
        b.vez = true;
        int i;
        for(i=0;i<this.numCartAcum;i++){
            b.ganharCarta(this.cartAcum[i]);
        }
        this.numCartAcum = 0;
        
        System.out.println("Computador ganhou a rodada!\n");
    }
    
    public void empataRodada(Jogador j, Bot b){
        this.mandaPilha(j, b);
        System.out.println("A rodada terminou em empate!\n");
    }
    
    public void mostraCartas(CartaDeus cartaJ, CartaDeus cartaB, char esc){
        System.out.println("CARTAS: \n");
        System.out.println("Nome:         " + cartaJ.nome + "   " + cartaB.nome + "\n");
        System.out.println("Poder:        " + cartaJ.poder + "         " + cartaB.poder);
        System.out.println("Força:        " + cartaJ.forca + "         " + cartaB.forca);
        System.out.println("Inteligencia: " + cartaJ.inteligencia + "         " + cartaB.inteligencia);
        System.out.println("Velocidade:   " + cartaJ.velocidade + "         " + cartaB.velocidade);
        System.out.println("Adoradores:   " + cartaJ.adoradores + "         " + cartaB.adoradores + "\n");
        
        switch (esc){
            case 'P':
                System.out.println("Atributo escolhido: Poder");
                break;
            case 'F':
                System.out.println("Atributo escolhido: Força");
                break;
            case 'I':
                System.out.println("Atributo escolhido: Inteligencia");
                break;
            case 'V':
                System.out.println("Atributo escolhido: Velocidade");
                break;
            case 'A':
                System.out.println("Atributo escolhido: Adoradores");
                break;
            default:
                System.out.println("Atributo escolhido: Nenhum");
                break;
        }
    }
    
    public void mandaPilha(Jogador j, Bot b){
        CartaDeus cartaJ = j.cartas[0];
        CartaDeus cartaB = b.cartas[0];
        this.numCartAcum += 2;
        this.cartAcum[this.numCartAcum - 2] = cartaJ;
        this.cartAcum[this.numCartAcum - 1] = cartaB;
        j.perderCarta();
        b.perderCarta();
    }
    
}
