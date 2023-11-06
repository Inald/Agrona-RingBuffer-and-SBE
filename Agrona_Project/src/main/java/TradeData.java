public class TradeData {

    private String ticker;

    public TradeData() {
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = new String(ticker);//not ideal
    }

    public void reset(){
        ticker = null;
    }

    @Override
    public String toString() {
        return "TradeData{" +
                "ticker='" + ticker + '\'' +
                '}';
    }
}
