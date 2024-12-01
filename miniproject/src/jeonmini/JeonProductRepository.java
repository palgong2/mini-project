package jeonmini;

import java.util.*;

public class JeonProductRepository {
    private Map<String, Map<String, List<JeonProduct>>> cosmetics;

    public JeonProductRepository() {
        cosmetics = new HashMap<>();
        initializeProducts();
    }

    private void initializeProducts() {
        cosmetics.put("스킨케어", new HashMap<>());
        cosmetics.put("베이스", new HashMap<>());
        cosmetics.put("클렌징", new HashMap<>());

        cosmetics.get("스킨케어").put("미스트", Arrays.asList(
                new JeonProduct("수분미스트", "브랜드A", 100, 10, "건조한 피부를 촉촉하게.", "건성"),
                new JeonProduct("진정미스트", "브랜드B", 120, 8, "민감한 피부를 진정시켜줍니다.", "민감성")
        ));
        cosmetics.get("스킨케어").put("토너", Arrays.asList(
                new JeonProduct("진정토너", "브랜드C", 300, 20, "민감한 피부에 적합.", "지성"),
                new JeonProduct("미백토너", "브랜드D", 250, 15, "피부 톤을 밝게.", "모든 피부")
        ));
        cosmetics.get("스킨케어").put("로션", Arrays.asList(
                new JeonProduct("수분로션", "브랜드E", 200, 12, "건조한 피부에 수분 공급.", "건성"),
                new JeonProduct("영양로션", "브랜드F", 250, 8, "피부 영양 공급.", "모든 피부")
        ));

        cosmetics.get("클렌징").put("클렌징폼", Arrays.asList(
                new JeonProduct("딥클렌징폼", "브랜드G", 150, 10, "모공 속까지 깨끗하게.", "모든 피부")
        ));
    }

    public Map<String, Map<String, List<JeonProduct>>> getCosmetics() {
        return cosmetics;
    }
}
