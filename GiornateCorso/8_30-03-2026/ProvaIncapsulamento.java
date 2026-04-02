public class ProvaIncapsulamento {
    private String nome;
    private int eta;
    private String citta;
    public ProvaIncapsulamento(String nome, int eta, String citta) { 
        this.nome = nome;
        this.eta = eta;
        this.citta = citta;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getEta() {
        return eta;
    }
    public void setEta(int eta) {
        this.eta = eta;
    }
    public String getCitta() {
        return citta;
    }
    public void setCitta(String citta) {
        this.citta = citta;
    }
    private boolean verificaMaggioreeta(){
        return this.eta > 18;
    }
    public static void main(String[] args) {
        ProvaIncapsulamento p = new ProvaIncapsulamento("Luca", 26, "Lecce");
        System.out.println(p.getNome());
        System.out.println(p.getEta());
        System.out.println(p.getCitta());
        p.stampaStatus();//chiamo il metodo sotto che mi manda a schermo lo status
        
    }
    public void stampaStatus(){
        if (verificaMaggioreeta()){
            System.out.println("maggiorenne");
        }
        else{
            System.out.println("minorenne");
        }
    }
}
