/**
 * 
 */
package com.github.biryani.web.model;

import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kadam.sachin
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BiryaniDto {
	
	@Null(message = "can not be null")
	private UUID id;
	@NotEmpty(message = "can not be empty")
	private String biryaniName;
	private String biryaniStyle;
	private long upc;

}
