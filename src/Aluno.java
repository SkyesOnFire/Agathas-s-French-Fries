import java.util.Random;
import java.util.concurrent.Semaphore;

public class Aluno extends Thread{
    private final String nome;
    private final Semaphore semaforo;
    private final Prateleira prateleira;
    private final Cozinheiro cozinheiro;
    Random random = new Random();

    public Aluno(String nome, Semaphore semaforo, Prateleira prateleira, Cozinheiro cozinheiro) {
        super(nome);
        this.semaforo = semaforo;
        this.nome = nome;
        this.prateleira = prateleira;
        this.cozinheiro = cozinheiro;
    }

    @Override
    public void run() {
        pegarBatata();
    }

    private void pegarBatata() {
        while (true) {
            try {
                semaforo.acquire();
                if (prateleira.getQuantidadeDeBatatas() > 0 && Boolean.FALSE.equals(cozinheiro.cozinhando)){
                    prateleira.quantidadeDeBatatas -= 1;
                    System.out.println("\u001B[35m" + nome + " pega uma batata frita. | Batatas restantes = " + prateleira.getQuantidadeDeBatatas());
                    semaforo.release();
                    sleep(random.nextInt(5001));
                }else if (Boolean.FALSE.equals(cozinheiro.cozinhando) && prateleira.quantidadeDeBatatas == 0){
                    System.out.println("\u001B[31m" + "Aluno(a) " + nome + " tenta comer batata mas não encontra nada. Então grita: COZINHEIRO!!!");
                    if (!cozinheiro.isAlive()){
                        cozinheiro.run();
                    }
                    sleep(cozinheiro.segundos+500);
                    semaforo.release();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (prateleira.getQuantidadeDeBatatas() == 0 && cozinheiro.vontadeDeFritar == 0){
                System.out.println("\u001B[31m" + "Aluno(a) " + nome + " berra em completo desespero: 'MEU DEUS QUEM DEIXOU O COZINHEIRO FUGIR ????' e logo apos morre de fome...");
                break;
            }
        }
    }
}
