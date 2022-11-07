import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Cozinheiro extends Thread{

    public Integer vontadeDeFritar;
    private static Prateleira prateleira;
    private final Semaphore semaforo;
    public Boolean cozinhando;
    public Long segundos;

    public Cozinheiro(Integer vontadeDeFritar, Prateleira prateleira, Semaphore semaforo) {
        this.vontadeDeFritar = vontadeDeFritar;
        this.prateleira = prateleira;
        this.semaforo = semaforo;
        this.segundos = (long) (5000 / prateleira.getQuantidadeLimite());
    }

    @Override
    public void run() {
        try {
            long total = (long) prateleira.getQuantidadeLimite();
            long startTime = System.currentTimeMillis();
            if (!Objects.equals(prateleira.getQuantidadeDeBatatas(), prateleira.getQuantidadeLimite())) {
                cozinhando = true;
                System.out.println("\u001B[32m" + "Cozinheiro liga a fritadeira...");
                while (!Objects.equals(prateleira.getQuantidadeDeBatatas(), prateleira.getQuantidadeLimite()) || prateleira.getQuantidadeDeBatatas() == 0) {
                    prateleira.setQuantidadeDeBatatas(prateleira.getQuantidadeDeBatatas() + 1);
                    printProgress(startTime, total, prateleira.getQuantidadeDeBatatas());
                    sleep(segundos);
                }
                System.out.println();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (Objects.equals(prateleira.getQuantidadeDeBatatas(), prateleira.getQuantidadeLimite())) {
            System.out.println("\u001B[34m" + "Prateleira cheia => Batatas restantes = " + prateleira.getQuantidadeDeBatatas());
            semaforo.release();
            vontadeDeFritar -= 1;
            if (vontadeDeFritar == 0) {
                System.out.println("\u001B[31m" + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>> COZINHEIRO DESISTIU DE FRITAR BATATAS E SAIU CORRENDO... <<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            }
            cozinhando = false;
        }
    }

    private static void printProgress(long startTime, long total, long current) {
        long eta = current == 0 ? 0 :
                (total - current) * (System.currentTimeMillis() - startTime) / current;

        String etaHms = current == 0 ? "N/A" :
                String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(eta),
                        TimeUnit.MILLISECONDS.toMinutes(eta) % TimeUnit.HOURS.toMinutes(1),
                        TimeUnit.MILLISECONDS.toSeconds(eta) % TimeUnit.MINUTES.toSeconds(1));

        StringBuilder string = new StringBuilder(140);
        int percent = (int) (current * 100 / total);
        string
                .append('\r')
                .append(String.join("", Collections.nCopies(percent == 0 ? 2 : 2 - (int) (Math.log10(percent)), " ")))
                .append(String.format(" %d%% [", percent))
                .append(String.join("", Collections.nCopies(percent, "=")))
                .append('>')
                .append(String.join("", Collections.nCopies(100 - percent, " ")))
                .append(']')
                .append(String.join("", Collections.nCopies((int) (Math.log10(total)) - (int) (Math.log10(current)), " ")))
                .append(String.format(" %d/%d, ETA: %s", current, total, etaHms));

        System.out.print("\u001B[33m" + string);
    }
}
