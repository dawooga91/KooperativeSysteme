package fh.dortmund.logic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Basisentity mit Primarykey
 * 
 * @author breslauer_r
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEntity {

	private long oid;

}
