package com.store.retail.discount;

/**
 * An object that represents the percentage discount provided to a <code>Customer</code>.
 * @author bsmurali
 */
public final class Discount {
    private double percent;

    public Discount(double percent) {
        this.percent = percent;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Discount other = (Discount) obj;
        return true;
    }

}
