package supertrunfo;
import java.util.Scanner;

public class SuperTrunfo {

    public static void main(String[] args) {
        
        Regra reg = new Regra();
        reg.numCartAcum = 0;
        reg.pilha[0] = reg.getCarta("A1", "Odin", 60, 70, 55, 30, 70);
        reg.pilha[1] = reg.getCarta("A2", "Thor", 70, 80, 30, 40, 80);
        reg.pilha[2] = reg.getCarta("A3", "Loki", 80, 30, 75, 70, 40);
        reg.pilha[3] = reg.getCarta("A4", "Hela", 90, 80, 75, 40, 10);
        reg.pilha[4] = reg.getCarta("A5", "Fenrir", 70, 99, 15, 70, 10);
        reg.pilha[5] = reg.getCarta("A6", "Heimdallr", 50, 60, 65, 30, 40);
        reg.pilha[6] = reg.getCarta("A7", "Ullr", 50, 60, 55, 70, 40);
        reg.pilha[7] = reg.getCarta("A8", "Sif", 40, 40, 55, 50, 40);
       
        reg.pilha[8] = reg.getCarta("B1", "Zeus", 70, 50, 60, 30, 90);
        reg.pilha[9] = reg.getCarta("B2", "Hera", 40, 40, 65, 40, 60);
        reg.pilha[10] = reg.getCarta("B3", "Ares", 50, 80, 25, 60, 65);
        reg.pilha[11] = reg.getCarta("B4", "Poseidon", 90, 50, 55, 40, 60);
        reg.pilha[12] = reg.getCarta("B5", "Hades", 70, 40, 55, 30, 30);
        reg.pilha[13] = reg.getCarta("B6", "Afrodite", 50, 30, 30, 30, 99);
        reg.pilha[14] = reg.getCarta("B7", "Hermes", 40, 40, 50, 99, 50);
        reg.pilha[15] = reg.getCarta("B8", "Atena", 60, 50, 99, 40, 70);
        
        reg.pilha[16] = reg.getCarta("C1", "Chaac", 60, 70, 35, 30, 80);
        reg.pilha[17] = reg.getCarta("C2", "Kukulcan", 80, 40, 60, 50, 60);
        reg.pilha[18] = reg.getCarta("C3", "Ixbalanque", 50, 50, 40, 70, 50);
        reg.pilha[19] = reg.getCarta("C4", "Mulzencab", 70, 40, 50, 80, 60);
        reg.pilha[20] = reg.getCarta("C5", "Ah Puch", 80, 30, 65, 40, 40);
        reg.pilha[21] = reg.getCarta("C6", "Hun Batz", 50, 60, 35, 60, 50);
        reg.pilha[22] = reg.getCarta("C7", "Cabrakan", 55, 90, 25, 20, 60);
        reg.pilha[23] = reg.getCarta("C8", "Awilix", 50, 30, 60, 70, 60);
        
        reg.pilha[24] = reg.getCarta("D1", "Guan Yu", 50, 70, 60, 50, 50);
        reg.pilha[25] = reg.getCarta("D2", "Chang'e", 70, 20, 60, 60, 40);
        reg.pilha[26] = reg.getCarta("D3", "Hou Yi", 50, 40, 65, 90, 50);
        reg.pilha[27] = reg.getCarta("D4", "He Bo", 90, 50, 50, 50, 70);
        reg.pilha[28] = reg.getCarta("D5", "Zhong Kui", 80, 40, 65, 30, 40);
        reg.pilha[29] = reg.getCarta("D6", "Ao Kuang", 70, 60, 60, 70, 50);
        reg.pilha[30] = reg.getCarta("D7", "Xing Tian", 50, 90, 25, 20, 70);
        reg.pilha[31] = reg.getCarta("D8", "Son Wukong", 60, 60, 50, 60, 60);
        
        Jogador jog1 = new Jogador();
        Bot bot = new Bot();
        
        reg.pilha = reg.embaralhar();
        reg.distribuir(jog1, bot);
        
        jog1.venc = false;
        bot.venc = false;
        
        jog1.vez = true;
        bot.vez = false;
        
        while(!reg.verificarCartas(jog1, bot)){
            reg.jogar(jog1, bot);
        }
        
    }
    
}
