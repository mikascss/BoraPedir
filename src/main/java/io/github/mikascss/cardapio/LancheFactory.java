package io.github.mikascss.cardapio;

public class LancheFactory {

    public static Lanche xBurger(){
        return new Lanche("X-Burger", 16.00);
    }

    public static Lanche xSalada(){
        return new Lanche("X-Salada", 18.00);
    }

    public static Lanche xBacon(){
        return new Lanche("X-Bacon", 22.00);
    }

    public static Lanche criarLanche(String nomeLanche) {
        return switch (nomeLanche) {
            case "X-Burger" -> xBurger();
            case "X-Salada" -> xSalada();
            case "X-Bacon" -> xBacon();
            default -> throw new IllegalArgumentException(
                    "Lanche não encontrado no cardápio."
            );
        };
    }

    public static ItemCardapio adicionarAdicional(String adicional, ItemCardapio item){
        return switch (adicional){
            case "cheddar" -> new ComCheddar(item);
            case "bacon" -> new ComBaconExtra(item);
            default -> throw new IllegalArgumentException("Adicional não encontrado.");
        };
    }

}
