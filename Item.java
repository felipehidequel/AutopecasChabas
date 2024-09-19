class Item {
    private int quantidadeItem;
    private float valorTotal;

    public Item(int quantidadeItem, float valorTotal){
        this.quantidadeItem = quantidadeItem;
        this.valorTotal = valorTotal;
    }

    public int getQuantidadeItem(){
        return quantidadeItem;
    }

    public float getValorTotal(){
        return valorTotal;
    }
}