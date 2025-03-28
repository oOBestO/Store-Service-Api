package com.example.Store_Service.Api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Store_Service.Api.model.Menu;
import com.example.Store_Service.Api.service.MenuService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/menus")
@CrossOrigin(origins = "http://localhost:4200")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> getMenuById(@PathVariable Long id) {
        Optional<Menu> menu = menuService.getMenuById(id);

        return menu.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteMenu(@PathVariable Long id) {
    Optional<Menu> menu = menuService.getMenuById(id);
    if (menu.isPresent()) {
        menuService.deleteMenu(id);
        // ✅ ส่ง JSON กลับไปแทนข้อความเปล่า
        Map<String, String> response = new HashMap<>();
        response.put("message", "ลบเมนูสำเร็จ!");
        return ResponseEntity.ok(response);
    } else {
        return ResponseEntity.status(404).body(Map.of("message", "ไม่พบเมนูที่ต้องการลบ"));
    }
    }

     // ✅ บันทึกหรืออัปเดตเมนูตาม ID
     @PostMapping("/save")
     public ResponseEntity<Menu> saveOrUpdateMenu(@RequestBody Menu menu) {
        Menu savedMenu = menuService.saveOrUpdate(menu);
        return ResponseEntity.ok(savedMenu);
     }

     @PostMapping("/getMenusByIds")
     public List<Menu> getMenusByIds(@RequestBody Map<String, List<Long>> request) {
         List<Long> ids = request.get("ids");
         return menuService.getMenusByIds(ids);
     }
    
    @PostMapping
    public ResponseEntity<Menu> saveMenu(@RequestBody Menu menu) {
    Menu savedMenu = menuService.saveMenu(menu); // ✅ กำหนดค่าให้ savedMenu
    return ResponseEntity.ok(savedMenu);
}
}
