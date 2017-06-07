package com.maven.project.hib.dialect;

// Imports--------------------------------------------------
import org.hibernate.dialect.MySQL5InnoDBDialect;

public class CustomMySQL5InnoDBDialect extends MySQL5InnoDBDialect {

	// Constructors--------------------------------------------------
	public CustomMySQL5InnoDBDialect() {
		super();
	}

}
