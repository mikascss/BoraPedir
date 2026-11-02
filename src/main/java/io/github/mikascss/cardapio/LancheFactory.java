package io.github.mikascss.cardapio;

public class LancheFactory {

    public static ItemCardapio xBurger(){
        return new Lanche("X-Burger", 16.00);
    }

    public static ItemCardapio xSalada(){
        return new Lanche("X-Salada", 18.00);
    }

    public static ItemCardapio xBacon(){
        return new Lanche("X-Bacon", 22.00);
    }

    public static ItemCardapio criarLanche(String nomeLanche) {
        return switch (nomeLanche) {
            case "X-Burger" -> xBurger();
            case "X-Salada" -> xSalada();
            case "X-Bacon" -> xBacon();
            default -> throw new IllegalArgumentException(
                    "Lanche não encontrado no cardápio."
            );
        };
    }

}
