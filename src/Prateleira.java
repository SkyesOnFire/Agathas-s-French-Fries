public class Prateleira {

    public Integer quantidadeDeBatatas = 0;
    private final Integer quantidadeLimite;

    public Prateleira(Integer quantidadeLimite) {
        this.quantidadeLimite = quantidadeLimite;
    }

    public Integer getQuantidadeDeBatatas() {
        return quantidadeDeBatatas;
    }

    public void setQuantidadeDeBatatas(Integer quantidadeDeBatatas) {
        this.quantidadeDeBatatas = quantidadeDeBatatas;
    }

    public Integer getQuantidadeLimite() {
        return quantidadeLimite;
    }
}
