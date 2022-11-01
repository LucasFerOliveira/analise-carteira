package com.desafiointer.DesafioInter.confg.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.desafiointer.DesafioInter.models.Usuario;
import com.desafiointer.DesafioInter.repository.UsuarioRepository;



public class AutenticacaoTokenFilter extends OncePerRequestFilter {
	
	private TokenService tokenService;
	private UsuarioRepository repository;

	public AutenticacaoTokenFilter(TokenService tokenService, UsuarioRepository repository) {
		this.tokenService = tokenService;
		this.repository = repository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recuperarToken(request);
		if (tokenService.isTokenValido(token)) {
			autenticarCliente(token);
		}
		filterChain.doFilter(request, response);
	}

	private void autenticarCliente(String token) {
		Long idUsuario = tokenService.getIdUsuario(token);
		Optional<Usuario> usuarioOpt = repository.findById(idUsuario);

		if(usuarioOpt.isPresent()) {
			Usuario usuario = usuarioOpt.get();
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (isTokenInvalido(token)) {
			return null;
		}
		
		return token.substring(7, token.length());
	}

	private boolean isTokenInvalido(String token) {
		return token == null || token.isEmpty() || !token.startsWith("Bearer ");
	}
}
