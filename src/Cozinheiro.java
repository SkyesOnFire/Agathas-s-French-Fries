import java.util.concurrent.Semaphore;

public class Cozinheiro extends Thread{

    public Integer vontadeDeFritar;
    private static Prateleira prateleira;
    private static Boolean desistiuDeFritar = false;
    public final Integer velocidadeDeEncherPrateleira;
    private final Semaphore semaforo;

    public Cozinheiro(Integer vontadeDeFritar, Prateleira prateleira, Semaphore semaforo) {
        this.vontadeDeFritar = vontadeDeFritar;
        this.prateleira = prateleira;
        this.velocidadeDeEncherPrateleira = prateleira.getQuantidadeLimite()/5;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        while (prateleira.getQuantidadeDeBatatas() != prateleira.getQuantidadeLimite()){
            prateleira.setQuantidadeDeBatatas(prateleira.getQuantidadeDeBatatas()+1);
        }
    }

    public Integer getVelocidadeDeEncherPrateleira() {
        return velocidadeDeEncherPrateleira;
    }
}
