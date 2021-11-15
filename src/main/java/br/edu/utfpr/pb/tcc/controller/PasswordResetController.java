package br.edu.utfpr.pb.tcc.controller;

import br.edu.utfpr.pb.tcc.dto.PasswordResetDto;
import br.edu.utfpr.pb.tcc.model.PasswordResetToken;
import br.edu.utfpr.pb.tcc.model.Usuario;
import br.edu.utfpr.pb.tcc.repository.PasswordTokenRepository;
import br.edu.utfpr.pb.tcc.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/reset-password")
public class PasswordResetController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PasswordTokenRepository tokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @ModelAttribute("passwordResetForm")
    public PasswordResetDto passwordReset() {
        return new PasswordResetDto();
    }

    @GetMapping
    public String displayResetPasswordPage(@RequestParam(required = false) String token, Model model) {
        PasswordResetToken resetToken = tokenRepository.findByToken(token);
        if (resetToken == null) {
            model.addAttribute("error", "Não foi possível encontrar o token para resetar a senha.");
        } else if (resetToken.isExpired()) {
            model.addAttribute("error", "Token expirado, por favor requisite outro reset da senha.");
        } else {
            model.addAttribute("token", resetToken.getToken());
        }

        return "reset-password";
    }

    @PostMapping
    @Transactional
    public String handlePasswordReset(@ModelAttribute("passwordResetForm") @Valid PasswordResetDto form,
                                      BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute(BindingResult.class.getName() + ".passwordResetForm", result);
            redirectAttributes.addFlashAttribute("passwordResetForm", form);
            return "redirect:/reset-password?token=" + form.getToken();
        }

        if(!form.getPassword().equals(form.getConfirmPassword())){
            result.rejectValue("confirmPassword", null, "As senhas informadas não são iguais.");
            return "redirect:/reset-password?token=" + form.getToken();
        }

        PasswordResetToken token = tokenRepository.findByToken(form.getToken());
        Usuario usuario = token.getUsuario();
        String updatedPassword = passwordEncoder.encode(form.getPassword());
        usuarioService.updatePassword(updatedPassword, usuario.getId());
        tokenRepository.delete(token);

        return "redirect:/login?resetSuccess";
    }
}