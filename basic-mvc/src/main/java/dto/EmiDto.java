package dto;

public class EmiDto {
    private double roi;
    private int tenure;
    private double loanAmount;

    public double getRoi() {
        return roi;
    }

    public EmiDto setRoi(double roi) {
        this.roi = roi;
        return this;
    }

    public int getTenure() {
        return tenure;
    }

    public EmiDto setTenure(int tenure) {
        this.tenure = tenure;
        return this;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public EmiDto setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
        return this;
    }

    @Override
    public String toString() {
        return "EmiDto{" +
                "roi=" + roi +
                ", tenure=" + tenure +
                ", loanAmount=" + loanAmount +
                '}';
    }
}
