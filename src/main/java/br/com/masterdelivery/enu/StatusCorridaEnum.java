/**
 * 
 */
package br.com.masterdelivery.enu;

/**
 * @author vitorlour
 *
 */
public enum StatusCorridaEnum {
	
	PRONTO_PARA_COLETA(0L),
	COLETADO_A_CAMINHO_DA_ENTREGA(1L),
	ENTREGUE(2L);
	
	private Long status;

	StatusCorridaEnum(Long status) {
        this.status = status;
    }

    public Long status() {
        return status;
    }
}
