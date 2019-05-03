/**
 * 
 */
package br.com.masterdelivery.dto;

import org.springframework.beans.factory.annotation.Value;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author vitorlour
 *
 */
@Getter
@Setter
@Builder
public class PageDTO {
	
	@Value("0")
	private Integer page;
	
	@Value("24")
	private Integer linesPerPage;
	
	@Value("id")
	private String orderBy;
	
	@Value("DESC")
	private String direction;
}
