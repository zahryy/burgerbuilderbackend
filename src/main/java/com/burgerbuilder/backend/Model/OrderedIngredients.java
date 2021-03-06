package com.burgerbuilder.backend.Model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name="order_ingredient")
public class OrderedIngredients  {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Type(type="uuid-char")
    private Order order;


    @ManyToOne(fetch = FetchType.LAZY)
    private Ingredient ingredient;

    private Integer quantity;

    public OrderedIngredients(Ingredient ingredient,Order order,Integer quantity ){
        this.ingredient=ingredient;
        this.order=order;
        this.quantity=quantity;
    }
}
