package supertrunfo;

import java.io.IOException;

public class Jogo {
    
    Jogador jog;
    Bot bot;
    
    Regra reg = new Regra();
    
    public void setPilha(){
        this.reg.numCartAcum = 0;
        this.reg.pilha.cartas[0] = reg.criarCarta("A1", "Odin", 60, 70, 55, 30, 70);
        this.reg.pilha.cartas[1] = reg.criarCarta("A2", "Thor", 70, 80, 30, 40, 80);
        this.reg.pilha.cartas[2] = reg.criarCarta("A3", "Loki", 80, 30, 75, 70, 40);
        this.reg.pilha.cartas[3] = reg.criarCarta("A4", "Hela", 90, 80, 75, 40, 10);
        this.reg.pilha.cartas[4] = reg.criarCarta("A5", "Fenrir", 70, 99, 15, 70, 10);
        this.reg.pilha.cartas[5] = reg.criarCarta("A6", "Heimdallr", 50, 60, 65, 30, 40);
        this.reg.pilha.cartas[6] = reg.criarCarta("A7", "Ullr", 50, 60, 55, 70, 40);
        this.reg.pilha.cartas[7] = reg.criarCarta("A8", "Sif", 40, 40, 55, 50, 40);
       
        this.reg.pilha.cartas[8] = reg.criarCarta("B1", "Zeus", 70, 50, 60, 30, 90);
        this.reg.pilha.cartas[9] = reg.criarCarta("B2", "Hera", 40, 40, 65, 40, 60);
        this.reg.pilha.cartas[10] = reg.criarCarta("B3", "Ares", 50, 80, 25, 60, 65);
        this.reg.pilha.cartas[11] = reg.criarCarta("B4", "Poseidon", 90, 50, 55, 40, 60);
        this.reg.pilha.cartas[12] = reg.criarCarta("B5", "Hades", 70, 40, 55, 30, 30);
        this.reg.pilha.cartas[13] = reg.criarCarta("B6", "Afrodite", 50, 30, 30, 30, 99);
        this.reg.pilha.cartas[14] = reg.criarCarta("B7", "Hermes", 40, 40, 50, 99, 50);
        this.reg.pilha.cartas[15] = reg.criarCarta("B8", "Atena", 60, 50, 99, 40, 70);
        
        this.reg.pilha.cartas[16] = reg.criarCarta("C1", "Isis", 80, 25, 70, 40, 80);
        this.reg.pilha.cartas[17] = reg.criarCarta("C2", "Osiris", 40, 85, 60, 15, 95);
        this.reg.pilha.cartas[18] = reg.criarCarta("C3", "Set", 70, 80, 65, 93, 10);
        this.reg.pilha.cartas[19] = reg.criarCarta("C4", "Néftis", 75, 30, 65, 99, 35);
        this.reg.pilha.cartas[20] = reg.criarCarta("C5", "Rá", 99, 45, 30, 15, 90);
        this.reg.pilha.cartas[21] = reg.criarCarta("C6", "Hórus", 30, 99, 35, 55, 99);
        this.reg.pilha.cartas[22] = reg.criarCarta("C7", "Toth", 78, 15, 99, 40, 55);
        this.reg.pilha.cartas[23] = reg.criarCarta("C8", "Anubis", 88, 50, 45, 80, 85);
        
        this.reg.pilha.cartas[24] = reg.criarCarta("D1", "Kali", 70, 99, 55, 85, 45);
        this.reg.pilha.cartas[25] = reg.criarCarta("D2", "Vamana", 80, 20, 55, 10, 70);
        this.reg.pilha.cartas[26] = reg.criarCarta("D3", "Rama", 20, 65, 80, 99, 85);
        this.reg.pilha.cartas[27] = reg.criarCarta("D4", "Ravana", 30, 88, 45, 65, 10);
        this.reg.pilha.cartas[28] = reg.criarCarta("D5", "Xiva", 99, 80, 15, 77, 78);
        this.reg.pilha.cartas[29] = reg.criarCarta("D6", "Ganesha", 60, 40, 99, 45, 88);
        this.reg.pilha.cartas[30] = reg.criarCarta("D7", "Brama", 99, 35, 55, 60, 99);
        this.reg.pilha.cartas[31] = reg.criarCarta("D8", "Vixnu", 88, 50, 68, 64, 90);
    }
    
    public void setSuperTrunfo(String cod){
        this.reg.codSuperTrunfo = cod;
    }
    
    public void setCatSuperior(char cat){
        this.reg.catSup = cat;
    }
    
    public void setJogador(){
        this.jog = new Jogador();
        this.jog.vez = true;
    }
    
    public void setBot(){
        this.bot = new Bot();
        this.bot.vez = false;
    }
    
    
    
    public void distribuir(){
        int i;
        for(i = 0;i<16;i++){
            this.jog.cartas[i] = this.reg.pilha.cartas[i];
            this.jog.numCartas = 16;
            this.bot.cartas[i] = this.reg.pilha.cartas[i+16];
            this.bot.numCartas = 16;
        }
    }
    
    public void novoJogo(){
        this.setPilha();
        this.setJogador();
        this.setBot();
        this.setCatSuperior('A');
        this.setSuperTrunfo("B1");
        this.reg.pilha.embaralhar();
        this.distribuir();
    }
    
    public void novaRodada(){
        this.reg.jogar(this.jog, this.bot);
        System.out.println("\nPressione Enter para continuar");
        try{
            System.in.read();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    
    public void comecarJogo(){
        while(!this.reg.verificarCartas(this.jog, this.bot)){
            this.novaRodada();
        }
    }
    
}
