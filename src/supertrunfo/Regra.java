package supertrunfo;

public class Regra {
    
    CartaDeus[] pilha = new CartaDeus[32];
    CartaDeus[] cartAcum = new CartaDeus[32];
    String codSuperTrunfo;
    char catSup;
    int numCartAcum;
    
    public void compararCarta(char esc, Jogador j, Bot b)
    {
        int attJ, attB;
        
        CartaDeus cartaJ;
        cartaJ = j.cartas[0];
        CartaDeus cartaB;
        cartaB = b.cartas[0];
        
        int resultado;
        
        
        if((attJ = this.getAtt(esc, cartaJ)) == -1){
            System.out.println("Erro na atribuição de valor para atributo (Jogador)");
        }

        if((attB = this.getAtt(esc, cartaB)) == -1){
            System.out.println("Erro na atribuição de valor para atributo (Bot)");
        }


        if(attJ > attB){
            resultado = 1;
        }
        else{
            if(attJ < attB){
                resultado = 2;
            }
            else{
                resultado = 0;
            }
        }
        
        switch (resultado){
            case 1:
                this.jogGanhaRodada(j, b);
                break;
            case 2:
                this.botGanhaRodada(j, b);
                break;
            case 0:
                this.empataRodada(j, b);
                break;
            default:
                System.out.println("Erro ao determinar vencedor da rodada!");
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
            System.out.println("Jogador ganhou o jogo!");
            j.venc = true;
            acabou = true;
        }
        else{
            if(j.numCartas == 0 && b.numCartas != 0){
                System.out.println("Computador ganhou o jogo!");
                b.venc = true;
                acabou = true;
            }
            else{
                if(j.numCartas == 0 && b.numCartas == 0){
                    System.out.println("O jogo terminou em empate!");
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
                    car = j.escolherCarac();
                    this.mostraCartas(cartaJ, cartaB, car);
                    this.compararCarta(car, j, b);
                }
                else{
                    if(!j.vez && b.vez){
                        car = b.escolherCarac();
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
    
    public int getAtt(char esc, CartaDeus carta){
        int att;
        switch (esc){
            case 'P':
                att = carta.poder;
                break;
            case 'F':
                att = carta.forca;
                break;
            case 'I':
                att = carta.inteligencia;
                break;
            case 'V':
                att = carta.velocidade;
                break;
            case 'A':
                att = carta.adoradores;
                break;
            default:
                att = -1;
    
        }
        return att;
    }
    
    public void jogGanhaRodada(Jogador j, Bot b){
        CartaDeus cartaB = b.cartas[0];
        j.vez = true;
        b.vez = false;
        j.ganharCarta(cartaB);
        b.perderCarta();
        if(this.numCartAcum != 0){
            int i;
            for(i=0;i<this.numCartAcum;i++){
                j.ganharCarta(this.cartAcum[i]);
            }
            this.numCartAcum = 0;
        }
        System.out.println("Jogador ganhou a rodada!\n");
    }
    
    public void botGanhaRodada(Jogador j, Bot b){
        CartaDeus cartaJ = j.cartas[0];
        j.vez = false;
        b.vez = true;
        b.ganharCarta(cartaJ);
        j.perderCarta();
        if(this.numCartAcum != 0){
            int i;
            for(i=0;i<this.numCartAcum;i++){
                b.ganharCarta(this.cartAcum[i]);
            }
            this.numCartAcum = 0;
        }
        System.out.println("Computador ganhou a rodada!\n");
    }
    
    public void empataRodada(Jogador j, Bot b){
        CartaDeus cartaJ = j.cartas[0];
        CartaDeus cartaB = b.cartas[0];
        this.numCartAcum += 2;
        this.cartAcum[this.numCartAcum - 2] = cartaJ;
        this.cartAcum[this.numCartAcum - 1] = cartaB;
        j.perderCarta();
        b.perderCarta();
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
    
}
