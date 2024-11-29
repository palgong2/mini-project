package jeonmini;

public class JeonProduct {
    private String name;
    private String brand;
    private int volume; // 용량 (ml)
    private int quantity; // 재고 개수
    private String description;
    
    //hi from mini-jeon

    public JeonProduct(String name, String brand, int volume, int quantity, String description) {
        this.name = name;
        this.brand = brand;
        this.volume = volume;
        this.quantity = quantity;
        this.description = description;
    }

    public void printDetails() {
        System.out.println("제품명: " + name);
        System.out.println("브랜드: " + brand);
        System.out.println("용량: " + volume + "ml");
        System.out.println("재고 개수: " + quantity);
        System.out.println("설명: " + description);
    }

    public String getName() {
        return name;
    }
}
