package br.unitins.diceshop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.unitins.diceshop.model.Usuario;

@WebFilter(filterName = "SecurityFilter", urlPatterns = {"/faces/*"})
public class SecurityFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

// 	 	Para desabilitar o filter, descomente as duas proximas linhas e comente o restante		
		chain.doFilter(request, response);
		return;
		
//		HttpServletRequest servletRequest = (HttpServletRequest) request;
//		// imprime o endereco da pagina
//		String endereco = servletRequest.getRequestURI();
//		if (endereco.equals("/diceshop2/faces/login.xhtml") || 
//			endereco.equals("/diceshop2/faces/vendadado.xhtml") || 
//			endereco.equals("/diceshop2/faces/register.xhtml")) {
//			chain.doFilter(request, response);
//			return;
//		}
//		
//		// retorna a sessao corrente (false - para nao criar uma nova sessao)
//		HttpSession session = servletRequest.getSession(false);
//		
//		Usuario usuario = null;
//		if (session != null)
//			usuario = (Usuario) session.getAttribute("usuarioLogado");
//		
//		if (usuario == null) {
//			((HttpServletResponse) response).sendRedirect("/diceshop2/faces/login.xhtml");
//			
//			// nesse local podemos trabalhar as permissoes por pagina
//			
//		} else if (usuario.getTipoUsuario().getId() == 1 || 
//				   usuario.getTipoUsuario().getId() == 2)	{
//			chain.doFilter(request, response);
//			return;
//		} else if (usuario.getTipoUsuario().getId() == 0 || 
//				   usuario.getTipoUsuario().getId() == 3 &&
//				   endereco.equals("/diceshop2/faces/consultadado.xhtml") ||
//				   endereco.equals("/diceshop2/faces/dado.xhtml") ||
//				   endereco.equals("/diceshop2/faces/usuario.xhtml")) {
//			return;
//		} else {
//			chain.doFilter(request, response);
//			return;
//		}
//			
//			// segue o fluxo 
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("SecurityFilter Iniciado.");
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	

}