package com.example.Store_Service.Api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Store_Service.Api.model.Menu;
import com.example.Store_Service.Api.repository.MenuRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    public void deleteMenu(Long id) {
        menuRepository.deleteById(id);
    }

    public Optional<Menu> getMenuById(Long id) {
        return menuRepository.findById(id);
    }

    public Menu saveMenu(Menu menu) {
        return menuRepository.save(menu); // ✅ ต้อง return `Menu`
    }

    // ✅ ฟังก์ชันบันทึกหรืออัปเดตเมนู
    public Menu saveOrUpdate(Menu menu) {
        if (menu.getId() != null) {
            Optional<Menu> existingMenu = menuRepository.findById(menu.getId());
            if (existingMenu.isPresent()) {
                // ✅ อัปเดตข้อมูลเก่า
                Menu updatedMenu = existingMenu.get();
                updatedMenu.setMenuName(menu.getMenuName());
                updatedMenu.setPrice(menu.getPrice());
                updatedMenu.setCategory(menu.getCategory());
                updatedMenu.setImageUrl(menu.getImageUrl());
                return menuRepository.save(updatedMenu);
            }
        }
        // ✅ ถ้า ID ไม่มี ให้สร้างใหม่
        return menuRepository.save(menu);
    }

     public List<Menu> getMenusByIds(List<Long> ids) {
        return menuRepository.findAllByIdIn(ids); // ✅ ค้นหาข้อมูลเมนูที่ตรงกับ id[]
    }
}