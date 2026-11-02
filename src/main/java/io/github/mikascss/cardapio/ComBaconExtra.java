package io.github.mikascss.cardapio;

public abstract class ComBaconExtra implements ItemCardapio {
    protected ItemCardapio item;

    public ComBaconExtra(ItemCardapio item){
        this.item = item;
    }

    @Override
    public String getDescricao(){
        return item.getDescricao() + " + bacon extra";
    }

    @Override
    public double getPreco(){
        return item.getPreco() + 6.00;
    }
}
