package com.vp.tw.entity.t100;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(schema = "dsdata")
public class Sfaa_t implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	String sfaadocno;
	 //String sfaastus;
	 
}
