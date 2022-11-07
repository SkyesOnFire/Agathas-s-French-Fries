import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        Scanner console = new Scanner(System.in);
        Semaphore semaforo = new Semaphore(1);
        Semaphore cozinhando = new Semaphore(1);
        Semaphore pratoServido = new Semaphore(1);

        System.out.print("Entre com o número máximo de batatas que cabem na prateleira: ");
        Prateleira prateleira = new Prateleira(console.nextInt());

        System.out.print("Entre com o número de vezes que o cozinheiro consegue encher a prateleira até desistir: ");
        Cozinheiro cozinheiro = new Cozinheiro(console.nextInt(), prateleira, semaforo);

        Aluno alunoTeilor = new Aluno("Teilor", semaforo, prateleira, cozinheiro);
        Aluno alunoPedro = new Aluno("Pedro", semaforo, prateleira, cozinheiro);
        Aluno alunaAgatha = new Aluno("Agatha", semaforo, prateleira, cozinheiro);

        System.out.println("Restaurante foi aberto!");
        alunoTeilor.start();
        alunoPedro.start();
        alunaAgatha.start();
        cozinheiro.start();

        if (random.nextBoolean()){
            cozinheiro.join();
            alunoTeilor.join();
            alunaAgatha.join();
            alunoPedro.join();
        }else{
            alunoTeilor.join();
            alunaAgatha.join();
            alunoPedro.join();
            cozinheiro.join();
        }
    }
}