package miniproject;

import java.util.*;

public class ProductManager {
	private static final String ADMIN_PASSWORD = "1111";

	public static boolean checkAdminPassword(Scanner scanner) {
		System.out.print("관리자 비밀번호를 입력하세요: ");
		String password = scanner.nextLine();
		return ADMIN_PASSWORD.equals(password);
	}

	// 대분류 소분류와 그에따른 제품들이 있기 때문에 처음 Map(대분류)에 의 key와 그에대한 Value Map(소분류),
	// 그리고 Map(소분 류)의key와 그에대한 Value List(제품들)로 Map을 정의
	public static void manageStock(Map<String, Map<String, List<Product>>> cosmetics, Scanner scanner) {
		System.out.println("=== 화장품 대분류 ===");
		for (String type : cosmetics.keySet()) {
			System.out.println(type);
		}

		System.out.print("화장품 대분류를 입력하세요: ");
		String type = scanner.nextLine();

		if (cosmetics.containsKey(type)) {
			// 대분류에대한 소분류 Map(소분류)을 가져오고 그에대한 제품 리스트 출력
			Map<String, List<Product>> subCategories = cosmetics.get(type);
			System.out.println("\n=== " + type + " 소분류 ===");
			for (String subType : subCategories.keySet()) {
				System.out.println("- " + subType);
			}

			// 입력한 소분류에 대한 제품리스트 출력
			System.out.print("소분류를 입력하세요: ");
			String subType = scanner.nextLine();

			if (subCategories.containsKey(subType)) {
				List<Product> products = subCategories.get(subType);

				System.out.println("\n=== " + subType + " 제품 리스트 ===");
				// list 크기만큼 처음부터 끝까지 하나씩 빼와서 출력하는 과정
				for (int i = 0; i < products.size(); i++) {
					System.out.println((i + 1) + ". " + products.get(i).getName());
				}

				System.out.print("제품 번호 또는 제품명을 입력하세요: ");
				String input = scanner.nextLine();

				Product selectedProduct = null;

				// 번호로 검색
				// try-catch block으로 NumberFormatException 발생시 catch block로 제어 이동
				// try-catch를 이용하여 번호입력, 제품명 입력을 구분
				try {
					// input변수에 대한 입력이 문자열이였을 경우 Integer.parseInt() 과정에서 NumberFormatException 발생
					int productNumber = Integer.parseInt(input);
					// 유효성 검사
					if (productNumber > 0 && productNumber <= products.size()) {
						selectedProduct = products.get(productNumber - 1);
					} else {
						System.out.println("유효한 번호가 아닙니다.");
					}
				} catch (NumberFormatException e) {
					// 이름으로 검색
					selectedProduct = findProductByName(products, input);
				}

				if (selectedProduct != null) {
					System.out.print("변경할 재고 수량을 입력하세요 (현재 재고: " + selectedProduct.getQuantity() + "): ");
					int quantityChange = scanner.nextInt();
					scanner.nextLine();

					if (selectedProduct.getQuantity() + quantityChange < 0) {
						System.out.println("재고는 음수가 될 수 없습니다.");
					} else {
						selectedProduct.setQuantity(selectedProduct.getQuantity() + quantityChange);
						System.out.println("재고가 " + quantityChange + "만큼 변경되었습니다.");
						System.out.println("현재 재고는 " + selectedProduct.getQuantity() + "개 입니다.");
					}
				} else {
					System.out.println("입력한 제품을 찾을 수 없습니다.");
				}
			} else {
				System.out.println("입력한 소분류가 없습니다.");
			}
		} else {
			System.out.println("입력한 대분류가 없습니다.");
		}
	}

	public static Product findProductByName(List<Product> products, String productName) {
		for (Product product : products) {
			if (product.getName().equalsIgnoreCase(productName)) {
				return product;
			}
		}
		return null;
	}
}
