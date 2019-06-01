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
	ENTREGUE(2L),
	PROBLEMA_COLETA(3L),
	PROBLEMA_A_CAMINHO_DA_ENTREGA(4L),
	PROBLEMA_NA_ENTREGA(5L);
	
	private Long status;

	StatusCorridaEnum(Long status) {
        this.status = status;
    }

    public Long status() {
        return status;
    }
}
