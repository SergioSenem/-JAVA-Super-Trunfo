package supertrunfo;

public class Pilha {
    
    CartaDeus[] cartas = new CartaDeus[32];
    
    public static int maxPoder = 99;
    public static int maxForca = 99;
    public static int maxInteligencia = 99;
    public static int maxVelocidade = 99;
    public static int maxAdoradores = 99;
    
    public void embaralhar()
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
                index = (int)Math.round(Math.random()*31);
                for(j=0;j<contNum;j++){
                    if(nums[j] == index){
                        jaExiste = true;
                    }
                }
            }
        
            
            nums[contNum] = index;
            pilhaNova[contNum] = this.cartas[index];
            contNum++;
        }
        
        this.cartas = pilhaNova;
    }
    
    public void setMaximos(int poder,      int forca,       int inteligencia, 
                           int velocidade, int adoradores)
    {
        this.maxPoder = poder;
        this.maxForca = forca;
        this.maxInteligencia = inteligencia;
        this.maxVelocidade = velocidade;
        this.maxAdoradores = adoradores;
    }
    
}
