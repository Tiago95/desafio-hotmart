package br.com.hotmart.desafiohotmart.vo;

import java.util.List;

/**
 * Classe que contém as ultimas informações do chat.
 * 
 * @author tgs
 *
 */
public class LastChatInfoVO implements BaseResponseVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2792615278227208970L;

	private Long quantidadeMensagensNaoLidas;
	
	private List<ChatMessageVO> ultimasMensagensRecebidas;

	/**
	 * @return the quantidadeMensagensNaoLidas
	 */
	public Long getQuantidadeMensagensNaoLidas() {
		return quantidadeMensagensNaoLidas;
	}

	/**
	 * @param quantidadeMensagensNaoLidas the quantidadeMensagensNaoLidas to set
	 */
	public void setQuantidadeMensagensNaoLidas(Long quantidadeMensagensNaoLidas) {
		this.quantidadeMensagensNaoLidas = quantidadeMensagensNaoLidas;
	}

	/**
	 * @return the ultimasMensagensRecebidas
	 */
	public List<ChatMessageVO> getUltimasMensagensRecebidas() {
		return ultimasMensagensRecebidas;
	}

	/**
	 * @param ultimasMensagensRecebidas the ultimasMensagensRecebidas to set
	 */
	public void setUltimasMensagensRecebidas(List<ChatMessageVO> ultimasMensagensRecebidas) {
		this.ultimasMensagensRecebidas = ultimasMensagensRecebidas;
	}

}
