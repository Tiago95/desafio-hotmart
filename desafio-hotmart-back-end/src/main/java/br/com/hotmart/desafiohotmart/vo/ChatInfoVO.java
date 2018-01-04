package br.com.hotmart.desafiohotmart.vo;

import java.util.List;

/**
 * Classe responsável por armazenar as informações do chat.
 * 
 * @author Tiago
 *
 */
public class ChatInfoVO implements BaseResponseVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1630002052655538996L;

	private List<UsuarioVO> usuariosComMensagens;
	
	private List<ChatMessageVO> mensagensAtivas;

	/**
	 * @return the usuariosComMensagens
	 */
	public List<UsuarioVO> getUsuariosComMensagens() {
		return usuariosComMensagens;
	}

	/**
	 * @param usuariosComMensagens the usuariosComMensagens to set
	 */
	public void setUsuariosComMensagens(List<UsuarioVO> usuariosComMensagens) {
		this.usuariosComMensagens = usuariosComMensagens;
	}

	/**
	 * @return the mensagensAtivas
	 */
	public List<ChatMessageVO> getMensagensAtivas() {
		return mensagensAtivas;
	}

	/**
	 * @param mensagensAtivas the mensagensAtivas to set
	 */
	public void setMensagensAtivas(List<ChatMessageVO> mensagensAtivas) {
		this.mensagensAtivas = mensagensAtivas;
	}

}
