package com.company.administracion_negocio;

public class Products {
    private String productCode;
    private String productName;
    private Double MSRP;

    public Products(String productCode, String productName, Double MSRP) {
        this.productCode = productCode;
        this.productName = productName;
        this.MSRP = MSRP;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public Double getMSRP() {
        return MSRP;
    }
}
