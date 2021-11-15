package br.edu.utfpr.pb.tcc.controller;

import br.edu.utfpr.pb.tcc.dto.PasswordForgotDto;
import br.edu.utfpr.pb.tcc.model.Mail;
import br.edu.utfpr.pb.tcc.model.PasswordResetToken;
import br.edu.utfpr.pb.tcc.model.Usuario;
import br.edu.utfpr.pb.tcc.repository.PasswordTokenRepository;
import br.edu.utfpr.pb.tcc.service.EmailService;
import br.edu.utfpr.pb.tcc.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/forgot-password")
public class PasswordForgotController {

    @Autowired private UsuarioService usuarioService;
    @Autowired private PasswordTokenRepository tokenRepository;
    @Autowired private EmailService emailService;

    @ModelAttribute("forgotPasswordForm")
    public PasswordForgotDto forgotPasswordDto() {
        return new PasswordForgotDto();
    }

    @GetMapping
    public String displayForgotPasswordPage() {
        return "forgot-password";
    }

    @PostMapping
    public String processForgotPasswordForm(@ModelAttribute("forgotPasswordForm") @Valid PasswordForgotDto form,
                                            BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "forgot-password";
        }

        Usuario usuario = usuarioService.findByEmail(form.getEmail());
        if (usuario == null) {
            result.rejectValue("email", null, "O email informado não está registrado.");
            return "forgot-password";
        }

        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUsuario(usuario);
        token.setExpiryDate(30);
        tokenRepository.save(token);

        Mail mail = new Mail();
        mail.setFrom("no-reply@memorynotfound.com");
        mail.setTo(usuario.getEmail());
        mail.setSubject("Requisição para troca de senha");

        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("usuario", usuario);
        model.put("signature", "E-CAKES"); //https://memorynotfound.com
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
        mail.setModel(model);
        emailService.sendEmail(mail);

        return "redirect:/forgot-password?success";
    }
}