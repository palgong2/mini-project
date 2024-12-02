package miniproject;

public class Product {
    private String name;
    private String brand;
    private int volume; // 용량 (ml)
    private int quantity; // 재고 개수
    private String description;
    private String skinType;
    private String price;

    public Product(String name, String brand, int volume, int quantity, String description, String skinType, String price) {
        this.name = name;
        this.brand = brand;
        this.volume = volume;
        this.quantity = quantity;
        this.description = description;
        this.skinType = skinType;
        this.price = price;
    }

    public void printDetails() {
        System.out.println("제품명: " + name);
        System.out.println("브랜드: " + brand);
        System.out.println("용량: " + volume + "ml");
        System.out.println("재고 개수: " + quantity);
        System.out.println("설명: " + description);
        System.out.println("피부타입: " + skinType);
        System.out.println("가격: " + price);
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSkinType() {
		return skinType;
	}

	public void setSkinType(String skinType) {
		this.skinType = skinType;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

 
}
