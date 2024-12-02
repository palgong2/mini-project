package miniproject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class CosmeticStore {
	private static ProductRepository repository = new ProductRepository();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		// mainmenu 출력
		while (true) {
			System.out.println("\n=== 화장품 가게 프로그램 ===");
			System.out.println("1. 화장품 종류 보기");
			System.out.println("2. 화장품 추천 받기");
			System.out.println("3. 재고 변경 (관리자)");
			System.out.println("4. 종료");
			System.out.print("메뉴를 선택하세요: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			if (choice == 4) {
				System.out.println("프로그램을 종료합니다.");
				break;
			} else if (choice == 1) {
				showProductTypes(scanner);
			} else if (choice == 2) {
				cosmeticsRecommendation(scanner);
			} else if (choice == 3) {
				handleStockManagement(scanner);
			} else {
				System.out.println("잘못된 입력입니다. 다시 시도하세요.");
			}
		}

		scanner.close();
	}

	// 대분류 소분류와 그에따른 제품들이 있기 때문에 처음 Map(대분류)에 의 key와 그에대한 Value Map(소분류),
	// 그리고 Map(소분 류)의key와 그에대한 Value List(제품들)로 Map을 정의
	private static void showProductTypes(Scanner scanner) {
		Map<String, Map<String, List<Product>>> cosmetics = repository.getCosmetics();

		System.out.println("=== 화장품 대분류 ===");
		for (String type : cosmetics.keySet()) {
			System.out.println(type);
		}

		System.out.print("화장품 대분류를 입력하세요: ");
		String type = scanner.nextLine();

		if (cosmetics.containsKey(type)) {
			//대분류에대한 소분류 Map(소분류)을 가져오고 그에대한 제품 리스트 출력
			Map<String, List<Product>> subCategories = cosmetics.get(type);
			System.out.println("\n=== " + type + " 소분류 ===");
			for (String subType : subCategories.keySet()) {
				System.out.println("- " + subType);
			}
			
			//입력한 소분류에 대한 제품리스트 출력
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
					if (productNumber > 0 && productNumber <= products.size()) {
						selectedProduct = products.get(productNumber - 1);
					} else {
						System.out.println("유효한 번호가 아닙니다.");
					}
				} catch (NumberFormatException e) {
					// 이름으로 검색
					selectedProduct = ProductManager.findProductByName(products, input);
				}

				if (selectedProduct != null) {
					System.out.println("\n=== 제품 상세 정보 ===");
					selectedProduct.printDetails();
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

	private static void cosmeticsRecommendation(Scanner scanner) {

		Set<String> skinTypes = new HashSet<>();

		// 모델클래스(Product.java)에서 skinType를 가져와 skinTypes set(key만있으면되고 중복허용하면안되기떄문에 set 사용)
		Map<String, Map<String, List<Product>>> cosmetics = repository.getCosmetics();
		for (Map<String, List<Product>> subCategoryMap : cosmetics.values()) {
			for (List<Product> productList : subCategoryMap.values()) {
				for (Product product : productList) {
					skinTypes.add(product.getSkinType());
				}
			}
		}

		System.out.println("다음 중 자신의 피부 타입을 입력해주세요:");
		for (String type : skinTypes) {
			System.out.println("- " + type);
		}

		System.out.print("피부 타입을 입력하세요: ");
		String userSkinType = scanner.nextLine();

		List<String> recommendedProducts = new ArrayList<>();

		// UI/UX 향상을 위해 카테고리 키를 하나씩 받아 저장하고 카테고리이름도 출력
		for (Map.Entry<String, Map<String, List<Product>>> categoryEntry : cosmetics.entrySet()) {
			String mainCategory = categoryEntry.getKey();
			for (Map.Entry<String, List<Product>> subCategoryEntry : categoryEntry.getValue().entrySet()) {
				String subCategory = subCategoryEntry.getKey();
				for (Product product : subCategoryEntry.getValue()) {
					if (product.getSkinType().equalsIgnoreCase(userSkinType)) {
						recommendedProducts.add(" [" + mainCategory + " - " + subCategory + "] " + product.getName());
					}
				}
			}
		}

		// 추천 제품 출력
		if (recommendedProducts.isEmpty()) {
			System.out.println("해당 피부 타입에 맞는 추천 제품이 없습니다.");
		} else {
			System.out.println("\n === " + userSkinType + " 추천 제품 리스트 ===");
			for (String productInfo : recommendedProducts) {
				System.out.println("- " + productInfo);
			}
		}
	}

	private static void handleStockManagement(Scanner scanner) {
		if (!ProductManager.checkAdminPassword(scanner)) {
			System.out.println("잘못된 비밀번호입니다.");
			return;
		}
		System.out.println("로그인 성공! 재고 관리를 시작합니다.");
		Map<String, Map<String, List<Product>>> cosmetics = repository.getCosmetics();
		ProductManager.manageStock(cosmetics, scanner);
	}
}
