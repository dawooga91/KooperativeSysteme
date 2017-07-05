package fh.dortmund.logic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {
	private String name;
	private int countYes;
	private int countNo;
	private boolean open;
}
