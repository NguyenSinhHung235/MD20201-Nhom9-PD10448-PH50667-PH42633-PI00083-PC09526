package com.khoahochay.controllers;

import com.khoahochay.dto.UserForm;
import com.khoahochay.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String accountManagement(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("userForm", new UserForm());
        model.addAttribute("isEdit", false); // Thêm dòng này
        return "account";
    }

    @PostMapping
    public String createUser(@ModelAttribute UserForm userForm, RedirectAttributes redirectAttributes) {
        try {
            userService.createUser(userForm);
            redirectAttributes.addFlashAttribute("successMessage", "Tạo tài khoản thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        return "redirect:/account";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        try {
            var user = userService.getUserById(id);
            if (user.isPresent()) {
                UserForm userForm = new UserForm();
                userForm.setEmail(user.get().getEmail());
                userForm.setRole(user.get().getRole());
                userForm.setFullName(user.get().getFullName());
                userForm.setPhone(user.get().getPhone());

                model.addAttribute("userForm", userForm);
                model.addAttribute("userId", id);
                model.addAttribute("isEdit", true);
            } else {
                model.addAttribute("errorMessage", "Không tìm thấy người dùng!");
                model.addAttribute("isEdit", false);
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi: " + e.getMessage());
            model.addAttribute("isEdit", false);
        }
        return "account";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute UserForm userForm, RedirectAttributes redirectAttributes) {
        try {
            userService.updateUser(id, userForm);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật tài khoản thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi: " + e.getMessage());
        }
        return "redirect:/account";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUser(id);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa tài khoản thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi xóa tài khoản: " + e.getMessage());
        }
        return "redirect:/account";
    }

    @GetMapping("/toggle-status/{id}")
    public String toggleUserStatus(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            userService.toggleUserStatus(id);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật trạng thái thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi cập nhật trạng thái: " + e.getMessage());
        }
        return "redirect:/account";
    }
}