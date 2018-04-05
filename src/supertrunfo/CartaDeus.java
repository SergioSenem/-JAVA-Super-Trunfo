package supertrunfo;

public class CartaDeus {

    String codigo;
    String nome;
    
    int poder;
    int forca;
    int inteligencia;
    int velocidade;
    int adoradores;
    
    public int getAtributo(char esc){
        int att;
        switch (esc){
            case 'P':
                att = this.poder;
                break;
            case 'F':
                att = this.forca;
                break;
            case 'I':
                att = this.inteligencia;
                break;
            case 'V':
                att = this.velocidade;
                break;
            case 'A':
                att = this.adoradores;
                break;
            default:
                att = -1;
    
        }
        return att;
    }
    
}
