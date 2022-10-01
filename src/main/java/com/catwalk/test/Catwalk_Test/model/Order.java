package com.catwalk.test.Catwalk_Test.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;
import java.util.List;
import javax.persistence.ManyToMany;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Order {

	private Integer userId;
	@ManyToMany
	private List<ProductOrder> products;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
}